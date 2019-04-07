
/*

Write a program to print your Firstname,LastName & age using init block,companion object.

*/


class QuesOne {
    companion object {

        val firstNameStatic = "Sarthak"
        val lastNameStatic = "Sharma"
        val ageStatic = 20
    }


    init {
        val firstName = "Sarthak"
        val lastName = "Sharma"
        val age = 20
        println("init block first name = $firstName init block last name = $lastName init block age = $age")
    }
}

fun main() {
    println("companion first name = ${QuesOne.firstNameStatic} companion last name = ${QuesOne.lastNameStatic} companion age = ${QuesOne.ageStatic}")
    QuesOne()
}