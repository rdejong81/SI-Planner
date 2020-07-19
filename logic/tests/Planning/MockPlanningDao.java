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

package Planning;

import Data.DaoResult;
import Data.IDataSource;
import Timeregistration.Timeregistration;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class MockPlanningDao implements IPlanningDAO
{
    private final ArrayList<Planning> plannings;
    private final ArrayList<Planning> planningsUpdating; // list of Employee instances that are being updated.

    private final IDataSource dataSource;

    public MockPlanningDao(IDataSource dataSource)
    {
        plannings = new ArrayList<>();
        planningsUpdating = new ArrayList<>();
        plannings.add(new Planning(
                this,1,false, ZonedDateTime.now(),
                ZonedDateTime.now().plusHours(1),
                dataSource.taskDao().findById(1),
                dataSource.employeeDao().findById(1)
        ));
        this.dataSource = dataSource;
    }

    @Override
    public List<Planning> findAll()
    {
        return plannings;
    }

    @Override
    public Planning findById(int id)
    {
        for(Planning planning : plannings)
            if(planning.getId() == id) return planning;
        return null;
    }

    @Override
    public DaoResult insertPlanning(Planning planning)
    {
        return plannings.add(planning) ? DaoResult.OP_OK : DaoResult.DAO_INUSE;
    }

    @Override
    public DaoResult updatePlanning(Planning planning)
    {
        return DaoResult.OP_OK;
    }

    @Override
    public DaoResult deletePlanning(Planning planning)
    {
        return plannings.remove(planning) ? DaoResult.OP_OK : DaoResult.DAO_INUSE;
    }

}
