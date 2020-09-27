package com.example.kioskapp.Models

import java.io.Serializable

class PostListModel:Serializable{
    var id:Int= 0
        get() = field
        set(value) {
            field = value
        }
    var addDate:String= ""
        get() = field
        set(value) {
            field = value
        }
    var body:String= ""
        get() = field
        set(value) {
            field = value
        }
    var picture:String= ""
        get() = field
        set(value) {
            field = value
        }
    var summary:String= ""
        get() = field
        set(value) {
            field = value
        }
    var title:String= ""
        get() = field
        set(value) {
            field = value
        }
    var hit:Int= 0
        get() = field
        set(value) {
            field = value
        }
}
