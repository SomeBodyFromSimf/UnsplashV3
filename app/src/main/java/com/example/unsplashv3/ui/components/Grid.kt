package com.example.unsplashv3.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun <T : Any> LazyGridFor(
    modifier: Modifier = Modifier,
    items: List<T>,
    state: LazyListState = rememberLazyListState(),
    rowSize: Int = 3,
    footer: LazyListScope.(LazyListState) -> Unit,
    content: @Composable BoxScope.(T) -> Unit
) {
    val rows = items.chunked(rowSize)
    LazyColumn(modifier,state) {
        items(rows) { row ->
            Row(Modifier.fillParentMaxWidth()) {
                for ((index, item) in row.withIndex()) {
                    Box(Modifier.fillMaxWidth(1f / (rowSize - index))) {
                        content(item)
                    }
                }
            }
        }
        footer.invoke(this, state)
    }
}