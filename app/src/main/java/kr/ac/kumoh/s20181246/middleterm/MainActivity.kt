package kr.ac.kumoh.s20181246.middleterm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.ac.kumoh.s20181246.middleterm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var main: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        main = ActivityMainBinding.inflate(layoutInflater)
        setContentView(main.root)

        main.imgGundam.setOnClickListener {
            val second = Intent(this, SecondActivity::class.java)
            startActivity(second)
        }
    }
}