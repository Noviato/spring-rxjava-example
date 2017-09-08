package com.vn.noviato.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import com.vn.noviato.info.OrderInfo;
import com.vn.noviato.model.Orders;
import com.vn.noviato.service.IOrderService;


@RestController
@RequestMapping("/order")
public class OrderRest {
	
	private static final Logger logger = LoggerFactory.getLogger(OrderRest.class);
	
	@Autowired
	private IOrderService orderService;
	
	@PostMapping(value="/rx-create")
	public DeferredResult<ResponseEntity<?>> createReactiveOrder(OrderInfo inputOrder) {
		
		logger.info("Begin insert order with rxjava");

		long t1 = System.currentTimeMillis();
		DeferredResult<ResponseEntity<?>> result = new DeferredResult<>();
		if (inputOrder != null) {
			orderService.createReactiveOrder(inputOrder)
						.map(order -> ResponseEntity.ok(order))
						.subscribe(result::setResult);
		}
		long t2 = System.currentTimeMillis();
		long t3 = t2-t1;
		logger.info("Total time ={} to second ={}", t3, t3/1000);
		return result;
	}
	
	
	@PostMapping(value="/normal-create")
	public ResponseEntity<?> createNormalOrder(OrderInfo inputOrder) {
		Orders order = null;
		logger.info("Begin insert order with normal way");
		long t1 = System.currentTimeMillis();

		if (inputOrder != null) {
			try {
				order = orderService.createNormalOrder(inputOrder);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			
			logger.info(inputOrder.toString());
		}
		long t2 = System.currentTimeMillis();
		long t3 = t2-t1;
		logger.info("Total time ={} to second ={}", t3, t3/1000);
		return ResponseEntity.ok(order);
	}
}
