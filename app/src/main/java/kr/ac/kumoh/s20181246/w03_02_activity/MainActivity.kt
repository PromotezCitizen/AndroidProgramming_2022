package kr.ac.kumoh.s20181246.w03_02_activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import kr.ac.kumoh.s20181246.w03_02_activity.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var cnt = 0
    private val model: CounterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.txtCount.text = cnt.toString()
//        binding.txtCount.text = model.count.toString()

//        model.getCount().observe(this, Observer {
//            binding.txtCount.text = it.toString()
//        })


        binding.btnAdd.setOnClickListener {
//            binding.txtCount.text = "" + ++cnt
            binding.txtCount.text = "${++cnt}"
//            model.add()
//            binding.txtCount.text = model.getCount().value.toString()
//            binding.txtCount.text = model.count.toString()
        }


        Log.i("Lifecycle", "onCreate")
    }

    override fun onStart() {
        super.onStart()
//        binding.txtCount.text = model.getCount().value.toString()
        Log.i("Lifecycle", "onStart")
    }

    override fun onResume() {
        super.onResume()

        Log.i("Lifecycle", "onResume")
    }

    override fun onStop() {
        super.onStop()

        Log.i("Lifecycle", "onStop")
    }

    override fun onPause() {
        super.onPause()

        Log.i("Lifecycle", "onPause")
    }

    override fun onRestart() {
        super.onRestart()

        Log.i("Lifecycle", "onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.i("Lifecycle", "onDestroy")
    }
}