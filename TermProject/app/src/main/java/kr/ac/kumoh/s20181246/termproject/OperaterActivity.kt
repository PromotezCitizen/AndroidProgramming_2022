package kr.ac.kumoh.s20181246.termproject

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.TEXT_ALIGNMENT_CENTER
import android.widget.TableRow
import android.widget.TextView
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
        const val KEY_WEAPON = "Weapon"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOperaterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        imageLoader = ImageLoader(
            Volley.newRequestQueue(this),
            object : ImageLoader.ImageCache {
                private val cache = LruCache<String, Bitmap>(100)
                override fun getBitmap(url: String): Bitmap? = cache.get(url)
                override fun putBitmap(url: String, bitmap: Bitmap) {
                    cache.put(url, bitmap)
                }
            })

        binding.textName.text = intent.getStringExtra(KEY_OPER_NAME)
        binding.textOrg.text = intent.getStringExtra(KEY_OPER_ORG)
        binding.iconOperater.setImageUrl(intent.getStringExtra(KEY_IMAGE), imageLoader)

        // serializable data class arraylist를 intent로 받아와서
        val weapons = (intent.getSerializableExtra(KEY_WEAPON) as ArrayList<OperaterViewModel.Weapon>)
        // 내림차순 정렬한 후
        weapons.sortByDescending { it.ismain }
        // 테이블을 만들어 로드아웃을 보여준다.
        makeTable(weapons)
    }

    private fun setTextView(attr: String): TextView = TextView(this).apply {
            text = attr
            textAlignment = TEXT_ALIGNMENT_CENTER
        }

    private fun makeTable(weapons: ArrayList<OperaterViewModel.Weapon>) {
        val table = binding.table
        for (weapon in weapons) {
            val tableRow = TableRow(this)
            tableRow.addView(setTextView(if (weapon.ismain == 0) "보조" else "메인"))
            tableRow.addView(setTextView(weapon.name))
            tableRow.addView(setTextView(weapon.damage.toString()))
            tableRow.addView(setTextView(weapon.type))
            tableRow.addView(setTextView(weapon.armo.toString()))

            table.addView(tableRow)
        }
    }
}