package task2

import org.assertj.core.api.SoftAssertions
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class ShellSortTest {
    @Test
    @DisplayName("Zero Seconds Length Test")
    fun testShellSortPositiveBlackBox() {
        val arr = intArrayOf(9, 8, 3, 7, 5, 6, 4, 1)
        val sortedArr = intArrayOf(1, 3, 4, 5, 6, 7, 8, 9)
        shellSortBlackBox(arr)
        assertArrayEquals(sortedArr, arr)
    }

    @Test
    fun testShellSortNegativeBlackBox() {
        val sortedArr = intArrayOf(-9, -8, -7, -6, -5, -4, -3, -1)
        val arr = intArrayOf(-1, -3, -4, -5, -6, -7, -8, -9)
        shellSortBlackBox(arr)
        assertArrayEquals(sortedArr, arr)
    }

    @Test
    fun testShellSortPositiveWhiteBox() {
        val arr = intArrayOf(9, 8, 3, 7, 5, 6, 4, 1)

        val sortedArr = intArrayOf(1, 3, 4, 5, 6, 7, 8, 9)
        val firstAttempt = intArrayOf(5, 6, 3, 1, 9, 8, 4, 7)
        val secondAttempt = intArrayOf(1, 3, 4, 5, 6, 7, 8, 9)
        val attempts = listOf(firstAttempt, secondAttempt)

        val softly = SoftAssertions()
        var attempt = 0

        var gap = getGapForShellSort(arr)
        while (gap > 0) {
            simpleSortForShell(arr, gap)
            gap = (gap - 1) / 3
            softly.assertThat(arr).isEqualTo(attempts[attempt++])
        }
        softly.assertThat(arr).isEqualTo(sortedArr)
        softly.assertAll()
    }

    @Test
    fun testGetGapForShellSort() {
        val softly = SoftAssertions()
        val arr = mutableListOf<Int>()

        val firstGap = 4
        val secondGap = 13
        val thirdGap = 40

        for (i in 0..10) {
            arr.add(i)
        }
        var gap = getGapForShellSort(arr.toIntArray())
        softly.assertThat(gap).isEqualTo(firstGap)

        for (i in 0..20) {
            arr.add(i)
        }
        gap = getGapForShellSort(arr.toIntArray())
        softly.assertThat(gap).isEqualTo(secondGap)

        for (i in 0..50) {
            arr.add(i)
        }
        gap = getGapForShellSort(arr.toIntArray())
        softly.assertThat(gap).isEqualTo(thirdGap)

        softly.assertAll()
    }
}
