package com.fnspl.hiplaretails.networkApi;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;


public class NetworkConnection {

	private Context mContext;
	private ConnectivityManager cm;

	public NetworkConnection(Context context) {
		// TODO Auto-generated constructor stub
		mContext = context;
		cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
	}

	public boolean isNetworkConnected() {
		NetworkInfo activeNetworkInfo = cm.getActiveNetworkInfo();
		return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}

	public boolean isNetworkConnectingOrConnected() {

		NetworkInfo activeNetworkInfo = cm.getActiveNetworkInfo();
		return activeNetworkInfo != null
				&& activeNetworkInfo.isConnectedOrConnecting();
	}

	public boolean isActive(){

		if (isNetworkConnected()){
			return true;
		} else if (isNetworkConnectingOrConnected()) {
			Toast.makeText(
					mContext,
					"Connecting...try again shortly",
					Toast.LENGTH_LONG).show();
		} else {
			Toast.makeText(mContext,
					"No internet connection", Toast.LENGTH_LONG)
					.show();

		}
		return false;
	}


}
