package com.hello.aop.internalcall;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import com.hello.aop.internalcall.aop.CallLogAspect;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Import(CallLogAspect.class)
@SpringBootTest
class CallServiceV3Test {
	@Autowired
	CallServiceV2 callServiceV3;

	@Test
	void external() {
		callServiceV3.external();
	}

	@Test
	void internal() {
		callServiceV3.internal();
	}
}