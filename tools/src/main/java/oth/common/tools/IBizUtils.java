package oth.common.tools;

import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qzsoft.common.inner.BizUtils1;

import okhttp3.MediaType;
import oth.common.ui.RequestResult;

public class IBizUtils {

	private static final Logger LOG = LoggerFactory.getLogger(IBizUtils.class);

	private static MediaType streamMedia = MediaType.parse("application/octet-stream");

	public static <T, REQUEST> RequestResult<T> getByForm(String url, REQUEST request, Class<T> responseClass) {
		return BizUtils1.getByForm(url, request, responseClass);
	}

	public static <T, REQUEST> RequestResult<T> postByForm(String url, REQUEST request, Class<T> responseClass) {
		return BizUtils1.postByForm(url, request, responseClass);
	}

	public static <T, REQUEST> RequestResult<T> postByJson(String url, REQUEST request, Class<T> responseClass) {
		return BizUtils1.postByJson(url, request, responseClass);
	}

	public static <REQUEST> String buildUrl(String url, REQUEST request)
			throws IllegalArgumentException, IllegalAccessException, UnsupportedEncodingException {
		return BizUtils1.buildUrl(url, request);
	}
}
