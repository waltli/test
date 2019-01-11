package com.sbolo.syk.common.inner.http;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.sbolo.syk.common.inner.BizUtils1;
import com.sbolo.syk.common.inner.UIDGenerator1;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.RequestBody;
import okhttp3.Response;
import oth.common.exception.BusinessException;
import oth.common.http.HttpResult;
import oth.common.http.callback.HttpSendCallback;
import oth.common.http.callback.HttpSendCallbackPure;
import oth.common.tools.IHttpUtils;
import oth.common.ui.RequestResult;

public class HttpUtils1 {
	private static final Logger LOG = LoggerFactory.getLogger(HttpUtils1.class);
//	private static final String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36";

	
	public static String httpUpload(String uploadUrl, byte[] bytes, Map<String, String> params, String suffix) throws IOException {
		OkHttpClient client = getOkHttpClient();
		
		okhttp3.RequestBody fileBody = okhttp3.RequestBody.create(MediaType.parse("application/octet-stream"), bytes);
		
		okhttp3.MultipartBody.Builder builder = new MultipartBody.Builder();
		
		String busType = params.get("bus_type");
		String JID = params.get("JID");
		if(StringUtils.isNotBlank(busType)) {
			builder.addFormDataPart("bus_type", busType);
		}
		if(StringUtils.isNotBlank(JID)) {
			builder.addFormDataPart("JID", JID);
		}
		builder.addFormDataPart("file", UIDGenerator1.getUID()+"."+suffix, fileBody);
		okhttp3.RequestBody requestBody = builder.build();
		
        Request request = new Request.Builder()
                .url(uploadUrl)
                .post(requestBody)
                .build();
        
        Response response = client.newCall(request).execute();
        if(!response.isSuccessful()){
        	throw new BusinessException("upload error code "+response);
        }
        String jsonString = response.body().string();
        return jsonString;
	}
	
	public static void httpGet(String url, HttpSendCallbackPure callback) throws Exception {
		HttpClient httpClient = getHttpClient();
		Request request = buildRequest(url);
		Response response = null;
		try {
			response = httpClient.execute(request);
			if (!response.isSuccessful()) {
				LOG.error("reqeust faild, code: {}.  message: {}. url: {}", 
						response.code(), response.message(), response.request().url().url());
			}
			callback.onResponse(response);
		} finally {
			if (response != null) {
				response.close();
			}
		}
	}

	public static <T> HttpResult<T> httpGet(String url, HttpSendCallback<T> callback) {
		HttpClient httpClient = getHttpClient();
		Request request = buildRequest(url);
		Response response = null;
		HttpResult<T> result = new HttpResult<T>();
		try {
			response = httpClient.execute(request);
			if (!response.isSuccessful()) {
				LOG.error("reqeust faild, code: {}.  message: {}. url: {}", 
						response.code(), response.message(), response.request().url().url());
			}
			result.setValue(callback.onResponse(response));
		} catch (Exception e) {
			result.setException(e);
			result.setHasException(true);
		} finally {
			if (response != null) {
				response.close();
			}
		}
		return result;
	}
	
	public static <T> HttpResult<T> httpPost(String url, HttpSendCallback<T> callback) {
		HttpClient httpClient = getHttpClient();
		FormBody body = new FormBody.Builder().build();
		Request request = buildRequest(url, body);
		Response response = null;
		HttpResult<T> result = new HttpResult<T>();
		try {
			response = httpClient.execute(request);
			if (!response.isSuccessful()) {
				LOG.error("reqeust faild, code: {}.  message: {}. url: {}", 
						response.code(), response.message(), response.request().url().url());
			}
			result.setValue(callback.onResponse(response));
		} catch (Exception e) {
			result.setException(e);
			result.setHasException(true);
		} finally {
			if (response != null) {
				response.close();
			}
		}
		return result;
	}

