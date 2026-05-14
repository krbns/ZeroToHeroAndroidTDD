package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import ru.easycode.zerotoheroandroidtdd.ui.theme.ZeroToHeroAndroidTDDTheme


enum class Status {
    NONE,
    CORRECT,
    INCORRECT
}
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ZeroToHeroAndroidTDDTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val status = remember { mutableStateOf(Status.NONE) }
                    Column {
                        Text(text = "some question")
                        Text(
                            modifier = Modifier
                                .clickable { status.value = Status.CORRECT }
                                .background(
                                    when (status.value) {
                                        Status.NONE -> Color.Yellow
                                        Status.CORRECT -> Color.Green
                                        Status.INCORRECT -> Color.Green
                                    }
                                ),
                            text = "correct"
                        )
                        Text(
                            modifier = Modifier
                                .clickable { status.value = Status.CORRECT }
                                .background(
                                    when (status.value) {
                                        Status.NONE -> Color.Yellow
                                        Status.CORRECT -> Color.Green
                                        Status.INCORRECT -> Color.Red
                                    }
                                ),
                            text = "incorrect")
                    }
                }
            }
        }
    }
}