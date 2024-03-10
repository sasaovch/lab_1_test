package task3

import org.assertj.core.api.SoftAssertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class StarSystemTest {
    @Test
    fun addStarTest() {
        val star = Star("Some Star", 900.0)
        val system = StarSystem(type = SystemType.BINARY)

        val softAssertions = SoftAssertions()
        softAssertions.assertThat(system.stars.isEmpty()).isTrue
        system.addStar(star)
        softAssertions.assertThat(system.stars.size).isEqualTo(1)
        softAssertions.assertThat(star).isEqualTo(system.stars[0])
        softAssertions.assertAll()
    }

    @Test
    fun addPlanetTestWithoutStar() {
        val planet = Planet("Some planet", 900.0)
        val system = StarSystem(type = SystemType.BINARY)

        assertThrows(IllegalStateException::class.java) {
            system.addPlane(planet)
        }
    }

    @Test
    fun addPlanetTest() {
        val star = Star("Some Star", 900.0)
        val planet = Planet("Some planet", 900.0)
        val system = StarSystem(type = SystemType.BINARY)

        system.addStar(star)
        val softAssertions = SoftAssertions()

        softAssertions.assertThat(system.planets.isEmpty()).isTrue
        system.addPlane(planet)
        softAssertions.assertThat(system.planets.size).isEqualTo(1)
        softAssertions.assertThat(planet).isEqualTo(system.planets[0])
        softAssertions.assertAll()
    }

    @Test
    fun addMoreStarsToBinarySystem() {
        val star1 = Star("Star 1", 900.0)
        val star2 = Star("Star 2", 900.0)
        val star3 = Star("Star 3", 900.0)
        val system = StarSystem(type = SystemType.BINARY)

        system.addStar(star1)
        system.addStar(star2)

        assertThrows(IllegalStateException::class.java) {
            system.addStar(star3)
        }
    }

    @Test
    fun addMoreStarsToMultiplesSystem() {
        val star1 = Star("Star 1", 900.0)
        val star2 = Star("Star 2", 900.0)
        val star3 = Star("Star 3", 900.0)
        val system = StarSystem(type = SystemType.MULTIPLES)

        system.addStar(star1)
        system.addStar(star2)
        system.addStar(star3)

        assertTrue(system.stars.size == 3)
        assertEquals(listOf(star1, star2, star3), system.stars)
    }
}
