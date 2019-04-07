import java.util.*

/*


Check letter in string which do not have pair.


*/


fun main() {

    val scanner = Scanner(System.`in`)
    var charArray:CharArray
    var char:Char
    var int:Int


    println("Enter the String :  ")
    charArray = scanner.next().toCharArray()

    int = charArray[0].toInt()

    for(index in 1..charArray.size-1){
        int = int xor charArray[index].toInt()
    }
    char = int.toChar()
    println(char)


}