package com.hello.aop.exam.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import com.hello.aop.exam.annotation.Retry;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
public class RetryAspect {
	@Around("@annotation(retry)")
	public Object doRetry(ProceedingJoinPoint joinPoint, Retry retry) throws Throwable{
		log.info("[retry] {} args={}", joinPoint.getSignature(), retry);

		int maxRetry = retry.value();
		Exception exceptionHolder = null;

		for (int retryCount = 1 ; retryCount <= maxRetry ; retryCount++) {
			try {
				return joinPoint.proceed();
			} catch (Exception e) {
				exceptionHolder = e;
			}
		}

		throw exceptionHolder;
	}
}
