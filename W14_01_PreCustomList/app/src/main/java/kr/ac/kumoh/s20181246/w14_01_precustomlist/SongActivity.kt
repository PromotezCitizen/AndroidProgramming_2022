package kr.ac.kumoh.s20181246.w14_01_precustomlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.ac.kumoh.s20181246.w14_01_precustomlist.databinding.ActivitySongBinding

class SongActivity : AppCompatActivity() {
    companion object {
        const val KEY_TITLE = "SongTitle"
        const val KEY_SINGER = "SongSinger"
        const val KEY_IMAGE = "SongImage"
    }
    lateinit var binding: ActivitySongBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySongBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = intent.getStringExtra(KEY_TITLE)
        val singer = intent.getStringExtra(KEY_SINGER)
        val image = intent.getStringExtra(KEY_IMAGE)
        binding.textTitle.text = title
    }
}