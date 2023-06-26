package com.example.mycasino5.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycasino5.api.Repository
import com.example.mycasino5.model.ResponseText
import com.example.mycasino5.model.TextLoading
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class LoadingViewModel:ViewModel() {

    val repo = Repository()
    val Text: MutableLiveData<Response<TextLoading>> = MutableLiveData()

    fun getTextPravila(){
        viewModelScope.launch(Dispatchers.IO) {
            val responce = repo.getTextPravila()
            withContext(Dispatchers.Main){
                Text.value = responce
            }
        }
    }



}