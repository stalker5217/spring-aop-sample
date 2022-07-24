package com.hello.aop.proxys.code;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
public class ProxyDIAspect {
	@Before("execution(* com.hello.aop..*.*(..))")
	public void doTrace(JoinPoint joinPoint) {
		log.info("[proxyDIAdvice] {}", joinPoint.getSignature());
	}
}
