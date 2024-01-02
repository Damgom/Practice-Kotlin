# 함수 정의

```
import kotlin.math.PI

fun circleArea(radius: Double): Double {
    return PI * radius * radius
}

fun main() {
    print("Enter radius: ")
    val radius = readLine()!!.toDouble()
    println("Circle radius: ${circleArea(radius)}")
}
```

fun 키워드는 컴파일러에게 함수 정의가 뒤따라 온다는 것을 알려줌

반환타입은 : 이후 오는 변수타입 여기서는 Double

Kotlin 에서의 함수 파라미터는 무조건 불변이다.

```
fun increment(n: Int): Int {
    return n++ // Exception
}
```

Kotlin은 call-by-value론을 사용. 파라미터 값에 호출하는 쪽의 인자를 복사한다.

변수와 달리 파라미터와 반환타입은 항상 타입을 명시해야한다.

하지만 예외적으로 unit타입 (java에서는 void)에서는 타입을 명시하지 않는다. unit타입은 함수가 의미있는 return value를 돌려주지 않는다는 뜻

즉 함수를 정의할 때 반환타입을 지정하지 않는 경우 Kotlin은 unit 함수를 정의한다고 가정한다.

식이 본문인 함수는 리턴타입을 명시하지 않아도 된다.
```
fun circleArea(radius: Double) = Pi * radius * radius
```
하지만 식이 본문인 함수에서 {}를 넣는 경우 람다식으로 인식하게 된다.

자바나 다른 언어에서 위치 기반 인자를 사용한다. 하지만 Kotlin에서는 이름 붙은 인자 방식도 지원한다.
```
rectangleArea(width = w, height = h)
```

### 오버로딩과 디폴트 값

컴파일러가 어떤 함수를 호출해야 할지 구분할 수 있도록 당연히 오버로딩한 함수의 파라미터 타입이 모두 달라야함

1. 파라미터의 개수와 타입을 기준으로 호출할 수 있는 모든 함수를 찾음
2. 덜 구체적인 함수를 제외함. 어떤 함수의 파라미터 타입이 다른 파라미터 타입의 상위타입인 경우 이 함수는 다른 함수보다 덜 구체적인 함수.
3. 후보가 하나로 압축되면 그 함수를 호출한다. 후보가 둘 이상이면 컴파일 오류 발생

디폴트 파라미터란?
```
fun readInt(radix: Int = 10) = readLine()!!.toInt(radix)
```
이렇게 디폴트 값을 줄 수 있다. 이렇게 디폴트값을 주면 이 함수를 파라미터 없이 호출해도 되고 파라미터를 지정해 호출해도 된다.

여기서 디폴트 파라미터 뒤에 디폴트가 지정되지 않은 파라미터가 존재하는 경우, 디폴트 파라미터를 사용하지 않고 함수를 호출하는 방법은 이름 붙은 인자를 사용하는 수밖에 없다
```
fun restrictToRange(
    from: Int = Int.MAX_VALUE,
    to: Int = int.MIN_VALUE,
    what: Int): Int = Math.max(from, Math.min(to, what)
fun main() {
    println(restrictToRange(10, what = 1))
}
```

### vararg

```
fun printSorted(vararg items: Int) {
    items.sort()
    println(items.contentToString())
}

fun main() {
    printSorted(5, 4, 6, 1) // [1, 4, 5, 6]
    val a = intArrayOf(5, 4, 6, 1)
    printSorted(*a)
    printSorted(6, 1, *intArrayOf(3, 8), 2) // [1, 2, 3, 6, 8]
}

// 여기서 items는 IntArray

스프레드 연산자 *를 이용해 배열을 가변인자 대신 넘길 수 있다.

```

스프레드는 배열을 복사한다. 파라미터 배열의 내용을 바꿔도 원본 원소에는 영향을 미치지 않는다.

배열 내부에 참조가 들어있는 경우에는 참조가 복사되기 때문에 참조가 가리키는 데이터가 호출하는 쪽과 함수 내부 배열에서 공유된다.

