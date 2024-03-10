package task3

import java.lang.IllegalArgumentException

class Event(
    private val subject: CosmicObject,
    var action: Action = Action.NONE,
) {
    fun appears() {
        check(subject.status != Status.APPEARS_ON_SCREEN)

        subject.status = Status.APPEARS_ON_SCREEN
        action = Action.APPEARS_ON_SCREEN
    }

    fun disAppears() {
        check(subject.status == Status.APPEARS_ON_SCREEN)

        subject.status = Status.DISAPPEARS_FROM_SCREEN
        action = Action.DISAPPEARS_FROM_SCREEN
    }

    fun setDayNightState(status: Status) {
        check(subject.type == Type.PLANET)

        subject.status = status
        action =
            when (status) {
                Status.BECOME_DAY -> Action.BECOME_DAY
                Status.BECOME_NIGHT -> Action.BECOME_NIGHT
                else -> throw IllegalArgumentException()
            }
    }

    fun followedBy(followedBy: Star) {
        check(subject is Star)

        subject.followedBy = followedBy
        subject.status = Status.FOLLOW_BY
        action = Action.FOLLOW_BY
    }
}
