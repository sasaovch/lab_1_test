package task1

import java.lang.Math.PI
import java.math.BigDecimal
import java.math.BigDecimal.ONE
import java.math.BigDecimal.valueOf
import java.math.MathContext
import java.math.RoundingMode
import kotlin.math.pow

const val TAYLOR_SERIES_TERMS = 11

fun calcTangent(x: BigDecimal): BigDecimal {
    check(x.toDouble() in (-PI / 2)..<(PI / 2) && x.toDouble() != (-PI / 2))

    var sum = BigDecimal.ZERO
    for (i in 1..TAYLOR_SERIES_TERMS) {
        val ber = bernoulli(2 * i)
        val coef = valueOf(2.0.pow(2.0 * i))
        val coef1 = coef.minus(ONE)

        val minux = (-1.0).toBigDecimal().pow(i - 1)
        sum += minux *
            (
                coef *
                    coef1 *
                    ber *
                    x.pow(2 * i - 1)
            ).divide(
                factorial(2 * i),
                MathContext(18, RoundingMode.HALF_UP),
            )
    }
    return sum
}

fun bernoulli(n: Int): BigDecimal {
    if (n == 0) return ONE
    if (n > 2 && n % 2 != 0) return BigDecimal.ZERO

    val coef = (-1.0).toBigDecimal().divide((n + 1).toBigDecimal(), MathContext(18, RoundingMode.HALF_UP))
    val sum = sumOfBeronulli(n)
    return coef.multiply(sum)
}

fun sumOfBeronulli(n: Int): BigDecimal {
    var result = BigDecimal.ZERO
    for (k in 1..n) {
        val num = numberOfCombinations(n + 1, k + 1)
        val prev = bernoulli(n - k)
        result = result.plus(num.multiply(prev))
    }
    return result
}

fun numberOfCombinations(
    n: Int,
    k: Int,
): BigDecimal {
    return factorial(
        n,
    ).divide(factorial(k), MathContext(18, RoundingMode.HALF_UP))
        .divide(factorial(n - k), MathContext(18, RoundingMode.HALF_UP))
}

fun factorial(n: Int): BigDecimal {
    if (n == 0) return ONE

    var fact = ONE
    for (i in 1..n) {
        fact *= i.toBigDecimal()
    }
    return fact
}
