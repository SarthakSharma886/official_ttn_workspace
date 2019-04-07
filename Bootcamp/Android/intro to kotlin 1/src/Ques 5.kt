/*


Find common elements between two arrays.



*/


fun main() {
    var firstArray = arrayOf(7, 5, 0, 3, 2, 7, 4, 4, 4, 2)
    var secondArray = arrayOf(7, 9, 8, 2, 8, 2, 8, 4, 0, 7)

    var commonElements = firstArray.intersect(secondArray.toList())

    println("common elements ")
    for (element in commonElements)
        println(element)
}