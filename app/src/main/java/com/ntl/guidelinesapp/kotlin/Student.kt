package com.ntl.guidelinesapp.kotlin

class Student(name: String) { // ham khoi tao
    val subObjects = mutableListOf<String>()

    init {
        // khoi khoi tao
        println("Student name $name")
    }

    constructor(name: String, list: List<String>) : this(name) {
        // them ham khoi tao thi phai su dung lai ham khoi tao dau tien
        subObjects.addAll(list)
    }
}

class C(val a: Int = 0)

open class Animal(name: String) // co tu khoa open thi moi ke thua duoc
class Cat(name: String) : Animal(name)

open class Animal1 {
    constructor(name: String)
    constructor(name: String, legs: Int)
}

class Cat1 : Animal1 {
    constructor(name: String, color: String) : super(name, 4)
}


fun main() {
    val student = Student(name = "Van A", list = listOf("Toan", "Van"))
    val c = C()
    println(c.a)
}