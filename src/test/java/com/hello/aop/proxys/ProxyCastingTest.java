package com.hello.aop.proxys;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;

import com.hello.aop.member.MemberService;
import com.hello.aop.member.MemberServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProxyCastingTest {
	@Test
	void jdkProxy() {
		MemberServiceImpl target = new MemberServiceImpl();
		ProxyFactory proxyFactory = new ProxyFactory(target);
		proxyFactory.setProxyTargetClass(false); // JDK 동적 프록시

		// 프록시를 인터페이스로 캐스팅
		MemberService interfaceCasting = (MemberService)proxyFactory.getProxy();

		// 프록시를 구체 클래스로 캐스팅
		assertThatThrownBy(() -> {
			MemberServiceImpl concreteCasting = (MemberServiceImpl)interfaceCasting;
		}).isInstanceOf(ClassCastException.class);
	}

	@Test
	void cglibProxy() {
		MemberServiceImpl target = new MemberServiceImpl();
		ProxyFactory proxyFactory = new ProxyFactory(target);
		proxyFactory.setProxyTargetClass(true); // CGLIB

		// 프록시를 인터페이스로 캐스팅 - 성공
		MemberService interfaceCasting = (MemberService)proxyFactory.getProxy();

		// 프록시를 구체 클래스로 캐스팅 - 성공
		MemberServiceImpl concreteCasting = (MemberServiceImpl)interfaceCasting;
	}
}
