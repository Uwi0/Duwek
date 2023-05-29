package com.kakapo.ui

import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.DisposableEffectResult
import androidx.compose.runtime.DisposableEffectScope
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.platform.LocalView
import androidx.metrics.performance.PerformanceMetricsState
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberMetricStateHolder(): PerformanceMetricsState.Holder{
    val localView = LocalView.current

    return remember(localView){
        PerformanceMetricsState.getHolderForHierarchy(localView)
    }
}


@Composable
fun TrackJank(
    vararg keys: Any?,
    reportMetric: suspend CoroutineScope.(state: PerformanceMetricsState.Holder) -> Unit
){
    val metrics = rememberMetricStateHolder()
    LaunchedEffect(metrics, *keys){
        reportMetric(metrics)
    }
}

@Composable
fun TrackDisposableJank(
    vararg keys: Any?,
    reportMetric: DisposableEffectScope.(state: PerformanceMetricsState.Holder) -> DisposableEffectResult
){
    val metrics = rememberMetricStateHolder()
    DisposableEffect(metrics, *keys){
        reportMetric(this, metrics)
    }
}

@Composable
fun TrackScrollJank(scrollState: ScrollableState, stateName: String){
    TrackJank(scrollState){metricsHolder ->
        snapshotFlow { scrollState.isScrollInProgress }.collect{ isScrollProgress ->
            metricsHolder.state?.apply {
                if (isScrollProgress){
                    putState(stateName, "Scrolling=true")
                } else{
                    removeState(stateName)
                }
            }
        }
    }
}