package ru.easycode.zerotoheroandroidtdd

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

interface RunAsync {
    fun <T : Any> runFlowCollect(scope: CoroutineScope, flow: Flow<T>, collect: suspend (T) -> Unit)

    fun run(
        scope: CoroutineScope,
        background: suspend () -> Unit
    ) {
        scope.launch {
            background()
        }
    }

    class Base() : RunAsync {
        override fun <T : Any> runFlowCollect(
            scope: CoroutineScope,
            flow: Flow<T>,
            collect: suspend (T) -> Unit
        ) {
            scope.launch {
                flow.collect { collect(it) }
            }
        }
    }
}