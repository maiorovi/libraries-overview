package com.sandbox.kotlin.course

fun main(args: Array<String>) {
    val s = "dsf"
    println(s as? Int)    // null
//    println(s as Int?)    // exception


    val s1 = "qwerty";

    val s2: String? = s1

    println(s2?.length)

    val s3: String? = null

    println(s3?.get(0) ?: "default value")
}