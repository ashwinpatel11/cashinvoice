package com.user.repository;

import com.user.entity.MyOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository  extends JpaRepository<MyOrder, Long> {


  //  MyOrder findByOrderIdAndCustomerId_Id(long orderId, Long id);

    List<MyOrder> findByCustomerId_Id(Long id);

}
