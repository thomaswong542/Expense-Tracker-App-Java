package com.project.expenseTrackerAPI.backend.controller;

import com.project.expenseTrackerAPI.backend.customException.BadRequestException;
import com.project.expenseTrackerAPI.backend.customException.NotFoundException;
import com.project.expenseTrackerAPI.backend.data.DataValidation;
import com.project.expenseTrackerAPI.backend.database.DataSourceConfiguration;
import com.project.expenseTrackerAPI.backend.model.Expense;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

// Endpoint
// get: v1/expense
// post: v1/expense
// update v1/expense/id
// delete v1/expense/id

@RestController
@RequestMapping(path = "v1/expense")
public class ExpenseController {

    private final DataSourceConfiguration dataSourceConfiguration;

    public ExpenseController(DataSourceConfiguration dataSourceConfiguration){
        this.dataSourceConfiguration = dataSourceConfiguration;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Expense>> getExpense(@RequestParam MultiValueMap<String, ?> params) throws BadRequestException {
        boolean dateExist = false;
        String[] paramCheckList = {"category", "location", "shop", "item", "startDate", "endDate"};
        ArrayList<String> paramsChecker = new ArrayList<>(List.of(paramCheckList));
        ArrayList<String> keyValueList = new ArrayList<>();

        if (params.isEmpty()){
            List<Expense> result = dataSourceConfiguration.getAll();
            return ResponseEntity.ok().body(result);
        }

        if (params.containsKey("startDate") && !params.containsKey("endDate")){
            throw new BadRequestException("startDate and endDate must both exist or both not exist");
        }

        if (!params.containsKey("startDate") && params.containsKey("endDate")){
            throw new BadRequestException("startDate and endDate must both exist or both not exist");
        }

        // data validation for startDate and endDate
        if (params.containsKey("startDate") && params.containsKey("endDate")){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String startDateVal = params.get("startDate").getFirst().toString();
            String endDateVal = params.get("endDate").getFirst().toString();

            LocalDate startDate;
            LocalDate endDate;
            startDate = DataValidation.dateCheck(formatter, startDateVal);
            endDate = DataValidation.dateCheck(formatter, endDateVal);
            if (startDate.isAfter(endDate)){
                throw new BadRequestException("startDate cannot be after endDate");
            }
            dateExist = true;
        }

        if (!paramsChecker.containsAll(params.keySet())){
            throw new BadRequestException("Field parameter is incorrect");
        }

        StringBuilder sql = new StringBuilder("SELECT id, date, category, location, shop, item, card, quantity, price, deleted FROM expense WHERE ");
        for (String key : params.keySet()){
            if (!key.equals("startDate") && !key.equals("endDate")) {
                sql.append(key).append(" = ").append("?").append(" AND ");
                keyValueList.add(params.get(key).getFirst().toString());
            }
        }

        if (!dateExist){
            int len = sql.length();
            sql.delete(len - 5, len);
        }
        else{
            // ::date for postgresql
            sql.append("date").append(" BETWEEN ").append("?::date").append(" AND ")
                    .append("?::date");
            keyValueList.add(params.get("startDate").getFirst().toString());
            keyValueList.add(params.get("endDate").getFirst().toString());
        }

        List<Expense> result = dataSourceConfiguration.getFiltered(sql.toString(), keyValueList.toArray());
        return ResponseEntity.ok().body(result);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<List<Expense>> addExpense(@RequestBody Expense expense) throws BadRequestException {
        if (!DataValidation.expenseNullCheck(expense)) {
            throw new BadRequestException("some or all fields are missing");
        }

        List<Expense> result = dataSourceConfiguration.postData(expense);
        return ResponseEntity.created(URI.create("/expense/" + result.getFirst().getId())).body(result);
    }

    @PutMapping(path = "/{id}", consumes = "application/json")
    public void updateExpense(@RequestBody Expense expense, @PathVariable("id") int id) throws BadRequestException, NotFoundException {
        if (!DataValidation.expenseNullCheck(expense)) {
            throw new BadRequestException("some or all fields are missing");
        }

        if (dataSourceConfiguration.getExpenseById(id).isEmpty()){
            throw new NotFoundException("Item Not Found in Database");
        }

        dataSourceConfiguration.updateData(expense, id);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteExpense(@PathVariable("id") int id) throws NotFoundException{
        if (dataSourceConfiguration.getExpenseById(id).isEmpty()){
            throw new NotFoundException("Item Not Found in Database");
        }

        dataSourceConfiguration.deleteData(id);
    }

}
