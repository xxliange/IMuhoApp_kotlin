package com.muhoapp.utils

import com.muhoapp.BaseConfig
import com.muhoapp.model.Api
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * 设置网络请求
 */
open class RetrofitManger {

    private var retrofit: Retrofit? = null

    private constructor() {
        val builder = OkHttpClient.Builder()
        /**
         * 拦截器 设置初始header
         */
        builder.addInterceptor {
            val original = it.request()
            val request = original.newBuilder()
                .header("token","web")
                .header("user-id","0")
                .method(original.method(),original.body())
                .build()
            it.proceed(request)
        }
        val client = builder.build()
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    companion object {
        const val BASE_URL = BaseConfig.BASE_URL_TEST
        var instance: RetrofitManger? = null
            get() {
                if (field == null) {
                    field = RetrofitManger()
                }
                return field
            }

        fun get(): RetrofitManger {
            return instance!!
        }
    }

    fun getApi(): Api {
        return retrofit?.create(Api::class.java)!!
    }
}