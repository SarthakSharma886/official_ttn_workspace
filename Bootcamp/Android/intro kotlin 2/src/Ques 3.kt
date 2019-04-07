
/*

Create 3 sub class of bank SBI,BOI,ICICI all 4 should have method called getDetails which provide there specific details like rateofinterest etc,print details of every bank.

*/


open class Bank {
    private val rateOfIntrest = 4
    open fun getDetails() = rateOfIntrest

}

class SBI : Bank() {
    private val rateOfIntrest = 5
    override fun getDetails() = rateOfIntrest
}

class BOI : Bank() {
    private val rateOfIntrest = 6
    override fun getDetails() = rateOfIntrest
}

class ICICI : Bank() {
    private val rateOfIntrest = 7
    override fun getDetails() = rateOfIntrest
}

fun main(){

    println("Bank Rate Of Interest = ${Bank().getDetails()}")
    println("SBI Bank Rate Of Interest = ${SBI().getDetails()}")
    println("BOI Bank Rate Of Interest = ${BOI().getDetails()}")
    println("ICICI Bank Rate Of Interest = ${ICICI().getDetails()}")
}
