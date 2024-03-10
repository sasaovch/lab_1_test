package task2

fun shellSortBlackBox(arr: IntArray) {
    if (arr.isEmpty()) {
        return
    }
    var h = getGapForShellSort(arr)

    while (h > 0) {
        simpleSortForShell(arr, h)
        h = (h - 1) / 3
    }
}

fun getGapForShellSort(arr: IntArray): Int {
    var h = 1
    while (h <= arr.size / 3) {
        h = h * 3 + 1
    }
    return h
}

fun simpleSortForShell(
    arr: IntArray,
    h: Int,
) {
    var inner: Int
    var temp: Int

    for (outer in h..<arr.size) {
        temp = arr[outer]
        inner = outer

        while (inner > h - 1 && arr[inner - h] >= temp) {
            arr[inner] = arr[inner - h]
            inner -= h
        }
        arr[inner] = temp
    }
}
