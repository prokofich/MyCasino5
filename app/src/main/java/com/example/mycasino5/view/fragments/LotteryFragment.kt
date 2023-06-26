package com.example.mycasino5.view.fragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.mycasino5.R
import com.example.mycasino5.constant.*
import com.example.mycasino5.viewmodel.LotteryViewModel
import kotlinx.android.synthetic.main.fragment_lottery.*
import kotlinx.android.synthetic.main.fragment_lottery.view.*
import java.time.LocalDate


class LotteryFragment : Fragment() {

    var currentDate = ""
    var listUrlImageForLottery = emptyList<String>()
    var cashWin = 0

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        id_lottery_tv_cash.text = MAIN.getMyCash().toString()

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_lottery, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val lotteryViewModel = ViewModelProvider(this)[LotteryViewModel::class.java]

        lotteryViewModel.Text.observe(viewLifecycleOwner){ TEXT ->
            id_lottery_text.text = TEXT.body()!!.text
        }

        currentDate = LocalDate.now().toString()

        loadImage(id_lottery_img_card_suit, url_image_emblema_lottery)
        loadImage(id_lottery_img_cash,url_image_cash)

        if(lotteryViewModel.checkLastDay(currentDate)){

            lotteryViewModel.getTextLottery1()
            listUrlImageForLottery = lotteryViewModel.getListRandomUrlImageSuit()
            loadImage(id_lottery_img1,listUrlImageForLottery[0])
            loadImage(id_lottery_img2,listUrlImageForLottery[1])
            loadImage(id_lottery_img3,listUrlImageForLottery[2])
            loadImage(id_lottery_img4,listUrlImageForLottery[3])
            loadImage(id_lottery_img5,listUrlImageForLottery[4])
            loadImage(id_lottery_img6,listUrlImageForLottery[5])
            loadImage(id_lottery_img7,listUrlImageForLottery[6])
            loadImage(id_lottery_img8,listUrlImageForLottery[7])
            loadImage(id_lottery_img9,listUrlImageForLottery[8])

        }else{

            lotteryViewModel.getTextLottery2()
            id_lottery_window.isVisible = false
            id_lottery_image_zamok.isVisible = true
            loadImage(id_lottery_image_zamok,url_image_lock)

        }

        id_lottery_image_zamok.setOnClickListener {
            showToast("wait for the next day")
        }

        id_lottery_img1.setOnClickListener {

            cashWin = lotteryViewModel.getRandomCashWin()
            clickToSuit( id_lottery_img1,id_lottery_img_cash1,id_lottery_tv_cash1)
            lotteryViewModel.getTextLottery2()
            allImageIsEnabled()

        }

        id_lottery_img2.setOnClickListener {

            cashWin = lotteryViewModel.getRandomCashWin()
            clickToSuit( id_lottery_img2,id_lottery_img_cash2,id_lottery_tv_cash2)
            lotteryViewModel.getTextLottery2()
            allImageIsEnabled()

        }

        id_lottery_img3.setOnClickListener {

            cashWin = lotteryViewModel.getRandomCashWin()
            clickToSuit( id_lottery_img3,id_lottery_img_cash3,id_lottery_tv_cash3)
            lotteryViewModel.getTextLottery2()
            allImageIsEnabled()

        }

        id_lottery_img4.setOnClickListener {

            cashWin = lotteryViewModel.getRandomCashWin()
            clickToSuit( id_lottery_img4,id_lottery_img_cash4,id_lottery_tv_cash4)
            lotteryViewModel.getTextLottery2()
            allImageIsEnabled()

        }

        id_lottery_img5.setOnClickListener {

            cashWin = lotteryViewModel.getRandomCashWin()
            clickToSuit( id_lottery_img5,id_lottery_img_cash5,id_lottery_tv_cash5)
            lotteryViewModel.getTextLottery2()
            allImageIsEnabled()

        }

        id_lottery_img6.setOnClickListener {

            cashWin = lotteryViewModel.getRandomCashWin()
            clickToSuit( id_lottery_img6,id_lottery_img_cash6,id_lottery_tv_cash6)
            lotteryViewModel.getTextLottery2()
            allImageIsEnabled()

        }

        id_lottery_img7.setOnClickListener {

            cashWin = lotteryViewModel.getRandomCashWin()
            clickToSuit( id_lottery_img7,id_lottery_img_cash7,id_lottery_tv_cash7)
            lotteryViewModel.getTextLottery2()
            allImageIsEnabled()

        }

        id_lottery_img8.setOnClickListener {

            cashWin = lotteryViewModel.getRandomCashWin()
            clickToSuit( id_lottery_img8,id_lottery_img_cash8,id_lottery_tv_cash8)
            lotteryViewModel.getTextLottery2()
            allImageIsEnabled()

        }

        id_lottery_img9.setOnClickListener {

            cashWin = lotteryViewModel.getRandomCashWin()
            clickToSuit( id_lottery_img9,id_lottery_img_cash9,id_lottery_tv_cash9)
            lotteryViewModel.getTextLottery2()
            allImageIsEnabled()

        }







    }


    private fun loadImage(idImageView:ImageView,url_image:String){
        Glide.with(requireContext())
            .load(url_image)
            .into(idImageView)
    }

    private fun showToast(message:String){
        Toast.makeText(requireContext(),message,Toast.LENGTH_SHORT).show()
    }

    private fun clickToSuit(idLotImg:ImageView,idLotCashImg:ImageView,idLotCashTv:TextView){
        idLotImg.isVisible = false
        MAIN.addCash(cashWin)
        MAIN.setCurrentDay(currentDate)
        id_lottery_tv_cash.text = MAIN.getMyCash().toString()
        showToast("+$cashWin$!!!")
        idLotCashImg.isVisible = true
        idLotCashTv.isVisible = true
        idLotCashTv.text = "$cashWin$"
        loadImage(idLotCashImg, url_image_cash)
    }

    private fun allImageIsEnabled(){
        id_lottery_img1.isEnabled = false
        id_lottery_img2.isEnabled = false
        id_lottery_img3.isEnabled = false
        id_lottery_img4.isEnabled = false
        id_lottery_img5.isEnabled = false
        id_lottery_img6.isEnabled = false
        id_lottery_img7.isEnabled = false
        id_lottery_img8.isEnabled = false
        id_lottery_img9.isEnabled = false
    }

}