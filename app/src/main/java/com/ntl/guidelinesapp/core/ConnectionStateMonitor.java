package com.ntl.guidelinesapp.core;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.widget.Toast;

public class ConnectionStateMonitor extends ConnectivityManager.NetworkCallback {

    final NetworkRequest networkRequest;
    final Context context;

    public ConnectionStateMonitor(Context context) {
        networkRequest = new NetworkRequest.Builder()
                .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
                .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                .build();
        this.context = context;
    }

    public void enable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        connectivityManager.registerNetworkCallback(networkRequest, this);
    }

    // Likewise, you can have a disable method that simply calls ConnectivityManager.unregisterNetworkCallback(NetworkCallback) too.

    @Override
    public void onAvailable(Network network) {
        // Do what you need to do here
        Toast.makeText(context, "Internet Connected 2", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onUnavailable() {
        Toast.makeText(context, "Internet Disconnected 2", Toast.LENGTH_LONG).show();
        super.onUnavailable();
    }
}
