package com.ThankSens.Hentori.Service.Interface;

import com.ThankSens.Hentori.Dto.ReportDto;

public interface ReportServiceImp {
    ReportDto reportOrderByDate(String date);
    ReportDto reportOrderByMonth(int month, int year);
    ReportDto reportOrderByQuarter(int quarter, int year);
}
