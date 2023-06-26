package com.example.mycasino5.view.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.mycasino5.R
import com.example.mycasino5.constant.*
import kotlinx.android.synthetic.main.activity_registration.*

class RegistrationActivity : AppCompatActivity() {

    var my_sex = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        loadImage(id_reg_img_suit, url_image_reg_suit)

        id_reg_radio.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId != -1) {

                val radioButton: RadioButton = findViewById(checkedId)
                my_sex = radioButton.text.toString()

            }
        }

        id_reg_button_next.setOnClickListener {
            if(id_reg_et.text.toString()!=""){
                if(my_sex!=""){
                    setMySex(my_sex)
                    setMyName(id_reg_et.text.toString())
                    var intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                }else{
                   Toast.makeText(this,"you haven't chosen your gender",Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this,"you didn't enter a name",Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        //выход
        finishAffinity()

    }

    private fun loadImage(idImageView: ImageView, url_image:String){
        Glide.with(this)
            .load(url_image)
            .into(idImageView)
    }

    //функция устаноки пола
    fun setMySex(sex:String){
        val pref = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        pref.edit()
            .putString(MY_SEX,sex)
            .apply()
    }

    //функция устаноки имени
    fun setMyName(name:String){
        val pref = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        pref.edit()
            .putString(MY_NAME,name)
            .apply()
    }

}