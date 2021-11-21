package dev.jacaceresf.kotlinmongo.kotlinmongopractice.repository

import dev.jacaceresf.kotlinmongo.kotlinmongopractice.model.Expense
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ExpenseRepository : MongoRepository<Expense, String> {

    @Query("{'name':?0}")
    fun findByName(name: String): Optional<Expense>

    @Query("{'name':?0, 'id':?1}")
    fun findByIdAndName(name: String, id: String): Optional<Expense>
}