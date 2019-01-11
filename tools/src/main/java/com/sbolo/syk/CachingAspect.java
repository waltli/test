package com.sbolo.syk;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import oth.common.anotation.Test;
import oth.common.tools.ICachingAspect;

@Aspect
@Component
public class CachingAspect {

	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;

	@Test
	@Around("@annotation(oth.common.anotation.CacheAvl) || @annotation(oth.common.anotation.CacheAvl)")
	public Object cast(ProceedingJoinPoint point) throws Throwable {
		return ICachingAspect.caching(point, redisTemplate);
	}
}
