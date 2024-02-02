package com.ThankSens.Hentori.Service;

import com.ThankSens.Hentori.Entity.KPIEntity;
import com.ThankSens.Hentori.Entity.Keys.KPIId;
import com.ThankSens.Hentori.Payload.Request.KPIRequest;
import com.ThankSens.Hentori.Repository.KPIRepository;
import com.ThankSens.Hentori.Service.Interface.KPIServiceImp;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class KPIService implements KPIServiceImp {
    private KPIRepository kpiRepository;

    public KPIService(KPIRepository kpiRepository) {
        this.kpiRepository = kpiRepository;
    }

    @Override
    public boolean createKPI(KPIRequest kpiRequest) {
        KPIId id = new KPIId(kpiRequest.getMonth(),kpiRequest.getYear());
        if (id == null ){
            return false;
        }
        Optional<KPIEntity> kpiEntityOptional = kpiRepository.findById(id);
        KPIEntity kpiEntity;
        if (kpiEntityOptional.isPresent()){
            kpiEntity = kpiEntityOptional.get();
        }else {
            kpiEntity = new KPIEntity();
            kpiEntity.setId(id);
        }
        kpiEntity.setTarget(kpiRequest.getTarget());
        kpiRepository.save(kpiEntity);
        return true;
    }
}
