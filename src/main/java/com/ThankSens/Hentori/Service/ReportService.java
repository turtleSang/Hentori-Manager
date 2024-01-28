package com.ThankSens.Hentori.Service;

import com.ThankSens.Hentori.Dto.ReportDto;
import com.ThankSens.Hentori.Entity.OrderEntity;
import com.ThankSens.Hentori.Entity.OrderShirtEntity;
import com.ThankSens.Hentori.Repository.OrderRepository;
import com.ThankSens.Hentori.Service.Interface.ReportServiceImp;
import org.modelmapper.ModelMapper;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class ReportService implements ReportServiceImp {
    private OrderRepository orderRepository;
    private ModelMapper modelMapper;

    public ReportService(OrderRepository orderRepository, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ReportDto reportOrderByMonth(int month, int year) {
        if (month<1 || month>12  || year < 1000 || year > 9999){
            return null;
        }
        LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);
        Date startDate = java.sql.Date.valueOf(firstDayOfMonth);
        LocalDate lastDayOfMonth = firstDayOfMonth.withDayOfMonth(firstDayOfMonth.lengthOfMonth());
        Date endDate = java.sql.Date.valueOf(lastDayOfMonth);
        List<OrderEntity> orderEntityList = orderRepository.findAllByDate(startDate,endDate);
        if (orderEntityList.isEmpty()){
            return null;
        }
        ReportDto reportDto = reportOrderFormOrderEntity(orderEntityList);

        return reportDto;
    }

    @Override
    public ReportDto reportOrderByQuarter(int quarter, int year) {
        return null;
    }

    public ReportDto reportOrderFormOrderEntity(List<OrderEntity> orderEntityList){
        int total = orderEntityList.size();
        int amount = 0;
        int shirtTotal=0;
        int shirtAmount=0;
        for (OrderEntity orderEntity: orderEntityList
             ) {
            total += orderEntity.getTotal();
            List<OrderShirtEntity> orderShirtEntityList = orderEntity.getOrderShirtEntityList();
            if (orderShirtEntityList.isEmpty()){
                shirtAmount += orderShirtEntityList.size();



            }
        }


        return null;
    }



}
