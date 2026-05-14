package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
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
                    val email = rememberSaveable { mutableStateOf("") }
                    val emailValid by remember {
                        derivedStateOf {
                            if (email.value.isNotEmpty()) {
                                android.util.Patterns.EMAIL_ADDRESS.matcher(email.value).matches()
                            } else {
                                false
                            }
                        }
                    }
                    Column {
                        TextField(
                            modifier = Modifier.testTag("emailInputTag"),
                            value = email.value,
                            onValueChange = { email.value = it }
                        )
                        Button(
                            modifier = Modifier.testTag("loginButtonTag"),
                            onClick = {},
                            enabled = emailValid,
                        ) {
                            Text("login")
                        }
                    }
                }
            }
        }
    }
}