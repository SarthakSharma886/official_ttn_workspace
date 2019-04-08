




val calculateInterest:(Double,Double,Double)->Double = {p:Double,r:Double,t:Double ->(p*r*t)/100}

fun lamdaFunc(p:Double,r:Double,t:Double,calculateInterest:(Double,Double,Double)->Double):Double =    calculateInterest(p,r,t)

fun main(){
    println(lamdaFunc(11.0,22.0,15.0,calculateInterest))
}