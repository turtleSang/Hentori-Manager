package com.ThankSens.Hentori.Service;

import com.ThankSens.Hentori.Entity.WaitingAdminEntity;
import com.ThankSens.Hentori.Repository.WaitingAdminRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class ScheduleService {
    private WaitingAdminRepository waitingAdminRepository;

    public ScheduleService(WaitingAdminRepository waitingAdminRepository) {
        this.waitingAdminRepository = waitingAdminRepository;
    }

    @Scheduled(fixedRate = 60000)
    public void deleteWaiting(){
        Date now = new Date();
        List<WaitingAdminEntity> waitingAdminEntityList = waitingAdminRepository.findAllByTimeDeleteBefore(now);
        if (waitingAdminEntityList != null){
            for (WaitingAdminEntity waitingAdminEntity: waitingAdminEntityList
                 ) {
                System.out.println("Delete waiting waiting users " + waitingAdminEntity.getUsername());
                waitingAdminRepository.delete(waitingAdminEntity);
            }
        }
    }
}
