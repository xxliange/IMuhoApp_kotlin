package com.muhoapp.view.utils

//单例模式
class Single private constructor() {
    companion object {
        fun instance(): Single {
            return Holder.instance;
        }

        private object Holder {
            val instance = Single();
        }

    }

    fun getIndex(): Int = 1;
}

object  Obj {
    @JvmStatic
    fun getIndex(){

    }
    @JvmStatic
    fun  getString(){

    }
}

fun duFunction(block: ()-> Unit){
    block.invoke()
}

class Single2 {
    fun getIndex(): Int = 1;
}

//超级枚举 ： 密闭类
sealed class SuperCommand {
    object UP : SuperCommand();
    object DOWN : SuperCommand();
    object LEFT : SuperCommand();
    object RIGHT : SuperCommand();
    class PACE(var pace: Int) : SuperCommand();
}

fun main() {
    duFunction {
        println("1")
    }
    val index = Single.instance().getIndex()
    println(index)
//    SuperCommand.RIGHT
//    val user = User("xx", 1);
//    val (id, name) = user;
//    println("$name + $id");

    for (i in 1..10 step 2) {
        println(i)
    }

    repeat(10) {
        println(it);
    }


}

fun exec(superCommand: SuperCommand) = when (superCommand) {
    SuperCommand.UP -> {

    }
    SuperCommand.DOWN -> {
    }
    SuperCommand.LEFT -> {
    }
    SuperCommand.RIGHT -> {
    }
    is SuperCommand.PACE -> {

    }

}