package com.ThankSens.Hentori.Service;

import com.ThankSens.Hentori.Entity.AdminEntity;
import com.ThankSens.Hentori.Payload.Request.AdminRequest;
import com.ThankSens.Hentori.Repository.AdminRepository;
import com.ThankSens.Hentori.Security.Units.JjwtHelper;
import com.ThankSens.Hentori.Service.Interface.AdminServiceImp;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService implements AdminServiceImp {
    private PasswordEncoder passwordEncoder;
    private AdminRepository adminRepository;
    private JjwtHelper jjwtHelper;
    private Gson gson = new Gson();

    @Autowired
    public AdminService(PasswordEncoder passwordEncoder, AdminRepository adminRepository, JjwtHelper jjwtHelper, Gson gson) {
        this.passwordEncoder = passwordEncoder;
        this.adminRepository = adminRepository;
        this.jjwtHelper = jjwtHelper;
    }

    @Override
    public boolean createAdmin(AdminRequest adminRequest) {
        try {
            String encoderPassword = passwordEncoder.encode(adminRequest.getPassword());
            AdminEntity adminEntity = new AdminEntity();
            adminEntity.setPassword(encoderPassword);
            adminEntity.setUsername(adminRequest.getUsername());
            adminRepository.save(adminEntity);
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    @Override
    public String signIn(AdminRequest adminRequest) {
        AdminEntity adminEntity = adminRepository.findByUsername(adminRequest.getUsername());
        if(adminEntity == null){
            return null;
        }
        boolean checkLogin = passwordEncoder.matches(adminRequest.getPassword(), adminEntity.getPassword());
        if (!checkLogin){
            return null;
        }
        String jsonAdminEntity = gson.toJson(adminEntity);
        String jwt = jjwtHelper.createJwt(jsonAdminEntity);
        return jwt;
    }
}
