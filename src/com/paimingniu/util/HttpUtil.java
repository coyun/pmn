package com.paimingniu.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpUtil {
	public static final int TIMEOUT = 60000;
	private static CloseableHttpClient httpClient = HttpClients.createDefault();

	public static void closs() {
		try {
			httpClient.close();
			httpClient = null;
		} catch (IOException localIOException) {
		}
	}

	public static String post(String url, Map<String, String> params)
			throws ClientProtocolException, IOException {
		HttpPost post = postForm(url, params);

		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(60000).setConnectTimeout(60000).build();
		post.setConfig(requestConfig);
		return sendRequest(httpClient, post);
	}

	public static String post(int timeout, String url,
			Map<String, String> params) throws ClientProtocolException,
			IOException {
		HttpPost post = postForm(url, params);

		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(timeout).setConnectTimeout(timeout).build();
		post.setConfig(requestConfig);
		return sendRequest(httpClient, post);
	}

	public static String get(String url) throws ClientProtocolException,
			IOException {
		HttpGet get = new HttpGet(url);
		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(60000).setConnectTimeout(60000).build();
		get.setConfig(requestConfig);
		return sendRequest(httpClient, get);
	}

	public static String get(int timeout, String url)
			throws ClientProtocolException, IOException {
		HttpGet get = new HttpGet(url);
		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(timeout).setConnectTimeout(timeout).build();
		get.setConfig(requestConfig);
		return sendRequest(httpClient, get);
	}

	public static HttpPost postForm(String url, Map<String, String> params) {
		HttpPost httpost = new HttpPost(url);
		List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();

		Iterator<?> it = params.entrySet().iterator();

		while (it.hasNext()) {
			Map.Entry<String, String> entry = (Map.Entry<String, String>) it
					.next();
			String key = entry.getKey();
			String val = entry.getValue();
			nvps.add(new BasicNameValuePair(key, val));
		}

		httpost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));

		return httpost;
	}

	public static String sendRequest(CloseableHttpClient httpclient,
			HttpUriRequest httpost) throws ClientProtocolException, IOException {
		return ((String) httpclient.execute(httpost,
				new ResponseHandler<String>() {
					public String handleResponse(final HttpResponse response)
							throws IOException {
						HttpEntity entity = response.getEntity();
						return entity != null ? EntityUtils.toString(entity)
								: null;
					}

				}));
	}
}