package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import kotlinx.coroutines.delay
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
                    var isLoading by rememberSaveable { mutableStateOf(false) }
                    var result by rememberSaveable { mutableStateOf<String?>(null) }

                    LaunchedEffect(isLoading) {
                        if (isLoading) {
                            delay(2000)
                            isLoading = false
                            result = "Success!"
                        }
                    }

                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        when {
                            isLoading -> {
                                CircularProgressIndicator(
                                    modifier = Modifier.testTag("progress")
                                )
                            }

                            result != null -> {
                                Text(
                                    text = result!!,
                                    modifier = Modifier.testTag("result")
                                )
                            }

                            else -> {
                                Button(
                                    onClick = {
                                        isLoading = true
                                    },
                                    modifier = Modifier.testTag("loadButton")
                                ) {
                                    Text("Load")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}