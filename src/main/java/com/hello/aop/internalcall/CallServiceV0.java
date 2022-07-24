package com.hello.aop.internalcall;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CallServiceV0 {
	public void external() {
		log.info("call external");
		internal();
	}

	public void internal() {
		log.info("call internal");
	}
}
