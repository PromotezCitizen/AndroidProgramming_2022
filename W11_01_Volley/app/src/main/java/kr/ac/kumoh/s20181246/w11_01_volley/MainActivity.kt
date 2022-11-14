package kr.ac.kumoh.s20181246.w11_01_volley

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kr.ac.kumoh.s20181246.w11_01_volley.databinding.ActivityMainBinding
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var queue: RequestQueue
    private val movies = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        queue = Volley.newRequestQueue(application)
        binding.btnConnect.setOnClickListener {
            val url = "https://yts.torrentbay.to/api/v2/list_movies.json?sort=rating&limit=30"
            val request = JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                {
                    movies.clear()
                    parseJson(it)
                    Toast.makeText(application,
                        movies.toString().replace(",", "\n").replace("[\\[\\]]".toRegex(), ""),
                        Toast.LENGTH_LONG).show()

//                    Toast.makeText(application, it.toString(), Toast.LENGTH_LONG).show()
                },
                {
                    Toast.makeText(application, it.toString(), Toast.LENGTH_LONG).show()
                }
            )

            queue.add(request)
        }
    }

    private fun parseJson(obj: JSONObject) {
        val items = obj.getJSONObject("data").getJSONArray("movies")
        for (i in 0 until items.length()) {
            val item: JSONObject = items[i] as JSONObject
            val title = item.getString("title_long")
            movies.add("-$title")
        }
    }
}