package kr.ac.kumoh.s20181246.w15_02_lazylist

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import kr.ac.kumoh.s20181246.w15_02_lazylist.ui.theme.W15_02_LazyListTheme

class MainActivity : ComponentActivity() {
    data class Song(var title: String, var singer: String)
    private val songs = mutableStateListOf<Song>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        repeat(10) {
//            songs.add(Song("테스형", "나훈아"))
//            songs.add(Song("소주 한 잔", "임창정"))
//            songs.add(Song("사랑에 연습이 있었다면", "임재현"))
//        }

        songs.add(Song("테스형", "나훈아"))
        songs.add(Song("소주 한 잔", "임창정"))
        songs.add(Song("사랑에 연습이 있었다면", "임재현"))

        setContent {
            MyApp()
        }
    }

    @Composable
    fun MyApp() {
        W15_02_LazyListTheme {
            Column {
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    onClick = {
                        songs.add(Song("a new start", "뭄 메 이"))
                    }
                ) {
                    Text("추 가")
                }
                SongList()
            }
        }
    }

    @Composable
    fun SongItem(index: Int, song: Song) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(255, 210, 210))
                .padding(8.dp)
                .clickable {
                    Toast.makeText(application, song.title, Toast.LENGTH_LONG).show()
                }
                ) {
            AsyncImage(
                model = "https://picsum.photos/300/300?random=${index}", // 추후에 이미지 넣기기
                contentDescription = "노래 앨범 이미지",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(100.dp)
                    //.clip(CircleShape),
                    .clip(RoundedCornerShape(percent = 10)),
            )
            Spacer(
                modifier = Modifier.width(10.dp)
            )
            Column(
                modifier = Modifier.fillMaxWidth()
                    .background(Color(255, 210, 210))
                    .padding(8.dp),
            ) {
                Text(text = song.title, fontSize = 30.sp)
                Text(text = song.singer, fontSize = 20.sp)
            }
        }
    }

    @Composable
    fun SongList() {
        // item : 단 하나
        // items : 아이템 여러개
        // itemsIndexed : items에 index까지 반환
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(4.dp),
        ) {
            itemsIndexed(songs)  { index, song ->
                SongItem(index, song)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

}