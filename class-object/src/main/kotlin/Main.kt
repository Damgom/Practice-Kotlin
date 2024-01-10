class Person(val firstName: String, val familyName: String = "") {
//    var firstName: String
//    var familyName: String
//    var age: Int = 0
//    val fullName = "$firstName $familyName"

//    fun setName(firstName: String, familyName: String) {
//        this.firstName = firstName
//        this.familyName = familyName
//    }

//    init {
//        println("Created new Person instance: $fullName")
//        val names = fullName.split(" ")
//        if (names.size != 2) {
//            throw IllegalArgumentException("Invalid name: $fullName")
//        }
//        firstName = names[0]
//        familyName = names[1]
//    }


    fun fullName() = "$firstName $familyName"

    fun showMe() {
//        println("${fullName()}: $age")
    }
}

class Human {
    private val firstName: String
    private val familyName: String
    constructor(firstName: String, familyName: String) {
        this.firstName = firstName
        this.familyName = familyName
    }
    constructor(fullName: String) {
        val names = fullName.split(" ")
        require (names.size != 2) {
            throw IllegalArgumentException("Invalid name: $fullName")
        }
        firstName = names[0]
        familyName = names[1]
    }
}

class Room(vararg val persons: Person) {
    fun showNames() {
        for (person in persons) println(person.fullName())
    }
}

fun showFullName(p: Person) = println(p.fullName())

fun main() {
//    val person = Person("John doe")
//    person.firstName = "John"
//    person.familyName = "Doe"
//    person.age = 25
//
//    person.showMe()

//    showFullName(person)
    val room = Room(Person("John"), Person("Jane", "Smith"))
    room.showNames()
}