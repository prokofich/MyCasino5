package com.example.mycasino5.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycasino5.api.Repository
import com.example.mycasino5.constant.MAIN
import com.example.mycasino5.constant.listUrlImageSuit
import com.example.mycasino5.constant.listWinCash
import com.example.mycasino5.model.ResponseText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class LotteryViewModel:ViewModel() {

    val repo = Repository()
    val Text: MutableLiveData<Response<ResponseText>> = MutableLiveData()

    fun getTextLottery1(){
        viewModelScope.launch(Dispatchers.IO) {
            val responce = repo.getTextLottery1()
            withContext(Dispatchers.Main){
                Text.value = responce
            }
        }
    }

    fun getTextLottery2(){
        viewModelScope.launch(Dispatchers.IO) {
            val responce = repo.getTextLottery2()
            withContext(Dispatchers.Main){
                Text.value = responce
            }
        }
    }



    fun checkLastDay(today:String): Boolean {
        return today!= MAIN.getLastDay()
    }

    fun getListRandomUrlImageSuit():List<String>{
        return listUrlImageSuit.shuffled()
    }

    fun getRandomCashWin():Int{
        return listWinCash.shuffled()[1]
    }




}