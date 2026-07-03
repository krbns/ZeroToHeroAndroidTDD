package ru.easycode

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface MonitorConnection {

    fun connectedFlow(): StateFlow<Boolean>

    @RequiresApi(Build.VERSION_CODES.M)
    class Base(
        context: Context
    ) : MonitorConnection {

        @RequiresApi(Build.VERSION_CODES.M)
        private val connectivityManager =
            context.getSystemService(ConnectivityManager::class.java)

        private val _connected = MutableStateFlow(isConnected())

        override fun connectedFlow(): StateFlow<Boolean> = _connected

        private val callback = object : ConnectivityManager.NetworkCallback() {

            override fun onAvailable(network: Network) {
                _connected.value = true
            }

            override fun onLost(network: Network) {
                _connected.value = isConnected()
            }
        }

        init {
            connectivityManager.registerDefaultNetworkCallback(callback)
        }

        private fun isConnected(): Boolean {
            val network = connectivityManager.activeNetwork ?: return false
            val capabilities =
                connectivityManager.getNetworkCapabilities(network) ?: return false

            return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        }
    }
}