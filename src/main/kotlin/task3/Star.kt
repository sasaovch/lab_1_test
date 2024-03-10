package task3

class Star(
    name: String,
    size: Double,
    var followedBy: Star? = null,
) : CosmicObject(Type.STAR, name, size) {
    override fun getInfo(): String {
        return "This is a Star. " + super.getInfo()
    }
}
