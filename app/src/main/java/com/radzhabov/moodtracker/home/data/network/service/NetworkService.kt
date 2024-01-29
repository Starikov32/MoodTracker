package com.radzhabov.moodtracker.home.data.network.service

import android.util.Log
import com.radzhabov.moodtracker.home.data.network.api.WeatherApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.lang.Exception


class NetworkService private constructor() {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(URL_API)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val weatherApi: WeatherApi
        get() = retrofit.create(WeatherApi::class.java)

    companion object {
        private const val URL_API = "https://api.weatherapi.com/v1/"
        private const val EXCEPTION = "Api returns nothing"
        private const val LOG_TAG = "NetworkService"

        @Volatile
        private var instance: NetworkService? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: NetworkService().also { instance = it }
            }

        suspend fun <T> handleCall(call: Call<T>): T? = withContext(Dispatchers.Default) {
            return@withContext try {
                Log.d("API", call.request().url.toString())
                val response = call.execute()
                if (response.isSuccessful) {
                    return@withContext response.body()
                } else {
                    throw IllegalArgumentException(EXCEPTION)
                }
            } catch (e: IOException) {
                val message = "Network error: ${e.localizedMessage}"
                Log.e(LOG_TAG, message)
                null
            } catch (e: HttpException) {
                val message = "HTTP error: ${e.localizedMessage}"
                Log.e(LOG_TAG, message)
                null
            } catch (e: IllegalArgumentException) {
                val message = "Invalid response: ${e.localizedMessage}"
                Log.e(LOG_TAG, message)
                null
            }
        }
    }
}
