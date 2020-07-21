package com.muhoapp.view.utils

class User(var name:String, var id : Int){
    operator fun component1() = id;
    operator fun component2() = name;
}