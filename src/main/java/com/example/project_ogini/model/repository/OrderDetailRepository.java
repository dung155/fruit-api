package com.example.project_ogini.model.repository;

import com.example.project_ogini.model.entities.OrderDetail;
import com.example.project_ogini.model.entities.OrderDetailDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
     @Query(value =" SELECT P.id, P.Product_Name,P.Product_Price_Out, OD.Quantity" +
             " FROM Oder_Detail OD" +
             " JOIN Product P ON P.id = OD.Product_Id" +
             " WHERE OD.Order_Id = :orderId", nativeQuery = true)
     List<OrderDetailDto> testFind(Integer orderId);
}
