package com.ThankSens.Hentori.Service;

import com.ThankSens.Hentori.Dto.*;
import com.ThankSens.Hentori.Entity.*;
import com.ThankSens.Hentori.Payload.Request.Order.OrderAccessoryRequest;
import com.ThankSens.Hentori.Payload.Request.Order.OrderShirtRequest;
import com.ThankSens.Hentori.Payload.Request.Order.OrderSuitRequest;
import com.ThankSens.Hentori.Payload.Request.Order.OrderTrousersRequest;
import com.ThankSens.Hentori.Payload.Request.OrderRequest;
import com.ThankSens.Hentori.Payload.Request.OrderUpdateRequest;
import com.ThankSens.Hentori.Repository.*;
import com.ThankSens.Hentori.Service.Interface.KPIServiceImp;
import com.ThankSens.Hentori.Service.Interface.OrderServiceImp;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements OrderServiceImp {
    private ClientRepository clientRepository;
    private OrderRepository orderRepository;
    private OrderStatusRepository orderStatusRepository;
    private OrderSuitRepository orderSuitRepository;
    private OrderTrousersRepository orderTrousersRepository;
    private OrderAccessoryRepository orderAccessoryRepository;
    private OrderShirtRepository orderShirtRepository;
    private KPIServiceImp kpiServiceImp;
    private KPIRepository kpiRepository;
    private ModelMapper modelMapper;
    private ConvertToDate convertToDate;

    @Autowired
    public OrderService(ClientRepository clientRepository,
                        OrderRepository orderRepository,
                        OrderStatusRepository orderStatusRepository,
                        OrderSuitRepository orderSuitRepository,
                        OrderTrousersRepository orderTrousersRepository,
                        OrderAccessoryRepository orderAccessoryRepository,
                        ModelMapper modelMapper, ConvertToDate convertToDate,
                        KPIServiceImp kpiServiceImp,
                        KPIRepository kpiRepository,
                        OrderShirtRepository orderShirtRepository) {
        this.clientRepository = clientRepository;
        this.orderRepository = orderRepository;
        this.orderStatusRepository = orderStatusRepository;
        this.orderSuitRepository = orderSuitRepository;
        this.orderTrousersRepository = orderTrousersRepository;
        this.orderAccessoryRepository = orderAccessoryRepository;
        this.orderShirtRepository = orderShirtRepository;
        this.modelMapper = modelMapper;
        this.convertToDate = convertToDate;
        this.kpiServiceImp = kpiServiceImp;
        this.kpiRepository = kpiRepository;
    }

    @Override
    @Transactional
    public int createOrder(OrderRequest orderRequest) {
        try {
            List<OrderSuitRequest> orderSuitRequestList = orderRequest.getOrderSuitRequestList();
            List<OrderTrousersRequest> orderTrousersRequestList = orderRequest.getOrderTrousersRequestList();
            List<OrderAccessoryRequest> orderAccessoryRequestList = orderRequest.getOrderAccessoryRequestList();
            List<OrderShirtRequest> orderShirtRequestList = orderRequest.getOrderShirtRequestList();
            Optional<ClientEntity> clientEntity = clientRepository.findById(orderRequest.getClient_id());
            Optional<OrderStatusEntity> orderStatusEntity = orderStatusRepository.findById(orderRequest.getOrderStatusRequest().getId());
            //Check request
            if (clientEntity.isPresent() && orderStatusEntity.isPresent()
                    && (orderSuitRequestList != null || orderTrousersRequestList != null || orderAccessoryRequestList != null || orderShirtRequestList != null)) {
                //Create new OrderEntity
                OrderEntity orderEntity = new OrderEntity();
                Date date = new Date();
                Date appointmentDay = convertToDate.convertDate(orderRequest.getAppointmentDay());
                //Calculate money order
                int totalOrder = calculateTotal(orderSuitRequestList, orderTrousersRequestList, orderAccessoryRequestList, orderShirtRequestList);
                // save Order
                orderEntity.setCreateAt(date);
                orderEntity.setAppointmentDay(appointmentDay);
                orderEntity.setClientEntity(clientEntity.get());
                orderEntity.setOrderStatusEntity(orderStatusEntity.get());
                orderEntity.setTotal(totalOrder);
                orderEntity.setPayment(orderRequest.getPayment());
                orderEntity = orderRepository.save(orderEntity);
                //Add item
                if (orderSuitRequestList != null) {
                    addListSuit(orderSuitRequestList, orderEntity);
                }
                if (orderTrousersRequestList != null) {
                    addListTrousers(orderTrousersRequestList, orderEntity);
                }
                if (orderAccessoryRequestList != null) {
                    addListAccessory(orderAccessoryRequestList, orderEntity);
                }
                if (orderShirtRequestList != null) {
                    addListShirt(orderShirtRequestList, orderEntity);
                }
                //Update Kpi
                updateKPIAddOrder(orderEntity);
                return orderEntity.getId();
            } else {
                return 0;
            }
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }
    }

    @Override
    public List<OrderDto> getAllOrder() {
        List<OrderEntity> orderEntityList = orderRepository.findAll();
        if (!(orderEntityList.size() > 0)) {
            return null;
        }
        List<OrderDto> orderDtoList = new ArrayList<>();
        for (OrderEntity orderEntity : orderEntityList
        ) {
            OrderDto orderDto = createOrderDtoFromOrderEntity(orderEntity);
            orderDtoList.add(orderDto);
        }
        return orderDtoList;
    }

    @Override
    public OrderDto getDetailOrder(int id) {
        Optional<OrderEntity> orderEntity = orderRepository.findById(id);

        if (orderEntity.isPresent()) {
            OrderDto orderDto = createOrderDtoFromOrderEntity(orderEntity.get());
            return orderDto;
        }

        return null;
    }

    @Transactional
    @Override
    public boolean updateOrder(int order_id, OrderUpdateRequest orderUpdateRequest) {
        try {
            Optional<OrderEntity> orderEntity = orderRepository.findById(order_id);
            int payment = orderUpdateRequest.getPayment();
            Optional<OrderStatusEntity> orderStatusEntity = orderStatusRepository.findById(orderUpdateRequest.getOrderStatusRequest().getId());
            if (orderEntity.isPresent() && orderStatusEntity.isPresent() && payment > 0) {
                orderEntity.get().setOrderStatusEntity(orderStatusEntity.get());
                orderEntity.get().setPayment(payment);
                orderEntity.get().setAppointmentDay(convertToDate.convertDate(orderUpdateRequest.getAppointmentDay()));
                orderRepository.save(orderEntity.get());
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }


    @Override
    public List<OrderDto> getProcessingOrder() {
        List<OrderEntity> orderEntityProcessingList = orderRepository.findProcessingOrder();
        if (!(orderEntityProcessingList.size() > 0)) {
            return null;
        }
        List<OrderDto> orderProcessingDtoList = new ArrayList<>();
        for (OrderEntity orderEntity : orderEntityProcessingList
        ) {
            OrderDto orderDto = createOrderDtoFromOrderEntity(orderEntity);
            orderProcessingDtoList.add(orderDto);
        }
        return orderProcessingDtoList;
    }

    @Override
    public List<OrderDto> getOrderByDate(String start, String end) {
        try {
            Date startDate = convertToDate.convertDate(start);
            Date endDate = convertToDate.convertDate(end);
            List<OrderEntity> orderEntityList = orderRepository.findOrderByDate(startDate, endDate);
            List<OrderDto> orderDtoList = new ArrayList<>();
            for (OrderEntity orderEntity : orderEntityList
            ) {
                OrderDto orderDto = createOrderDtoFromOrderEntity(orderEntity);
                orderDtoList.add(orderDto);
            }
            return orderDtoList;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public List<OrderDto> getDueOrder() {
        Date date = new Date();
        List<OrderEntity> orderDueEntityList = orderRepository.findDueOrder(date);
        if (!(orderDueEntityList.size() > 0)) {
            return null;
        }
        List<OrderDto> orderProcessingDtoList = new ArrayList<>();
        for (OrderEntity orderEntity : orderDueEntityList
        ) {
            OrderDto orderDto = createOrderDtoFromOrderEntity(orderEntity);
            orderProcessingDtoList.add(orderDto);
        }
        return orderProcessingDtoList;
    }

    // Calculate total money
    private int calculateTotal(List<OrderSuitRequest> orderSuitRequestList,
                               List<OrderTrousersRequest> orderTrousersRequestList,
                               List<OrderAccessoryRequest> orderAccessoryRequestList,
                               List<OrderShirtRequest> orderShirtRequestList) {
        int total = 0;
        if (orderSuitRequestList != null) {
            total += calculateSuitOrder(orderSuitRequestList);
        }
        if (orderTrousersRequestList != null) {
            total += calculateTrouserOrder(orderTrousersRequestList);
        }
        if (orderAccessoryRequestList != null) {
            total += calculateAccessoryOrder(orderAccessoryRequestList);
        }
        if (orderShirtRequestList != null) {
            total += calculateShirtOrder(orderShirtRequestList);
        }
        return total;
    }

    // Calculate money suit
    private int calculateSuitOrder(List<OrderSuitRequest> orderSuitRequestList) {
        int total = 0;
        for (OrderSuitRequest orderSuitRequest : orderSuitRequestList
        ) {
            total += orderSuitRequest.getPrice() * orderSuitRequest.getAmount();
        }
        return total;
    }

    // Calculate money trouser
    private int calculateTrouserOrder(List<OrderTrousersRequest> orderTrousersRequestList) {
        int total = 0;
        for (OrderTrousersRequest orderTrousersRequest : orderTrousersRequestList
        ) {
            total += orderTrousersRequest.getAmount() * orderTrousersRequest.getPrice();
        }
        return total;
    }

    // Calculate money Accessory
    private int calculateAccessoryOrder(List<OrderAccessoryRequest> orderAccessoryRequestList) {
        int total = 0;
        for (OrderAccessoryRequest orderAccessoryRequest : orderAccessoryRequestList) {
            total += orderAccessoryRequest.getAmount() * orderAccessoryRequest.getPrice();
        }
        return total;
    }
    // Calculate money Shirt
    private int calculateShirtOrder(List<OrderShirtRequest> orderShirtRequestList) {
        int total = 0;
        for (OrderShirtRequest orderShirtRequest : orderShirtRequestList
        ) {
            total += orderShirtRequest.getPrice() * orderShirtRequest.getAmount();
        }
        return total;
    }

    //    Save list suit
    private void addListSuit(List<OrderSuitRequest> orderSuitRequestList, OrderEntity orderEntity) throws Exception {
        try {
            List<OrderSuitEntity> orderSuitEntityList = new ArrayList<>();
            for (OrderSuitRequest orderSuitRequest : orderSuitRequestList) {
                OrderSuitEntity orderSuitEntity = modelMapper.map(orderSuitRequest, OrderSuitEntity.class);
                orderSuitEntity.setTotal(orderSuitRequest.getPrice() * orderSuitRequest.getAmount());
                orderSuitEntity.setOrderEntity(orderEntity);
                orderSuitEntityList.add(orderSuitEntity);
            }
            orderSuitRepository.saveAll(orderSuitEntityList);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    // Save list trousers
    private void addListTrousers(List<OrderTrousersRequest> orderTrousersRequestList, OrderEntity orderEntity) throws Exception {

        try {
            List<OrderTrousersEntity> orderTrousersEntityList = new ArrayList<>();
            for (OrderTrousersRequest orderTrousersRequest : orderTrousersRequestList) {
                OrderTrousersEntity orderTrousersEntity = modelMapper.map(orderTrousersRequest, OrderTrousersEntity.class);
                orderTrousersEntity.setOrderEntity(orderEntity);
                orderTrousersEntity.setTotal(orderTrousersRequest.getPrice() * orderTrousersRequest.getAmount());
                orderTrousersEntityList.add(orderTrousersEntity);
            }
            orderTrousersRepository.saveAll(orderTrousersEntityList);

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    // Save list Accessory
    private void addListAccessory(List<OrderAccessoryRequest> orderAccessoryRequestList, OrderEntity orderEntity) throws Exception {
        try {
            List<OrderAccessoryEntity> orderAccessoryEntityList = new ArrayList<>();
            for (OrderAccessoryRequest orderAccessoryRequest : orderAccessoryRequestList) {
                OrderAccessoryEntity orderAccessoryEntity = modelMapper.map(orderAccessoryRequest, OrderAccessoryEntity.class);
                orderAccessoryEntity.setOrderEntity(orderEntity);
                orderAccessoryEntity.setTotal(orderAccessoryRequest.getAmount() * orderAccessoryRequest.getPrice());
                orderAccessoryEntityList.add(orderAccessoryEntity);
            }
            orderAccessoryRepository.saveAll(orderAccessoryEntityList);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    //Save list Shirt
    private void addListShirt(List<OrderShirtRequest> orderShirtRequestList, OrderEntity orderEntity) throws Exception {
        try {
            List<OrderShirtEntity> orderShirtEntityList = new ArrayList<>();
            for (OrderShirtRequest orderShirtRequest : orderShirtRequestList) {
                OrderShirtEntity orderShirtEntity = modelMapper.map(orderShirtRequest, OrderShirtEntity.class);
                orderShirtEntity.setOrderEntity(orderEntity);
                orderShirtEntity.setTotal(orderShirtRequest.getAmount() * orderShirtRequest.getPrice());
                orderShirtEntityList.add(orderShirtEntity);
            }
            orderShirtRepository.saveAll(orderShirtEntityList);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    // transfer OrderEntity to OrderDto
    private OrderDto createOrderDtoFromOrderEntity(OrderEntity orderEntity) {
        OrderDto orderDto = modelMapper.map(orderEntity, OrderDto.class);
        OrderStatusDto orderStatusDto = modelMapper.map(orderEntity.getOrderStatusEntity(), OrderStatusDto.class);
        OrderClientDto orderClientDto = modelMapper.map(orderEntity.getClientEntity(), OrderClientDto.class);
        List<OrderSuitDto> orderSuitDtoList = createOrderSuitDtoList(orderEntity.getOrderSuitEntityList());
        List<OrderTrousersDto> orderTrousersDtoList = createOrderTrousersDtoList(orderEntity.getOrderTrousersEntityList());
        List<OrderAccessoryDto> orderAccessoryDtoList = createOrderAccessoryDtoList(orderEntity.getOrderAccessoryEntityList());
        List<OrderShirtDto> orderShirtDtoList = createOrderShirtDtoList(orderEntity.getOrderShirtEntityList());
        orderDto.setOrderStatusDto(orderStatusDto);
        orderDto.setOrderClientDto(orderClientDto);
        orderDto.setOrderSuitDtoList(orderSuitDtoList);
        orderDto.setOrderTrousersDtoList(orderTrousersDtoList);
        orderDto.setOrderAccessoryDtoList(orderAccessoryDtoList);
        orderDto.setOrderShirtDtoList(orderShirtDtoList);
        return orderDto;
    }

    private List<OrderSuitDto> createOrderSuitDtoList(List<OrderSuitEntity> orderSuitEntityList) {
        if (orderSuitEntityList.size() > 0) {
            ArrayList<OrderSuitDto> orderSuitDtoArrayList = new ArrayList<>();

            for (OrderSuitEntity orderSuitEntity : orderSuitEntityList
            ) {
                OrderSuitDto orderSuitDto = modelMapper.map(orderSuitEntity, OrderSuitDto.class);
                orderSuitDtoArrayList.add(orderSuitDto);
            }

            return orderSuitDtoArrayList;
        } else {
            return null;
        }
    }

    private List<OrderTrousersDto> createOrderTrousersDtoList(List<OrderTrousersEntity> orderTrousersEntityList) {
        if (orderTrousersEntityList.size() > 0) {
            ArrayList<OrderTrousersDto> orderTrousersDtoArrayList = new ArrayList<>();

            for (OrderTrousersEntity orderTrousersEntity : orderTrousersEntityList
            ) {
                OrderTrousersDto orderTrousersDto = modelMapper.map(orderTrousersEntity, OrderTrousersDto.class);
                orderTrousersDtoArrayList.add(orderTrousersDto);
            }
            return orderTrousersDtoArrayList;
        }
        return null;
    }

    private List<OrderAccessoryDto> createOrderAccessoryDtoList(List<OrderAccessoryEntity> orderAccessoryEntityList) {
        if (orderAccessoryEntityList.size() > 0) {
            ArrayList<OrderAccessoryDto> orderAccessoryDtoArrayList = new ArrayList<>();
            for (OrderAccessoryEntity orderAccessoryEntity : orderAccessoryEntityList
            ) {
                OrderAccessoryDto orderAccessoryDto = modelMapper.map(orderAccessoryEntity, OrderAccessoryDto.class);
                orderAccessoryDtoArrayList.add(orderAccessoryDto);
            }
            return orderAccessoryDtoArrayList;
        }
        return null;
    }

    private List<OrderShirtDto> createOrderShirtDtoList(List<OrderShirtEntity> orderShirtEntityList){
        if (orderShirtEntityList.size() > 0) {
            List<OrderShirtDto> orderShirtDtoArrayList = new ArrayList<>();
            for (OrderShirtEntity orderShirtEntity: orderShirtEntityList
                 ) {
                OrderShirtDto orderShirtDto = modelMapper.map(orderShirtEntity, OrderShirtDto.class);
                orderShirtDtoArrayList.add(orderShirtDto);
            }
            return orderShirtDtoArrayList;
        }
        return null;

    }

    //update KPI
    private void updateKPIAddOrder(OrderEntity orderEntity) throws Exception {

        Date createAt = orderEntity.getCreateAt();
        Instant instant = createAt.toInstant();
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());
        int year = zonedDateTime.getYear();
        int month = zonedDateTime.getMonthValue();
        KPIEntity kpiEntity = kpiServiceImp.getKPI(year, month);
        if (kpiEntity == null) {
            kpiEntity = kpiServiceImp.createKPI(year, month, 0);
        }
        kpiEntity.setComplete(kpiEntity.getComplete() + orderEntity.getTotal());
        kpiRepository.save(kpiEntity);
    }
}
