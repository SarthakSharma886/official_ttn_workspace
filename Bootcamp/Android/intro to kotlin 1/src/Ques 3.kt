import java.util.*

/*

Write a program to find the number of occurrences of a character in a string without using loop.


*/

fun main() {

    val scanner = Scanner(System.`in`)
    var string = ""
    var character:Char

    println("Enter the String :  ")
    string = scanner.nextLine()

    println("Enter the Character")

    character = scanner.next().toCharArray().get(0)

    val charCount = string.length - string.replace(""+character,"").length
    println("Occurance of Character is : $charCount")


}