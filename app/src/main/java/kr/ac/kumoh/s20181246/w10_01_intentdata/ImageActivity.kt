package kr.ac.kumoh.s20181246.w10_01_intentdata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.ac.kumoh.s20181246.w10_01_intentdata.databinding.ActivityImageBinding

class ImageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityImageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        Toast.makeText(this, intent.getStringExtra("image"), Toast.LENGTH_SHORT)

        val res = when(intent.getStringExtra("image")) {
            "gundam" -> R.drawable.img
            "zaku" -> R.drawable.ic_launcher_background
            else -> R.drawable.ic_launcher_foreground
        }

        binding.imgGundam.setImageResource(res)
    }
}