package com.example.ExTest0221

import java.io.InputStream
import java.util.*

class User(id:String, pwd:String, email:String, phone:String) {

    var id = "admin"
    var pwd = "1234"
    var email = "1@naver.com"
    var phone = "1234"

}

class Login {

    companion object {
        fun loginTest(user: User){
            if(user.id.equals("admin")&&user.pwd.equals("1234")
                &&user.email.equals("1@naver.com")&&user.phone.equals("1234"))
                println("로그인 성공")
        }

    }
}

fun main() {

    val scan = Scanner(System.`in`)

    println("id: ")
    val id = scan.nextLine()

    println("pwd: ")
    val pwd = scan.nextLine()

    println("email: ")
    val email = scan.nextLine()

    println("phone: ")
    val phone = scan.nextLine()

    val lsy = User(id,pwd,email,phone)

    Login.loginTest(lsy)
}
