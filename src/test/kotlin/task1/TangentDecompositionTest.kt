package task1

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.math.BigDecimal
import java.math.BigDecimal.valueOf
import java.math.MathContext
import java.math.RoundingMode
import java.util.stream.Stream
import kotlin.math.PI
import kotlin.math.tan
import kotlin.test.assertFailsWith

class TangentDecompositionTest {
    @Test
    fun `tangent of 0 is 0`() {
        val actual = calcTangent(BigDecimal.ZERO)
        val expected = BigDecimal.ZERO
        val mc = MathContext(9, RoundingMode.HALF_UP)
        val act = actual.round(mc).toDouble()
        val exp = expected.round(mc).toDouble()
        assertEquals(exp, act)
    }

    @Test
    fun `tangent of PI4 should be close to 1`() {
        val actual = calcTangent((PI / 4.0).toBigDecimal())
        val expected = 1.0.toBigDecimal()
        val mc = MathContext(9, RoundingMode.HALF_UP)
        val act = actual.round(mc).toDouble()
        val exp = expected.round(mc).toDouble()
        assertEquals(exp, act, 1E-6)
    }

    @Test
    fun `tangent of -PI4 should be close to -1`() {
        val actual = calcTangent((-PI / 4.0).toBigDecimal())
        val expected = (-1.0).toBigDecimal()
        val mc = MathContext(9, RoundingMode.HALF_UP)
        val act = actual.round(mc).toDouble()
        val exp = expected.round(mc).toDouble()
        assertEquals(exp, act, 1E-6)
    }

    @Test
    fun `throws IllegalArgumentException when input is multiple of PI2`() {
        assertFailsWith<IllegalStateException> {
            calcTangent((PI / 2.0).toBigDecimal())
        }
        assertFailsWith<IllegalStateException> {
            calcTangent((PI * 1.5).toBigDecimal())
        }
    }

    companion object {
        @JvmStatic
        fun testProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(valueOf(PI / 3)),
                Arguments.of(valueOf(PI / 4)),
                Arguments.of(valueOf(PI / 5)),
                Arguments.of(valueOf(PI / 6)),
                Arguments.of(valueOf(PI / 7)),
                Arguments.of(valueOf(PI / -3)),
                Arguments.of(valueOf(PI / -4)),
                Arguments.of(valueOf(PI / -5)),
                Arguments.of(valueOf(PI / -6)),
                Arguments.of(valueOf(PI / -7)),
            )
        }
    }

    @ParameterizedTest(name = "tangent of arbitrary angle should be close to tan of the same angle, arg {0}")
    @MethodSource("testProvider")
    fun parameterizedTestForTangentFunc(arg: BigDecimal) {
        val actual = calcTangent(arg)
        val expected = tan(arg.toDouble()).toBigDecimal()
        val mc = MathContext(9, RoundingMode.HALF_UP)
        val act = actual.round(mc).toDouble()
        val exp = expected.round(mc).toDouble()
        assertEquals(exp, act, 1E-2)
    }
}
