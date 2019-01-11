package oth.common.tools;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.data.redis.core.RedisTemplate;

import com.sbolo.syk.common.inner.aspect.CachingAspect1;

public class ICachingAspect {

	public static Object caching(ProceedingJoinPoint point, RedisTemplate<Object, Object> redisTemplate) throws Throwable {
		return CachingAspect1.caching(point, redisTemplate);
	}

}
