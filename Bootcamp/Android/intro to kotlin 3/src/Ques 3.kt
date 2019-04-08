/*

WAP to create sealed Base class and 3 subclasses of Base class,
write a function which will have base class object as an argument and it will return name of the subclass based argument type.

*/

sealed class BaseClass

class SubClass1(var info:String) : BaseClass()
class SubClass2(var info:String) : BaseClass()
class SubClass3(var info:String) : BaseClass()

fun returnInfo(subClass:BaseClass) = when (subClass){
    is SubClass1 -> subClass.info
    is SubClass2 -> subClass.info
    is SubClass3 -> subClass.info
}

fun main(){
    println(returnInfo(SubClass1("info 1")))
}