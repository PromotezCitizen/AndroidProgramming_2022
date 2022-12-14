package kr.ac.kumoh.s20181246.termproject

import android.app.Application
import android.graphics.Bitmap
import android.util.Log
import android.widget.Toast
import androidx.collection.LruCache
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.ImageLoader
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject
import java.net.URLEncoder

class OperaterViewModel(application: Application)  : AndroidViewModel(application) {
    data class Operater(val id: Int, val name: String, val organization: String, val icon: String)

    companion object {
        const val QUEUE_TAG = "OperaterVolleyRequest"
        const val SERVER_URL = "https://androidexpressdb-ajrmo.run.goorm.io"
        private const val IMAGE_PATH = "image"
    }

    private val operaters = ArrayList<Operater>()
    private val _list = MutableLiveData<ArrayList<Operater>>()
    val list: LiveData<ArrayList<Operater>>
        get() = _list

    private val queue: RequestQueue
    var imageLoader: ImageLoader

    init {
        _list.value = operaters
        queue = Volley.newRequestQueue(getApplication())
        imageLoader = ImageLoader(queue,
            object : ImageLoader.ImageCache {
                private val cache = LruCache<String, Bitmap>(100)
                override fun getBitmap(url: String): Bitmap? = cache.get(url)
                override fun putBitmap(url: String, bitmap: Bitmap) {
                    cache.put(url, bitmap)
                }
            }
        )
    }

    fun getImageUrl(i: Int) = "${SERVER_URL}/${IMAGE_PATH}/${URLEncoder.encode(operaters[i].icon, "utf-8")}"

    fun requestOperater() {
        val request = JsonArrayRequest(
            Request.Method.GET,
            "${SERVER_URL}/operater",
            null,
            {
                operaters.clear()
                parseJson(it)
                _list.value = operaters
            },
            {
                Toast.makeText(getApplication(), it.toString(), Toast.LENGTH_LONG).show()
            }
        )

        request.tag = QUEUE_TAG
        queue.add(request)
    }

    private fun parseJson(items: JSONArray) {
        for (i in 0 until items.length()) {
            (items[i] as JSONObject).let {
                operaters.add(
                    Operater(
                        it.getInt("id"),
                        it.getString("name"),
                        it.getString("organization"),
                        it.getString("img")
                    )
                )
            }
        }
    }

}