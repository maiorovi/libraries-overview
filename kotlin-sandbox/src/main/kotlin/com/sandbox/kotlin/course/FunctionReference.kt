package com.sandbox.kotlin.course

class Person(val name: String, val age:Int) {
    fun isOlder(ageLimit: Int) = age > ageLimit
}




fun main() {
    val person = Person("Yehor", 29)

    val agePredicateUnboundedReference = Person::isOlder
    val agePredicateBoundedReference = person::isOlder

    println(agePredicateUnboundedReference(person, 25))
    println(agePredicateBoundedReference(25))
}