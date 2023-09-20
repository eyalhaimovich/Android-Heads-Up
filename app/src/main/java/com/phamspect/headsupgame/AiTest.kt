package com.phamspect.headsupgame

import android.util.Log
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException


private var client = OkHttpClient()

fun getResponse(){
    val key = "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"
    val url = "https://api.openai.com/v1/chat/completions"

    val reBody = """
        {
        "model": "gpt-3.5-turbo",
        "messages": [
          {
            "role": "system",
            "content": "how are you"
          },
          {
            "role": "user",
            "content": "Hello!"
          }
        ]
        }
        """.trimIndent()

    val request = Request.Builder()
        .url(url)
        .addHeader("Content-Type", "application/json")
        .addHeader("Authorization", "Bearer $key")
        .post(reBody.toRequestBody("application/json".toMediaTypeOrNull()))
        .build()

    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            Log.e("error", "API FAIL",e)
        }

        override fun onResponse(call: Call, response: Response) {
            val body: String? = response.body?.string()
            if (body != null) {
                Log.v("data",body)
            }
            else{
                Log.v("data", "empty")
            }
            val jsonOBJ = JSONObject(body)
            val jsonarray : JSONArray = jsonOBJ.getJSONArray("choices")
            val message = jsonarray.getJSONObject(0).getJSONObject("message").getString("content")
            println(message)
        }

    })
}
 fun main(){
    getResponse()
}