package com.example.mycasino5.view.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.example.mycasino5.R
import com.example.mycasino5.constant.*
import com.example.mycasino5.view.fragments.GameFragment
import com.example.mycasino5.view.fragments.LotteryFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val gameFragment = GameFragment()
    private val lotteryFragment = LotteryFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //полноэкранный режим
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        MAIN = this

        //первый фрагмент для показа
        replaceFragment(lotteryFragment)

        //слушатель для нижнего меню приложения
        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.id_menu_lottery ->replaceFragment(lotteryFragment)
                R.id.id_menu_game ->replaceFragment(gameFragment)
            }
            true
        }



    }

    override fun onBackPressed() {
        super.onBackPressed()

        finishAffinity()

    }


    //функция замены->показа фрагмента
    private fun replaceFragment(fragment: Fragment){
        if(fragment!=null){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container,fragment)
            transaction.commit()
        }
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

    //функция получения имени юзера
    fun getMyName(): String {
        val preferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE).getString(MY_NAME,"")
        return preferences ?: ""
    }


    //функция получения последнего дня,когда было активно приложение
    fun getLastDay():String{
        val preferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE).getString(LAST_DAY,"")
        return preferences ?: ""
    }

    //функция обновления последнего дня,когда было активно приложение
    fun setCurrentDay(day:String){
        val preferences = getSharedPreferences(APP_PREFERENCES,Context.MODE_PRIVATE)
        preferences.edit()
            .putString(LAST_DAY,day)
            .apply()
    }

    //функция получения выигранных денег
    fun getMyCash(): Int {
        return getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE).getInt(MY_CASH, 0)
    }

    //функция добавления денег
    fun addCash(cash:Int){
        val preferences = getSharedPreferences(APP_PREFERENCES,Context.MODE_PRIVATE).getInt(MY_CASH,0) + cash
        val pref = getSharedPreferences(APP_PREFERENCES,Context.MODE_PRIVATE)
        pref.edit()
            .putInt(MY_CASH,preferences)
            .apply()
    }

    //функция оплачивания деньгами
    fun minusCash(cash: Int){
        val preferences = getSharedPreferences(APP_PREFERENCES,Context.MODE_PRIVATE).getInt(MY_CASH,0) - cash
        val pref = getSharedPreferences(APP_PREFERENCES,Context.MODE_PRIVATE)
        pref.edit()
            .putInt(MY_CASH,preferences)
            .apply()
    }



}