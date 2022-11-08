package kr.ac.kumoh.s20181246.w05_01_carddealer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kr.ac.kumoh.s20181246.w05_01_carddealer.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    companion object {
        const val POCKER_NUM = 52
    }
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(R.layout.activity_main)
        setContentView(binding.root)

        // not use int, try use random
//        val c = Random.nextInt(52)
//        Log.i("Test", "$c : ${getCardName(c)}")
//
//        val res = resources.getIdentifier(
//            getCardName(c),
//            "drawable",
//            packageName)
        // getCardName(30)

        binding.btnDeal.setOnClickListener {
            val c = IntArray(5)
            val res = IntArray(5) // 아마도 identifier가 int값으로 id를 반환하는듯?
            
            // indices : 리스트(어레이)의 최소 인덱스와 최대 인덱스로 이루어진 범위 반환
            for (i in c.indices) {
                c[i] = Random.nextInt(POCKER_NUM)

                Log.i("Test", "${c[i]} : " + getCardName(c[i])) // 가져오는 값이 정해져있으니 string template를 쓰지 말라고 한다

                res[i] = resources.getIdentifier(
                    getCardName(c[i]),
                    "drawable",
                    packageName
                )
            }
            binding.card1.setImageResource(res[0])
            binding.card2.setImageResource(res[1])
            binding.card3.setImageResource(res[2])
            binding.card4.setImageResource(res[3])
            binding.card5.setImageResource(res[4])
        }

        // R.drawable.c_10_of_clubs == resource.getIdentifier(getCardName(30), "drawable", packageName)

//        binding.card1.setImageResource(res)

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