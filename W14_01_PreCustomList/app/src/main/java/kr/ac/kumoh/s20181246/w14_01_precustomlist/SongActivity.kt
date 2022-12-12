package kr.ac.kumoh.s20181246.w14_01_precustomlist

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.collection.LruCache
import com.android.volley.toolbox.ImageLoader
import com.android.volley.toolbox.Volley
import kr.ac.kumoh.s20181246.w14_01_precustomlist.databinding.ActivitySongBinding

class SongActivity : AppCompatActivity() {
    private lateinit var imageLoader: ImageLoader
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

        imageLoader = ImageLoader(Volley.newRequestQueue(this),
            object : ImageLoader.ImageCache {
                private val cache = LruCache<String, Bitmap>(100)
                override fun getBitmap(url: String): Bitmap? {
                    return cache.get(url)
                }
                override fun putBitmap(url: String, bitmap: Bitmap) {
                    cache.put(url, bitmap)
                }
            })

        val title = intent.getStringExtra(KEY_TITLE)
        val singer = intent.getStringExtra(KEY_SINGER)
        val image = intent.getStringExtra(KEY_IMAGE)
        binding.textTitle.text = title
        binding.textSinger.text = singer
        binding.imageSong.setImageUrl(image, imageLoader)


    }
}