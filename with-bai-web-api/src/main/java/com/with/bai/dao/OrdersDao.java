package com.with.bai.dao;

import com.with.bai.domain.Orders;

public interface OrdersDao {
    int insertOrdersByFid(Orders orders);

    Orders selectOrdersByFid(Orders orders);

    int updateOrdersByFid(Orders orders);
}
