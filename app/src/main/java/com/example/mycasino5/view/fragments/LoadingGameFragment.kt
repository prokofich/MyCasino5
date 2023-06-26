package com.example.mycasino5.view.fragments

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.mycasino5.R
import com.example.mycasino5.constant.*
import com.example.mycasino5.view.activity.MainActivity
import com.example.mycasino5.viewmodel.LoadingViewModel
import kotlinx.android.synthetic.main.fragment_loading_game.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoadingGameFragment : Fragment() {

    var nameOpponent = ""
    var urlImageOpponent = ""
    var myIndex = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_loading_game, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        id_loading_tv_1.text = GAME_ACTIVITY.getMyName()
        loadMyImg()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner){
            //ждем загрузки
        }

        id_loading_progress.max = 10000
        val finishProgress = 10000

        ObjectAnimator.ofInt(id_loading_progress,"progress",finishProgress)
            .setDuration(10000)
            .start()



        CoroutineScope(Dispatchers.Main).launch {
            delay(10000)
            var bundle = Bundle()
            bundle.putInt("index",myIndex)
            GAME_ACTIVITY.navController.navigate(R.id.action_loadingGameFragment_to_gamePlayFragment,bundle)
        }

        val loadingViewModel = ViewModelProvider(this)[LoadingViewModel::class.java]
        loadingViewModel.getTextPravila()

        loadingViewModel.Text.observe(viewLifecycleOwner){ TEXT ->
            id_loading_pravila1.text = TEXT.body()!![0].text
            id_loading_pravila2.text = TEXT.body()!![1].text
        }


        var randomIndex = listOf(0,1,2,3,4,5,6,7).shuffled()[1]
        myIndex = randomIndex

        id_loading_tv2.text = listNameManAndGirl[randomIndex]

        Glide.with(requireContext())
            .load(listUrlImageManAndGirl[randomIndex])
            .into(id_loading_img_2)





    }



    private fun loadMyImg(){
        when(GAME_ACTIVITY.getMySex()){
            MALE -> {
                Glide.with(requireContext())
                    .load(url_image_i_am_paren)
                    .into(id_loading_img_1)
            }
            FEMALE -> {
                Glide.with(requireContext())
                    .load(url_image_i_am_girl)
                    .into(id_loading_img_1)
            }
        }
    }


}