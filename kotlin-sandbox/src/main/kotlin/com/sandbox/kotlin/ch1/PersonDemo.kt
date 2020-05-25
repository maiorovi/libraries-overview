package com.sandbox.kotlin.ch1

class Person1(var name: String,
              var isMarried: Boolean)

fun main() {
    val p = Person1("qwerty", true)

    println(p.name)
    println(p.isMarried)

    p.name = "zxc"
    p.isMarried = false

    println(p.name)
    println(p.isMarried)

    println(Rectangle(20, 20).isSquare)
}

class Rectangle(val width: Int, val height: Int) {
    val isSquare: Boolean
        get() {
            return width == height;
        }
}

enum class Color(
        val red: Int,
        val green: Int,
        val blue: Int) {
    RED(255, 0, 0),
    GREEN(0, 255, 0);

    fun rgb() = (red * 256 + green) * 256 + blue
}