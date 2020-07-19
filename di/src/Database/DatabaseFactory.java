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

package Database;

import Data.IDataSource;
import Facade.EDataSourceConnection;
import Facade.ISQLConnectionFactory;
import MySQL.MySQLConnection;
import Sqlite.SqliteConnection;

import javax.security.auth.login.FailedLoginException;
import java.util.Map;

public class DatabaseFactory implements ISQLConnectionFactory
{

    public Map<String,Integer> getDatabaseDrivers(){
        return Map.of(
                "MySQL",1,
                "Sqlite",2
        );
    }

    public IDataSource SQLFactoryCreate(int type, String server, String database, String user, String password) throws EDataSourceConnection
    {
        try
        {
            switch (type)
            {
                case 1:
                    return new MySQLConnection(server, database, user,password);
                case 2:
                    return new SqliteConnection(server,database,user,password);
            }
        } catch (EDataSourceConnection e){
            System.out.println(e.getMessage());
            throw new EDataSourceConnection(e.getReason(),e.getMessage());
        }

        return null;
    }

}
