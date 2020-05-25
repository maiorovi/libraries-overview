package com.sandbox.kotlin

import com.sandbox.kotlin.Color.BLUE
import com.sandbox.kotlin.Color.GREEN
import com.sandbox.kotlin.Color.INDIGO
import com.sandbox.kotlin.Color.ORANGE
import com.sandbox.kotlin.Color.RED
import com.sandbox.kotlin.Color.VIOLET
import com.sandbox.kotlin.Color.YELLOW

fun main(args: Array<String>) {
//    val name = if (args.size > 0) args[0] else "Kotlin"
//    println("Hello, $name")
//
//    identifyColor(BLUE)
//    println(respondToInput("y"))
//    println(respondToInput("yese"))
//    println(respondToInput("yes"))
//    println(respondToInput("no"))
//    executeMyPet()
//
//    println(isNotDigit('1'))
//    println(isNotDigit('q'))
//    println(isNotDigit(' '))

    println(isValidIdentifier("_name"));
    println("qwerty1".lastChar());
}

fun max(a: Int, b: Int): Int = if (a > b) a else b

enum class Color {
    BLUE, ORANGE, RED, GREEN, INDIGO, YELLOW, VIOLET;
}

fun identifyColor(c: Color) {
    when (c) {
        BLUE -> println("cold")
        ORANGE -> println("mild")
        else -> println("hot")
    }
}

//match one of arguments
fun respondToInput(input: String): String = when (input) {
    "y", "yes" -> "I'm glad you agree"
    "n", "no" -> "Sorry to hear that"
    else -> "I don't understand you"
}

//matching against collection
//kotlin uses equals to check taht collections matches other collection
fun mix(c1: Color, c2: Color) = when (setOf(c1, c2)) {
    setOf(RED, YELLOW) -> ORANGE
    setOf(YELLOW, BLUE) -> GREEN
    setOf(BLUE, VIOLET) -> INDIGO
    else -> throw Exception("dirty color")
}

interface Pet

class Cat : Pet {
    fun meow() {
        println("Meow meow");
    }
}

class Dog : Pet {
    fun woof() {
        println("Woof! Woof!")
    }
}

fun getMyFavoritePet(): Pet = Cat()

fun executeMyPet() {
    when (val pet = getMyFavoritePet()) {
        is Cat -> pet.meow()
        is Dog -> pet.woof()
    }
}

fun isValidIdentifier(s: String): Boolean {
    return when {
        s.isEmpty() -> false
        isNotUnderscore(s[0]) && isNotLetter(s[0]) -> false
        containsNotDigitOrNotLetters(s) -> false
        else -> true
    }
}

fun containsNotDigitOrNotLetters(s: String): Boolean {
    for (ch in s) {
        if (isNotLetter(ch) && isNotDigit(ch) && isNotUnderscore(ch)) {
            return false;
        }
    }

    return true;
}

fun isNotUnderscore(ch: Char): Boolean = ch != '_';
fun isNotLetter(ch: Char): Boolean = ch !in 'a'..'z' && ch !in 'A'..'Z'
fun isNotDigit(ch: Char): Boolean = ch !in '0'..'9'

fun String.lastChar(): Char {
    return this.get(this.length - 1);
}