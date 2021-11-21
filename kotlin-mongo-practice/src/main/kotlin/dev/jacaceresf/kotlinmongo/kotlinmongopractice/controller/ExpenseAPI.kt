package dev.jacaceresf.kotlinmongo.kotlinmongopractice.controller

import dev.jacaceresf.kotlinmongo.kotlinmongopractice.model.Expense
import dev.jacaceresf.kotlinmongo.kotlinmongopractice.service.ExpenseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/expense")
class ExpenseAPI(
    @Autowired val expenseService: ExpenseService
) {

    @PostMapping
    fun addExpense(@RequestBody expense: Expense): ResponseEntity<String> {
        expenseService.addExpense(expense)
        return ResponseEntity.status(HttpStatus.ACCEPTED).build()
    }

    @PatchMapping
    fun updateExpense(@RequestBody expense: Expense): ResponseEntity<String> {
        expenseService.updateExpense(expense)
        return ResponseEntity.ok().build()
    }

    @GetMapping
    fun getExpenses() = ResponseEntity.ok(expenseService.getAllExpenses())

    @GetMapping("/name/{name}")
    fun getExpenseByName(@PathVariable name: String) = ResponseEntity.ok(expenseService.getExpenseByName(name))

    @GetMapping("/name/{name}/id/{id}")
    fun getExpenseByName(@PathVariable name: String, @PathVariable id: String) =
        ResponseEntity.ok(expenseService.getExpenseByNameAndId(name, id))

    @DeleteMapping("/id/{id}")
    fun deleteExpense(@PathVariable id: String): ResponseEntity<String> {
        expenseService.deleteExpense(id)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }
}