/*

Create a list of Employee which will have name and age as properties. print the name of all employees age >30.

*/

class Employee(var name:String,var age:Int)

fun main(){
    val employeeList = ArrayList<Employee>()
    employeeList.add(Employee("sarthak",20))
    employeeList.add(Employee("sharma",27))
    employeeList.add(Employee("ankush",35))
    employeeList.add(Employee("garg",37))
    employeeList.add(Employee("sajal",55))
    employeeList.add(Employee("saurav",45))

    println(employeeList.filter { it.age >= 30 }.map{it.name}.joinToString { "$it ages more than 30" })

}
