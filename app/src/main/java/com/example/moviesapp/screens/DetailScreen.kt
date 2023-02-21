package com.example.moviesapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import com.example.moviesapp.MainViewModel
import androidx.compose.material.Text
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter

@Composable
fun DetailScreen(viewModel: MainViewModel, itemId: String) {
    val currentItem = viewModel.allMovies
        .observeAsState(listOf()).value
        .firstOrNull { it.id == itemId.toInt() }
    
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 24.dp, horizontal = 8.dp)
    ) {
        LazyColumn {
            item {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = rememberImagePainter(currentItem?.image?.medium),
                        contentDescription = null,
                        modifier = Modifier.size(512.dp)
                    )
                    Text(
                        text = currentItem?.name ?: "",
                        fontWeight = FontWeight.Bold,
                        fontSize = 32.sp,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                    Row(
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(
                            text = "Rating: ",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                        Text(
                            text = currentItem?.rating?.average.toString(),
                            fontSize = 18.sp
                        )
                    }
                    Row(
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(
                            text = "Genre: ",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                        currentItem?.genres?.take(2)?.forEach {
                            Text(
                                text = " $it ",
                                fontSize = 18.sp
                            )
                        }
                    }
                    Text(text = currentItem?.summary ?: "")
                }
            }
        }
    }
}