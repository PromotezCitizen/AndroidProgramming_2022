package kr.ac.kumoh.s20181246.w11_02_volleywithviewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject

class SongViewModel(application: Application) : AndroidViewModel(application) {
    companion object {
        const val QUEUE_TAG = "SongVolleyRequest"
    }
    private val list = ArrayList<String>()
    val songs = MutableLiveData<ArrayList<String>>()

    private var queue: RequestQueue = Volley.newRequestQueue(getApplication())
    // getApplication : AndroidViewModel에서 상속받는다.

    init { // 변수 초기화 기능
        songs.value = list
    }

    fun requestSongs() {
        val url = "https://androidexpressdb-ajrmo.run.goorm.io"
        val request = JsonArrayRequest(
            Request.Method.GET,
            url,
            null,
            {
                list.clear()
                parseJson(it)
                songs.value = list
//                Toast.makeText(getApplication(), list.toString().replace(" ", "").replace("[\\[\\]]".toRegex(), ""), Toast.LENGTH_LONG).show()
            },
            {
                Toast.makeText(getApplication(), it.toString(), Toast.LENGTH_LONG).show()
            }
        )
        request.tag = QUEUE_TAG
        queue.add(request)
    }

    override fun onCleared() {
        super.onCleared()
        queue.cancelAll(QUEUE_TAG)
    }

    private fun parseJson(items: JSONArray) {
        for (i in 0 until items.length()){
            val item = items[i] as JSONObject
            val id = item.getInt("id")
            val title = item.getString("title")
            val singer = item.getString("singer")
            list.add("${singer}-${title}(${id})")
//            list.add((items[i] as JSONObject)["title"] as String + (items[i] as JSONObject)["singer"] as String)
        }
    }
}



