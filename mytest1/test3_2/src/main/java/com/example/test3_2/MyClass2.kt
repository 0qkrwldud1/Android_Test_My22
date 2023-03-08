package com.example.test3_2

// 최고 상위 영역 : 선언과 동시에 초깃값 할당.
//val name = "이상용"
val name2:String = "이상용2"
val num1 = 10;

val data4 : Int by lazy {
    println("lazy 테스트")
    10
}


class MyClass2 {
    // 클래스 안에 영역, 선언과 동시에 초깃값 할당.
//     var name4 :
    var name = "이상용3"
    var age = 40;
    val name2 = "이상용4"
}

class User {
    var name = "lsy"
    constructor (name: String) {
        this.name = name
    }
    fun someFun(){
        println("name: $name")
    }

}

class User2(name:String, age:Int) {
    init  {
        println("객체 생성 마다 init 실행")
    }
}

class User3(val name:String, age:Int) {
    init  {
        println("init 안에서는 주생성자 매개변수 사용가능 : $name, $age")
    }
    fun someFun(){
        println("someFun() name: $name")
    }
}

class User4 (name: String, age:Int, phone: String) {
    // 실제 작업은 주생성자에서 선언 후 작업

    //constructor(name: String, age:Int) : this(name)
    //constructor(name: String, age:Int, phone: String) : this(name) : this(name,age)
}

open class Super(name:String) {
}

class Sub: Super {
    constructor(name: String): super(name)
}

open class Super2 (name:String) {
    var superData = 10
    fun superFun () {
        println("super class")
    }
}

class Sub2 constructor(name: String) : Super2(name)


open class Super3 (name:String) {

    open var superData = 10

    open fun superFun () {
        println("super class")
    }
}

class Sub3(name:String) : Super3(name) {

    override var superData = 20

    override fun superFun () {
        println("super class override")
    }
}

open class Super4 () {

    protected var protectedData = 10
    var publicData = 10
    private var privateData = 10

}

class Sub4: Super4() {

    fun subFun(){
        protectedData ++
        publicData ++
        //privateData ++

    }
}

class NonDataClass(val name:String, val age:Int)

class DataClass(val name:String, val age:Int)

val obj = object {
    var data = 10
    fun some(){
        println("익명 클래스 테스트")
    }
}

open class Super5() {

    open var publicData = 10
    open fun some() {
        println("익명클래스 상속 테스트")

    }

}

val obj2 = object: Super5() {
    override var publicData = 20

    override fun some(){
        println("익명 클래스 상속 테스트")
    }
}

class Myclass {

    // 인스턴스 생성 후 접근
    var outdata = 20

    // 클래스명으로 접근
    companion object {
        var data = 10
        fun Some() {
            println("companion 테스트")
        }
    }
}

//람다 예제
val sum = {no1: Int, no2: Int -> no1 + no2}

// 매개변수 1개인경우 it사용하기

val sum2 = {no1: Int ->
    println(no1)
    30 }
// 함수타입 : (Int) -> Unit
val sum3: (Int) -> Unit = {println(it)}

fun FunTest (arg: (Int) -> Boolean): () -> String {
    val result = if (arg(10)) {
        "valid"
    } else {
        "invalid"
    }
    return {"고차함수 람다테스트 : $result"}
}


