package ru.easycode

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import ru.easycode.zerotoheroandroidtdd.MainViewModel
import ru.easycode.zerotoheroandroidtdd.ProgressUi
import ru.easycode.zerotoheroandroidtdd.ui.theme.ZeroToHeroAndroidTDDTheme

@Composable
fun MainScreen(viewModel: MainViewModel) {
    val state = viewModel.stateFlow.collectAsState()
    MainScreenContent(
        state = state.value,
        onClick = {
            viewModel.load()
            viewModel.loadInternal()
        }
    )
}

@Composable
private fun MainScreenContent(
    state: ProgressUi,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        if (state == ProgressUi.Disconnected) {
            Text(
                text = "No Internet Connection",
                modifier = Modifier
                    .align(Alignment.Center)
                    .testTag("noInternetConnection")
            )
        }

        when (state) {
            ProgressUi.Connected,
            ProgressUi.Disconnected -> {
                Button(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .testTag("loadButton"),
                    enabled = state == ProgressUi.Connected,
                    onClick = onClick
                ) {
                    Text("Load")
                }
            }

            ProgressUi.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .testTag("progress")
                )
            }

            is ProgressUi.Data -> {
                Text(
                    text = state.value,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .testTag("result")
                )
            }

            ProgressUi.Empty -> Unit
        }
    }
}

@Preview
@Composable
private fun MainScreenPreview() {
    ZeroToHeroAndroidTDDTheme {
        MainScreenContent(
            state = ProgressUi.Disconnected,
            onClick = {}
        )
    }
}