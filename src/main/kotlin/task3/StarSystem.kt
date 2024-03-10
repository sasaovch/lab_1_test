package task3

class StarSystem(
    val stars: MutableList<Star> = mutableListOf<Star>(),
    val planets: MutableList<Planet> = mutableListOf<Planet>(),
    val type: SystemType,
) {
    fun addStar(star: CosmicObject) {
        check(star.type == Type.STAR)

        when (type) {
            SystemType.BINARY -> addStarToBinary(star as Star)
            SystemType.MULTIPLES -> addStarToMultiples(star as Star)
        }
    }

    fun addPlane(planet: CosmicObject) {
        check(stars.isNotEmpty())
        check(planet.type == Type.PLANET)

        planets.add(planet as Planet)
    }

    private fun addStarToBinary(star: Star) {
        if (type == SystemType.BINARY) {
            check(stars.size < 2)
        }

        stars.add(star)
    }

    private fun addStarToMultiples(star: Star) {
        stars.add(star)
    }
}

enum class SystemType {
    BINARY,
    MULTIPLES,
}
