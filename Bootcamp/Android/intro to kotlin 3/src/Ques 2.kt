/*

WAP to create singleton class.

*/

fun main() {

    Singleton.printName()
    Singleton.name = "changed"

    var singletonCaller = SingletonCaller()

}

object Singleton {
    init {
        println("singleton class")
        println()
    }

    var name = "singleton"

    fun printName() {
        println(name)
        println()
    }
}

class SingletonCaller {

    init {
        println("Singleton caller class")
        println()
        Singleton.printName()
    }

}