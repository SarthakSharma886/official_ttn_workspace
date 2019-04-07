/*


Write a single program for following operation using overloading

A) Adding 2 integer number

B) Adding 2 double

D) multiplying 2 int

E) concate 2 string

F) Concate 3 String





*/



fun main() {

    println("Add two Integer : ${add(1, 2)}")
    println("Add two Double : ${add(1.2, 2.2)}")
    println("Multiply two Double : ${mul(1, 2)}")
    println("Multiply two Double : ${mul(1.2, 2.2)}")
    println("Concatinate two Strings : ${concat("Hello ", "Mr. ")}")
    println("Concatinate three Strings : ${concat("Hello ", "Mr. ", "Sarthak ")}")


}

fun add(num1: Int, num2: Int) = num1 + num2

fun add(num1: Double, num2: Double) = num1 + num2

fun mul(num1: Int, num2: Int) = num1 * num2

fun mul(num1: Double, num2: Double) = num1 * num2

fun concat(str1: String, str2: String) = str1 + str2

fun concat(str1: String, str2: String, str3: String) = str1 + str2 + str3





