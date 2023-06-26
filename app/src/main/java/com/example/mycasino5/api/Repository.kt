package com.example.mycasino5.api

import com.example.mycasino5.model.ResponceWebView
import com.example.mycasino5.model.ResponseText
import com.example.mycasino5.model.TextLoading
import retrofit2.Response

class Repository {

    suspend fun setParametersPhone(phone_name:String,locale:String,unique:String): Response<ResponceWebView> {
        return RetrofitInstance.api.setPostParametersPhone(phone_name, locale, unique)
    }

    suspend fun getTextLottery1(): Response<ResponseText> {
        return RetrofitInstance.api.getTextForLottery1()
    }

    suspend fun getTextLottery2(): Response<ResponseText> {
        return RetrofitInstance.api.getTextForLottery2()
    }

    suspend fun getTextGameFragment(): Response<ResponseText> {
        return RetrofitInstance.api.getTextGameFragment()
    }

    suspend fun getTextPravila(): Response<TextLoading> {
        return RetrofitInstance.api.getTextPravila()
    }






}