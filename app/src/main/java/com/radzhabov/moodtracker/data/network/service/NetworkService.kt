package com.radzhabov.moodtracker.data.network.service

import android.util.Log
import com.radzhabov.moodtracker.data.network.api.WeatherApi
import com.radzhabov.moodtracker.domain.util.Constants.Companion.EXCEPTION
import com.radzhabov.moodtracker.domain.util.Constants.Companion.LOG_TAG
import com.radzhabov.moodtracker.domain.util.Constants.Companion.URL_API
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception


class NetworkService private constructor() {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(URL_API)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val weatherApi: WeatherApi
        get() = retrofit.create(WeatherApi::class.java)

    companion object {

        @Volatile
        private var instance: NetworkService? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: NetworkService().also { instance = it }
            }

        suspend fun <T> handleCall(call: Call<T>): T? = withContext(Dispatchers.Default) {
            return@withContext try {
                val response = call.execute()
                if (response.isSuccessful) {
                    return@withContext response.body()
                } else {
                    throw IllegalArgumentException(EXCEPTION)
                }
            } catch (exception: Exception) {
                val message = "Error executing the request: ${exception.localizedMessage}"
                Log.e(LOG_TAG, message)
                null
            }
        }
    }
}
