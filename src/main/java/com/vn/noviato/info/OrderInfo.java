package com.vn.noviato.info;

import java.time.LocalDate;

import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import com.vn.noviato.model.Orders;

public class OrderInfo {
	
	private String orderId;
	private Integer quantity;
	private String productCode;
	private Double price;
	private String customerName;
	private String email;
	private String phone;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDate orderDate;
	
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public LocalDate getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}
	
	
	
	public Orders toOrderObject() {
		Orders order = new Orders();
		BeanUtils.copyProperties(this, order);
		return order;
	}
	@Override
	public String toString() {
		return "OrderInfo [orderId=" + orderId + ", quantity=" + quantity + ", productCode=" + productCode + ", price="
				+ price + ", customerName=" + customerName + ", email=" + email + ", phone=" + phone + ", orderDate="
				+ orderDate + "]";
	}
	
}
