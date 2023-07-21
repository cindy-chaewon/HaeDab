package com.example.haedab.data.api

import com.example.haedab.BuildConfig
import com.example.haedab.model.RequestModel
import com.example.haedab.model.ResponseModel
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ChatApiService {

    //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
    @Headers(
        "Content-Type: application/json",
        "Authorization: Bearer ${BuildConfig.GPT_KEY}"
    )
    @POST("v1/chat/completions")
    suspend fun getResponse(@Body request: RequestModel): ResponseModel
}