import java.util.*

fun main() {
    val text = "Hello, world!\nThis is \"multiline\" string"
    println("\u03c0 \u2248 3.14")
    println("text print : $text");
    val name = readLine()
    println("Hello, $name!\n Today is ${Date()}")
    val message = """
        Hello, $name!
        Today is ${Date()}
        """.trimIndent()
    println(message)
    val emptyArray = emptyArray<String>()
    val b = arrayOf("hello", "world")
    val c = arrayOf(1, 4, 9)
    val size = readLine()!!.toInt()
    val squares = Array(size) { (it + 1) * (it + 1)}

    val array = intArrayOf(1, 2, 3) + 4
    val array2 = intArrayOf(1, 2, 3) + intArrayOf(4, 5, 6)
    println(array.contentToString())
    println(array2.contentToString())
}