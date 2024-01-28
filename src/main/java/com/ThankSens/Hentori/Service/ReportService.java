package com.ThankSens.Hentori.Service;

import com.ThankSens.Hentori.Dto.ReportDto;
import com.ThankSens.Hentori.Entity.*;
import com.ThankSens.Hentori.Repository.OrderRepository;
import com.ThankSens.Hentori.Service.Interface.ReportServiceImp;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class ReportService implements ReportServiceImp {
    private OrderRepository orderRepository;
    private ModelMapper modelMapper;

    public ReportService(OrderRepository orderRepository, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ReportDto reportOrderByMonth(int month, int year) {
        if (month < 1 || month > 12 || year < 1000 || year > 9999) {
            return null;
        }
        LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);
        Date startDate = java.sql.Date.valueOf(firstDayOfMonth);
        LocalDate lastDayOfMonth = firstDayOfMonth.withDayOfMonth(firstDayOfMonth.lengthOfMonth());
        Date endDate = java.sql.Date.valueOf(lastDayOfMonth);
        List<OrderEntity> orderEntityList = orderRepository.findAllByDate(startDate, endDate);
        if (orderEntityList.isEmpty()) {
            return null;
        }
        ReportDto reportDto = reportOrderFormOrderEntity(orderEntityList);

        return reportDto;
    }

    @Override
    public ReportDto reportOrderByQuarter(int quarter, int year) {
        if (quarter < 1 || quarter > 4 || year < 1000 || year > 9999) {
            return null;
        }
        int startMonth = 0;
        int endMonth;
        switch (quarter) {
            case 1:
                startMonth = 1;
                endMonth = 3;
                break;
            case 2:
                startMonth = 4;
                endMonth = 6;
                break;
            case 3:
                startMonth = 7;
                endMonth = 9;
                break;
            default:
                startMonth = 10;
                endMonth = 12;
                break;
        }
//        find start date
        LocalDate firstDayOfQuarter = LocalDate.of(year, startMonth, 1);
        Date startDate = java.sql.Date.valueOf(firstDayOfQuarter);
//        find end date
        LocalDate lastDayOfQuarter = LocalDate.of(year, endMonth, 1).plusMonths(1).minusDays(1);
        Date endDate = java.sql.Date.valueOf(lastDayOfQuarter);

        List<OrderEntity> orderEntityList = orderRepository.findAllByDate(startDate, endDate);
        if (orderEntityList.isEmpty()) {
            return null;
        }
        ReportDto reportDto = reportOrderFormOrderEntity(orderEntityList);
        return reportDto;
    }

    private ReportDto reportOrderFormOrderEntity(List<OrderEntity> orderEntityList) {
        double total = 0;
        int amount = orderEntityList.size();
        double shirtTotal = 0;
        int shirtAmount = 0;
        double trouserTotal = 0;
        int trousersAmount = 0;
        double suitTotal = 0;
        int suitAmount = 0;
        double accessoryTotal = 0;
        int accessoryAmount = 0;
        for (OrderEntity orderEntity : orderEntityList
        ) {
            total += orderEntity.getTotal();
            List<OrderShirtEntity> orderShirtEntityList = orderEntity.getOrderShirtEntityList();
            List<OrderTrousersEntity> orderTrousersEntityList = orderEntity.getOrderTrousersEntityList();
            List<OrderSuitEntity> orderSuitEntityList = orderEntity.getOrderSuitEntityList();
            List<OrderAccessoryEntity> orderAccessoryEntityList = orderEntity.getOrderAccessoryEntityList();
            if (!orderShirtEntityList.isEmpty()) {
                shirtAmount += calculateAmountListItem(orderShirtEntityList);
                shirtTotal += calculateTotalListItem(orderShirtEntityList);
            }
            if (!orderTrousersEntityList.isEmpty()) {
                trousersAmount += calculateAmountListItem(orderTrousersEntityList);
                trouserTotal += calculateTotalListItem(orderTrousersEntityList);
            }
            if (!orderSuitEntityList.isEmpty()) {
                suitAmount += calculateAmountListItem(orderSuitEntityList);
                suitTotal += calculateTotalListItem(orderSuitEntityList);
            }
            if (!orderAccessoryEntityList.isEmpty()) {
                accessoryAmount += calculateAmountListItem(orderAccessoryEntityList);
                accessoryTotal += calculateTotalListItem(orderAccessoryEntityList);
            }
        }

        ReportDto reportDto = new ReportDto(total, amount, shirtTotal, shirtAmount, suitTotal, suitAmount, trouserTotal, trousersAmount, accessoryTotal, accessoryAmount);


        return reportDto;
    }

    private <T extends OrderItemEntity> double calculateTotalListItem(List<T> listItem) {
        double total = 0;
        for (T item : listItem
        ) {
            total += item.getTotal();
        }
        return total;
    }

    private <T extends OrderItemEntity> int calculateAmountListItem(List<T> listItem) {
        int amount = 0;
        for (T item : listItem
        ) {
            amount += item.getAmount();
        }
        return amount;
    }
}
