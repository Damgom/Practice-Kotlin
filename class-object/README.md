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

