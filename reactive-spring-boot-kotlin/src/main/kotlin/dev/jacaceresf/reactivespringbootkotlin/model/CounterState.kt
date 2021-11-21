package dev.jacaceresf.reactivespringbootkotlin.model

import java.time.ZoneId
import java.time.ZonedDateTime

class CounterState(
    val value: Long,
    val id: String,
    val at: ZonedDateTime = ZonedDateTime.now(ZoneId.of("UTC"))
) {
    fun toEvent(action: CounterAction) = CounterEvent(value, action, id, at)
}