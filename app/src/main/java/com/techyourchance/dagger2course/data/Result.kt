package com.techyourchance.dagger2course.data

sealed class Result<out T>{
    class Success<T>(val data: T): Result<T>()
    object Failure: Result<Nothing>()
}
