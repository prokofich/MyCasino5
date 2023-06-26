package com.example.mycasino5.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycasino5.api.Repository
import com.example.mycasino5.model.ResponseText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class GameFragmentViewModel:ViewModel() {

    val repo = Repository()
    val Text: MutableLiveData<Response<ResponseText>> = MutableLiveData()

    fun getTextGameFragment(){
        viewModelScope.launch(Dispatchers.IO) {
            val responce = repo.getTextGameFragment()
            withContext(Dispatchers.Main){
                Text.value = responce
            }
        }
    }

}