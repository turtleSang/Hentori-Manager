package com.ThankSens.Hentori.Service;

import com.ThankSens.Hentori.Dto.LogBookDto;
import com.ThankSens.Hentori.Entity.ClientEntity;
import com.ThankSens.Hentori.Entity.LogBookEntity;
import com.ThankSens.Hentori.Payload.Request.Client.LogBookRequest;
import com.ThankSens.Hentori.Repository.ClientRepository;
import com.ThankSens.Hentori.Repository.LogBookRepository;
import com.ThankSens.Hentori.Service.Interface.LogBookServiceImp;
import jakarta.annotation.Nullable;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;

@Service
public class LogBookService implements LogBookServiceImp {
    private LogBookRepository logBookRepository;
    private ModelMapper modelMapper;
    private ClientRepository clientRepository;
    private ConvertToDate convertToDate;

    private final int pageSizeDefault = 3;


    @Autowired
    public LogBookService(LogBookRepository logBookRepository, ModelMapper modelMapper, ClientRepository clientRepository, ConvertToDate convertToDate) {
        this.logBookRepository = logBookRepository;
        this.modelMapper = modelMapper;
        this.clientRepository = clientRepository;
        this.convertToDate = convertToDate;
    }

    @Override
    public boolean createLogBook(LogBookRequest logBookRequest) {
        LogBookEntity logBookEntity = new LogBookEntity();
        try {
            Optional<ClientEntity> clientEntity = clientRepository.findById(logBookRequest.getClientId());
            if (!clientEntity.isPresent()) {
                return false;
            }
            Date visitDate = convertToDate.convertDateTime(logBookRequest.getVisitDate());
            logBookEntity.setApproach(logBookRequest.getApproach());
            logBookEntity.setDemand(logBookRequest.getDemand());
            logBookEntity.setNote(logBookRequest.getNote());
            logBookEntity.setVisitDate(visitDate);
            logBookEntity.setClientEntity(clientEntity.get());
            logBookRepository.save(logBookEntity);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }


    }

    @Override
    public List<LogBookDto> getAllLogBookByPhoneNumber(String phoneNumber, int pageNumber) {
        ClientEntity clientEntity = clientRepository.findByPhoneNumber(phoneNumber);
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSizeDefault, Sort.by("visitDate").descending());
        Page<LogBookEntity> logBookEntityPage= logBookRepository.findAllByClientEntity(clientEntity, pageRequest);
        if (logBookEntityPage.isEmpty()){
            return null;
        }
        List<LogBookDto> logBookDtoList = new ArrayList<>();
        for (LogBookEntity logBookEntity : logBookEntityPage
             ) {
            LogBookDto logBookDto = transferLogBookEntityToDto(logBookEntity);
            logBookDtoList.add(logBookDto);
        }
        return logBookDtoList;
    }

    @Override
    public int getAllPageLogBook(String phoneNumber) {
        ClientEntity clientEntity = clientRepository.findByPhoneNumber(phoneNumber);
        PageRequest pageRequest = PageRequest.of(0, pageSizeDefault);
        Page<LogBookEntity> logBookEntityPage= logBookRepository.findAllByClientEntity(clientEntity, pageRequest);
        return logBookEntityPage.getTotalPages();
    }

    private LogBookDto transferLogBookEntityToDto(LogBookEntity logBookEntities){
        try {
            LogBookDto logBookDto = modelMapper.map(logBookEntities, LogBookDto.class);
            logBookDto.setClient_name(logBookEntities.getClientEntity().getUsername());
            logBookDto.setClient_phone(logBookEntities.getClientEntity().getPhoneNumber());
            return logBookDto;

        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }
}
