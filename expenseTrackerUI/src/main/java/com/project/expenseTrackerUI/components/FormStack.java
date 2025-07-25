package com.project.expenseTrackerUI.components;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class FormStack extends TabPane {

    private ExpenseForm expenseForm;
    private FilterForm filterForm;

    public FormStack(){
        init();
    }

    private void init(){
        expenseForm = new ExpenseForm();
        filterForm = new FilterForm();

        Tab expenseTab = new Tab("Expense", expenseForm);
        expenseTab.setClosable(false);

        Tab filterTab = new Tab("Filter", filterForm);
        filterTab.setClosable(false);
        this.getTabs().addAll(expenseTab, filterTab);

        String cssUrl;
        try {
            cssUrl = getClass().getResource("/css/formStack.css").toExternalForm();
            this.getStylesheets().add(cssUrl);
            this.getStyleClass().add("tab-header-background");
            expenseTab.getStyleClass().add("control-buttons-tab");
            filterTab.getStyleClass().add("control-buttons-tab");
        } catch (NullPointerException ex){
            ex.printStackTrace();
        }
    }

    public ExpenseForm getExpenseForm(){
        return this.expenseForm;
    }

    public FilterForm getFilterForm(){
        return this.filterForm;
    }

}
