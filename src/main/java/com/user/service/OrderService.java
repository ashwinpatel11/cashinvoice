package com.user.service;


import com.user.dto.CreateOrderRequest;
import com.user.dto.OrderResponseDto;
import com.user.entity.MyOrder;
import com.user.entity.User;
import com.user.enums.Message;
import com.user.enums.Role;
import com.user.exception.CustomException;
import com.user.jwt.CommonUtil;
import com.user.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class OrderService {

    private final OrderRepository repository;

    private final UserService userService;

    private final CommonUtil commonUtil;

    public OrderService(OrderRepository repository, UserService userService, CommonUtil commonUtil) {

        this.repository = repository;
        this.userService = userService;
        this.commonUtil = commonUtil;
    }

    public String createOrder(CreateOrderRequest request) {
        MyOrder order = new MyOrder();
        User singleUser = commonUtil.getLoggedInUser();
        order.setCustomerId(singleUser);
        order.setProduct(request.getProduct());
        order.setAmount(request.getAmount());
        repository.save(order);
        return String.valueOf(order.getId());
    }


    public OrderResponseDto getOrder(long orderId) {
        User loggedInUser = commonUtil.getLoggedInUser();
        if (!loggedInUser.getRole().equals(Role.ADMIN.name())) {
            throw new CustomException(Message.You_Do_Not_Have_Permission.name());
        }
        MyOrder myOrder = repository.findById(orderId).get();
        OrderResponseDto dto = new OrderResponseDto(myOrder.getId(), myOrder.getProduct(), myOrder.getAmount(), myOrder.getCustomerId().getId(), myOrder.getCreatedAt());
        return dto;
    }

    public List<OrderResponseDto> orderList(long customerId) {
        User loggedInUser = commonUtil.getLoggedInUser();
        List<MyOrder> myOrders = new ArrayList<>();
        if (loggedInUser.getRole().equals(Role.ADMIN.name())) {
            myOrders = repository.findByCustomerId_Id(customerId);
            //  throw new CustomException(Message.You_Do_Not_Have_Permission.name());
        } else {
            myOrders = repository.findByCustomerId_Id(loggedInUser.getId());
        }
        List<OrderResponseDto> list = myOrders.stream().map(o -> new OrderResponseDto(o.getId(), o.getProduct(), o.getAmount(), o.getCustomerId().getId(), o.getCreatedAt())).toList();
        return list;
    }
}
