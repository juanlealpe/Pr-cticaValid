package com.sport.practicavalid.api.response

import android.util.Log
import com.sport.practicavalid.utility.Constants
import retrofit2.Response

@Suppress("unused")
sealed class ApiResponse<T>{
    companion object {
        fun <T> create(response: Response<T>): ApiResponse<T> {
            Log.i(Constants.LOG_I, "URL: ${response.raw().request().url()}")
            Log.i(Constants.LOG_I, "MESSAGE: ${response.message()}")

            return if (response.isSuccessful) {
                val body = response.body()
                if (body == null || response.code() == 204) {
                    ApiEmptyResponse()
                } else {
                    ApiSuccessResponse(
                        code = response.code(),
                        message = response.message(),
                        body = body)
                }
            } else {
                val msg = response.errorBody()?.string()
                val errorMsg = if (msg.isNullOrEmpty()) {
                    response.message()
                } else {
                    msg
                }
                ApiErrorResponse(response.code(), errorMsg)
            }
        }
    }
}

/**
 * TODO: Comentar esto
 */
class ApiEmptyResponse<T> : ApiResponse<T>()
data class ApiSuccessResponse<T>(val code: Int, val message: String?, val body: T) : ApiResponse<T>()
data class ApiErrorResponse<T>(val code: Int, val message: String?) : ApiResponse<T>()