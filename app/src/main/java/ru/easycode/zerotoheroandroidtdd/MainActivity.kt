package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.easycode.zerotoheroandroidtdd.ui.theme.ZeroToHeroAndroidTDDTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ZeroToHeroAndroidTDDTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        val isClicked = remember { mutableStateOf(false) }
                        if (!isClicked.value) {
                            Button(
                                modifier = Modifier.size(100.dp),
                                onClick = {
                                    isClicked.value = true
                                },
                                content = {
                                    Text(text = "Click me!")
                                }
                            )
                        }

                        if (isClicked.value) {
                            Button(
                                modifier = Modifier.size(100.dp),
                                onClick = {
                                    isClicked.value = false
                                },
                                content = {
                                    Text(text = "That's right!")
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}