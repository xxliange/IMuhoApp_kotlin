package com.muhoapp.view.utils

class Operator {


}

fun main() {
    val a : Array<String> = arrayOf("a","v","s","f","31","d","2")
    val index : Array<Int> = arrayOf(1,4,3,2,5,6,9)

    index
        .filter {
            it < a.size
        }
        .map {
            a[it]
        }
        .reduce { s, s1 ->
            "$s$s1"
        }
        .also {
            println("密码是: $it")
        }

}