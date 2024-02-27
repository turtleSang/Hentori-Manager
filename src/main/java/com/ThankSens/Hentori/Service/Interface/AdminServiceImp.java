package com.ThankSens.Hentori.Service.Interface;

import com.ThankSens.Hentori.Payload.Request.AdminRequest;

public interface AdminServiceImp {
    boolean createAdmin(AdminRequest adminRequest);
    boolean confirmAdmin(AdminRequest adminRequest);
    String signIn(AdminRequest adminRequest);
    boolean checkConfirm(AdminRequest adminRequest);
    boolean checkAdminName(String name);
    boolean checkUpdatePassword(String name);
    boolean updatePassword(AdminRequest adminRequest);
}
