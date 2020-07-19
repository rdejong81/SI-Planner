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

ALTER TABLE projects DROP FOREIGN KEY FKprojects193985;
ALTER TABLE attribute_values DROP FOREIGN KEY FKattribute_629872;
ALTER TABLE templates DROP FOREIGN KEY FKtemplates583413;
ALTER TABLE attribute_definitions DROP FOREIGN KEY FKattribute_168032;
ALTER TABLE employees_customers DROP FOREIGN KEY FKemployees_764185;
ALTER TABLE employees_customers DROP FOREIGN KEY FKemployees_781482;
ALTER TABLE tasks DROP FOREIGN KEY FKtasks510016;
ALTER TABLE time DROP FOREIGN KEY FKtime535173;
ALTER TABLE time DROP FOREIGN KEY FKtime476502;
DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS projects;
DROP TABLE IF EXISTS time;
DROP TABLE IF EXISTS templates;
DROP TABLE IF EXISTS attribute_definitions;
DROP TABLE IF EXISTS attribute_values;
DROP TABLE IF EXISTS employees;
DROP TABLE IF EXISTS employees_customers;
DROP TABLE IF EXISTS tasks;
CREATE TABLE customers (
  id        int(10) NOT NULL AUTO_INCREMENT, 
  name      varchar(255) NOT NULL, 
  shortName varchar(10) NOT NULL, 
  PRIMARY KEY (id)) CHARACTER SET UTF8;
CREATE TABLE projects (
  id              int(10) NOT NULL AUTO_INCREMENT, 
  customer_id     int(10) NOT NULL, 
  timesheetNeeded tinyint(1) NOT NULL, 
  name            varchar(255) NOT NULL, 
  color           int(11) NOT NULL, 
  shortCode       varchar(10) NOT NULL, 
  PRIMARY KEY (id)) CHARACTER SET UTF8;
CREATE TABLE time (
  id          int(10) NOT NULL AUTO_INCREMENT, 
  tasks_id    int(11) NOT NULL,
  synckey     varchar(255),
  `start`     timestamp NOT NULL, 
  `end`       timestamp NOT NULL, 
  synced      tinyint(1) NOT NULL, 
  planned     tinyint(1) NOT NULL, 
  employeesid int(10) NOT NULL, 
  PRIMARY KEY (id)) CHARACTER SET UTF8;
CREATE TABLE templates (
  id               int(10) NOT NULL AUTO_INCREMENT, 
  templateDocument blob NOT NULL, 
  documentType     int(11) NOT NULL, 
  customers_id     int(10) NOT NULL, 
  name             varchar(255) NOT NULL, 
  PRIMARY KEY (id)) CHARACTER SET UTF8;
CREATE TABLE attribute_definitions (
  id            int(11) NOT NULL AUTO_INCREMENT, 
  attributeType int(10) NOT NULL, 
  attributeName varchar(255) NOT NULL, 
  entityType    int(11) NOT NULL, 
  customers_id  int(10) NOT NULL, 
  PRIMARY KEY (id)) CHARACTER SET UTF8;
CREATE TABLE attribute_values (
  id                       int(11) NOT NULL AUTO_INCREMENT, 
  attribute_definitions_id int(11) NOT NULL, 
  stringValue              varchar(255), 
  intValue                 int(10), 
  boolValue                tinyint(1), 
  dateValue                timestamp NULL, 
  entity_id                int(10) NOT NULL, 
  doubleValue              double, 
  PRIMARY KEY (id)) CHARACTER SET UTF8;
CREATE TABLE employees (
  id       int(10) NOT NULL AUTO_INCREMENT, 
  name     varchar(255) NOT NULL, 
  sqlLogin varchar(255) NOT NULL UNIQUE, 
  PRIMARY KEY (id)) CHARACTER SET UTF8;
CREATE TABLE employees_customers (
  employees_id int(10) NOT NULL, 
  customers_id int(10) NOT NULL, 
  PRIMARY KEY (employees_id, 
  customers_id)) CHARACTER SET UTF8;
CREATE TABLE tasks (
  id          int(11) NOT NULL AUTO_INCREMENT, 
  projects_id int(10) NOT NULL, 
  name        varchar(255) NOT NULL, 
  completed   tinyint(1) NOT NULL, 
  PRIMARY KEY (id)) CHARACTER SET UTF8;
ALTER TABLE projects ADD CONSTRAINT FKprojects193985 FOREIGN KEY (customer_id) REFERENCES customers (id);
ALTER TABLE attribute_values ADD CONSTRAINT FKattribute_629872 FOREIGN KEY (attribute_definitions_id) REFERENCES attribute_definitions (id);
ALTER TABLE templates ADD CONSTRAINT FKtemplates583413 FOREIGN KEY (customers_id) REFERENCES customers (id);
ALTER TABLE attribute_definitions ADD CONSTRAINT FKattribute_168032 FOREIGN KEY (customers_id) REFERENCES customers (id);
ALTER TABLE employees_customers ADD CONSTRAINT FKemployees_764185 FOREIGN KEY (employees_id) REFERENCES employees (id);
ALTER TABLE employees_customers ADD CONSTRAINT FKemployees_781482 FOREIGN KEY (customers_id) REFERENCES customers (id);
ALTER TABLE tasks ADD CONSTRAINT FKtasks510016 FOREIGN KEY (projects_id) REFERENCES projects (id);
ALTER TABLE time ADD CONSTRAINT FKtime535173 FOREIGN KEY (tasks_id) REFERENCES tasks (id);
ALTER TABLE time ADD CONSTRAINT FKtime476502 FOREIGN KEY (employeesid) REFERENCES employees (id);
