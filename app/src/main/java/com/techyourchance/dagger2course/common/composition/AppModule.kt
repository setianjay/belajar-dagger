package com.techyourchance.dagger2course.common.composition

import android.app.Application
import com.techyourchance.dagger2course.Constants
import com.techyourchance.dagger2course.MyApplication
import com.techyourchance.dagger2course.networking.StackoverflowApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * this class provide service application scope
 * */
@Module
class AppModule(private val application: Application) {

    @Provides
    fun application(): Application = application

    @Provides
    @AppScope
    fun retrofit(): Retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @AppScope
    fun stackoverflowApi(retrofit: Retrofit): StackoverflowApi = retrofit.create(StackoverflowApi::class.java)

}