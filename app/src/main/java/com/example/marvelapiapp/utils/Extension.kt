package com.example.marvelapiapp.utils

import android.app.Activity
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import com.example.marvelapiapp.R
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

fun Activity.goToActivityAnimation(){
    overridePendingTransition(
        R.anim.slide_in_right,
        R.anim.slide_out_left
    )
}


/**
 * for UnitTesting with LiveData
 * */

@VisibleForTesting(otherwise = VisibleForTesting.NONE)
fun <T> LiveData<T>.getOrAwaitValue(
    time:Long = 2,
    timeUnit: TimeUnit = TimeUnit.SECONDS,
    afterObserve : () -> Unit = {}
) : T {
    var data: T? = null
    val latch = CountDownLatch(1)
    val observer = object : androidx.lifecycle.Observer<T> {
        override fun onChanged(o: T?) {
            data = o
            latch.countDown()
            this@getOrAwaitValue.removeObserver(this)
        }
    }
    this.observeForever(observer)
    try{
        afterObserve.invoke()
        if(!latch.await(time,timeUnit)){
            throw TimeoutException("LiveData value was never set.")
        }
    } finally {
        this.removeObserver(observer)
    }
    @Suppress("UNCHECKED_CAST")
    return data as T
}