package kr.ac.kumoh.s20181246.w14_01_precustomlist

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

class SongViewModel(application: Application) : AndroidViewModel(application) {
    data class Song (var id: Int, var title: String, var singer: String, var image: String)
    data class Operater(var id: Int, var name: String, var org: String, var image: String)

    companion object {
        const val QUEUE_TAG = "SongVolleyRequest"
        const val SERVER_URL = "https://androidexpressdb-ajrmo.run.goorm.io"
        private const val IMAGE_PATH = "image"
    }

    private val songs = ArrayList<Song>()
    private val _list = MutableLiveData<ArrayList<Song>>()
    val list: LiveData<ArrayList<Song>>
        get() = _list

    private val operater = ArrayList<Operater>()
    private val _list_o = MutableLiveData<ArrayList<Operater>>()
    val list_o: LiveData<ArrayList<Operater>>
        get() = _list_o

    private var queue: RequestQueue
    var imageLoader: ImageLoader

    init {
        _list.value = songs
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

    fun getImageUrl(i: Int): String = "${SERVER_URL}/${IMAGE_PATH}/${URLEncoder.encode(songs[i].image, "utf-8")}"

    fun requestSong() {
        // NOTE: 서버 주소는 본인의 서버 IP 사용할 것
        // Array를 반환할 경우에는 JsonObjectRequest 대신 JsonArrayRequest 사용
        val request = JsonArrayRequest(
            Request.Method.GET,
            "${SERVER_URL}/operater",
            null,
            {
                //Toast.makeText(getApplication(), it.toString(), Toast.LENGTH_LONG).show()
                songs.clear()
                parseJson(it)
//                Toast.makeText(getApplication(), it.toString(), Toast.LENGTH_LONG).show()
                _list.value = songs
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
            val item: JSONObject = items[i] as JSONObject
//            val id = item.getInt("id")
//            val title = item.getString("title")
//            val singer = item.getString("singer")
//            val image = item.getString("img")
//            songs.add(Song(id, title, singer, image))

            val id = item.getInt("id")
            val name = item.getString("name")
            val org = item.getString("organization")
            val image = item.getString("img")
        }
    }

    override fun onCleared() {
        super.onCleared()
        queue.cancelAll(QUEUE_TAG)
    }
}