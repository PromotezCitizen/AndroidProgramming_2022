package kr.ac.kumoh.s20181246.w04_03_composecounter

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kr.ac.kumoh.s20181246.w04_03_composecounter.ui.theme.W04_03_ComposeCounterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                Column {
                    Counter()
                    Counter()
                    Counter()
                }
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    W04_03_ComposeCounterTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            content()
        }
    }
}

@Composable
fun Counter() {
//     var count by remember { mutableStateOf(0) }
    val (count, setCount) = remember { mutableStateOf(0) }
    Column(
        modifier = Modifier
            // .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        Text(
            text = "$count",
            fontSize = 70.sp,

        )
        Row{
            Button(
                onClick = {
    //                    ++count
                    setCount(count + 1)
                    Log.i("counter", count.toString())
                },
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(text = "증가")
            }
            Spacer(
                modifier = Modifier
                    .width(8.dp)
            )
            Button(
                onClick = {
    //                    --count
                    if (count > 0) setCount(count - 1)
                    Log.i("counter", count.toString())
                },
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(text = "감소")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp {
        Counter()
    }
}