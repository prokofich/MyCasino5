package com.example.mycasino5.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycasino5.api.Repository
import com.example.mycasino5.model.ResponceWebView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class SplashViewModel:ViewModel() {

    var repo = Repository()
    var webViewUrl: MutableLiveData<Response<ResponceWebView>> = MutableLiveData()

    fun setPostParametersPhone(phone_name:String,locale:String,unique:String){
        viewModelScope.launch(Dispatchers.IO) {
            val response = repo.setParametersPhone(phone_name, locale, unique)
            withContext(Dispatchers.Main){
                webViewUrl.value = response
            }
        }
    }

}