import java.util.*
import kotlin.collections.HashSet

/*

    Write a program to find the number of occurrences of the duplicate words in a string and print them.


*/

fun main() {

    var scanner = Scanner(System.`in`)
    var inputString = scanner.nextLine()
    var inputStringList = inputString.split(" ").toList()
    val uniqueWords = HashSet(inputStringList)


    for (word in uniqueWords) {
        println("${word}:  ${Collections.frequency(inputStringList, word)}")
    }


}