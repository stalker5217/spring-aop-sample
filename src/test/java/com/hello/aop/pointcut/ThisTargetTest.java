package com.hello.aop.pointcut;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import com.hello.aop.member.MemberService;

import lombok.extern.slf4j.Slf4j;

/**
 * spring.aop.proxy-target-class=true  CGLIB
 * spring.aop.proxy-target-class=false JDK 동적 프록시
 */
@Slf4j
@Import(ThisTargetTest.ThisTargetAspect.class)
@SpringBootTest(properties = "spring.aop.proxy-target-class=false")
class ThisTargetTest {
	@Autowired
	MemberService memberService;

	@Test
	void success() {
		log.info("memberService Proxy={}", memberService.getClass());
		memberService.hello("helloA");
	}

	@Slf4j
	@Aspect
	static class ThisTargetAspect {
		@Around("this(com.hello.aop.member.MemberService)")
		public Object doThisInterface(ProceedingJoinPoint joinPoint) throws Throwable {
			log.info("[this-interface] {}", joinPoint.getSignature());

			return joinPoint.proceed();
		}

		@Around("this(com.hello.aop.member.MemberService)")
		public Object doTargetInterface(ProceedingJoinPoint joinPoint) throws Throwable {
			log.info("[target-interface] {}", joinPoint.getSignature());

			return joinPoint.proceed();
		}

		@Around("this(com.hello.aop.member.MemberServiceImpl)")
		public Object doThisConcrete(ProceedingJoinPoint joinPoint) throws Throwable {
			log.info("[this-concrete] {}", joinPoint.getSignature());

			return joinPoint.proceed();
		}

		@Around("this(com.hello.aop.member.MemberServiceImpl)")
		public Object doTargetConcrete(ProceedingJoinPoint joinPoint) throws Throwable {
			log.info("[target-concrete] {}", joinPoint.getSignature());

			return joinPoint.proceed();
		}
	}
}
