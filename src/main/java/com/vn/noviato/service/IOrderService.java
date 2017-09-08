package com.vn.noviato.service;

import com.vn.noviato.info.OrderInfo;
import com.vn.noviato.model.Orders;

import io.reactivex.Observable;

public interface IOrderService {

	Observable<Orders> createReactiveOrder(OrderInfo inputOrder);

	Orders createNormalOrder(OrderInfo inputOrder) throws InterruptedException;

}
