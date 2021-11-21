package dev.jacaceresf.reactivespringbootkotlin.controller

import dev.jacaceresf.reactivespringbootkotlin.model.CounterEvent
import dev.jacaceresf.reactivespringbootkotlin.model.CounterState
import dev.jacaceresf.reactivespringbootkotlin.repository.CounterRepository
import kotlinx.coroutines.flow.Flow
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CounterController(private val counterRepository: CounterRepository) {

    @GetMapping("/")
    suspend fun get(): CounterState = counterRepository.get()

    @PutMapping("/up")
    suspend fun up(): CounterState = counterRepository.up()

    @PutMapping("/down")
    suspend fun down(): CounterState = counterRepository.down()

    @GetMapping(value = ["/"], produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    suspend fun stream(): Flow<CounterEvent> = counterRepository.stream()
}