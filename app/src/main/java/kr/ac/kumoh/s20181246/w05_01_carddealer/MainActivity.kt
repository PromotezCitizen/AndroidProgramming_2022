package kr.ac.kumoh.s20181246.w05_01_carddealer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kr.ac.kumoh.s20181246.w05_01_carddealer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(R.layout.activity_main)
        setContentView(binding.root)

        getCardName(30)

        binding.card1.setImageResource(R.drawable.c_10_of_clubs)
        binding.card2.setImageResource(R.drawable.c_9_of_clubs)
        binding.card3.setImageResource(R.drawable.c_8_of_clubs)
        binding.card4.setImageResource(R.drawable.c_7_of_clubs)
        binding.card5.setImageResource(R.drawable.c_6_of_clubs)

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


        return "c_10_of_clubs"
    }
}