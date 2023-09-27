package com.coolreeceI

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.coolreeceI.ui.theme.ItemScannerTheme
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ItemScannerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {


                    val snackbarHostState = remember { SnackbarHostState() }
                    val scope = rememberCoroutineScope()
                    Scaffold(
                        snackbarHost = { SnackbarHost(snackbarHostState) },
                        floatingActionButton = {
                            var clickCount by remember { mutableStateOf(0) }
                            ExtendedFloatingActionButton(
                                onClick = {

                                    val intent = Intent(this, ScannerActivity::class.java)
                                    startActivity(intent)
                                    // show snackbar as a suspend function
//                                    scope.launch {
//                                        snackbarHostState.showSnackbar(
//                                            "Snackbar # ${++clickCount}"
//                                        )
//                                    }
                                }
                            ) { Text("Show snackbar") }
                        },
                        content = { innerPadding ->
                            Text(
                                text = "Body content",
                                modifier = Modifier
                                    .padding(innerPadding)
                                    .fillMaxSize()
                                    .wrapContentSize()
                            )
                        }
                    )

                }
            }
        }
    }



    private lateinit var webSocketClient: WebSocketClient
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ItemScannerTheme {
        Greeting("Android")
    }
}