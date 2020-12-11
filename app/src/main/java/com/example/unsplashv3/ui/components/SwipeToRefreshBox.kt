package com.example.unsplashv3.ui.components

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.gesture.LongPressDragObserver
import androidx.compose.ui.gesture.longPressDragGestureFilter
import androidx.compose.ui.layout.WithConstraints
import androidx.compose.ui.unit.dp
import com.example.unsplashv3.utils.ComposableView
import com.example.unsplashv3.utils.lambda
import com.example.unsplashv3.utils.toPx

@Composable
fun SwipeToRefreshBox(onRefresh: lambda, bodyContent: ComposableView) {
    WithConstraints {
        val startPosition = 0.dp
        val containerHeight = constraints.maxHeight.toFloat()
        val position = remember { mutableStateOf(startPosition) }
        val status : MutableState<Status> = remember { mutableStateOf(Status.Hidden)}
        Surface(modifier = Modifier.fillMaxSize()
            .longPressDragGestureFilter(object : LongPressDragObserver {
                override fun onLongPress(pxPosition: Offset) {
                    if(pxPosition.x < containerHeight*0.3f)
                        status.value = Status.Dragging.also { Log.d("COMPOSEEE", "start dragging") }
                    super.onLongPress(pxPosition)
                }

                override fun onDrag(dragDistance: Offset): Offset {
                    if(status.value == Status.Dragging){
                        position.value = dragDistance.x.dp
                        if(dragDistance.x > 60.toPx()) {
                            status.value = Status.Stop.also { Log.d("COMPOSEEE", "stop dragging") }
                        }
                    }
                    return super.onDrag(dragDistance)
                }

                override fun onStop(velocity: Offset) {
                    super.onStop(velocity)
                    onRefresh().also { Log.d("COMPOSEEE", "finish refresh") }
                    position.value = startPosition
                }
            })) {

            ConstraintLayout {

                val (circleBox,mainContainer) = createRefs()

                Box(modifier = Modifier.constrainAs(circleBox) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.top, margin = -(position.value))
                }) {
                    CircularProgressIndicator(modifier = Modifier.wrapContentWidth(Alignment.CenterHorizontally))
                }
                Box(modifier = Modifier.constrainAs(mainContainer){
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                    top.linkTo(circleBox.bottom)
                }) {
                    bodyContent.invoke()
                }
            }

        }
    }

}

sealed class Status{
    object Hidden : Status()
    object Dragging : Status()
    object Stop : Status()
}