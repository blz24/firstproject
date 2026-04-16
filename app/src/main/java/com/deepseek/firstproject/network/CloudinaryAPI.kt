package com.deepseek.firstproject.network

import com.deepseek.firstproject.models.CloudinaryResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface CloudinaryAPI {
    @Multipart
    @POST("v1_1/ddlc31ao6/image/upload")
    suspend fun uploadImage(
        @Part image: MultipartBody.Part,
        @Part("upload_preset") uploadPreset: RequestBody
    ): Response<CloudinaryResponse>
}
