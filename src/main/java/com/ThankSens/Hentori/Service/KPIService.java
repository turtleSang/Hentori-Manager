package com.ThankSens.Hentori.Service;

import com.ThankSens.Hentori.Entity.KPIEntity;
import com.ThankSens.Hentori.Entity.Keys.KPIId;
import com.ThankSens.Hentori.Repository.KPIRepository;
import com.ThankSens.Hentori.Service.Interface.KPIServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class KPIService implements KPIServiceImp {
    private KPIRepository kpiRepository;

    @Autowired
    public KPIService(KPIRepository kpiRepository) {
        this.kpiRepository = kpiRepository;
    }

    @Override
    public KPIEntity createKPI(int year, int month, int target) {
        try {
            KPIEntity kpiEntity = new KPIEntity();
            KPIId id = new KPIId(month, year);
            kpiEntity.setId(id);
            kpiEntity.setTarget(target);
            kpiEntity.setComplete(0);

            return kpiRepository.save(kpiEntity);
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    @Override
    public KPIEntity getKPI(int year, int month) {
        KPIId id = new KPIId(month, year);
        Optional<KPIEntity> kpiEntityOptional = kpiRepository.findById(id);
        if (kpiEntityOptional.isPresent()){
            return kpiEntityOptional.get();
        }
        return null;
    }

    @Override
    public boolean updateKPIWhenAddOrder(KPIId id, int money) {
        try {
            KPIEntity kpiEntity = getKPI(id.getMonth(), id.getYear());
            int newComplete = money + kpiEntity.getComplete();
            kpiEntity.setComplete(newComplete);
            kpiRepository.save(kpiEntity);
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }


}
