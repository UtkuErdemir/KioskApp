package com.example.kioskapp.Requests

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.kioskapp.Tools.Data
import com.example.kioskapp.Tools.getToken
import com.example.kioskapp.Tools.getUrl
import org.json.JSONArray
import org.json.JSONObject

class Companies(context: Context){
    var context = context
    fun getCompanyList(data:Data, highlight:Boolean, companyName:String,sectorId:Int?){
        val queue = Volley.newRequestQueue(context)
        val url: String = if(highlight) getUrl("company")
        else getUrl("company",arrayOf("name"),arrayOf(companyName))
        var posts: JSONArray
        val jsonObjectRequest = object: JsonObjectRequest(Method.GET, url,null,
            Response.Listener<JSONObject> { response ->
                posts = response.getJSONArray("Posts")
                data.onSuccess(posts)
            },
            Response.ErrorListener {
                Log.e("CEVAP:", it.toString())
            })
        {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["From"] = getToken()
                return headers
            }
        }

        queue.add(jsonObjectRequest)
    }
}
