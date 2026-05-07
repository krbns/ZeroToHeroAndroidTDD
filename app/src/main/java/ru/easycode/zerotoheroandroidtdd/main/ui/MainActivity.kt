package ru.easycode.zerotoheroandroidtdd.main.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
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
                    Row {
                        val count = rememberSaveable {
                            mutableStateOf(
                                Count.Base(
                                    value = 1,
                                    step = 1,
                                    max = 2,
                                    min = 0
                                )
                            )
                        }

                        Button(
                            modifier = Modifier.size(100.dp),
                            enabled = !count.value.isMin(),
                            onClick = {
                                count.value = count.value.decrement() as Count.Base
                            },
                            content = {
                                Text(text = "-")
                            }
                        )
                        Text(text = "${count.value}")
                        Button(
                            modifier = Modifier.size(100.dp),
                            enabled = !count.value.isMax(),
                            onClick = {
                                count.value = count.value.increment() as Count.Base
                            },
                            content = {
                                Text(text = "+")
                            }
                        )
                    }
                }
            }
        }
    }
}