package com.ThankSens.Hentori.Service;

import com.ThankSens.Hentori.Configs.ConstanceConfigs;
import com.ThankSens.Hentori.Configs.MailConfigs;
import com.ThankSens.Hentori.Entity.AdminEntity;
import com.ThankSens.Hentori.Entity.WaitingAdminEntity;
import com.ThankSens.Hentori.Payload.Request.AdminRequest;
import com.ThankSens.Hentori.Repository.AdminRepository;
import com.ThankSens.Hentori.Repository.WaitingAdminRepository;
import com.ThankSens.Hentori.Security.Units.JjwtHelper;
import com.ThankSens.Hentori.Service.Interface.AdminServiceImp;
import com.ThankSens.Hentori.Service.Interface.EmailServiceImp;
import com.google.gson.Gson;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.Random;

@Service
public class AdminService implements AdminServiceImp {
    private PasswordEncoder passwordEncoder;
    private AdminRepository adminRepository;
    private JjwtHelper jjwtHelper;
    private Gson gson = new Gson();
    private WaitingAdminRepository waitingAdminRepository;
    private EmailServiceImp emailServiceImp;
    private ModelMapper modelMapper;


    @Autowired
    public AdminService(PasswordEncoder passwordEncoder, AdminRepository adminRepository, JjwtHelper jjwtHelper, WaitingAdminRepository waitingAdminRepository, EmailServiceImp emailServiceImp, ModelMapper modelMapper) {
        this.passwordEncoder = passwordEncoder;
        this.adminRepository = adminRepository;
        this.jjwtHelper = jjwtHelper;
        this.waitingAdminRepository = waitingAdminRepository;
        this.emailServiceImp = emailServiceImp;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean createAdmin(AdminRequest adminRequest) {
        try {
            AdminEntity adminEntity = adminRepository.findByUsername(adminRequest.getUsername());
            Optional<WaitingAdminEntity> waitingAdminEntityOptional = waitingAdminRepository.findById(adminRequest.getUsername());
            if (waitingAdminEntityOptional.isPresent() || adminEntity != null) {
                return false;
            }

            LocalDateTime now = LocalDateTime.now().plusMinutes(10);
            Date timeDelete = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
            String password = passwordEncoder.encode(adminRequest.getPassword());
            int otp = (int) Math.round(Math.random() * 1000);
            WaitingAdminEntity waitingAdminEntity = new WaitingAdminEntity();
            waitingAdminEntity.setUsername(adminRequest.getUsername());
            waitingAdminEntity.setPassword(password);
            waitingAdminEntity.setTimeDelete(timeDelete);
            waitingAdminEntity.setOtp(otp);
            waitingAdminRepository.save(waitingAdminEntity);
            emailServiceImp.sendSimpleMessage("Hentorij@gmail.com", "Xác nhận tài khoản " + waitingAdminEntity.getUsername(), "Mã xác nhận: " + otp);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    @Transactional
    public boolean confirmAdmin(AdminRequest adminRequest) {
        Optional<WaitingAdminEntity> waitingAdminEntityOptional = waitingAdminRepository.findById(adminRequest.getUsername());
        if ((!waitingAdminEntityOptional.isPresent()) || adminRequest.getOtp() != waitingAdminEntityOptional.get().getOtp()) {
            return false;
        }
        boolean checkPassword = passwordEncoder.matches(adminRequest.getPassword(), waitingAdminEntityOptional.get().getPassword());
        if (!checkPassword) {
            return false;
        }
        try {
            AdminEntity adminEntity = modelMapper.map(waitingAdminEntityOptional.get(), AdminEntity.class);
            waitingAdminRepository.delete(waitingAdminEntityOptional.get());
            adminRepository.save(adminEntity);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }

    }

    @Override
    public String signIn(AdminRequest adminRequest) {

        AdminEntity adminEntity = adminRepository.findByUsername(adminRequest.getUsername());
        if (adminEntity == null) {
            return null;
        }
        boolean checkLogin = passwordEncoder.matches(adminRequest.getPassword(), adminEntity.getPassword());
        if (!checkLogin) {
            return null;
        }
        String jsonAdminEntity = gson.toJson(adminEntity);
        String jwt = jjwtHelper.createJwt(jsonAdminEntity);
        return jwt;
    }

    @Override
    public boolean checkConfirm(AdminRequest adminRequest) {
        Optional<WaitingAdminEntity> waitingAdminEntityOptional = waitingAdminRepository.findById(adminRequest.getUsername());
        return waitingAdminEntityOptional.isPresent();
    }

    @Override
    public boolean checkAdminName(String name) {
        Optional<WaitingAdminEntity> waitingAdminEntityOptional = waitingAdminRepository.findById(name);
        AdminEntity adminEntity = adminRepository.findByUsername(name);
        if (adminEntity != null || waitingAdminEntityOptional.isPresent()) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean checkUpdatePassword(String name) {
        if (name == null){
            return false;
        }
        AdminEntity adminEntity = adminRepository.findByUsername(name);
        Optional<WaitingAdminEntity> waitingAdminEntityOptional = waitingAdminRepository.findById(name);
        if (adminEntity == null || waitingAdminEntityOptional.isPresent()) {
            return false;
        }
        try {
            int otp = (int) Math.round(Math.random() * 1000);
            LocalDateTime now = LocalDateTime.now().plusMinutes(10);
            Date timeDelete = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
            WaitingAdminEntity waitingAdminEntity = new WaitingAdminEntity();
            waitingAdminEntity.setOtp(otp);
            waitingAdminEntity.setUsername(name);
            waitingAdminEntity.setTimeDelete(timeDelete);
            waitingAdminRepository.save(waitingAdminEntity);
            emailServiceImp.sendSimpleMessage(ConstanceConfigs.EMAIL_DESTINATION,"Xác nhận đổi mật khẩu tài khoản " + name, "Mã xác nhận: " + otp);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean updatePassword(AdminRequest adminRequest) {
        AdminEntity adminEntity = adminRepository.findByUsername(adminRequest.getUsername());
        Optional<WaitingAdminEntity> waitingAdminEntityOptional = waitingAdminRepository.findById(adminRequest.getUsername());
        if (adminEntity == null || waitingAdminEntityOptional.isEmpty() || waitingAdminEntityOptional.get().getOtp() != adminRequest.getOtp()){
            return false;
        }
        String password = passwordEncoder.encode(adminRequest.getPassword());
        adminEntity.setPassword(password);
        adminRepository.save(adminEntity);
        waitingAdminRepository.delete(waitingAdminEntityOptional.get());
        return true;
    }


}
