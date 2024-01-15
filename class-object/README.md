# 클래스 정의

클래스 선언은 참조타입(referential type)을 정의

### 클래스 내부 구조
```
class Person {
    var firstName: String = ""
    var familyName: String = ""
    var age: Int = 0
    
    fun fullName() = "$firstName $familyName"
    
    fun showMe() {
        println("${fullName()}: $age")
    }
}

fun showAge(p: Person) = println(p.age)
fun readAge(p: Person) {
    p.age = readLine()!!.toInt()
}  

fun showFullName(p: Person) = println(p.fullName()) // call method 
```
모든 프로퍼티에서 일반적으로 쓸 수 있는 기능에는 변수처럼 프로퍼티를 사용하는 참조구문이 있다.

클래스 내부에서 this 식으로 수신 객체를 참조할 수 있다. 대부분의 경우 this를 디폴트로 가정하기 때문에 수신 객체의 멤버 안에서 수신 객체의 멤버를 참조할 때는 this를 생략해도 된다.

this가 꼭 필요한 경우는 클래스의 프로퍼티와 메서드 파라미터의 이름이 같은 경우
```
class Person {
    var firstName: String = ""
    var familyName: String = ""
    
    fun setName(firstName: String, familyName: String) {
        this.firstName = firstName
        this.familyName = familyName
    }
}
```

프로퍼티가 사용하는 내부 필드는 항상 캡슐화돼 있고, 클래스 정의 밖에서는 이 내부 필드에 접근할 수 없다.

```
fun main() {
    val person = Person()
    person.firstName = "John"
    person.familyName = "Doe"
    person.age = 25
    person.showMe()
}
```

생성자 호출을 사용하면 프로그램이 새 인스턴스에 대한 힙 메모리를 할당, 인스턴스의 상태를 초기화해주는 생성자 코드를 호출해준다.

기본적으로 코틀린의 클래스는 public. internal, private으로 설정할 수도 있다.

### 생성자

생성자는 클래스 인스턴스를 초기화해주고 인스턴스 생성 시 호출되는 함수
```
class Person(firstName: String, familyName: String) {
    val fullName = "$firstName $familyName"
}

fun main() {
    val person = Person("John", "Doe")
    println(person.fullName)
}
```
클래스명 이후에 붙은 파라미터는 클래스의 인스턴스를 생성할 때 클래스에 전달된다.

자바와는 달리 생성자를 호출할 때 new 키워드를 붙이지 않음.

클래스 헤더의 파라미터 목록을 primary constructor 선언이라 부른다.

primary constructor는 클래스 정의 내에서 프로퍼티 초기화와 초기화블록(init)이 등장하는 순서대로 구성

```
class Person(firstName: String, familyName: String) {
    val fullName = "$firstName $familyName"
    init {
        println("init : $fullName")
    }
}
```

초기화 블록에는 return 문이 들어가지 못함

```
class Person(fullName: String) {
    val firstName: String
    val familyName: String
    init {
        val names = fullName.split(" ")
        if (names.size != 2) {
            throw IllegalArgumentException("Invalid name: $fullName")
        }
        firstName = names[0]
        familyName = names[1]
    }
}

fun main() {
    val person = Person("John Doe")
    println(person.firstName)
}
```

```
class Person(firstName: String, familyName: String) {
    val firstNAme = firstName
    val fullName = "$firstName $familyName"
}
주 생성자 파라미터를 프로퍼티 초기화나 init 블록 밖에서 사용할 수 없다. 그래서 생성자 파라미터의 값을 저장할 멤버 프로퍼티를 정의해야한다.

간단하게 생성자 파라미터의 값을 멤버 프로퍼티로 만드는 방법이 있다.

class Person(val firstName: String, familyName: String) {
    val fullName = "$firstName $familyName"
}
```
주 생성자 파라미터에 val, var 키워드를 붙이면 자동으로 해당 생성자 파라미터로 초기화되는 프로퍼티를 정의한다.

```
class Person(val firstName: String, val familyName: String = "") {
}
```
이런식으로 사용할 수 있는데 이 때 본문을 아예 생략할 수도 있다. (intelliJ 코틀린 플러그인 추천)

```kotlin
class Person {
    val firstName: String
    val familyName: String
    
    constructor(firstName: String, familyName: String) {
        this.firstName = firstName
        this.familyName = familyName
    }
    constructor(fullName: String) {
        val names = fullName.split(" ")
        if (names.size != 2) {
            throw IllegalArgumentException("Invalid name: $fullName")
        }
        firstName = names[0]
        familyName = names[1]
    }
}
```

부생성자를 사용해 서로 다른 방법으로 초기화할 수 있다.

부생성자에는 val, var 키워드를 사용할 수 없다.

### 멤버 가시성

- public: 멤버를 어디서나 볼 수 있다. 디폴트 가시성
- internal: 멤버를 멤버가 속한 클래스가 포함된 컴파일 모듈 내부에서만 볼 수 있다
- protected: 멤버를 멤버가 속한 클래스와 멤버가 속한 클래스의 모든 하위 클래스 안에서 볼 수 있다.
- private: 멤버를 멤버가 속한 클래스 내부에서만 볼 수 있다.

```kotlin
class Person(private val firstName: String, private val familyName: String) {
    fun fullName() = "$firstName $familyName"
}

fun main() {
    val person = Person("John", "Doe")
//    println(person.firstName) -> error
    println(person.fullName())
}
```
주생성자의 가시성을 지정하려면 constructor 키워드 명시
```kotlin
class Empty private constructor() {
    fun showMe() = println("Empty")
}

fun main() {
//    Empty().showMe() -> 이 경우 Empty 클래스의 유일한 생성자가 private이므로 클래스 본문 외부에서 인스턴스화 할 수 없다.
}
```

