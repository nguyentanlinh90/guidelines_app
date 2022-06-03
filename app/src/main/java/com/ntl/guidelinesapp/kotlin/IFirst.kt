package com.ntl.guidelinesapp.kotlin

interface IFirst {
    fun add()
    fun add1() {
        println(this::class.simpleName)
    }
}