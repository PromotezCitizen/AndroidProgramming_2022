package kr.ac.kumoh.s20181246.w10_01_intentdata

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import kr.ac.kumoh.s20181246.w10_01_intentdata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnClickListener {
    companion object {
        const val keyName = "image"
    }
    private lateinit var binding: ActivityMainBinding

    override fun onClick(v: View?) {
        val intent = Intent(this, ImageActivity::class.java)
        val value = when(v?.id) {
            binding.btnGundam.id -> "gundam"
            binding.btnGundam.id -> "zaku"
            else -> null
        }
        intent.putExtra(keyName, value)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.btnGundam.setOnClickListener {
//            val intent = Intent(this, ImageActivity::class.java)
//            intent.putExtra(keyName, R.drawable.img) // key, data.    R.drawable.img 또는 "img" 가능
//            startActivity(intent)
//        }
        binding.btnGundam.setOnClickListener(this)
        binding.btnZaku.setOnClickListener(this)


    }
}