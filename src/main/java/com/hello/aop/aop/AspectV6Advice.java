package com.hello.aop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
public class AspectV6Advice {
	@Around("com.hello.aop.aop.Pointcuts.orderAndService()")
	public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
		try {
			// @Before - start
			log.info("[트랜잭션 시작] {}", joinPoint.getSignature());

			// Target Method 실행
			Object result = joinPoint.proceed();

			// @AfterReturning
			log.info("[트랜잭션 종료] {}", joinPoint.getSignature());

			return result;
		} catch (Exception e) {
			// @AfterThrowing
			log.info("[트랜잭션 롤백] {}", joinPoint.getSignature());
			throw  e;
		} finally {
			// @After
			log.info("[리소스 릴리즈] {}", joinPoint.getSignature());
		}
	}

	@Before("com.hello.aop.aop.Pointcuts.orderAndService()")
	public void doBefore(JoinPoint joinPoint) {
		log.info("[before] {}", joinPoint.getSignature());
	}

	@AfterReturning(
		value = "com.hello.aop.aop.Pointcuts.orderAndService()",
		returning = "result"
	)
	public void doReturn(JoinPoint joinPoint, Object result) {
		log.info("[return] {} return {}", joinPoint.getSignature(), result);
	}

	@AfterThrowing(
		value = "com.hello.aop.aop.Pointcuts.orderAndService()",
		throwing = "ex"
	)
	public void doThrowing(JoinPoint joinPoint, Exception ex) {
		log.info("[ex] {} message={}", joinPoint.getSignature(), ex);
	}

	@After("com.hello.aop.aop.Pointcuts.orderAndService()")
	public void doAfter(JoinPoint joinPoint) {
		log.info("[after] {}", joinPoint.getSignature());
	}
}
