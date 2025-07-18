package com.project.expenseTrackerAPI.backend.database;

import com.project.expenseTrackerAPI.backend.model.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataSourceConfiguration {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DataSourceConfiguration(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Expense> getAll(){
        return jdbcTemplate.query(
                "SELECT id, date, category, location, shop, item, card, quantity, price, deleted FROM expense",
                expenseMapper());
    }

    public List<Expense> getFiltered(String sql, Object[] params){
        return jdbcTemplate.query(
                sql,
                expenseMapper(),
                params);
    }

    public List<Expense> postData(Expense expense){
        jdbcTemplate.update("INSERT INTO expense (date, category, location, shop, item, card, quantity, price) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)", expense.getDate(), expense.getCategory(),
                expense.getLocation(), expense.getShop(), expense.getItem(), expense.getCard(), expense.getQuantity(),
                expense.getPrice());

        return jdbcTemplate.query("SELECT id, date, category, location, shop, item, card, quantity, price, deleted FROM expense" +
                " ORDER BY id DESC LIMIT 1", expenseMapper());
    }

    public List<Expense> getExpenseById(int id){
        return jdbcTemplate.query("SELECT id, date, category, location, shop, item, card, quantity, price, deleted " +
                        "FROM expense WHERE id = ? AND deleted = ?",
                expenseMapper(),
                id, false);
    }

    public void updateData(Expense expense, int id){
        jdbcTemplate.update("UPDATE expense SET date = ?, category = ?, location = ?," +
                        "shop = ?, item = ?, card = ?, quantity = ?, price = ? WHERE id = ?",
                expense.getDate(), expense.getCategory(), expense.getLocation(), expense.getShop(),
                expense.getItem(), expense.getCard(), expense.getQuantity(),
                expense.getPrice(), id);
    }

    public void deleteData(int id){
        jdbcTemplate.update("UPDATE expense SET deleted = ? WHERE id = ?",
                true, id);
    }

    private RowMapper<Expense> expenseMapper(){
        return (rs, rowNum) -> {
            Expense expense = new Expense(
                    (rs.getDate("date")).toLocalDate(),
                    rs.getString("category"),
                    rs.getString("location"),
                    rs.getString("shop"),
                    rs.getString("item"),
                    rs.getString("card"),
                    rs.getInt("quantity"),
                    rs.getDouble("price"));
            expense.setId(rs.getInt("id"));
            expense.setDeleted(rs.getBoolean("deleted"));
            return expense;
        };
    }

}
