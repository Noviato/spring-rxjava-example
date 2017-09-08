package com.vn.noviato.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vn.noviato.model.Orders;

//@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {
	
}
