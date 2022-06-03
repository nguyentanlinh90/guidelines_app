package com.ntl.guidelinesapp.modules.coroutines.repositories

import com.ntl.guidelinesapp.modules.coroutines.common.Logger
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class FakeRepository(private val dispatcher: CoroutineDispatcher) {

    suspend fun requestWithIndex(index: Int, isThrowException: Boolean = false): Int =
        withContext(dispatcher) {
            Logger.log("request with index $index at ${System.currentTimeMillis()} on thread ${Thread.currentThread().name}")
            val delayTime = index * 1000L
            delay(delayTime)
            if (isThrowException) {
                throw Exception("Exception with index $index")
            }
            Logger.log("Done with index $index")
            index
        }

    suspend fun saveToDB(index: Int) =
        withContext(dispatcher) {
            Logger.log("Saving to DB: $index")
            val delayTime = index * 1000L
            delay(delayTime)
            Logger.log("Save to DB success: $index")
        }
}