package dev.jacaceresf.kotlinmongo.kotlinmongopractice.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.math.BigDecimal

@Document("expense")
data class Expense(
    @Id
    val id: String,
    @Field(name = "name")
    @Indexed(unique = true)
    var expenseName: String,
    @Field(name = "category")
    var expenseCategory: ExpenseCategory,
    @Field(name = "amount")
    var expenseAmount: BigDecimal
)

enum class ExpenseCategory {
    ENTERTAINMENT, GROCERIES, RESTAURANT, UTILITIES, MISC
}