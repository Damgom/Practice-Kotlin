import kotlin.math.PI

fun circleArea(radius: Double): Double {
    return PI * radius * radius
}

fun increment(a: IntArray): Int {
    return ++a[0];
}

fun printSorted(vararg items: Int) {
    items.sort()
    println(items.contentToString())
}

fun main() {
//    print("Enter radius: ")
//    val radius = readLine()!!.toDouble()
//    println("Circle radius: ${circleArea(radius)}")
//    val arr = intArrayOf(1, 2, 3)
//    println("Increment: ${increment(arr)}")
//    printSorted(5, 4, 6, 1)
//    val a = intArrayOf(5, 4, 6, 1)
//    printSorted(*a)
//    val s = readLine()!!
//    val i = s.indexOf("/")
//
//    val result = if (i >= 0) {
//        val a = s.substring(0, i).toInt()
//        val b = s.substring(i + 1).toInt()
//        (a / b).toString()
//    } else ""
//
//    println(result)
//    val chars = 'a'..'h'
//    val twoDigits = 10..99
//    val zero20ne = 0.0..1.0
//    val num = readLine()!!.toInt()
//    println(num in twoDigits)

//    val a = IntArray(10) { it * it }
//    var sum = 0
//    for (x in a) {
//        sum += x
//    }
//    println("Sum: $sum")
    println(countLetters("abcd").contentToString())

}

fun countLetters(text: String): IntArray {
    val counts = IntArray(26)
    for (char in text) {
        val charLower = char.lowercaseChar()
        if (charLower !in 'a'..'z') continue
        counts[charLower - 'a']++
    }
    return counts
}

/*
문자열이 2진수 형태인지를 판별하는 함수
 */
fun parseIntNumbers(s: String, fallback: Int = -1): Int {
    var num = 0
    if (s.length !in 1..31) return fallback
    for (c in s) {
        if (c !in '0'..'1') return fallback
        num = num * 2 + (c - '0')
    }
    return num
}

fun hexDigit2(n: Int): Char {
    if (n in 0..9) return '0' + n
    else if (n in 10..15) return 'A' + n - 10
    else return '?'
}

fun hexDigit(n: Int): Char {
    return when {
        n in 0..9 -> '0' + n
        n in 10..15 -> 'A' + n - 10
        else -> '?'
    }
}

fun numberDescription(n: Int, max: Int = 100): String = when (n) {
    0 -> "Zero"
    1, 2, 3 -> "Small"
    in 4..9 -> "Medium"
    in 10..max -> "Large"
    !in Int.MIN_VALUE until 0 -> "Negative"
    else -> "Huge"
}