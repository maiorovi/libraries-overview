package com.sandbox.kotlin.ch1

import java.util.*

sealed class Expr
class Num(val v: Int) : Expr()
class Sum(val e1: Expr, val e2: Expr) : Expr()

fun evaluate(expr: Expr): Int = when (expr) {
    is Num -> expr.v
    is Sum -> evaluate(expr.e1) + evaluate(expr.e2)
}


fun main() {
    val result = evaluate(Sum(Sum(Num(3), Num(4)), Num(3)))

    println(result)

    ranges1()
}

fun ranges1() {
    val chars = TreeMap<Char, String>()

    for (i in 'A'..'F') {
        val binaryValue = Integer.toBinaryString(i.toInt())
        chars[i] = binaryValue
    }

    for ((letter, binary) in chars) {
        println("$letter = $binary")
    }
}