package com.vn.noviato.service.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vn.noviato.info.OrderInfo;
import com.vn.noviato.model.Orders;
import com.vn.noviato.repositories.OrderRepository;
import com.vn.noviato.service.IOrderService;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

@Service
public class OrderServiceImpl implements IOrderService {
	
	private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
	
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Override
	public Orders createNormalOrder(OrderInfo inputOrder) throws InterruptedException {
		Orders order = orderRepository.save(inputOrder.toOrderObject());
		
		if (order != null) {
			sendEmailToCustomer(order);
			sendSmsToCustomer(order);
			updatePortfolioData(order);
			updateSaleForceData(order);
			
			return order;
		}
		
		return null;
	
	}

	@Override
	public Observable<Orders> createReactiveOrder(OrderInfo inputOrder) {
		
		Observable<Orders> obser = Observable.just(orderRepository.save(inputOrder.toOrderObject()))
											.subscribeOn(Schedulers.computation());
		obser.subscribe(order -> {
			if (order != null)
				sendSmsToCustomer(order);
			else logger.info("Error order data return null");
		}, error -> {
			logger.info(error.getMessage(),error);
		});

		obser.subscribe(order -> {
			if (order != null) 
				sendEmailToCustomer(order);
			else logger.info("Error order data return null");
		}, error -> {
			logger.info(error.getMessage(),error);
		});
		
		obser.subscribe(order -> {
			if (order != null) 
				updatePortfolioData(order);
			else logger.info("Error order data return null");
		}, error -> {
			logger.info(error.getMessage(),error);
		});
		
		obser.subscribe(order -> {
			if (order != null) 
				updateSaleForceData(order);
			else logger.info("Error order data return null");
		}, error -> {
			logger.info(error.getMessage(),error);
		});
		
		return obser;
	}
	
	
	public boolean sendSmsToCustomer(Orders orderObj) throws InterruptedException {
		
		logger.info("Current thread ={} send sms to customer={} for order id={}",Thread.currentThread().getName(), orderObj.getCustomerName(),orderObj.getOrderId());
		Thread.sleep(200);
		return true;
	}
	
	
	public boolean sendEmailToCustomer(Orders orderObj) throws InterruptedException {
		logger.info("Current thread ={} send email to customer={} for order id={}",Thread.currentThread().getName(),orderObj.getCustomerName(),orderObj.getOrderId());
		Thread.sleep(200);
		return true;
	}
	
	
	public boolean updatePortfolioData(Orders orderObj) throws InterruptedException {
		logger.info("Current thread ={} Update portfolio for customer={} with product={}",Thread.currentThread().getName(),orderObj.getCustomerName(),orderObj.getProductCode());
		Thread.sleep(200);
		return true;
	}
	
	public boolean updateSaleForceData(Orders orderObj) throws InterruptedException {
		logger.info("Current thread ={} Update SaleForce data for customer={} with orderId={}",Thread.currentThread().getName(),orderObj.getCustomerName(),orderObj.getOrderId());
		Thread.sleep(200);
		return true;
	}

}
