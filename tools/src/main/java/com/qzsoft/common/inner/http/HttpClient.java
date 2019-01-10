package com.qzsoft.common.inner.http;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import com.qzsoft.common.inner.http.interceptor.OuterChain;
import com.qzsoft.common.inner.http.interceptor.OuterInterceptor;
import com.qzsoft.common.inner.http.interceptor.outer.DynamicProxyInterceptor;
import com.qzsoft.common.inner.http.interceptor.outer.ExecutorInterceptor;

import okhttp3.Cookie;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request;
import okhttp3.Response;
import oth.common.http.proxy.StatefulProxy;

public class HttpClient {
	
	private static final Logger log = LoggerFactory.getLogger(HttpClient.class);
	
	private static OkHttpClient okHttpClient;
	private List<StatefulProxy> listProxies = new ArrayList<StatefulProxy>();
	private StatefulProxy proxy;
	private List<OuterInterceptor> oIntercepts = new ArrayList<OuterInterceptor>();
	public static HashMap<String, Map<String, Cookie>> cookieStore = new HashMap<String, Map<String, Cookie>>();
	private int READ_TIMEOUT = 1000;
	private int WRITE_TIMEOUT = 1000;
	private int CONNECT_TIMEOUT = 1000;
	
	private static class HttpClientHolder{
		private static HttpClient HTTPCLIENT = new HttpClient();
	}
	
	private HttpClient(){
		Builder okHttpBuilder = new OkHttpClient().newBuilder()
//				.addInterceptor(new RetryIntercepter(4))
//				.addInterceptor(new SafetyCheckIntercepter())
                .readTimeout(READ_TIMEOUT,TimeUnit.SECONDS)//设置读取超时时间  
                .writeTimeout(WRITE_TIMEOUT,TimeUnit.SECONDS)//设置写的超时时间  
                .connectTimeout(CONNECT_TIMEOUT,TimeUnit.SECONDS)//设置连接超时时间 
//                .cookieJar(new CookieJar() {
//					@Override
//					public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
//						String host = url.host();
//						Map<String , Cookie> cookieKeyVal = cookieStore.get(host);
//						if(cookieKeyVal == null){
//							cookieKeyVal = new HashMap<String, Cookie>();
//							cookieStore.put(url.host(), cookieKeyVal);
//						}
//						for(int i=0; i<cookies.size(); i++){
//							Cookie cookie = cookies.get(i);
//							cookieKeyVal.put(cookie.name(), cookie);
//						}
//					}
//					
//					@Override
//					public List<Cookie> loadForRequest(HttpUrl url) {
//						Map<String, Cookie> cookieKeyVal = cookieStore.get(url.host());
//						List<Cookie> cookies = new ArrayList<Cookie>();
//						if(cookieKeyVal != null){
//							for (Map.Entry<String, Cookie> entry : cookieKeyVal.entrySet()) {
//								cookies.add(entry.getValue());
//							}
//						}
//				        return cookies;
//					}
//				}) //配置cookie存储
                ;
				SSLSocketFactory sslSocketFactory = createSSLSocketFactory();
				if(sslSocketFactory != null) {
					okHttpBuilder.sslSocketFactory(sslSocketFactory).hostnameVerifier(new TrustAllHostnameVerifier());
				}
				okHttpClient = okHttpBuilder.build();
	}
	
	protected static final HttpClient getHttpClient(){
		return HttpClientHolder.HTTPCLIENT;
	}
	
	public static OkHttpClient getOkHttpClient() {
		return okHttpClient;
	}
	
	public HttpClient addOInterceptor(OuterInterceptor oInterceptor){
		oIntercepts.add(oInterceptor);
		return this;
	}
	
	public Response execute(Request request) throws IOException{
		DynamicProxyInterceptor dynamicProxyInterceptor = new DynamicProxyInterceptor(this, listProxies);
		ExecutorInterceptor executorInterceptor = new ExecutorInterceptor(okHttpClient);
		oIntercepts.add(dynamicProxyInterceptor);
		oIntercepts.add(executorInterceptor);
		OuterChain oChain = new OuterChain(this, request, 0, oIntercepts);
		Response response = oChain.proceed(request);
		return response;
	}
	
	public void proxyWithRunning(StatefulProxy proxy){
		okHttpClient = okHttpClient.newBuilder().proxy(proxy.proxy()).build();
		this.proxy = proxy;
//		ExecutorInterceptor executorInterceptor = (ExecutorInterceptor) oIntercepts.get(oIntercepts.size()-1);
//		executorInterceptor.setOkHttpClient(okHttpClient);
	}
	
	public HttpClient proxy(StatefulProxy proxy){
		listProxies.add(proxy);
		return this;
	}
	
	public HttpClient proxyPool(List<StatefulProxy> listProxies){
		this.listProxies = listProxies;
		return this;
	}

	public StatefulProxy currProxy() {
		return proxy;
	}
	
	private class TrustAllCerts implements X509TrustManager {
		@Override
		public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
	 
		@Override
		public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
	 
		@Override
		public X509Certificate[] getAcceptedIssuers() {return new X509Certificate[0];}
	}
	private class TrustAllHostnameVerifier implements HostnameVerifier {
		@Override
		public boolean verify(String hostname, SSLSession session) {
			return true;
		}
	}
	private SSLSocketFactory createSSLSocketFactory() {
		SSLSocketFactory ssfFactory = null;
	 
		try {
			SSLContext sc = SSLContext.getInstance("TLS");
			sc.init(null,  new TrustManager[] { new TrustAllCerts() }, new SecureRandom());
			ssfFactory = sc.getSocketFactory();
		} catch (Exception e) {
			log.error("Create SSLSocketFactory", e);
		}
		return ssfFactory;
	}
}
