package com.ThankSens.Hentori.Service.Interface;

import com.ThankSens.Hentori.Dto.LogBookDto;
import com.ThankSens.Hentori.Payload.Request.Client.LogBookRequest;

import java.util.List;
import java.util.UUID;


public interface LogBookServiceImp {
    boolean createLogBook(LogBookRequest logBookRequest);
    List<LogBookDto> getAllLogBookByPhoneNumber(String phoneNumber, int pageNumber);
    int getAllPageLogBook(String phoneNumber);
    boolean deleteLogBook(int idLogBook);
}
