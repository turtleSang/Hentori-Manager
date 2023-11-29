package com.ThankSens.Hentori.Service.Interface;


import com.ThankSens.Hentori.Dto.KPIDto;
import com.ThankSens.Hentori.Entity.KPIEntity;
import com.ThankSens.Hentori.Entity.Keys.KPIId;

public interface KPIServiceImp {
    KPIEntity createKPI(int year, int month, int target);
    boolean updateKPIWhenAddOrder(KPIId id, int money);
    KPIEntity getKPI(int year, int month);
}
