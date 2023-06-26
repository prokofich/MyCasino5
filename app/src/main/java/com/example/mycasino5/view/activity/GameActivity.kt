package com.example.mycasino5.view.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.mycasino5.R
import com.example.mycasino5.constant.*
import com.example.mycasino5.view.fragments.GamePlayFragment
import com.example.mycasino5.view.fragments.LoadingGameFragment

class GameActivity : AppCompatActivity() {

    lateinit var navController: NavController
    var myBet = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        GAME_ACTIVITY = this
        navController = Navigation.findNavController(this,R.id.id_nav_host)

        myBet = intent.getIntExtra(MY_BET,0)

    }

    override fun onBackPressed() {
        super.onBackPressed()

        val fragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        if (fragment is GamePlayFragment) {
            // Обработка нажатия на кнопку "Назад" внутри фрагмента
            fragment.handleBackPressed()
        } else {
            super.onBackPressed()
        }

    }

    //функция получения имени
    fun getMyName(): String {
        val preferences = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE).getString(MY_NAME,"")
        return preferences ?: ""
    }

    //функция получения пола
    fun getMySex(): String {
        val preferences = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE).getString(MY_SEX,"")
        return preferences ?: ""
    }

    //функция добавления денег
    fun addCash(cash:Int){
        val preferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE).getInt(MY_CASH,0) + cash
        val pref = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        pref.edit()
            .putInt(MY_CASH,preferences)
            .apply()
    }

    //функция получения количества побед
    fun getMyNumberWins(): Int {
        return getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE).getInt(MY_NUMBER_WINS, 0)
    }

    //функция обновления количества побед
    fun updateNumberWins(){
        val number = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE).getInt(MY_NUMBER_WINS, 0)
        val pref = getSharedPreferences(APP_PREFERENCES,Context.MODE_PRIVATE)
        pref.edit()
            .putInt(MY_NUMBER_WINS,number+1)
            .apply()
    }

}