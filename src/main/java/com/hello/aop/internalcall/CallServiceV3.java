package com.hello.aop.internalcall;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CallServiceV3 {
	private final InternalService internalService;

	public CallServiceV3(InternalService internalService) {
		this.internalService = internalService;
	}

	public void external() {
		log.info("call external");
		internalService.internal();
	}
}
