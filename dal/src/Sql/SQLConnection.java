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

package Sql;

import Data.IDataSource;

import java.sql.*;

abstract public class SQLConnection implements IDataSource
{

    private final String connectString;
    private final String database;
    private final String user;
    private final String server;
    private final String password;
    private Connection connection;

    protected SQLConnection(String server, String database, String user, String password)
    {
        this.user = user;
        this.password = password;
        this.server = server;
        this.database = database;
        this.connectString = this.getConnectString();
    }

    final protected void connectDatabase() throws SQLException
    {
        this.connection = DriverManager.getConnection(this.connectString,this.getUser(),this.getPassword());
    }

    abstract public String getConnectString();

    abstract public Statement executeStatement(String statementText);

    final public String getDatabase()
    {
        return database;
    }

    final public String getUser()
    {
        return user;
    }

    final public String getServer()
    {
        return server;
    }

    final public String getPassword()
    {
        return password;
    }

    final public Connection getConnection()
    {
        return connection;
    }



}