### 내포된 클래스
함수, 프로퍼티, 생성자 외에 코틀린 클래스는 다른 클래스도 멤버로 가질 수 있다. (내포된 클래스)

```kotlin
class Person(val id: Id, val age: Int) {
    class Id(val firstName: String, val familyName: String)
    fun showMe() = println("${id.firstName} ${id.familyName}, $age")
}

fun main() {
    val id = Person.Id("John", "Doe")
    val person = Person(id, 25)
    person.showMe()
}
```
내포된 클래스에서도 가시성을 지정가능
```kotlin
class Person(private val id: Id, private val age: Int) {
    class Id(private val firstName: String, private val familyName: String) {
        fun nameSake(person: Person) = person.id.firstName == firstName
    }
    // 바깥쪽 클래스는 자신에게 내포된 클래스의 비공개 멤버에 접근 불가
    fun showMe() = println("${id.firstName} ${id.familyName}, $age")
}
```

내포된 클래스에 inner를 붙여야 자신을 둘러싼 외부 클래스의 현재 인스턴스에 접근 가능

- 자바에서의 inner 클래스와 비슷하다. 차이는 코틀린은 inner 변경자가 붙는다. 자바에서는 디폴트로 내부 클래스이지만 내부 클래스가 외부 클래스 인스턴스와 연관되지 않기는 원한다면 static을 붙여야한다.
- 하지만 코틀린에서는 inner가 없으면 외부 클래스 인스턴스와 연관되지 않는다.

### 지역 클래스

함수 본문에서 클래스를 정의할 수 있다.
```kotlin
fun main() {
    class Point(val x: Int, val y: Int) {
        fun shift(dx: Int, dy: Int): Point = Point(x + dx, y + dy)
        override fun toString() = "($x, $y)"
    }
    val p = Point(10, 10)
    println(p.shift(-1, 3))
}
```

```kotlin
fun main() {
    var x = 1
    
    class Counter {
        fun increment() {
            x++
        }
    }
    
    Counter().increment()
    
    println(x)
}
```

### 널 가능성
- 코틀린의 타입 시스템에는 null 값이 될 수 있는 참조 타입과 null 값이 될 수 없는 참조 타입을 확실히 구분해준다. null 발생 여부를 컴파일 시점으로 옮겨주기 때문에 null safe 한 환경을 구성할 수 있다.

#### 널이 될 수 있는 타입

- 코틀린에서는 기본적으로 모든 참조타입은 null이 될 수 없다. 따라서 String 같은 타입에 null 값을 대입할 수 없다. 

```kotlin
fun isLetterString(s: String): Boolean {
    if (s.isEmpty()) return false
    for (ch in s) {
        if (!ch.isLetter()) return false
    }
    return true
}

fun main() {
    println(isLetterString(null)) // error 발생
}
```

이런 함수는 코틀린 컴파일러에서 컴파일 시점에 방지해준다

코틀린에서 널이 될 수 있는 값을 받는 함수를 작성하려면
```kotlin
fun isBooleanString(s: String?) = s == "false" || s == "true"
```

파라미터 타입 뒤에 ? 를 붙여서 타입이 널이 될 수 있는 타입으로 지정해야 한다.

코틀린에서 String? 과 같이 널이 될 수 있는 타입을 nullable type이라 한다. ?가 붙은 타입은 원래 타입의 상위 타입이며, 원래 타입에 속하는 모든 값으로 이뤄진 집합을 null로 확장한 집합이다. (java에서의 wrapper class?)

nullable type은 하위타입의 값을 대입할 수 있다. 하지만 반대는 안된다.
```kotlin
fun main() {
    val s: String? = "abc"
    val ss: String = s //error 발생
}
```

nullable type은 원래 타입에 들어있는 어떤 프로퍼티나 메서드도 제공하지 않는다.
```kotlin
fun isLetterString(s: String?): Boolean {
    if (s.isEmpty()) return false
    for (ch in s) {
        if (!ch.isLetter()) return false
    }
    return true
}
```
String? 타입에 iterator() 메서드가 없기 때문에 for 루프를 사용해 이터레이션을 수행할 수는 없다.

#### 널 가능성과 스마트 캐스트

널이 될 수 있는 값을 처리하는 가장 직접적인 방법은 조건문을 사용해 null과 비교하는것
```kotlin
fun isLetterString(s: String?): Boolean {
    if (s == null) return false
    if (s.isEmpty()) return false
    for (ch in s) {
        if (!ch.isLetter()) return false
    }
    return true
}
```

스마트 캐스트를 실행하려면 대상 변수의 값이 검사 지점과 사용 지점 사이에서 변하지 않는다고 컴파일러가 확신할 수 있어야 한다. 즉 널 검사와 사용 지점 사이에서 값이 변경되는 경우에는 스마트캐스트가 작동하지 않음

#### 엘비스 연산자

널이 될 수 있는 값을 다룰 때 엘비스 연산자를 사용할 수 있다. 이것은 널을 대신할 디폴트 값을 지정할 수 있다.
```kotlin
fun sayHello(name: String?) {
    println("Hello, " + (name ?: "Unknown"))
}

fun main() {
    sayHello("John") // Hello, John
    sayHello(null) // Hello, Unknown
}
```

return이나 throw를 엘비스 연산자 오른쪽에 끼워넣거 early return 가능

