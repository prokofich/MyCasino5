package com.example.mycasino5.view.activity

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.mycasino5.R
import com.example.mycasino5.constant.APP_PREFERENCES
import com.example.mycasino5.constant.ID
import com.example.mycasino5.constant.MY_NAME
import com.example.mycasino5.constant.url_image_splash
import com.example.mycasino5.viewmodel.SplashViewModel
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*

class SplashActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        loadSplashImg()

        val splashViewModel = ViewModelProvider(this)[SplashViewModel::class.java]

        var namePhone = Build.MODEL.toString()
        var locale = Locale.getDefault().getDisplayLanguage().toString()
        var id = ""

        if (getMyId()!=""){
            id = getMyId()
        }else{
            id = UUID.randomUUID().toString()
            setMyId(id)
        }

        splashViewModel.setPostParametersPhone(namePhone,locale,id)

        splashViewModel.webViewUrl.observe(this){ responce ->
            when(responce.body()!!.url){
                "no" -> { goToMainPush() }
                "nopush" -> { goToMainNoPush() }
                else -> { goToWeb(responce.body()!!.url) }
            }
        }



    }
    //функция получения имени
    fun getMyName(): String {
        val preferences = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE).getString(MY_NAME,"")
        return preferences ?: ""
    }

    private fun loadSplashImg(){
        Glide.with(this)
            .load(url_image_splash)
            .into(id_splash_img)
    }

    fun getMyId():String{
        var preferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE).getString(ID,"")
        return preferences ?: ""
    }

    fun setMyId(id:String){
        var preferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        preferences.edit()
            .putString(ID,id)
            .apply()
    }

    fun goToMainPush() {
        CoroutineScope(Dispatchers.Main).launch {
            delay(4000)
            if(getMyName()!=""){
                var intent = Intent(this@SplashActivity,MainActivity::class.java)
                startActivity(intent)
            }else{
                var intent = Intent(this@SplashActivity,RegistrationActivity::class.java)
                startActivity(intent)
            }

        }
    }

    fun goToMainNoPush() {
        CoroutineScope(Dispatchers.Main).launch {
            delay(4000)
            if(getMyName()!=""){
                var intent = Intent(this@SplashActivity,MainActivity::class.java)
                intent.putExtra("url","nopush")
                startActivity(intent)
            }else{
                var intent = Intent(this@SplashActivity,RegistrationActivity::class.java)
                intent.putExtra("url","nopush")
                startActivity(intent)
            }

        }
    }

    fun goToWeb(url:String) {
        CoroutineScope(Dispatchers.Main).launch {
            delay(4000)
            var intent = Intent(this@SplashActivity,WebViewActivity::class.java)
            intent.putExtra("url",url)
            startActivity(intent)
        }
    }

}