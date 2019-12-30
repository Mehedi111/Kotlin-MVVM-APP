package customview.gakk.com.mvvmsampleapp.data.network

import com.google.gson.JsonObject
import customview.gakk.com.mvvmsampleapp.util.ApiException
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response
import java.lang.StringBuilder


/**
 *CREATED BY MEHEDI on 12/30/2019.
 */
abstract class SafeApiRequest {
    suspend fun <T: Any> apiRequest(call : suspend (() -> Response<T>)) : T{
        val response = call.invoke()

        if (response.isSuccessful){
            return response.body()!!
        }else{
            val error = response.errorBody()?.toString()
            val message = StringBuilder()
            error?.let {
                try {
                    message.append(JSONObject(it).getString("message"))
                }catch (e : JSONException){
                    message.append("\n")
                }
            }
            message.append("Erro code: ${response.code()}")
            throw ApiException(message.toString())
        }
    }
}