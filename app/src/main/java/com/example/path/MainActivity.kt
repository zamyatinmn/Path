package com.example.path

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.path.ui.theme.PathTheme
import kotlin.math.pow
import kotlin.math.sqrt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PathTheme {
                Surface(Modifier.fillMaxSize()) {
                    Box {
                        Track()
                        Text(
                            modifier = Modifier
                                .align(Alignment.TopCenter)
                                .padding(30.dp),
                            text = "Draw the path"
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Track() {
    val points = remember { mutableStateListOf<Offset>() }
    var i by remember { mutableStateOf(0) }
    var speed = 1f
    val defaultOffset = Offset(0f, 0f)

    val animatedOffset by animateOffsetAsState(
        targetValue =
        if (i < points.size) {
            val first = points[i]
            val second = if (i > 0) points[(i - 1)] else defaultOffset
            speed = sqrt((second.x - first.x).pow(2) + (second.y - first.y).pow(2))
            points[i]
        } else {
            defaultOffset
        },
        animationSpec = tween(durationMillis = speed.toInt() * 10, easing = LinearEasing),
        finishedListener = { i++ })

    val path = Path().apply {
        points.forEach { point -> lineTo(point.x, point.y) }
    }

    Canvas(
        Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectDragGestures { change, _ ->
                    points.add(change.position)
                }
            }
    ) {
        drawPath(path = path, color = Color.Blue, style = Stroke(10f))
        drawCircle(color = Color.Red, radius = 30f, center = animatedOffset)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PathTheme {
        Track()
    }
}