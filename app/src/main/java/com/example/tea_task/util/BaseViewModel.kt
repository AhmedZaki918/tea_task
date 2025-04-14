package com.example.tea_task.util

import androidx.lifecycle.ViewModel

abstract class BaseViewModel<T> : ViewModel() {
    abstract fun onIntent(intent: T)
}