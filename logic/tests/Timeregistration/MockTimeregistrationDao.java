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

package Timeregistration;

import Data.DaoResult;
import Data.IDataSource;
import Timeregistration.ITimeregistrationDAO;
import Timeregistration.Timeregistration;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class MockTimeregistrationDao implements ITimeregistrationDAO
{
    private final ArrayList<Timeregistration> timeregistrations;
    private final ArrayList<Timeregistration> timeregistrationsUpdating; // list of Employee instances that are being updated.

    private final IDataSource dataSource;

    public MockTimeregistrationDao(IDataSource dataSource)
    {
        timeregistrations = new ArrayList<>();
        timeregistrationsUpdating = new ArrayList<>();
        timeregistrations.add(new Timeregistration(
                this,1,false, ZonedDateTime.now(),
                ZonedDateTime.now().plusHours(1),
                dataSource.taskDao().findById(1),
                dataSource.employeeDao().findById(1)
        ));
        this.dataSource = dataSource;
    }

    @Override
    public List<Timeregistration> findAll()
    {
        return timeregistrations;
    }

    @Override
    public Timeregistration findById(int id)
    {
        for(Timeregistration timeregistration : timeregistrations)
            if(timeregistration.getId() == id) return timeregistration;
        return null;
    }

    @Override
    public DaoResult insertTimeregistration(Timeregistration timeregistration)
    {
        return timeregistrations.add(timeregistration) ? DaoResult.OP_OK : DaoResult.DAO_INUSE;
    }

    @Override
    public DaoResult updateTimeregistration(Timeregistration timeregistration)
    {
        return DaoResult.OP_OK;
    }

    @Override
    public DaoResult deleteTimeregistration(Timeregistration timeregistration)
    {
        return timeregistrations.remove(timeregistration) ? DaoResult.OP_OK : DaoResult.DAO_INUSE;
    }

}
