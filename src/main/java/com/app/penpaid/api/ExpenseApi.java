package com.app.penpaid.api;

import com.app.penpaid.model.Expense;
import com.app.penpaid.service.ExpenseService;
import com.app.penpaid.validation.ExpenseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
@RequestMapping("/expense/api")
public class ExpenseApi {

    private final ExpenseService expenseService;
    private final ExpenseValidator expenseValidator;

    @Autowired
    public ExpenseApi(ExpenseService expenseService, ExpenseValidator expenseValidator) {
        this.expenseService = expenseService;
        this.expenseValidator = expenseValidator;
    }

    @PostMapping("/new")
    public ResponseEntity<Expense> createExpense(@RequestBody List<Expense> expenseList) {
        expenseValidator.validateExpenseBody(expenseList);
        boolean isCreated = expenseService.createExpense(expenseList);
        return new ResponseEntity<>(isCreated ? CREATED : INTERNAL_SERVER_ERROR);
    }
}
