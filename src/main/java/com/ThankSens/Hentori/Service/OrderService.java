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
import com.ThankSens.Hentori.Service.Interface.OrderServiceImp;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.time.ZoneId;
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

    private ModelMapper modelMapper;
    private ConvertToDate convertToDate;
    private final int pageSizeDefault = 5;
    private final int pageNumberDefault =0;
    private final int completeIdStatus = 3;

    @Autowired
    public OrderService(ClientRepository clientRepository,
                        OrderRepository orderRepository,
                        OrderStatusRepository orderStatusRepository,
                        OrderSuitRepository orderSuitRepository,
                        OrderTrousersRepository orderTrousersRepository,
                        OrderAccessoryRepository orderAccessoryRepository,
                        ModelMapper modelMapper, ConvertToDate convertToDate,
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
    public List<OrderDto> getAllOrder(int pageNumber) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSizeDefault);
        Page<OrderEntity> orderEntityList = orderRepository.findAll(pageRequest);
        if (orderEntityList.isEmpty()) {
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
    public int getPageAll() {
        PageRequest pageRequest = PageRequest.of(0, pageSizeDefault);
        Page<OrderEntity> orderEntityList = orderRepository.findAll(pageRequest);
        return orderEntityList.getTotalPages();
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
    public List<OrderDto> getProcessingOrder(int pageNumber) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSizeDefault);
        Page<OrderEntity> orderEntityProcessingList = orderRepository.findProcessingOrder(pageRequest);
        if (orderEntityProcessingList.isEmpty()) {
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
    public int getPageProcessing() {
        PageRequest pageRequest = PageRequest.of(0, pageSizeDefault);
        Page<OrderEntity> orderEntityProcessingList = orderRepository.findProcessingOrder(pageRequest);
        int total = orderEntityProcessingList.getTotalPages();
        return total;
    }

    @Override
    public List<OrderDto> getOrderByDate(String start, String end, int pageNumber) {
        try {
            PageRequest pageRequest = PageRequest.of(pageNumber, pageSizeDefault);
            Date startDate = convertToDate.convertDate(start);
            Date endDate = convertToDate.convertDate(end);
            endDate = Date.from(endDate.toInstant().atZone(ZoneId.systemDefault()).plusDays(1).toInstant());
            Page<OrderEntity> orderEntityList = orderRepository.findOrderByDate(startDate, endDate, pageRequest);
            List<OrderDto> orderDtoList = new ArrayList<>();
            if (orderEntityList.isEmpty()){
                return null;
            }
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
    public int getPageDate(String start, String end) {
        try {
            PageRequest pageRequest = PageRequest.of(0, pageSizeDefault);
            Date startDate = convertToDate.convertDate(start);
            Date endDate = convertToDate.convertDate(end);
            endDate = Date.from(endDate.toInstant().atZone(ZoneId.systemDefault()).plusDays(1).toInstant());
            Page<OrderEntity> orderEntityList = orderRepository.findOrderByDate(startDate, endDate, pageRequest);
            if (orderEntityList.isEmpty()){
                return 0;
            }
            return orderEntityList.getTotalPages();
        }catch (Exception e){
            return 0;
        }

    }


    @Override
    public List<OrderDto> getDueOrder(int PageNumber) {
        Date date = new Date();
        PageRequest pageRequest = PageRequest.of( PageNumber, pageSizeDefault);
        List<OrderEntity> orderDueEntityList = orderRepository.findDueOrder(date, pageRequest);
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

    @Override
    public int getPageDue() {
        PageRequest pageRequest = PageRequest.of(0, pageSizeDefault);
        Page<OrderEntity> orderEntityPage = orderRepository.findDuePage(new Date(), pageRequest);
        int total = orderEntityPage.getTotalPages();
        return total;
    }

    @Override
    public List<OrderDto> getCompleteOrder(int pageNumber) {
        Optional<OrderStatusEntity> orderStatusEntityOptional = orderStatusRepository.findById(completeIdStatus);
        if (!(orderStatusEntityOptional.isPresent())){
            return null;
        }
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSizeDefault);
        Page<OrderEntity> orderEntityPage = orderRepository.findByOrderStatusEntityEquals(orderStatusEntityOptional.get(), pageRequest);
        if (orderEntityPage.isEmpty()){
            return null;
        }
        List<OrderDto> orderDtoList = new ArrayList<>();
        for (OrderEntity orderEntity: orderEntityPage
        ) {
            OrderDto orderDto = createOrderDtoFromOrderEntity(orderEntity);
            orderDtoList.add(orderDto);
        }

        return orderDtoList;
    }

    @Override
    public int getPageComplete() {
        Optional<OrderStatusEntity> orderStatusEntityOptional = orderStatusRepository.findById(completeIdStatus);
        if (!(orderStatusEntityOptional.isPresent())){
            return 0;
        }
        PageRequest pageRequest = PageRequest.of(0, pageSizeDefault);
        Page<OrderEntity> orderEntityPage = orderRepository.findByOrderStatusEntityEquals(orderStatusEntityOptional.get(), pageRequest);
        return orderEntityPage.getTotalPages();
    }

    @Override
    public List<OrderDto> findOrderProcessingByClient(String phoneNumber, int pageNumber) {
        try {
            ClientEntity clientEntity = clientRepository.findByPhoneNumber(phoneNumber);
            Optional<OrderStatusEntity> orderStatusEntity = orderStatusRepository.findById(completeIdStatus);
            PageRequest pageRequest = PageRequest.of(pageNumber, pageSizeDefault, Sort.by("createAt").descending());
            Page<OrderEntity> orderEntityPage = orderRepository.findAllByClientEntityAndOrderStatusEntityIsNot(clientEntity,orderStatusEntity.get(),pageRequest);
            List<OrderDto> orderDtoList = new ArrayList<>();
            for (OrderEntity orderEntity: orderEntityPage
            ) {
                OrderDto orderDto = createOrderDtoFromOrderEntity(orderEntity);
                orderDtoList.add(orderDto);
            }
            return orderDtoList;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public int findOrderProcessingPage(String phoneNumber) {
        try {
            ClientEntity clientEntity = clientRepository.findByPhoneNumber(phoneNumber);
            Optional<OrderStatusEntity> orderStatusEntity = orderStatusRepository.findById(completeIdStatus);
            PageRequest pageRequest = PageRequest.of(pageNumberDefault, pageSizeDefault);
            Page<OrderEntity> orderEntityPage = orderRepository.findAllByClientEntityAndOrderStatusEntityIsNot(clientEntity,orderStatusEntity.get(),pageRequest);
            return orderEntityPage.getTotalPages();
        }catch (Exception e){
            return 0;
        }
    }

    @Override
    public List<OrderDto> findOrderCompleteByClient(String phoneNumber, int pageNumber) {
        try {
            ClientEntity clientEntity = clientRepository.findByPhoneNumber(phoneNumber);
            Optional<OrderStatusEntity> orderStatusEntity = orderStatusRepository.findById(completeIdStatus);
            PageRequest pageRequest = PageRequest.of(pageNumber, pageSizeDefault, Sort.by("createAt").descending());
            Page<OrderEntity> orderEntityPage = orderRepository.findAllByClientEntityAndOrderStatusEntity(clientEntity, orderStatusEntity.get(),pageRequest);
            List<OrderDto> orderDtoList = new ArrayList<>();
            for (OrderEntity orderEntity: orderEntityPage
            ) {
                OrderDto orderDto = createOrderDtoFromOrderEntity(orderEntity);
                orderDtoList.add(orderDto);
            }
            return orderDtoList;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public int findOrderCompletePage(String phoneNumber) {
        try {
            ClientEntity clientEntity = clientRepository.findByPhoneNumber(phoneNumber);
            Optional<OrderStatusEntity> orderStatusEntity = orderStatusRepository.findById(completeIdStatus);
            PageRequest pageRequest = PageRequest.of(pageNumberDefault, pageSizeDefault);
            Page<OrderEntity> orderEntityPage = orderRepository.findAllByClientEntityAndOrderStatusEntity(clientEntity,orderStatusEntity.get(),pageRequest);
            return orderEntityPage.getTotalPages();
        }catch (Exception e){
            return 0;
        }
    }

    @Override
    public List<OrderDto> findAllByClient(String phoneNumber, int pageNumber) {
        try {
            ClientEntity clientEntity = clientRepository.findByPhoneNumber(phoneNumber);
            PageRequest pageRequest = PageRequest.of(pageNumber, pageSizeDefault, Sort.by("createAt").descending());
            Page<OrderEntity> orderEntityPage = orderRepository.findAllByClientEntity(clientEntity,pageRequest);
            List<OrderDto> orderDtoList = new ArrayList<>();
            for (OrderEntity orderEntity: orderEntityPage
            ) {
                OrderDto orderDto = createOrderDtoFromOrderEntity(orderEntity);
                orderDtoList.add(orderDto);
            }
            return orderDtoList;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public int findAllPage(String phoneNumber) {
        try {
            ClientEntity clientEntity = clientRepository.findByPhoneNumber(phoneNumber);
            PageRequest pageRequest = PageRequest.of(pageNumberDefault, pageSizeDefault);
            Page<OrderEntity> orderEntityPage = orderRepository.findAllByClientEntity(clientEntity,pageRequest);
            return orderEntityPage.getTotalPages();
        }catch (Exception e){
            return 0;
        }
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
}