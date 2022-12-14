package kr.ac.kumoh.s20181246.termproject

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.collection.LruCache
import com.android.volley.toolbox.ImageLoader
import com.android.volley.toolbox.Volley
import kr.ac.kumoh.s20181246.termproject.databinding.ActivityOperaterBinding

class OperaterActivity : AppCompatActivity() {
    private lateinit var imageLoader: ImageLoader
    lateinit var binding: ActivityOperaterBinding

    companion object {
        const val KEY_OPER_NAME = "OperaterName"
        const val KEY_OPER_ORG = "OperaterOrganization"
        const val KEY_IMAGE = "OperaterIcon"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOperaterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        imageLoader = ImageLoader(
            Volley.newRequestQueue(this),
            object : ImageLoader.ImageCache {
                private val cache = LruCache<String, Bitmap>(100)
                override fun getBitmap(url: String): Bitmap? {
                    return cache.get(url)
                }
                override fun putBitmap(url: String, bitmap: Bitmap) {
                    cache.put(url, bitmap)
                }
            })

        val name = intent.getStringExtra(KEY_OPER_NAME)
        val organization = intent.getStringExtra(KEY_OPER_ORG)
        val image = intent.getStringExtra(KEY_IMAGE)

        binding.textName.text = name
        binding.textOrg.text = organization
        binding.iconOperater.setImageUrl(image, imageLoader)
    }
}