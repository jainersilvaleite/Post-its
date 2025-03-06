package com.jainer.postits.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jainer.postits.ui.theme.PostitsTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun PostitScreen(
    modifier: Modifier = Modifier,
    viewModel: PostitViewModel = viewModel()
) {
    // lista contendo todos os post-its armazenados
    val postitList = viewModel.postitList.observeAsState()
    // exposição do UiState relacionado a esta Screen
    val uiState by viewModel.postitUiState.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxSize()
                .padding(top = 10.dp)
                .weight(1f)
        ) {
            // cria um container (Row) para acomodar cada post-it da lista
            // obs: ?.let {} executa um bloco de código para dado valor que pode ser nulo
            postitList.value?.let {
                items(it.size) { index ->
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = modifier
                            .fillMaxWidth()
                            .background(
                                shape = RoundedCornerShape(15.dp),
                                color = Color(255, 235, 117, 255)
                            ).padding(10.dp)
                    ) {
                        Column(
                            modifier = modifier
                                .weight(1f)
                                .padding(top = 10.dp, start = 8.dp, bottom = 10.dp)
                        ) {
                            Text(
                                text = postitList.value!![index].content,
                                textAlign = TextAlign.Start,
                                color = Color(136, 102, 0, 255),
                                fontWeight = FontWeight.SemiBold,
                            )
                            Text(
                                text = formatDateTime(postitList.value!![index].createdAt),
                                textAlign = TextAlign.Start,
                                color = Color(136, 102, 0, 255),
                            )
                        }
                        IconButton(
                            onClick = {
                                viewModel.deletePostit(
                                    postit = postitList.value!![index]
                                )
                            },
                            modifier = modifier.padding(top = 10.dp, start = 8.dp, bottom = 10.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete post-it",
                                tint = Color(136, 102, 0, 255)
                            )
                        }
                    }
                    Spacer(modifier = modifier.height(8.dp))
                }
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = modifier
                .fillMaxWidth()
        ) {
            OutlinedTextField(
                value = uiState.content,
                onValueChange = {
                    uiState.onChangeContent(it)
                },
                placeholder = {
                    Text(
                        text = "Digite o conteúdo do post-it"
                    )
                },
                modifier = modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
            IconButton(
                onClick = {
                    viewModel.addPostit(
                        content = uiState.content
                    )
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add post-it",
                    modifier = modifier.size(30.dp)
                )
            }
        }
    }
}

// formatar as datas para um padrão específico
fun formatDateTime(date: Date): String {
    val format = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
    return format.format(date)
}

@Preview
@Composable
fun PostitScreenPreview() {
    PostitsTheme {
        PostitScreen()
    }
}