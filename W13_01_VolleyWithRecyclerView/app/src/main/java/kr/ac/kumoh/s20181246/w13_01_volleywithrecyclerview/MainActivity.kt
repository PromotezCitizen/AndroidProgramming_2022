package kr.ac.kumoh.s20181246.w13_01_volleywithrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.ac.kumoh.s20181246.w13_01_volleywithrecyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var model: SongViewModel
    private val songAdapter = SongAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        model = ViewModelProvider(this)[SongViewModel::class.java]

        binding.list.apply {
            // 필수
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = songAdapter
            // 선택
            setHasFixedSize(true)
            itemAnimator = DefaultItemAnimator()
        }

        model.list.observe(this) {
            songAdapter.notifyItemRangeInserted(0, model.list.value?.size ?: 0)
        }

        model.requestSong()
    }

    inner class SongAdapter : RecyclerView.Adapter<SongAdapter.ViewHolder>() {
        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val txSong: TextView = itemView.findViewById(R.id.text1)
            val txSinger: TextView = itemView.findViewById(R.id.text2)
            val imgI: ImageView = itemView.findViewById(R.id.image)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//            val view = layoutInflater.inflate(android.R.layout.simple_list_item_2, parent, false)
            val view = layoutInflater.inflate(R.layout.user_layout_2text_1img, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//            holder.txSong.text = model.list.value?.get(position).toString()
            holder.txSong.text = model.list.value?.get(position)?.title ?: "null"
            holder.txSinger.text = model.list.value?.get(position)?.singer ?: "null"
        }

        override fun getItemCount(): Int = model.list.value?.size ?: 0
    }
}