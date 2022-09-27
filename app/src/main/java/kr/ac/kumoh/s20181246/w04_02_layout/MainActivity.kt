package kr.ac.kumoh.s20181246.w04_02_layout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.ac.kumoh.s20181246.w04_02_layout.ui.theme.W04_02_LayoutTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    W04_02_LayoutTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            // Greeting("인사")
            MyLinearLayout()
        }
    }
}

@Composable
fun MyLinearLayout() {
    Column {
        Row{
            Text(
                text = "안녕하세요",
                modifier = Modifier
                    .background(Color.Yellow)
                    .padding(8.dp),
                color = Color.Black
            )
            Text(
                text = "한상현",
                modifier = Modifier
                    .background(Color.Cyan)
                    .padding(20.dp),
                color = Color.Black

            )
        }
        Text(
            text = "어디선가 본 것 같은 예제",
            modifier = Modifier
                .background(Color.Magenta)
                .padding(10.dp),
            color = Color.Black
        )
    }
//    Row {
//        Text(text = "안녕하세요")
//        Text(text = "한상현")
//    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp()
}