package com.example.mycasino5.api

import com.example.mycasino5.model.ResponceWebView
import com.example.mycasino5.model.ResponseText
import com.example.mycasino5.model.TextLoading
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("splash.php")
    suspend fun setPostParametersPhone(
        @Field("phone_name") phone_name:String,
        @Field("locale") locale:String,
        @Field("unique") unique:String
    ): Response<ResponceWebView>


    @GET("12/textLottery1.json")
    suspend fun getTextForLottery1():Response<ResponseText>

    @GET("12/textLottery2.json")
    suspend fun getTextForLottery2():Response<ResponseText>

    @GET("12/textGameFragment.json")
    suspend fun getTextGameFragment():Response<ResponseText>

    @GET("12/textLoadingPravila.json")
    suspend fun getTextPravila():Response<TextLoading>


}