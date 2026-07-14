package ru.easycode.zerotoheroandroidtdd

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag

@Composable
fun MainScreen(viewModel: ListViewModel) {

    val state = viewModel.state.collectAsState()
    Box(Modifier.fillMaxSize()) {

        Column(Modifier.align(Alignment.Center)) {
            var text by rememberSaveable { mutableStateOf("") }

            TextField(
                modifier = Modifier.testTag("textField"),
                value = text,
                onValueChange = { text = it }
            )
            Button(
                modifier = Modifier.testTag("addButton"),
                onClick = {
                    viewModel.add(text)
                    text = ""
                }
            ) {
                Text("Add")
            }
            LazyColumn(modifier = Modifier.testTag("ListLazyColumn")) {
                itemsIndexed(state.value) { index, content ->
                    Text(
                        modifier = Modifier.testTag("Element at $index"),
                        text = content
                    )
                }
            }
        }
    }
}