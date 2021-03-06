/*
 *    Copyright © 2020 Richard de Jong
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS projects;
DROP TABLE IF EXISTS time;
DROP TABLE IF EXISTS templates;
DROP TABLE IF EXISTS attribute_definitions;
DROP TABLE IF EXISTS attribute_values;
DROP TABLE IF EXISTS employees;
DROP TABLE IF EXISTS employees_customers;
DROP TABLE IF EXISTS tasks;
CREATE TABLE customers (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, name varchar(255) NOT NULL, shortName varchar(10) NOT NULL);
CREATE TABLE projects (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, customer_id integer(10) NOT NULL, timesheetNeeded integer(1) NOT NULL, name varchar(255) NOT NULL, color integer(10) NOT NULL, shortCode varchar(10) NOT NULL, FOREIGN KEY(customer_id) REFERENCES customers(id));
CREATE TABLE time (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, tasks_id integer(10) NOT NULL, synckey varchar(255), "start" timestamp NOT NULL, "end" timestamp NOT NULL, synced integer(1) NOT NULL, planned integer(1) NOT NULL, employeesid integer(10) NOT NULL, FOREIGN KEY(tasks_id) REFERENCES tasks(id), FOREIGN KEY(employeesid) REFERENCES employees(id));
CREATE TABLE templates (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, templateDocument blob NOT NULL, documentType integer(10) NOT NULL, customers_id integer(10) NOT NULL, name varchar(255) NOT NULL, FOREIGN KEY(customers_id) REFERENCES customers(id));
CREATE TABLE attribute_definitions (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, attributeType integer(10) NOT NULL, attributeName varchar(255) NOT NULL, entityType integer(10) NOT NULL, customers_id integer(10) NOT NULL, FOREIGN KEY(customers_id) REFERENCES customers(id));
CREATE TABLE attribute_values (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, attribute_definitions_id integer(10) NOT NULL, stringValue varchar(255), intValue integer(10), boolValue integer(1), dateValue timestamp, entity_id integer(10) NOT NULL, doubleValue double(10), FOREIGN KEY(attribute_definitions_id) REFERENCES attribute_definitions(id));
CREATE TABLE employees (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, name varchar(255) NOT NULL, sqlLogin varchar(255) NOT NULL UNIQUE);
CREATE TABLE employees_customers (employees_id integer(10) NOT NULL, customers_id integer(10) NOT NULL, PRIMARY KEY (employees_id, customers_id), FOREIGN KEY(employees_id) REFERENCES employees(id), FOREIGN KEY(customers_id) REFERENCES customers(id));
CREATE TABLE tasks (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, projects_id integer(10) NOT NULL, name varchar(255) NOT NULL, completed integer(1) NOT NULL, FOREIGN KEY(projects_id) REFERENCES projects(id));