fun main() {

    // ? 를 이용해서 null 할당
    var data33: String?= null
    // ? 연산자를 사용해서 특정 함수에 접근시?. 기호를 사용하거나
    // !! 예외발생 연산자 사용

    data33?.length

    data33?.length ?: 0


    //println(data33!!.length ?: 0)


    val result5 = FunTest ({no -> no >0})
    println(result5())


    var result3 =sum(10,20)

    sum3(10)

    val result4 = sum2(10)
    println(result4)

    var result2 = Myclass.data
    println(result2)
    Myclass.Some()

    val myclass = Myclass()
    myclass.outdata


    println(obj2.publicData)

    println(obj.toString())


    val nondata1 = NonDataClass("lsy1",30)
    val nondata2 = NonDataClass("lsy2",20)
    val dataclass1 = DataClass("lsy3",40)
    val dataclass2 = DataClass("lsy3",40)

    println(nondata1.equals(nondata2)) // 메모리주솟값
    println(dataclass1.equals(dataclass2)) //데이터비교
    println(nondata1.toString())
    println(dataclass1.toString())


    var obj4 = Sub4()
    obj4.publicData


    var obj2 = Sub3("lsy")
    println(obj2.superData)
    obj2.superFun()

    var obj = Sub2("lsy")
    println(obj.superData)
    obj.superFun()

    var user5 = User3("lsy2",30)
    user5.someFun()

    var user2 = User2("lsy2",30)
    var user3 = User2("lsy2",30)
    var user4 = User2("lsy2",30)


    var myClass2 = MyClass2()
    myClass2.age
    // 객체 생성시 new 없이 생성자 호출하는 방법


    val user = User("lsy2")

    user.someFun()

    var data23 = arrayOf<Int>(10,20,30)
    for ( (index ,value) in data23.withIndex() ) {
        print(value)
        if( index !== data23.size -1) print(",")
    }

    var data22 = arrayOf<Int>(10,20,30)
    for (i in data22.indices) {
        print(data22[i])
        if( i !== data22.size -1) print(",")
    }


    println("======================")

    var sum1: Int = 0
    for( i in 1..10) {
        sum1 += i
    }
    println(sum1)


    var data21 : Any ="hi"
    val result21 = when (data21) {
        is String -> println("data is String")
        in 1..10 -> println("data is 1...10")
        else -> {
            println("data is not valid")
        }

    }

    println("when 표현식 사용으로 결과값 확인 : $result21")


    var data20 : Any ="hi"
    when (data20) {
        is String -> println("data is String")
        in 1..10 -> println("data is 1...10")
        else -> {
            println("data is not valid")
        }

    }


    var data = 10
    var result = if (data>0) {
        println("테스트")
        true
    } else {
        println("else테스트")
        false
    }
    println("result결과값 테스트: $result")

    val data14 : Array<Int> = Array(3,{0})
    data14[0] =10
    data14[1] =20
    data14.set(2,30)


    var data19 = "hi"
    when (data19) {
        "hi" -> println("data is hi")
        "hi2" -> println("data is hi2")
        else -> {
            println("data is not valid")
        }

    }


    println(
        """
array size : ${data14.size}
array data : ${data14[0]}, ${data14[1]}, ${data14.get(2)}
            """
)

    fun some ( data1: Int, data2: Int = 10) : Int {
        return data1 * data2
    }
    println(some(data2=200, data1 = 100))

    fun some2(test: Int, test2:Int) : Nothing {
         throw java.lang.Exception()
    }

    var n1 : Int?
     n1 = null

    var data13 : Nothing? = null

    var data12: Any = 10
    var data2: Any = "String"
    var data3: Any = MyClass2()

    fun test3() {
        println(data12)
        println(data2)
        println(data3)
    }
    var testxx = test3()
    println(testxx)


    fun addSum(no: Int):Int {
        var sum = 0
        for(i in 1..no){
            sum += i
        }
        return sum
    }
    val name = "yong"
    println("name: $name, sum: ${addSum(10)}")

    val str1 = "hi \n yong"
    val str2 = """
     hi
	 world
	 """
    println("str1: $str1")
    println("str2: $str2")

    var data1: Int = 10

    data1 = data1 + 10
    data1 = data1.plus(10)

    // 함수 내부에서는 선언만 가능.
    var name10: String
    // MyClass2 myclass2 = new MyClass2();
    var myclass2 = MyClass2()
    // val 재할당 안됨.
    //myclass2.name2 = "이상용5"
    myclass2.name = "이상용5"
    println("helloworld")
    println(myclass2.name)
    println(myclass2.age)
    println(myclass2.name2)
    println("lazy 테스트 및 결괏값 재할당해서 연산 확인 ")
    println(data4 + 10)


}