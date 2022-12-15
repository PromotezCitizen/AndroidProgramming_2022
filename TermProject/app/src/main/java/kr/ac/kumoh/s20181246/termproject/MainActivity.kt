package kr.ac.kumoh.s20181246.termproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.toolbox.NetworkImageView
import kr.ac.kumoh.s20181246.termproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var model: OperaterViewModel
    private val operaterAdapter = OperaterAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        model = ViewModelProvider(this)[OperaterViewModel::class.java]

        binding.list.apply {
            layoutManager = LinearLayoutManager(application)
            setHasFixedSize(true)
            itemAnimator = DefaultItemAnimator()
            adapter = operaterAdapter
        }

        model.list_o.observe(this) {
            operaterAdapter.notifyItemRangeInserted(
                0,
                operaterAdapter.itemCount
            )
        }


        model.requestOperater()
    }

    inner class OperaterAdapter: RecyclerView.Adapter<OperaterAdapter.ViewHolder>() {
        inner class ViewHolder(itemView: View):
            RecyclerView.ViewHolder(itemView),
            View.OnClickListener {
                val txName: TextView = itemView.findViewById(R.id.text1)
                val txOrg: TextView = itemView.findViewById(R.id.text2)
                val niIcon: NetworkImageView = itemView.findViewById(R.id.image)

                init {
                    niIcon.setDefaultImageResId(android.R.drawable.ic_menu_report_image)
                    itemView.setOnClickListener(this@ViewHolder)
                }

                override fun onClick(v: View?) {
                    val intent = Intent(application, OperaterActivity::class.java)
                    intent.putExtra(OperaterActivity.KEY_OPER_NAME, model.list_o.value?.get(adapterPosition)?.name)
                    intent.putExtra(OperaterActivity.KEY_OPER_ORG, model.list_o.value?.get(adapterPosition)?.organization)
                    intent.putExtra(OperaterActivity.KEY_IMAGE, model.getImageUrl((adapterPosition)))

                    // 해당 오퍼레이터의 무기를 intent로 넘기려는 작업
                    // filter를 이용하여 해당 오퍼레이터의 무기인지를 확인한다.

                    val ls = model.list_w.value?.filter {
                        it.op_id == model.list_o.value?.get(adapterPosition)?.id
                    }

                    // ls는 Array타입. intent로 넘기려면 ArrayList로 변환해야 함
                    intent.putExtra(OperaterActivity.KEY_WEAPON, ls?.toCollection(ArrayList<OperaterViewModel.Weapon>()))

                    startActivity(intent)
                }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = layoutInflater.inflate(R.layout.custom_list,
                parent,
                false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.txName.text = model.list_o.value?.get(position)?.name
            holder.txOrg.text = model.list_o.value?.get(position)?.organization
            holder.niIcon.setImageUrl(model.getImageUrl(position), model.imageLoader)
        }

        override fun getItemCount() = model.list_o.value?.size ?: 0
    }
}