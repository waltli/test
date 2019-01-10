package com.qzsoft.common.inner.http.interceptor.outer;

import java.io.IOException;

import com.qzsoft.common.inner.http.interceptor.OuterChain;
import com.qzsoft.common.inner.http.interceptor.OuterInterceptor;

import okhttp3.Request;
import okhttp3.Response;
import oth.common.tools.IStringUtil;

public class DoubanInterceptor implements OuterInterceptor {

	@Override
	public Response intercept(OuterChain oChain) throws IOException {
		Request request = oChain.getRequest();
		if("movie.douban.com".equals(request.url().host())){
			request = request.newBuilder().addHeader("Cookie", "bid="+IStringUtil.randomString(11)).build();
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return oChain.proceed(request);
	}

}
