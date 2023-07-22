package haedab.haedab.haedab.data.api

import haedab.haedab.haedab.BuildConfig
import haedab.haedab.haedab.model.RequestModel
import haedab.haedab.haedab.model.ResponseModel
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ChatApiService {

    //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
    @Headers(
        "Content-Type: application/json",
        "Authorization: Bearer ${haedab.haedab.haedab.BuildConfig.GPT_KEY}"
    )
    @POST("v1/chat/completions")
    suspend fun getResponse(@Body request: RequestModel): ResponseModel
}