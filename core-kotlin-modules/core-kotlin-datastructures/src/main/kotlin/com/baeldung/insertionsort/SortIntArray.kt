fun insertionSort(arr: IntArray) {
    val n = arr.size
    for (i in 1 until n) {
        val key = arr[i]
        var j = i - 1
        while (j >= 0 && arr[j] > key) {
            arr[j + 1] = arr[j]
            j--
        }
        arr[j + 1] = key
    }
}

fun insertionSortWithIntArray() {
    val myArray = intArrayOf(5, 2, 9, 3, 6)
    insertionSort(myArray)
    println("Sorted Array:")
    for (element in myArray) {
        println(element)
    }
}