	public static void httpPost(String url, Map<String, String> params, HttpSendCallbackPure callback)
			throws Exception {
		HttpClient httpClient = getHttpClient();
		FormBody.Builder builder = new FormBody.Builder();
		if (params != null) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				builder.add(entry.getKey(), entry.getValue());
			}
		}
		FormBody body = builder.build();
		Request request = buildRequest(url, body);
		Response response = null;
		try {
			response = httpClient.execute(request);
			if (!response.isSuccessful()) {
				LOG.error("reqeust faild, code: {}.  message: {}. url: {}", 
						response.code(), response.message(), response.request().url().url());
			}
			callback.onResponse(response);
		} finally {
			if (response != null) {
				response.close();
			}
		}
	}

	@SuppressWarnings("rawtypes")
	public static <T> HttpResult<T> httpPost(String url, Map params, HttpSendCallback<T> callback) {
		HttpClient httpClient = getHttpClient();
		FormBody.Builder builder = new FormBody.Builder();
		if (params != null) {
			Set keys = params.keySet();
			for (Object key : keys) {
				builder.add(String.valueOf(key), String.valueOf(params.get(key)));
			}
		}
		FormBody body = builder.build();
		Request request = buildRequest(url, body);
		Response response = null;
		HttpResult<T> result = new HttpResult<T>();
		try {
			response = httpClient.execute(request);
			if (!response.isSuccessful()) {
				LOG.error("reqeust faild, code: {}.  message: {}. url: {}", 
						response.code(), response.message(), response.request().url().url());
			}
			result.setValue(callback.onResponse(response));
		} catch (Exception e) {
			result.setException(e);
			result.setHasException(true);
		} finally {
			if (response != null) {
				response.close();
			}
		}
		return result;
	}

	public static <T, PARAMS> HttpResult<T> httpPost(String url, PARAMS params, HttpSendCallback<T> callback) {
		HttpClient httpClient = getHttpClient();
		MediaType mediaType = MediaType.parse("application/json;charset=UTF-8");
		String paramStr = JSON.toJSONString(params);
		RequestBody body = RequestBody.create(mediaType, paramStr);
		Request request = buildRequest(url, body);
		Response response = null;
		HttpResult<T> result = new HttpResult<T>();
		try {
			response = httpClient.execute(request);
			if (!response.isSuccessful()) {
				LOG.error("reqeust faild, code: {}.  message: {}. url: {}", 
						response.code(), response.message(), response.request().url().url());
			}
			result.setValue(callback.onResponse(response));
		} catch (Exception e) {
			result.setException(e);
			result.setHasException(true);
		} finally {
			if (response != null) {
				response.close();
			}
		}
		return result;
	}
	
	public static <T, PARAMS> HttpResult<T> httpPost(String url, String jsonStr, HttpSendCallback<T> callback) {
		HttpClient httpClient = getHttpClient();
		MediaType mediaType = MediaType.parse("application/json;charset=UTF-8");
		RequestBody body = RequestBody.create(mediaType, jsonStr);
		Request request = buildRequest(url, body);
		Response response = null;
		HttpResult<T> result = new HttpResult<T>();
		try {
			response = httpClient.execute(request);
			if (!response.isSuccessful()) {
				LOG.error("reqeust faild, code: {}.  message: {}. url: {}", 
						response.code(), response.message(), response.request().url().url());
			}
			result.setValue(callback.onResponse(response));
		} catch (Exception e) {
			result.setException(e);
			result.setHasException(true);
		} finally {
			if (response != null) {
				response.close();
			}
		}
		return result;
	}

	private static Request buildRequest(String url, RequestBody body) {
		Builder builder = new Request.Builder().url(url);
//		builder.header("User-Agent", userAgent);
		if (body != null) {
			builder.post(body);
		}
		return builder.build();
	}

	private static Request buildRequest(String url) {
		return buildRequest(url, null);
	}

	public static final HttpClient getHttpClient() {
		return HttpClient.getHttpClient();
		// return HttpClient.getHttpClient().addOInterceptor(new
		// DoubanInterceptor());
	}
	
	public static OkHttpClient getOkHttpClient() {
		return HttpClient.getOkHttpClient();
	}
	/**
	 * 获取Post方式 的json数据
	 * @param url
	 * @param params
	 * @return
	 */
	public static String getResultJsonByPost(String url,Map<String,String> params){
		HttpResult<String> httpPost = HttpUtils1.httpPost(url, params, new HttpSendCallback<String>() {

			@Override
			public String onResponse(Response response) throws Exception {
				if (!response.isSuccessful()) {
					throw new BusinessException("reqeust faild, code: " + response.code() + ". message: "
							+ response.message() + ". url: " + response.request().url().url());
				}
				String value = response.body().string();
				return value;
			}

		});
		String value = "";
		try {
			value = httpPost.getValue();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		if(params != null){
			params.put("obj", value);
		}
		return value;
	}
	/**
	 * 获取Get方式 的json数据
	 * @param url
	 * @param params
	 * @return
	 */
	public static String getResultJsonByGet(String url,Map<String,String> params){
		String value = "";
		try {
			url = BizUtils1.buildUrl(url, params);
			HttpResult<String> httpPost = HttpUtils1.httpGet(url, new HttpSendCallback<String>() {
	
				@Override
				public String onResponse(Response response) throws Exception {
					if (!response.isSuccessful()) {
						throw new BusinessException("reqeust faild, code: " + response.code() + ". message: "
								+ response.message() + ". url: " + response.request().url().url());
					}
					String value = response.body().string();
					return value;
				}
	
			});
			value = httpPost.getValue();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		if(params != null){
			params.put("obj", value);
		}
		return value;
	}

}
