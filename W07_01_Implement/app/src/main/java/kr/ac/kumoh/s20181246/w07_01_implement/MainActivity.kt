package kr.ac.kumoh.s20181246.w07_01_implement

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kr.ac.kumoh.s20181246.w07_01_implement.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnHomepage.setOnClickListener {
            val uri = Uri.parse("https://www.youtube.com/results?search_query=")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

        binding.btnMap.setOnClickListener {
            val uri = Uri.parse("geo:0,0?z=17&q=김해대로 2326")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

        binding.btnSms.setOnClickListener {
            val uri = Uri.parse("sms:01040792799")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            if (intent.resolveActivity(packageManager) === null)
                startActivity(intent)
            else
                Toast.makeText(this, "연결할 앱이 없습니다", Toast.LENGTH_SHORT)
                // toast :
        }
    }
}