둘 이상의 vararg 파라미터로 선언하는 것은 금지.

### 조건문

자바와 다른점 : 함수식으로 사용할 수 있음
```
fun max(a: Int, b: Int) = if (a > b) a else b

fun main() {
    val s = readLine()!!
    val i = s.indexOf("/")
    
    val result = if (i >= 0) {
        val a = s.substring(0, i).toInt()
        val b = s.substring(i + 1).toInt()
        (a / b).toString()
    } else ""
    
    println(result)
}
```
if문을 식으로 사용할 때는 양 가지가 모두 필요. else가 필요하다는 뜻

자바와 다르게 코틀린은 3항연산자가 없다.

### 범위, 진행, 연산

```
val chars = 'a'..'h' // 'a'<= .. <= 'h'
val twoDigits = 10..99 // 10 <= .. <= 99
```

in 연산을 사용하면 어떤 값이 범위 안에 들어있는지 여부를 알 수 있다 (boolean)
```
val num = readLine()!!.toInt
println(num in 10..99) // if (num >= 10 && num <= 99) 와 같음
```

in이 아닌 until 키워드를 사용하는경우 반만 닫힌 범위를 만들 수 있다
```
val twoDigits = 10 until 100 // 10 <= .. < 100
```

범위의 간격을 지정할 수 있다. step 이용
```
1..10 step 3 // 1, 4, 7, 10
```

내림차순 : downTo 이용

### when
```
fun hexDigit(n: Int): Char {
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

// 2개의 함수는 같은 기능을 하는 함수이다

fun hexDigit(n: Int) = when {
    n in 0..9 -> '0' + n
    n in 10..15 -> 'A' + n - 10
    else -> '?'
}

// 이것처럼 함수식으로도 표현할 수 있다
```

조건이 어떤 값과 같은경우 연산을 수행하는 경우 (자바의 switch 문처럼) when 형태를 다르게 사용할 수 있다
```
fun numberDescription(n: Int, max: Int = 100): String = when (n) {
    0 -> "Zero"
    1, 2, 3 -> "Small"
    in 4..9 -> "Medium"
    in 10..max -> "Large"
    !in Int.MIN_VALUE until 0 -> "Negative"
    else -> "Huge"
}
```

### for loop and iterable

```
    val a = IntArray(10) { it * it }
    var sum = 0
    for (x in a) {
        sum += x
    }
    println("Sum: $sum")
```
1. 이터레이션에 담을 변수 정의(x)
2. 이터레이션에 사용할 값들이 담겨 있는 컨테이너를 계산하기 위한 식(a)
3. 루프 몸통에 해당하는 문 ({sum += 1}) 이터레이션 시 이 몸통이 실행됨
* 이터레이션 변수는 루프 몸통 안쪽에서만 접근할 수 있다.

### 루프 제어 흐름 변경하기 : break, continue

- break는 즉시 루프를 종료
- continue는 현재 루프 이터레이션을 마치고 조건 검사로 바로 진행하게 만듬


### 예외 처리

```
fun parseIntNumbers(s: String): Int {
    var num = 0
    
    if (s.length !in 1..31) throw NumberFormatException("Not a numbers: $s")
    
    for (c in s) {
        if (c !in '0'..'1') throw NumberFormatException("Not a number: $s")
        num = num * 2 + (c - '0')
    }
    
    return num
}
```
자바와 달리 new 구문을 사용하지 않고 NumberFormatException을 던질 수 있다.

예외처리의 순서
1. 예외를 잡아내는 핸들러를 찾는다. 일치하면 예외 핸들러가 처리
2. 핸들러를 찾을 수 없다면 함수 실행 종료. 함수가 스택에서 pop. 이후 호출한 쪽의 문맥 안에서 예외 핸들러 검색 수행 (propagate)
3. 프로그램 진입점에 이를때까지 예외를 잡지 못하면 스레드 종료

try catch는 자바에서와 유사