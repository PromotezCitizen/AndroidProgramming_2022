package kr.ac.kumoh.s20181246.w12_01_recyclerview

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.ac.kumoh.s20181246.w12_01_recyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    // implementation 'androidx.activity:activity-ktx:1.6.1' 있을 경우 아래 사용 가능
//    private val model: ListViewModel by viewModels()
    // 또는
     private lateinit var model: ListViewModel // 이후 onCreate에서
    private val songAdapter = SongAdapter()

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        model = ViewModelProvider(this)[ListViewModel::class.java]

        // getList 대신 public인 list 변수 사용
        model.list.observe(this) {
              // 추가하여 warning 없앨 수 있긴 함
             songAdapter.notifyDataSetChanged() // 다 다시 그려!!!!!
//            songAdapter.notifyItemRangeChanged(0, model.list.value?.size ?: 0)
        }

        for (i in 1..3) {
            model.add(i.toString())
        }

        binding.list.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            itemAnimator = DefaultItemAnimator()
            setHasFixedSize(true)
            adapter = songAdapter
        }
// 위나 밑이나 동일한 결과를 보인다.
//        binding.list.layoutManager = LinearLayoutManager(this)
//        binding.list.itemAnimator = DefaultItemAnimator()
//        binding.list.setHasFixedSize(true)
//        binding.list.adapter = songAdapter
    }

    inner class SongAdapter : RecyclerView.Adapter<SongAdapter.ViewHolder>() {
        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val txSong: TextView = itemView.findViewById(android.R.id.text1)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater
                .from(parent.context)
                .inflate(android.R.layout.simple_list_item_1, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.txSong.text = model.list.value?.get(position) ?: null
        }

        override fun getItemCount() = model.list.value?.size ?: 0
    }
}

