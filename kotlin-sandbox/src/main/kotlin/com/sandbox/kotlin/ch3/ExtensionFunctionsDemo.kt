package strings.extensions

fun String.lastChar(): Char = this[length - 1]

fun <T> Collection<T>.joinToString(separator: String = ",",
                                   prefix: String = "",
                                   suffix: String = ""): String {
    val stringBuilder = StringBuilder()
    stringBuilder.append(prefix)

    for ((index, elem) in this.withIndex()) {
        stringBuilder.append(elem)

        if (index != size - 1) {
            stringBuilder.append(separator)
        }

    }

    return stringBuilder
            .append(suffix)
            .toString()
}

fun main() {
    println("qwerty".lastChar())

    println(listOf(1,2,3,4,5).joinToString())
}