val list = listOf(1, 2, 3, 4, 5, 4)


list

list.map { it * 2 }

list.filter { it % 2 == 0 }

list.find { it == 4 }

list.find { it == 10 }

list.firstOrNull { it == 10}
list.firstOrNull ({ it == 4})

val (even, odd) = list.partition { it % 2 == 0 }

list.groupBy { it * 10 } // groups by key key -> List<Values>

list.associateBy { it * 20 } // the same as group by but


list.associate { it * 20 to it}

list.zip(listOf(10, 11, 12, 13, 14))

list.zipWithNext()