package kr.ac.kumoh.s20181246.w05_01_carddealer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kr.ac.kumoh.s20181246.w05_01_carddealer.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(R.layout.activity_main)
        setContentView(binding.root)

        // not use int, try use random
        val c = Random.nextInt(52)
        Log.i("Test", "$c : ${getCardName(c)}")

        val res = resources.getIdentifier(
            getCardName(c),
            "drawable",
            packageName)
        // getCardName(30)



        // R.drawable.c_10_of_clubs == resource.getIdentifier(getCardName(30), "drawable", packageName)

        binding.card1.setImageResource(res)
//        binding.card1.setImageResource(R.drawable.c_10_of_clubs)
//        binding.card2.setImageResource(R.drawable.c_9_of_clubs)
//        binding.card3.setImageResource(R.drawable.c_8_of_clubs)
//        binding.card4.setImageResource(R.drawable.c_7_of_clubs)
//        binding.card5.setImageResource(R.drawable.c_6_of_clubs)

    }

    private fun getCardName(c: Int): String {
        val shape = when (c / 13) {
            0 -> "spades"
            1 -> "diamonds"
            2 -> "hearts"
            3 -> "clubs"
            else -> "error"
        }
        val num = when (c % 13) {
            0 -> "ace"
            in 1..9 -> (c%13 + 1).toString()
            10 -> "jack"
            11 -> "queen"
            12 -> "king"
            else -> "error"
        }

        Log.i("getCardName()", "Shape: ${shape}, Num: ${num}")

        return "c_${num}_of_${shape}"
    }
}