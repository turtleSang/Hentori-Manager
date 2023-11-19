package com.ThankSens.Hentori.Service;

import com.ThankSens.Hentori.Entity.AdminEntity;
import com.ThankSens.Hentori.Payload.Request.AdminRequest;
import com.ThankSens.Hentori.Repository.AdminRepository;
import com.ThankSens.Hentori.Service.Interface.AdminServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService implements AdminServiceImp {
    private PasswordEncoder passwordEncoder;
    private AdminRepository adminRepository;

    @Autowired
    public AdminService(PasswordEncoder passwordEncoder, AdminRepository adminRepository) {
        this.passwordEncoder = passwordEncoder;
        this.adminRepository = adminRepository;
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
}
