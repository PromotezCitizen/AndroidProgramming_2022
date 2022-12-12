package kr.ac.kumoh.s20181246.w15_02_lazylist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kr.ac.kumoh.s20181246.w15_02_lazylist.ui.theme.W15_02_LazyListTheme

class MainActivity : ComponentActivity() {
    data class Song(var title: String, var singer: String)
    private val songs = mutableListOf<Song>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        repeat(10) {
            songs.add(Song("테스형", "나훈아"))
            songs.add(Song("소주 한 잔", "임창정"))
            songs.add(Song("사랑에 연습이 있었다면", "임재현"))
        }

        setContent {
            MyApp()
        }
    }

    @Composable
    fun MyApp() {
        W15_02_LazyListTheme {
            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                Greeting("Android")
            }
        }
    }

    @Composable
    fun SongItem(song: Song) {
        Column(
            modifier = Modifier.fillMaxWidth()
                .background(Color(255, 210, 210))
                .padding(8.dp),
        ) {
            Text(text = song.title, fontSize = 30.sp)
            Text(text = song.singer, fontSize = 20.sp)
        }
    }

    @Composable
    fun SongList() {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(4.dp),
        ) {
            items(songs)  { song ->
                SongItem(song)
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    W15_02_LazyListTheme {
        Greeting("Android")
    }
}