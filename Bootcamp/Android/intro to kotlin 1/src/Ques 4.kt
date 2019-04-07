import java.util.*

/*


Calculate the number & Percentage Of Lowercase Letters,Uppercase Letters, Digits And Other Special Characters In A String.


*/


fun main() {
    val scanner = Scanner(System.`in`)
    println("Enter a string : ")
    calculatePercentage(scanner.nextLine())
}

fun calculatePercentage(string: String) {
    val stringLength = string.length
    var upperCharCount = 0
    var digitCount = 0
    var lowerCharCount = 0
    var specialCharCount = 0

    for (char in string) {
        if (char.isUpperCase())
            upperCharCount++
        else if (char.isLowerCase())
            lowerCharCount++
        else if (char.isDigit())
            digitCount++
        else
            specialCharCount++
    }
    println("Uppercase  : " + upperCharCount + " : " + ((upperCharCount * 100) / stringLength).toFloat() + "%")
    println("Lowercase  : " + lowerCharCount + " : " + ((lowerCharCount * 100) / stringLength).toFloat() + "%")
    println("Digits  : " + digitCount + " : " + ((digitCount * 100) / stringLength).toFloat() + "%")
    println("Special  : " + specialCharCount + " : " + ((specialCharCount * 100) / stringLength).toFloat() + "%")


}