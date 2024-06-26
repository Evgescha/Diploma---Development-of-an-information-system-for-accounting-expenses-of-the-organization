package com.hescha.moneycounter.controller;

import com.hescha.moneycounter.model.Budget;
import com.hescha.moneycounter.model.BudgetAllocation;
import com.hescha.moneycounter.model.BudgetAllocation;
import com.hescha.moneycounter.service.BudgetAllocationService;
import com.hescha.moneycounter.service.BudgetService;
import com.hescha.moneycounter.service.ExpenseCategoryService;
import com.hescha.moneycounter.service.ExpenseItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.stereotype.Controller;

import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping(BudgetAllocationController.CURRENT_ADDRESS)
public class BudgetAllocationController {
    public static final String API_PATH = "budgetallocation";
    public static final String CURRENT_ADDRESS = "/" + API_PATH;
    public static final String MESSAGE = "message";
    public static final String THYMELEAF_TEMPLATE_ALL_ITEMS_PAGE = API_PATH;
    public static final String THYMELEAF_TEMPLATE_ONE_ITEM_PAGE = THYMELEAF_TEMPLATE_ALL_ITEMS_PAGE + "-one";
    public static final String THYMELEAF_TEMPLATE_EDIT_PAGE = THYMELEAF_TEMPLATE_ALL_ITEMS_PAGE + "-edit";
    public static final String REDIRECT_TO_ALL_ITEMS = "redirect:" + CURRENT_ADDRESS;

    private final BudgetAllocationService service;

    private final BudgetService budgetService;
    private final ExpenseCategoryService expenseCategoryService;
    private final ExpenseItemService expenseItemService;

    @GetMapping
    public String readAll(Model model) {
        model.addAttribute("list", service.readAll());
        return THYMELEAF_TEMPLATE_ALL_ITEMS_PAGE;
    }

    @GetMapping("/{id}")
    public String read(@PathVariable("id") Long id, Model model) {
        model.addAttribute("entity", service.read(id));
        return THYMELEAF_TEMPLATE_ONE_ITEM_PAGE;
    }

    @GetMapping(path = {"/edit", "/edit/{id}"})
    public String editPage(Model model, @PathVariable(name = "id", required = false) Long id) {
        if (id == null) {
            model.addAttribute("entity", new BudgetAllocation());
        } else {
            model.addAttribute("entity", service.read(id));
        }

        model.addAttribute("budgets", budgetService.readAll());
        model.addAttribute("categories", expenseCategoryService.readAll());

        return THYMELEAF_TEMPLATE_EDIT_PAGE;
    }

    @GetMapping("/budged/{id}")
    public String addBudgetAlloctaion(Model model, @PathVariable(name = "id", required = false) Long id) {
        model.addAttribute("entity", new BudgetAllocation());
        model.addAttribute("budgets", List.of(budgetService.read(id)));
        model.addAttribute("categories", expenseCategoryService.readAll());
        return THYMELEAF_TEMPLATE_EDIT_PAGE;
    }

    @PostMapping
    public String save(@ModelAttribute BudgetAllocation entity, RedirectAttributes ra) {
        if (entity.getId() == null) {
            try {
                BudgetAllocation createdEntity = service.create(entity);

                Budget budget = entity.getBudget();
                budget.getBudgetAllocations().add(createdEntity);
                budgetService.update(budget);
                ra.addFlashAttribute(MESSAGE, "Creating is successful");
                return REDIRECT_TO_ALL_ITEMS + "/" + createdEntity.getId();
            } catch (Exception e) {
                ra.addFlashAttribute(MESSAGE, "Creating failed");
                e.printStackTrace();
            }
            return REDIRECT_TO_ALL_ITEMS;
        } else {
            try {
                BudgetAllocation budgetAllocation = service.read(entity.getId());
                Budget budget = budgetAllocation.getBudget();
                budget.getBudgetAllocations().remove(budgetAllocation);
                budgetService.update(budget);

                BudgetAllocation update = service.update(entity.getId(), entity);

                budget = update.getBudget();
                budget.getBudgetAllocations().add(update);
                budgetService.update(budget);

                ra.addFlashAttribute(MESSAGE, "Editing is successful");
            } catch (Exception e) {
                e.printStackTrace();
                ra.addFlashAttribute(MESSAGE, "Editing failed");
            }
            return REDIRECT_TO_ALL_ITEMS + "/" + entity.getId();
        }
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        try {
            BudgetAllocation budgetAllocation = service.read(id);
            Budget budget = budgetAllocation.getBudget();
            budget.getBudgetAllocations().remove(budgetAllocation);
            budgetService.update(budget);
            expenseItemService.removeRelated(budgetAllocation);

            service.delete(id);
            ra.addFlashAttribute(MESSAGE, "Removing is successful");
        } catch (Exception e) {
            e.printStackTrace();
            ra.addFlashAttribute(MESSAGE, "Removing failed");
        }
        return REDIRECT_TO_ALL_ITEMS;
    }
}
