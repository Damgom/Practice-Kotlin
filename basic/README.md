# 기본 문법

### 변수 정의
val keyword

```
fun main() {
    val a = readLine()!!.toInt()
    val b = readLine()!!.toInt()
    println(a + b)
}
```

- !! 는 not-null assertion 으로 readLine() 의 결과가 null인 경우 예외 발생
- typescript 에서의 !와 유사한 느낌인 것 같음

val n: Int = 100

val str: String = "Hello!" 와 같은 형식으로도 타입을 명시할 수 있음


### 가변 변수
val 대신 var 사용

```
var sum = 1
sum += 2
sum += 3
print(sum)

result = 6 의 결과를 얻을 수 있다
```
Double > Float > Long > Int > Short > Byte

이외 산술동작은 자바와 비슷

### 수 변환

자바와 달리 범위가 큰 타입에 사용돼야 하는 문맥에 범위가 작은 타입을 쓸 수 없다. ex. Int값을 Long변수에 대입할 수 없음

### 문자열

문자열 템플릿

```
import java.util.Date
fun main() {
    val name = readLine();
    println("Hello, $name!\n Today is ${Date()}")
}
```
간단한 변수참조의 경우 $name과 같이 중괄호를 생략해도 됨.

기본적으로는 ${} 안에 넣으면 됨

* 문자열에 대한 자바의 ==와 != 연산자는 참조동등성을 비교하기 때문에 실제 문자열 내용을 비교하려면 equals() 메서드를 사용해야한다. 하지만 코틀린에서는 ==가 기본적으로 equals()를 가리키는 편의 문법이기 때문에 equals()를 호출할 필요가 없다.

### 배열

```
val a = emptyArray<String>()
val b = arrayOf("hello", "world")
val c = arrayOf(1, 4, 9)

val size = readLine()!!.toInt()
val squares = Array(size) { (it + 1) * (it + 1) }

val array = intArrayOf(1, 2, 3) + 4
// result = [1, 2, 3, 4]
val array2 = intArrayOf(1, 2, 3) + intArrayOf(4, 5, 6)
// result = [1, 2, 3, 4, 5, 6]
```
