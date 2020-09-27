package com.example.kioskapp.Tools

private var link: String = "https://utkuerdemir.com/"
private var path: String = "rest/"
private var token: String = "c8e7ef4f8f8c89fa7b9f3f70b4447zgh"

fun getUrl(request:String):String{
    return link + path + request
}
fun getUrl(request:String,parametersNames:Array<String>,parametersValues:Array<String>):String{
    var url = "$link$path$request?"
    for(i in parametersNames.indices){
        url += parametersNames[i]+"="+parametersValues[i]
    }
    return url
}
fun getToken():String{
    return token
}
