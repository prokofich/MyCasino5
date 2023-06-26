package com.example.mycasino5.view.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import com.bumptech.glide.Glide
import com.example.mycasino5.R
import com.example.mycasino5.constant.*
import com.example.mycasino5.view.activity.MainActivity
import kotlinx.android.synthetic.main.fragment_game_over.*

class GameOverFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_game_over, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner){
            startActivity(Intent(requireContext(),MainActivity::class.java))
        }

        loadImgResult(requireArguments().getString(RESULT).toString())

        when(requireArguments().getString(RESULT).toString()){
            LOSS -> {
                id_gameover_tv1.text = "you've lost"
                id_gameover_tv2.text = "you have lost ${GAME_ACTIVITY.myBet}$"
            }
            WIN -> {
                id_gameover_tv2.text = "you get ${GAME_ACTIVITY.myBet*2}$"
                GAME_ACTIVITY.addCash(GAME_ACTIVITY.myBet*2)
                GAME_ACTIVITY.updateNumberWins()
            }
        }

        id_gameover_btn_finish.setOnClickListener {
            startActivity(Intent(requireContext(),MainActivity::class.java))
        }



    }

    private fun loadImgResult(result:String){
        when(result){
            LOSS -> {
                Glide.with(requireContext())
                    .load(url_image_smile)
                    .into(id_gameover_img)
            }
            WIN -> {
                Glide.with(requireContext())
                    .load(url_image_kubok)
                    .into(id_gameover_img)
            }
        }
    }


}