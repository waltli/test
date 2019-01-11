package com.sbolo.syk.common.inner.aspect;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import oth.common.anotation.CacheAvl;
import oth.common.anotation.CacheDel;
import oth.common.tools.IUtils;

public class CachingAspect1 {

	public static Object caching(ProceedingJoinPoint point, RedisTemplate<Object, Object> redisTemplate) throws Throwable {
		Signature signature = point.getSignature();
		Object[] params = point.getArgs();
		MethodSignature methodSignature = (MethodSignature) signature;
		Method targetMethod = methodSignature.getMethod();
		Object obj = null;
		CacheAvl cacheAvl = targetMethod.getAnnotation(CacheAvl.class);
		CacheDel cacheDel = targetMethod.getAnnotation(CacheDel.class);

		if (cacheAvl != null) {
			String key = cacheAvl.key();
			String hKey = cacheAvl.hKey();
			if (null != params && params.length > 1) {
				if (StringUtils.isNotEmpty(key) && key.startsWith("#")) {
					key = String.valueOf(params[0]);
				}
				if (StringUtils.isNotEmpty(hKey) && hKey.startsWith("#")) {
					hKey = String.valueOf(params[1]);
				}
			}
			long timeout = cacheAvl.timeout();
			TimeUnit unit = cacheAvl.timeUnit();
			String dateStr = cacheAvl.expireAt();
			String format = cacheAvl.format();
			if (timeout > -1 && StringUtils.isBlank(key)) {
				throw new Exception("key, timeout must be present at the same time!");
			}
			if (StringUtils.isNotBlank(dateStr) && StringUtils.isBlank(key)) {
				throw new Exception("key, expireAt must be present at the same time!");
			}
			if (StringUtils.isNotBlank(dateStr) && StringUtils.isBlank(format)) {
				throw new Exception("expireAt, format must be present at the same time!");
			}
			if (StringUtils.isBlank(key)) {
				key = point.getTarget().getClass().getName();
			}
			if (StringUtils.isBlank(hKey)) {
				String args = Arrays.toString(point.getArgs());
				hKey = signature.toLongString() + args;
			}
			BoundHashOperations<Object, Object, Object> boundHashOps = redisTemplate.boundHashOps(key);

			obj = boundHashOps.get(hKey);
			if (obj == null) {
				obj = point.proceed();
				boundHashOps.put(hKey, obj);

				if (timeout > -1) {
					boundHashOps.expire(timeout, unit);
				}
				if (StringUtils.isNotBlank(dateStr)) {
					Date date = IUtils.str2Date(dateStr, format);
					boundHashOps.expireAt(date);
				}
			}

			// 默认情况下每调用一次后，更新有效期。
			if (timeout <= -1) {
				// 默认有效期，7天。
				timeout = 7;
				unit = TimeUnit.DAYS;
				boundHashOps.expire(timeout, unit);
			}
		} else if (cacheDel != null) {
			obj = point.proceed();
			String key = cacheDel.key();
			if (StringUtils.isBlank(key)) {
				key = point.getTarget().getClass().getName();
			}
			redisTemplate.delete(key);
		} else {
			obj = point.proceed();
		}
		return obj;
	}

}
