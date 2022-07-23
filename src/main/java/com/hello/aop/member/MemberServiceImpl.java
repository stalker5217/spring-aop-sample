package com.hello.aop.member;

import org.springframework.stereotype.Service;

import com.hello.aop.member.annotation.ClassAop;
import com.hello.aop.member.annotation.MethodAop;

@Service
@ClassAop
public class MemberServiceImpl implements MemberService {
	@Override
	@MethodAop("test value")
	public String hello(String param) {
		return "ok";
	}

	public String internal(String param) {
		return "ok";
	}
}
