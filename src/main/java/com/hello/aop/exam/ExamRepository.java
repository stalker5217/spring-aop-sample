package com.hello.aop.exam;

import org.springframework.stereotype.Repository;

import com.hello.aop.exam.annotation.Retry;
import com.hello.aop.exam.annotation.Trace;

@Repository
public class ExamRepository {
	private static int seq = 0;

	@Trace
	@Retry(4)
	public String save(String itemId) {
		seq++;
		if (seq % 5 == 0) {
			throw new IllegalArgumentException("예외 발생");
		}

		return "ok";
	}
}
