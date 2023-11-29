package com.example.myapplication.service

import com.example.myapplication.ForeCast
import com.example.myapplication.Utils
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {
    @GET ("forecast?")
    fun getcurrentweather(

        @Query("lat")
        lat:String,
        @Query("lon")
        lon:String,
        @Query("appid")
        appid:String=Utils.API_KEY


    ): Call<ForeCast>
    @GET ("forecast?")
    fun getWeatherByCity(

        @Query("q")
        city:String,
        @Query("appid")
        appid:String=Utils.API_KEY


    ): Call<ForeCast>







}