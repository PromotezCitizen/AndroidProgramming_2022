package kr.ac.kumoh.s20181246.termproject

import android.app.Application
import android.graphics.Bitmap
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
import java.io.Serializable

class OperaterViewModel(application: Application)  : AndroidViewModel(application) {
    data class Operater(val id: Int, val name: String, val organization: String, val icon: String)
    data class Weapon(val op_id: Int, val name: String, val damage: Int, val armo: Int, val type: String, val ismain: Int) : Serializable
    // serializable 이용 시 필요

    companion object {
        const val QUEUE_TAG = "OperaterVolleyRequest"
        const val SERVER_URL = "https://androidexpressdb-ajrmo.run.goorm.io"
        private const val IMAGE_PATH = "image"
    }

    private val operaters = ArrayList<Operater>()
    private val _list_o = MutableLiveData<ArrayList<Operater>>()
    val list_o: LiveData<ArrayList<Operater>>
        get() = _list_o

    private val weapons = ArrayList<Weapon>()
    private val _list_w = MutableLiveData<ArrayList<Weapon>>()
    val list_w: LiveData<ArrayList<Weapon>>
        get() = _list_w

    private val queue: RequestQueue
    var imageLoader: ImageLoader

    init {
        _list_o.value = operaters
        _list_w.value = weapons
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
        val request_o = JsonArrayRequest(
            Request.Method.GET,
            "${SERVER_URL}/operater",
            null,
            {
                operaters.clear()
                parseJson_o(it)
                _list_o.value = operaters
            },
            {
                Toast.makeText(getApplication(), it.toString(), Toast.LENGTH_LONG).show()
            }
        )

        request_o.tag = QUEUE_TAG
        queue.add(request_o)

        val request_w = JsonArrayRequest(
            Request.Method.GET,
            "${SERVER_URL}/weapon",
            null,
            {
                weapons.clear()
                parseJson_w(it)
                _list_w.value = weapons
            },
            {
                Toast.makeText(getApplication(), it.toString(), Toast.LENGTH_LONG).show()
            }
        )

        request_w.tag = QUEUE_TAG
        queue.add(request_w)
    }

    private fun parseJson_o(items: JSONArray) {
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

    private fun parseJson_w(items: JSONArray) {
        for (i in 0 until items.length()) {
            (items[i] as JSONObject).let {
                weapons.add(
                    Weapon(
                        it.getInt("op_id"),
                        it.getString("name"),
                        it.getInt("damage"),
                        it.getInt("armo"),
                        it.getString("type"),
                        it.getInt("ismain")
                    )
                )
                // data class Weapon(val op_id: Int, val name: String, val damage: Int, val armo: Int, val type: String, val ismain: Int)
            }
        }
    }
}