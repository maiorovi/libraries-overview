
val testFunction = { str: String -> str.contains("bu|ba|be".toRegex()) }

val str = "aqwefbu"
val str1 = "aqwef"
val str2 = "buaqbywef"
val str3 = "aqwefub"


testFunction(str)
testFunction(str1)
testFunction(str2)
testFunction(str3)