package com.example.mycasino5.view.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.addCallback
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.mycasino5.R
import com.example.mycasino5.constant.MAIN
import com.example.mycasino5.constant.MY_BET
import com.example.mycasino5.constant.url_image_cash
import com.example.mycasino5.constant.url_image_game_kresti
import com.example.mycasino5.view.activity.GameActivity
import com.example.mycasino5.viewmodel.GameFragmentViewModel
import kotlinx.android.synthetic.main.fragment_game.*
import kotlinx.android.synthetic.main.fragment_lottery.*
import kotlinx.coroutines.*


class GameFragment : Fragment() {

    var job1:Job = Job()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        id_game_tv_cash.text = MAIN.getMyCash().toString()
        id_game_tv_wins.text = "number of wins: ${MAIN.getMyNumberWins()}"

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val gameFragmentViewModel = ViewModelProvider(this)[GameFragmentViewModel::class.java]
        gameFragmentViewModel.getTextGameFragment()

        gameFragmentViewModel.Text.observe(viewLifecycleOwner){ TEXT ->
            id_game_tv_text.text = TEXT.body()!!.text
        }

        loadImage(id_game_img_card_suit, url_image_game_kresti)
        loadImage(id_game_img_cash, url_image_cash)

        id_game_button_play.setOnClickListener {
            if(id_game_et_money.text.toString()!=""){
                if(id_game_et_money.text.toString().toInt() <= MAIN.getMyCash()){

                    //можно сделать маленькую задержку
                    job1 = CoroutineScope(Dispatchers.Main).launch {
                        delay(500)
                        MAIN.minusCash(id_game_et_money.text.toString().toInt())
                        var intent = Intent(requireContext(),GameActivity::class.java)
                        intent.putExtra(MY_BET,id_game_et_money.text.toString().toInt())
                        startActivity(intent)
                        id_game_et_money.text.clear()
                    }

                }else{
                    showToast("you don't have enough funds")
                }
            }else{
                showToast("enter the amount you want to bet on your victory")
            }

        }




    }

    private fun loadImage(idImageView: ImageView, url_image:String){
        Glide.with(requireContext())
            .load(url_image)
            .into(idImageView)
    }

    private fun showToast(message:String){
        Toast.makeText(requireContext(),message, Toast.LENGTH_SHORT).show()
    }






}