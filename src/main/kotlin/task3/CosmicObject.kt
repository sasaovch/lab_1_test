package task3

abstract class CosmicObject(
    val type: Type,
    private val name: String,
    private val size: Double,
    var status: Status = Status.NONE,
) {
    open fun getInfo(): String {
        return "Type: $type, Name: $name, Size: $size"
    }
}

enum class Action {
    NONE,
    APPEARS_ON_SCREEN,
    DISAPPEARS_FROM_SCREEN,
    FOLLOW_BY,
    BECOME_NIGHT,
    BECOME_DAY,
}

enum class Status {
    NONE,
    APPEARS_ON_SCREEN,
    DISAPPEARS_FROM_SCREEN,
    FOLLOW_BY,
    BECOME_NIGHT,
    BECOME_DAY,
}

enum class Type {
    PLANET,
    STAR,
}
