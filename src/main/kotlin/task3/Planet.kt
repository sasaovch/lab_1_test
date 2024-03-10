package task3

class Planet(
    name: String,
    size: Double,
) : CosmicObject(Type.PLANET, name, size) {
    override fun getInfo(): String {
        return "This is a Planet. " + super.getInfo()
    }
}
