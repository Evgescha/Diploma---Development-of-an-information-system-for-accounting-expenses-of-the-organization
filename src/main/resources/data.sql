Создание ролей
INSERT INTO ROLE (id, name) VALUES (1, 'ROLE_GUEST');
INSERT INTO ROLE (id, name) VALUES (2, 'ROLE_USER');
INSERT INTO ROLE (id, name) VALUES (3, 'ROLE_MANAGER');
INSERT INTO ROLE (id, name) VALUES (4, 'ROLE_ADMIN');

Создание пользователей
INSERT INTO MY_USER (id, username, password, email, role_id) VALUES (1, 'guest', 'guest123', 'guest@example.com', 1);
INSERT INTO MY_USER (id, username, password, email, role_id) VALUES (2, 'user', 'user123', 'user@example.com', 2);
INSERT INTO MY_USER (id, username, password, email, role_id) VALUES (3, 'manager', 'manager123', 'manager@example.com', 3);
INSERT INTO MY_USER (id, username, password, email, role_id) VALUES (4, 'admin', 'admin123', 'admin@example.com', 4);

Создание категорий расходов
INSERT INTO EXPENSE_CATEGORY (id, name, description, parent_id) VALUES (1, 'Office', 'Office expenses', NULL);
INSERT INTO EXPENSE_CATEGORY (id, name, description, parent_id) VALUES (2, 'Utilities', 'Utility expenses', NULL);
INSERT INTO EXPENSE_CATEGORY (id, name, description, parent_id) VALUES (3, 'Office supplies', 'Expenses for office supplies', 1);
INSERT INTO EXPENSE_CATEGORY (id, name, description, parent_id) VALUES (4, 'Electricity', 'Electricity expenses', 2);

Создание позиций расходов
INSERT INTO EXPENSE_ITEM (id, name, amount, date, category_id) VALUES (1, 'Printer ink', 100, '2023-04-01', 3);
INSERT INTO EXPENSE_ITEM (id, name, amount, date, category_id) VALUES (2, 'Paper', 50, '2023-04-02', 3);
INSERT INTO EXPENSE_ITEM (id, name, amount, date, category_id) VALUES (3, 'Electricity bill', 150, '2023-04-10', 4);

Создание отчетов о расходах
INSERT INTO EXPENSE_REPORT (id, name, description, create_Date, status) VALUES (1, 'April 2023', 'Expenses report for April 2023', '2023-04-01', 'DRAFT');

Связь отчетов о расходах с позициями расходов
INSERT INTO EXPENSE_REPORT_EXPENSE_ITEMS (expense_report_id, expense_items_id) VALUES (1, 1);
INSERT INTO EXPENSE_REPORT_EXPENSE_ITEMS (expense_report_id, expense_items_id) VALUES (1, 2);
INSERT INTO EXPENSE_REPORT_EXPENSE_ITEMS (expense_report_id, expense_items_id) VALUES (1, 3);

Создание бюджетов
INSERT INTO BUDGET (id, name, description, total_Amount, period_Start, period_End) VALUES (1, 'April 2023 Budget', 'Budget for April 2023', 1000, '2023-04-01', '2023-04-30');

Создание распределений бюджета
INSERT INTO BUDGET_ALLOCATION (id, allocated_Amount, budget_id, category_id) VALUES (1, 200, 1, 3);
INSERT INTO BUDGET_ALLOCATION (id, allocated_Amount, budget_id, category_id) VALUES (2, 150, 1, 4);
INSERT INTO BUDGET_ALLOCATION (id, allocated_Amount, budget_id, category_id) VALUES (3, 650, 1, 1);

Связь бюджетов с распределениями бюджета
INSERT INTO BUDGET_BUDGET_ALLOCATIONS(BUDGET_ID, BUDGET_ALLOCATIONS_ID) VALUES (1, 1);
INSERT INTO BUDGET_BUDGET_ALLOCATIONS(BUDGET_ID, BUDGET_ALLOCATIONS_ID) VALUES (1, 2);
INSERT INTO BUDGET_BUDGET_ALLOCATIONS(BUDGET_ID, BUDGET_ALLOCATIONS_ID) VALUES (1, 3);
