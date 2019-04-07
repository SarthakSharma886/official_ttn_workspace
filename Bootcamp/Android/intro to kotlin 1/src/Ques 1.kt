import java.util.*


/*

  Write a program to replace a substring inside a string with other string.


*/

fun main() {

    var scanner = Scanner(System.`in`)
    var originalString = ""
    var replacableString = ""
    var replaceString = ""
    var replacedString = ""

    println("Enter the Original String")
    originalString = scanner.nextLine()

    println("Enter the String need to replaced")
    replacableString = scanner.nextLine()


    println("Enter the replace String")
    replaceString = scanner.nextLine()


    replacedString = originalString.replace(replacableString, replaceString)

    println(replacedString)


}