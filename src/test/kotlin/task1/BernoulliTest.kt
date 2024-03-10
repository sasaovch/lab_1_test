package task1

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.math.BigDecimal.valueOf
import java.math.MathContext
import java.math.RoundingMode

class BernoulliTest {
    @Test
    fun testBernoulli() {
        val knownBernoulli =
            listOf(
                valueOf(1),
                valueOf(-0.5),
                valueOf(1.0 / 6),
                valueOf(0),
                valueOf(-1.0 / 30),
                valueOf(0),
                valueOf(1.0 / 42),
                valueOf(0),
                valueOf(-1.0 / 30),
                valueOf(0),
                valueOf(5.0 / 66),
                valueOf(0),
                valueOf(-691.0 / 2730),
                valueOf(0),
                valueOf(7.0 / 6),
                valueOf(0),
                valueOf(-3617.0 / 510),
                valueOf(0),
                valueOf(43867.0 / 798),
                valueOf(0),
                valueOf(-174611.0 / 330),
                valueOf(0),
                valueOf(854513.0 / 138),
                valueOf(0),
                valueOf(-236364091.0 / 2730),
            )

        val mc = MathContext(9, RoundingMode.HALF_UP)
        knownBernoulli.forEachIndexed { index, expected ->
            val actual = bernoulli(index)
            val act = actual.round(mc).toDouble()
            val exp = expected.round(mc).toDouble()
            assertEquals(exp, act)
        }
    }
}
