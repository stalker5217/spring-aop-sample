package com.hello.aop.internalcall;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CallServiceV2 {

	private final ObjectProvider<CallServiceV2> callServiceV2ObjectProvider;

	public CallServiceV2(ObjectProvider<CallServiceV2> callServiceV2ObjectProvider) {
		this.callServiceV2ObjectProvider = callServiceV2ObjectProvider;
	}

	public void external() {
		log.info("call external");
		CallServiceV2 callServiceV2 = callServiceV2ObjectProvider.getObject();
		callServiceV2.internal();
	}

	public void internal() {
		log.info("call internal");
	}
}

