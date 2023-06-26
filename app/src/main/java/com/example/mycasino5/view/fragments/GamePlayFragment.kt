package com.example.mycasino5.view.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import com.bumptech.glide.Glide
import com.example.mycasino5.R
import com.example.mycasino5.constant.*
import com.example.mycasino5.view.activity.MainActivity
import kotlinx.android.synthetic.main.fragment_game_play.*
import kotlinx.android.synthetic.main.fragment_loading_game.*
import kotlinx.coroutines.*

class GamePlayFragment : Fragment() {

    var job1:Job = Job()

    var cardCurrentOp = ""
    var cardCurrentMy = ""

    var LISTALLCARDS = listAllCard.shuffled()
    var listMyCard = LISTALLCARDS.slice(0..17).toMutableList()
    var listOpponentCard = LISTALLCARDS.slice(18..35).toMutableList()


    var CardsInGameOpponent = mutableListOf<String>()
    var CardsInGameMy = mutableListOf<String>()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_game_play, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner){
            if(job1.isActive){
                job1.cancel()
            }
            startActivity(Intent(requireContext(),MainActivity::class.java))
        }

        id_gameplay_btn_finish.setOnClickListener {
            if(job1.isActive){
                job1.cancel()
            }
            var bundle = Bundle()
            bundle.putString(RESULT,LOSS)
            GAME_ACTIVITY.navController.navigate(R.id.action_gamePlayFragment_to_gameOverFragment,bundle)
        }



        //загрузка картинок////////////////////////////////////////////////////////////
        id_gameplay_tv_name_me.text = GAME_ACTIVITY.getMyName()
        loadMyImg()

        loadImgStopkaCard()

        id_gameplay_tv_name_op.text = listNameManAndGirl[requireArguments().getInt("index")]
        loadOpponentImg(requireArguments().getInt("index"))
        ///////////////////////////////////////////////////////////////////////////////

        //противник сразу кладет карту
        showCardOpponent(listOpponentCard[0])
        deleteAndInsertOpCard()



        //кидаю свою карту и происходит проверка
        id_gameplay_btn_putcard.setOnClickListener {

            job1 = CoroutineScope(Dispatchers.Main).launch {

                showCardMy(listMyCard[0])
                deleteAndInsertMyCard()

                loadImgZnakSravneniya()

                delay(3000)

                checkCard()


            }

        }











    }

    fun handleBackPressed(){
        startActivity(Intent(requireContext(), MainActivity::class.java))
    }

    private fun loadMyImg(){
        when(GAME_ACTIVITY.getMySex()){
            MALE -> {
                Glide.with(requireContext())
                    .load(url_image_i_am_paren)
                    .into(id_gameplay_img_me)
            }
            FEMALE -> {
                Glide.with(requireContext())
                    .load(url_image_i_am_girl)
                    .into(id_gameplay_img_me)
            }
        }
    }

    private fun loadOpponentImg(index:Int){
        Glide.with(requireContext())
            .load(listUrlImageManAndGirl[index])
            .into(id_gameplay_img_op)
    }

    private fun showCardOpponent(url:String){
        Glide.with(requireContext())
            .load(nameServer+url)
            .into(id_gameplay_img_card_op)
    }

    private fun showCardMy(url:String){
        Glide.with(requireContext())
            .load(nameServer+url)
            .into(id_gameplay_img_card_me)
    }

    private fun deleteAndInsertMyCard(){
        //получаю текущую карту
        cardCurrentMy = listMyCard[0]
        //удаляю первую карту у него
        listMyCard.removeAt(0)
        //добавляю карту в текущие карты
        CardsInGameMy.add(cardCurrentMy)
    }

    private fun deleteAndInsertOpCard(){
        //получаю текущую карту
        cardCurrentOp = listOpponentCard[0]
        //удаляю первую карту у него
        listOpponentCard.removeAt(0)
        //добавляю карту в текущие карты
        CardsInGameOpponent.add(cardCurrentOp)
    }

    //загрузка картинки
    private fun loadImgStopkaCard(){
        Glide.with(requireContext())
            .load(url_image_stopka)
            .into(id_gameplay_img_coloda_me)
        Glide.with(requireContext())
            .load(url_image_stopka)
            .into(id_gameplay_img_coloda_op)
    }

    //проверка карт
    private fun checkCard(){
        if(mapAllCard[cardCurrentMy]!! > mapAllCard[cardCurrentOp]!!){
            //победа
            listMyCard.addAll(CardsInGameMy)
            listMyCard.addAll(CardsInGameOpponent)
            cardCurrentMy = ""
            cardCurrentOp = ""
            CardsInGameMy.clear()
            CardsInGameOpponent.clear()
            id_gameplay_img_sravneniye.setImageDrawable(null)
            id_gameplay_img_card_me.setImageDrawable(null)
            id_gameplay_tv_count_card_me.text = listMyCard.size.toString()
            id_gameplay_tv_count_card_op.text = listOpponentCard.size.toString()
            if(listOpponentCard.size<=0){
                //конец игры победа
                var bundle = Bundle()
                bundle.putString(RESULT, WIN)
                GAME_ACTIVITY.navController.navigate(R.id.action_gamePlayFragment_to_gameOverFragment,bundle)
            }else{
                showCardOpponent(listOpponentCard[0])
                deleteAndInsertOpCard()
            }
        }else{
            if(mapAllCard[cardCurrentMy]!! < mapAllCard[cardCurrentOp]!!){
                //проигрыш
                listOpponentCard.addAll(CardsInGameOpponent)
                listOpponentCard.addAll(CardsInGameMy)
                cardCurrentMy = ""
                cardCurrentOp = ""
                CardsInGameMy.clear()
                CardsInGameOpponent.clear()
                id_gameplay_img_sravneniye.setImageDrawable(null)
                id_gameplay_img_card_me.setImageDrawable(null)
                id_gameplay_tv_count_card_me.text = listMyCard.size.toString()
                id_gameplay_tv_count_card_op.text = listOpponentCard.size.toString()
                if(listMyCard.size<=0){
                    //конец игры проигрыш
                    var bundle = Bundle()
                    bundle.putString(RESULT, LOSS)
                    GAME_ACTIVITY.navController.navigate(R.id.action_gamePlayFragment_to_gameOverFragment,bundle)
                }else{
                    showCardOpponent(listOpponentCard[0])
                    deleteAndInsertOpCard()
                }
            }else{
                //ничья
                if(listMyCard.size<=0){
                    var bundle = Bundle()
                    bundle.putString(RESULT, LOSS)
                    GAME_ACTIVITY.navController.navigate(R.id.action_gamePlayFragment_to_gameOverFragment,bundle)
                }else{
                    if(listOpponentCard.size<=0){
                        var bundle = Bundle()
                        bundle.putString(RESULT, WIN)
                        GAME_ACTIVITY.navController.navigate(R.id.action_gamePlayFragment_to_gameOverFragment,bundle)
                    }else{
                        showCardOpponent(listOpponentCard[0])
                        deleteAndInsertOpCard()
                        id_gameplay_img_card_me.setImageDrawable(null)
                    }
                }
            }
        }
    }




    //показ знака сравнения
    private fun loadImgZnakSravneniya(){
        if(mapAllCard[cardCurrentMy]!! > mapAllCard[cardCurrentOp]!!){
            showToast("your card is stronger")
            Glide.with(requireContext())
                .load(url_image_bolshe)
                .into(id_gameplay_img_sravneniye)
        }else{
            if(mapAllCard[cardCurrentMy]!! < mapAllCard[cardCurrentOp]!!){
                showToast("your card is weaker")
                Glide.with(requireContext())
                    .load(url_image_menshe)
                    .into(id_gameplay_img_sravneniye)
            }else{
                showToast("the cards are equal, put one more card each")
                Glide.with(requireContext())
                    .load(url_image_ravno)
                    .into(id_gameplay_img_sravneniye)
            }
        }
    }



    private fun showToast(message:String){
        Toast.makeText(requireContext(),message,Toast.LENGTH_SHORT).show()
    }

}