package com.ntl.guidelinesapp.core

import android.os.Build
import androidx.annotation.RequiresApi
import java.lang.Exception
import java.util.function.BinaryOperator
import java.util.function.IntBinaryOperator

fun main() {
    val a = 1 // immutable
    var b = 2 //mutable
    b += 1
    //todo
    println(add(4, 5))
    println(add1(4, 5))
    //todo
    val a1 = 6
    println("value of a = $a")
    println("value of sum a and b = ${a + b}")
    //todo
    println(maxOf(a, b))
    // can null with ?
    val abc = parser("45")
    //todo
    println(checkString(32))
    println(length(32))
    //todo for
    val arr = listOf(1, 3, 45, 5)
    for (item in arr) {
        println(item)
    }
    for (index in arr.indices) {
        println(arr[index])
    }
    //todo while
    var index = 0
    while (index < arr.size) {
        println("item at $index is: ${arr[index]}")
        index++
    }
    //todo when
    println(description(1))
    //todo ranges
    val range = 1..9
    for (item in range) {
        println(item)
    }
    for (item in range step 2) {
        println(item) // 1 3 5 7 9
    }
    for (item in 9 downTo 0 step 3) {
        println(item) // 9 6 3 0
    }
    //todo collection
    when {
        45 in arr -> println("true")
        else -> println("false")
    }
    arr.filter { it > 1 }
        .sortedBy { it }
        .forEach { println(it) }
    //todo class
    val rectangle = Rectangle(4, 5)
    rectangle.printArea()

    //todo Data type
    // Byte(8 bits -128 -> 127), Short(16 bits), Int(32 bits), Long(64 bits)
    val one = 1 //Int
    val fourBillion = 4000000000 //Long
    val fiveLong = 5L //Long
    val oneByte: Byte = 1
    // Float (32 bits), Double (64 bits)
    val oneMillion = 1_000_000
    val cardNumber = 1234_5678_1234_5678L
    val socialNumber = 999_99_9999L
    val bytes = 0b110010101_010101_101010100_100101010

    val ab: Int? = 1 // can null
    val bc: Long? = a?.toLong()
    println(bc == ab?.toLong())

    val s = "ab"
    //println(s.toInt()) // fail
    val l = 4f + 1
    /*
        shl(bits) - dịch trái số có dấu
        shr(bits) - dịch phải số có dấu
        ushr(bits) - phải số không dấu
        and(bits) - bitwise and
        or(bits) - bitwise or
        inv(bits) - bitwise inversion
    */
    /*
        kiểm tra bằng a == b, a != b
        so sánh toán hạng: a< b, a>b, a<=b, a>=b
        dãy và kiểm tra dãy: a..b, x in a..b, x !in a..b
    */
    val r = 1..9
    val x = 3
    if (x in r) {
        println(x) //3
    }

    //array
    val ar = arrayOf(1, "2", 3) //[1,2,3]
    val aNull = arrayOfNulls<Int>(5)
    val asc = Array(4) { i -> (i * i).toString() }
    asc.forEach { it -> println(it) } //0 1 4 9

    val ra = 1u..9u // so khong dau Int
    val xy = 13u // so khong dau Int

    //Generics
    val oneList = createList<Int>(1)
    sort(listOf<Int>(3, 4, 5))

    println(IntArithmetics.PLUS.applyAsInt(4, 5)) //9
    println(IntArithmetics.PLUS.apply(4, 5)) //9
    println(IntArithmetics.valueOf("PLUS").apply(4, 6)) //10

    IntArithmetics.values().forEach {
        println(it.name) // PLUS, TIMES
    }

    printAllValue<IntArithmetics>() //PLUS; TIMES
}

inline fun <reified T : Enum<T>> printAllValue() {
    println(enumValues<T>().joinToString("; ") { it.name })
}

enum class Ocean {
    ocean1, ocean2, ocean3
}

enum class Level(mark: Float) {
    very_good(0.8f),
    good(7.0f),
    fail(3.0f)
}

enum class ToggleState {
    ON {
        override fun toggle(): ToggleState {
            return OFF
        }
    },
    OFF {
        override fun toggle(): ToggleState {
            return ON
        }
    };

    abstract fun toggle(): ToggleState
}

@RequiresApi(Build.VERSION_CODES.N)
enum class IntArithmetics : BinaryOperator<Int>, IntBinaryOperator {
    PLUS {
        override fun apply(t: Int, u: Int): Int {
            return t + u
        }
    },
    TIMES {
        override fun apply(t: Int, u: Int): Int {
            return t * u
        }
    };

    override fun applyAsInt(left: Int, right: Int): Int {
        return applyAsInt(left, right)
    }
}

fun <T> T.toBaseString(): String {
    return "toBaseString $this"
}

fun <T : Comparable<T>> sort(list: List<T>) {

}

fun <T> createList(item: T): List<T> {
    return listOf(item);
}


class Rectangle(private val width: Int, private val height: Int) {
    fun areas() = width * height
    fun printArea() {
        println("Area of this: ${areas()}")
    }
}

fun description(obj: Any): String = when (obj) {
    1 -> "One"
    "hello" -> "Greeting"
    is Long -> "Long"
    else -> "UnKnow"
}

fun length(abc: Any): Int? {
    if (abc !is String) {
        return null
    }
    return abc.length
}

fun checkString(abc: Any): Boolean? {
    return abc is String
}

fun parser(str: String): Int? {
    return try {
        str.toInt()
    } catch (e: Exception) {
        null
    }
}

fun maxOf(a: Int, b: Int) = if (a > b) a else b

fun add(a: Int, b: Int): Int {
    return a + b
}

fun add1(a: Int, b: Int) = a + b

fun add2(a: Int, b: Int): Unit {
    println(add(4, 5))
    return Unit
}