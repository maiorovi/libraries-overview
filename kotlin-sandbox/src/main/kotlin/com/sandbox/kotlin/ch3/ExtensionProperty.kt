package com.sandbox.kotlin.ch3

import java.lang.StringBuilder

val String.lastChar: Char
    get() {
        return this.get(this.length - 1)
    }


var StringBuilder.lastChar: Char
    get() {
        return this.get(this.length - 1)
    }
    set(value) {
        this.setCharAt(this.length - 1, value)
    }


fun main() {
    println("qwerty".lastChar)

    val sb = StringBuilder("qwerty");
    println(sb.lastChar)
    sb.lastChar = 'Q'

    println(sb.toString())
}