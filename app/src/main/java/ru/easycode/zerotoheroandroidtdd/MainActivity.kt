package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import ru.easycode.zerotoheroandroidtdd.data.Repository
import ru.easycode.zerotoheroandroidtdd.ui.ProgressUi
import ru.easycode.zerotoheroandroidtdd.ui.ProgressViewModel
import ru.easycode.zerotoheroandroidtdd.ui.ProgressViewModelFactory
import ru.easycode.zerotoheroandroidtdd.ui.theme.ZeroToHeroAndroidTDDTheme

class MainActivity : ComponentActivity() {

    private val runAsync = RunAsync.Base()
    private val repository = Repository.Base()

    private val viewModel: ProgressViewModel by viewModels {
        ProgressViewModelFactory(this, runAsync, repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ZeroToHeroAndroidTDDTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val state = viewModel.state.collectAsState()

                    Column {
                        when (state.value) {
                            is ProgressUi.Data -> {
                                Text(
                                    modifier = Modifier.testTag("result"),
                                    text = (state.value as ProgressUi.Data).value
                                )
                            }

                            is ProgressUi.Initial -> {
                                Button(modifier = Modifier.testTag("loadButton"), onClick = {
                                    viewModel.load()
                                    viewModel.loadInternal()
                                }) {}
                            }

                            is ProgressUi.Loading -> {
                                CircularProgressIndicator(Modifier.testTag("progress"))
                            }
                        }
                    }
                }
            }
        }
    }
}