package dev.jacaceresf.kotlinmongo.kotlinmongopractice.service

import dev.jacaceresf.kotlinmongo.kotlinmongopractice.model.Expense
import dev.jacaceresf.kotlinmongo.kotlinmongopractice.repository.ExpenseRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ExpenseService(@Autowired val expenseRepository: ExpenseRepository) {

    fun addExpense(expense: Expense) = expenseRepository.insert(expense)

    fun updateExpense(expense: Expense) {
        val savedExpense: Expense = expenseRepository
            .findById(expense.id)
            .orElseThrow { throw RuntimeException("Cannot find Expense by ID") }

        savedExpense.apply {
            expenseName = expense.expenseName
            expenseCategory = expense.expenseCategory
            expenseAmount = expense.expenseAmount
        }

        expenseRepository.save(savedExpense)
    }

    fun getAllExpenses(): List<Expense> = expenseRepository.findAll()

    fun getExpenseByName(name: String): Expense =
        expenseRepository.findByName(name).orElseThrow { java.lang.RuntimeException("Expense not found!") }

    fun getExpenseByNameAndId(name: String, id: String): Expense =
        expenseRepository.findByIdAndName(name, id)
            .orElseThrow { RuntimeException("Expense with name $name and id $id not found") }

    fun deleteExpense(id: String) = expenseRepository.deleteById(id)
}