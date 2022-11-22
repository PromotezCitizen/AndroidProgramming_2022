package kr.ac.kumoh.s20181246.w11_02_volleywithviewmodel

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kr.ac.kumoh.s20181246.w11_02_volleywithviewmodel.SongViewModel.Companion.QUEUE_TAG
import kr.ac.kumoh.s20181246.w11_02_volleywithviewmodel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var model: SongViewModel
    private var songs: Array<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        model = ViewModelProvider(this)[SongViewModel::class.java] // 몬가... 몬가... 어렵다
        model.requestSongs()

        model.songs.observe(
            this,
            Observer<ArrayList<String>> {
//                Toast.makeText(this, model.songs.value.toString().replace(" ", "\n").replace("[\\[\\]]".toRegex(), ""), Toast.LENGTH_LONG)
                songs = model.songs.value?.toTypedArray()
                binding.listSongs.adapter = ArrayAdapter<String>(
                    this,
                    android.R.layout.simple_list_item_1,
                    songs as Array<out String>
                )
            }
        )
    }
}

