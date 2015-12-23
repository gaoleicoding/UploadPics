package com.example.administrator.uploadphoto;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class NetRequest {
	private MyInterface.NetRequestIterface netRequestIterface;
	private Context context;

	public NetRequest(MyInterface.NetRequestIterface netRequestIterface, Context context) {
		this.netRequestIterface = netRequestIterface;
		this.context = context;
	}
	/**
	 * 网络请求用的是OKHttp，这个开源项目的好处是1.Android 6.0后不支持HttpClient请求，而它使用HttpUrlConnection 2.默认支持https
	 */
	public void httpRequest(Map<String, Object> map, final String requestUrl) {
		if (!CommonUtils.getUtilInstance().isConnectingToInternet(context)) {
			Toast.makeText(context,
					context.getString(R.string.internet_fail_connect),Toast.LENGTH_LONG).show();
			return;
		}

		OkHttpClient mOkHttpClient = new OkHttpClient();
		FormEncodingBuilder builder = new FormEncodingBuilder();
		if (null != map && !map.isEmpty())
			for (String key : map.keySet()) {
				builder.add(key, map.get(key)+"");
			}
		if(UserInfoUtil.getInstance().getAuthKey()!=null) {
			builder.add("authKey", UserInfoUtil.getInstance().getAuthKey());
		}
		Log.d("gaolei", " authKey------------------"+UserInfoUtil.getInstance().getAuthKey());
		Request request = new Request.Builder()
				.url(requestUrl)
				.post(builder.build())
				.build();
		try {
			mOkHttpClient.setConnectTimeout(5000, TimeUnit.MILLISECONDS);

			mOkHttpClient.newCall(request).enqueue(new Callback() {
				@Override
				public void onFailure(Request request, IOException e) {
//					Log.d("gaolei", "NetRequest-----------onFailure----------------" + e.getMessage());
					netRequestIterface.exception(e, requestUrl);
				}

				@Override
				public void onResponse(final Response response) throws IOException {
					String result = response.body().string();
//					Log.d("gaolei", "onResponse----------------------" + result);
					netRequestIterface.changeView(result, requestUrl);
				}
			});
		}catch (Exception e){

		}
	}
}
