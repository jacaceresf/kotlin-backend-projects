package dev.jacaceresf.reactivespringbootkotlin.model

import java.time.ZoneId
import java.time.ZonedDateTime


enum class CounterAction { UP, DOWN }

class CounterEvent(
    val value: Long,
    val action: CounterAction,
    val id: String,
    val at: ZonedDateTime = ZonedDateTime.now(ZoneId.of("UTC"))
) {
}