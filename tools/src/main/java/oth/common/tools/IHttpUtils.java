package oth.common.tools;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qzsoft.common.inner.http.HttpClient;
import com.qzsoft.common.inner.http.HttpUtils1;

import okhttp3.OkHttpClient;
import oth.common.http.HttpResult;
import oth.common.http.callback.HttpSendCallback;
import oth.common.http.callback.HttpSendCallbackPure;
import oth.common.ui.RequestResult;

public class IHttpUtils {
	private static final Logger LOG = LoggerFactory.getLogger(IHttpUtils.class);
	private static final String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36";

	public static void httpGet(String url, HttpSendCallbackPure callback) throws Exception {
		HttpUtils1.httpGet(url, callback);
	}

	public static <T> HttpResult<T> httpGet(String url, HttpSendCallback<T> callback) {
		return HttpUtils1.httpGet(url, callback);
	}
	
	public static <T> HttpResult<T> httpPost(String url, HttpSendCallback<T> callback) {
		return HttpUtils1.httpPost(url, callback);
	}

	public static void httpPost(String url, Map<String, String> params, HttpSendCallbackPure callback)
			throws Exception {
		HttpUtils1.httpPost(url, params, callback);
	}

	public static <T> HttpResult<T> httpPost(String url, Map params, HttpSendCallback<T> callback) {
		return HttpUtils1.httpPost(url, params, callback);
	}

	public static <T, PARAMS> HttpResult<T> httpPost(String url, PARAMS params, HttpSendCallback<T> callback) {
		return HttpUtils1.httpPost(url, params, callback);
	}
	
	public static <T, PARAMS> HttpResult<T> httpPost(String url, String jsonStr, HttpSendCallback<T> callback) {
		return HttpUtils1.httpPost(url, jsonStr, callback);
	}

	public static final HttpClient getHttpClient() {
		return HttpUtils1.getHttpClient();
	}
	public static String getResultJsonByPost(String url,Map<String,String> params){
		return HttpUtils1.getResultJsonByPost(url, params);
	}
	public static String getResultJsonByGet(String url,Map<String,String> params){
		return HttpUtils1.getResultJsonByGet(url, params);
	}
	public static String httpUpload(String uploadUrl, byte[] bytes, Map<String, String> params, String suffix) throws IOException {
		return HttpUtils1.httpUpload(uploadUrl, bytes, params, suffix);
	}
}
