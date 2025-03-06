package com.jainer.postits

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.jainer.postits.data.local.PostitDatabase
import com.jainer.postits.ui.PostitScreen
import com.jainer.postits.ui.PostitViewModel
import com.jainer.postits.ui.theme.PostitsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // obtém a ViewModel respectiva à tela PostitScreen
        val viewModel = ViewModelProvider(this)[PostitViewModel::class.java]

        setContent {
            PostitsTheme {
                // inicializa a tela PostitScreen com sua ViewModel obtida
                PostitScreen(
                    viewModel = viewModel
                )
            }
        }
    }
}