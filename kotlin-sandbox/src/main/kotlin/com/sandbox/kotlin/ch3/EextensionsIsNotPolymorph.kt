package com.sandbox.kotlin.ch3

open class View {
    open fun click() {
        println("Click on a view");
    }
}

class Button : View() {
    override fun click() {
        println("Click on a button");
    }
}

fun View.showOff() = println("I am view")
fun Button.showOff() = println("I am button")

fun main() {
    println("Polymorphism in kotlin demo")

    val view: View = Button()

    view.click() //Click on a button
    view.showOff() //I am view

}