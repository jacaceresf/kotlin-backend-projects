package dev.jacaceresf.reactivespringbootkotlin.repository

import dev.jacaceresf.reactivespringbootkotlin.model.CounterAction
import dev.jacaceresf.reactivespringbootkotlin.model.CounterEvent
import dev.jacaceresf.reactivespringbootkotlin.model.CounterState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.springframework.data.redis.core.*
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class CounterRepository(
    private val redisTemplate: ReactiveRedisTemplate<String, CounterEvent>
) {

    companion object {
        private const val COUNTER_KEY = "COUNTER"
        private const val COUNTER_CHANNEL = "COUNTER_CHANNEL"
    }

    suspend fun get(): CounterState =
        CounterState(redisTemplate.opsForValue().incrementAndAwait(COUNTER_KEY, 0L), UUID.randomUUID().toString())

    suspend fun up(): CounterState =
        CounterState(redisTemplate.opsForValue().incrementAndAwait(COUNTER_KEY), UUID.randomUUID().toString()).also {
            redisTemplate.sendAndAwait(COUNTER_CHANNEL, it.toEvent(CounterAction.UP))
        }

    suspend fun down(): CounterState =
        CounterState(redisTemplate.opsForValue().decrementAndAwait(COUNTER_KEY), UUID.randomUUID().toString()).also {
            redisTemplate.sendAndAwait(COUNTER_CHANNEL, it.toEvent(CounterAction.DOWN))
        }

    suspend fun stream(): Flow<CounterEvent> =
        redisTemplate.listenToChannelAsFlow(COUNTER_CHANNEL).map { it.message }
}