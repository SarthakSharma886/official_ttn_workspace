/*



WAP to produce NoClassDefFoundError and ClassNotFoundException exception.



*/

fun main() {

    classNotFound()

    println()


    var exceptionThrower: ExceptionThrower

    try {
        exceptionThrower = ExceptionThrower()
    } catch (e: ExceptionInInitializerError) {

    } finally {
        exceptionThrower = ExceptionThrower()
    }
}


fun classNotFound() {
    try {
        Class.forName("sarthakSharma")
    } catch (e: ClassNotFoundException) {
        println(e)
    }
}

class ExceptionThrower {
    companion object {
        val calculation = 1 / 0
    }
}