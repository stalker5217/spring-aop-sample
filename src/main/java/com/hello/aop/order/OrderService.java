package com.hello.aop.order;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderService {
	private final OrderRepository orderRepository;

	public void orderItem(String itemId) {
		log.info("[orderService] start");
		orderRepository.save(itemId);
	}
}
