package task3

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException

class CosmicObjectTest {
    @Nested
    inner class StarTest {
        @Test
        fun testAppearOnScreen() {
            val star = Star("Betelgeuse", 900.0)
            val event = Event(star)
            assertEquals(Status.NONE, star.status) // Проверяем, что звезда ещё не появилась на экране
            event.appears()
            assertEquals(Status.APPEARS_ON_SCREEN, star.status) // Проверяем, что звезда появилась на экране после вызова метода
            assertEquals(Action.APPEARS_ON_SCREEN, event.action) // Проверяем, что звезда появилась на экране после вызова метода
        }

        @Test
        fun testDisappearOnScreen() {
            val star = Star("Betelgeuse", 900.0)
            val event = Event(star)
            event.appears()
            event.disAppears()
            assertEquals(Status.DISAPPEARS_FROM_SCREEN, star.status) // Проверяем, что звезда появилась на экране после вызова метода
            assertEquals(Action.DISAPPEARS_FROM_SCREEN, event.action) // Проверяем, что звезда появилась на экране после вызова метода
        }

        @Test
        fun testDisappearOnScreenThrowIllegalState() {
            val star = Star("Betelgeuse", 900.0)
            val event = Event(star)
            assertThrows(IllegalStateException::class.java) {
                event.disAppears()
            }
        }

        @Test
        fun testFollowedBy() {
            val star = Star("Betelgeuse", 900.0)
            val followedBy = Star("Some star", 1000.0)
            val event = Event(star)
            assertEquals(Status.NONE, star.status) // Проверяем, что звезда ещё не появилась на экране
            event.followedBy(followedBy)
            assertEquals(Status.FOLLOW_BY, star.status) // Проверяем, что звезда появилась на экране после вызова метода
            assertEquals(Action.FOLLOW_BY, event.action) // Проверяем, что звезда появилась на экране после вызова метода
            assertEquals(followedBy, star.followedBy)
        }
    }

    @Nested
    inner class PlanetTest {
        @Test
        fun setDayNightState() {
            val planet = Planet("Gas Giant", 900.0)
            val event = Event(planet)
            assertAll(
                {
                    event.setDayNightState(Status.BECOME_DAY)
                    assertEquals(
                        Status.BECOME_DAY,
                        planet.status,
                    ) // Проверяем, что звезда появилась на экране после вызова метода
                    assertEquals(
                        Action.BECOME_DAY,
                        event.action,
                    ) // Проверяем, что звезда появилась на экране после вызова метода
                },
                {
                    event.setDayNightState(Status.BECOME_NIGHT)
                    assertEquals(
                        Status.BECOME_NIGHT,
                        planet.status,
                    ) // Проверяем, что звезда появилась на экране после вызова метода
                    assertEquals(
                        Action.BECOME_NIGHT,
                        event.action,
                    ) // Проверяем, что звезда появилась на экране после вызова метода
                },
            )
        }

        @Test
        fun setDayNightStateWithWrongStatus() {
            val planet = Planet("Gas Giant", 900.0)
            val event = Event(planet)
            assertThrows(IllegalArgumentException::class.java) { event.setDayNightState(Status.APPEARS_ON_SCREEN) }
        }

        @Test
        fun setDayNightStateForStar() {
            val star = Star("Betelgeuse", 900.0)
            val event = Event(star)
            assertThrows(IllegalStateException::class.java) {
                event.setDayNightState(Status.BECOME_DAY)
            }
        }
    }

    @Nested
    inner class GetInfoTest {
        @Test
        fun getInfoPlanet() {
            val planet = Planet("Gas Giant", 900.0)
            val expectedInfo = "This is a Planet. Type: PLANET, Name: Gas Giant, Size: 900.0"
            val actualInfo = planet.getInfo()
            assertEquals(expectedInfo, actualInfo)
        }

        @Test
        fun getInfoStar() {
            val star = Star("Betelgeuse", 1000.0)
            val expectedInfo = "This is a Star. Type: STAR, Name: Betelgeuse, Size: 1000.0"
            val actualInfo = star.getInfo()
            assertEquals(expectedInfo, actualInfo)
        }
    }
}
