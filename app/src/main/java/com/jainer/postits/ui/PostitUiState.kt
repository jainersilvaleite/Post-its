package com.jainer.postits.ui

data class PostitUiState(
    val content: String = "",
    val onChangeContent: (String) -> Unit = {}
)
