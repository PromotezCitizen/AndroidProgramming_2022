package kr.ac.kumoh.s20181246.w10_01_intentdata

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import kr.ac.kumoh.s20181246.w10_01_intentdata.databinding.ActivityImageBinding

class ImageActivity : AppCompatActivity(), OnClickListener {
    companion object {
        const val imgName = "image"
        const val resultName = "result"

        const val LIKE = 10
        const val DISLIKE = 20
        const val NONE = 0
    }

    private lateinit var binding: ActivityImageBinding
    private var image: String? = null // nullable으로 만들어주자

    override fun onClick(v: View?) {
        val intent = Intent()
        val value = when (v?.id) {
            binding.btnDislike.id -> DISLIKE
            binding.btnLike.id -> LIKE
            else -> NONE
        }
        intent.putExtra(imgName, image)
        intent.putExtra(resultName, value)
        setResult(RESULT_OK)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        Toast.makeText(this, intent.getStringExtra("image"), Toast.LENGTH_SHORT)

        image = intent.getStringExtra(MainActivity.keyName)

        val res = when(intent.getStringExtra(imgName)) {
            "gundam" -> R.drawable.img
            "zaku" -> R.drawable.ic_launcher_background
            else -> R.drawable.ic_launcher_foreground
        }

        binding.imgGundam.setImageResource(res)

        binding.btnDislike.setOnClickListener(this)
        binding.btnLike.setOnClickListener(this)
    }
}