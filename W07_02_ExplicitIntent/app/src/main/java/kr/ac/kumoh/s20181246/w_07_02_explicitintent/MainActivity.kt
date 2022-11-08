package kr.ac.kumoh.s20181246.w_07_02_explicitintent

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.split
import android.util.Log
import kr.ac.kumoh.s20181246.w_07_02_explicitintent.databinding.ActivityMainBinding
import java.util.zip.Inflater

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.btnExplicitIntent.setOnClickListener {
            val text = binding.editText.text.toString()
            val uri = Uri.parse("https://www.youtube.com/results?search_query=${text}")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity((intent))
        }

        binding.btnImage.setOnClickListener {
            val intent = Intent(this, ImageActivity::class.java)
            startActivity((intent))
        }
    }
}