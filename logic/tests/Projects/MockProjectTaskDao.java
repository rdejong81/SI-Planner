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

package Projects;

import Data.DaoResult;
import Data.IDataSource;

import java.util.ArrayList;
import java.util.List;

public class MockProjectTaskDao implements IProjectTaskDAO
{
    private final ArrayList<ProjectTask> projectTasks;
    private final ArrayList<ProjectTask> projectTasksUpdating; // list of Employee instances that are being updated.

    private final IDataSource dataSource;

    public MockProjectTaskDao(IDataSource dataSource)
    {
        projectTasks = new ArrayList<>();
        projectTasksUpdating = new ArrayList<>();
        projectTasks.add(new ProjectTask(
                this,1,"Task A",false,dataSource.projectDao().findById(1)));

        projectTasks.add(new ProjectTask(
                this,2,"Task B",false,dataSource.projectDao().findById(1)));
        this.dataSource = dataSource;
    }

    @Override
    public List<ProjectTask> findAll()
    {
        return projectTasks;
    }

    @Override
    public ProjectTask findById(int id)
    {
        for(ProjectTask projectTask : projectTasks)
            if(projectTask.getId() == id) return projectTask;
        return null;
    }

    @Override
    public DaoResult insertTask(ProjectTask projectTask)
    {
        return projectTasks.add(projectTask) ? DaoResult.OP_OK : DaoResult.DAO_INUSE;
    }

    @Override
    public DaoResult updateTask(ProjectTask projectTask)
    {
        return DaoResult.OP_OK;
    }

    @Override
    public DaoResult deleteTask(ProjectTask projectTask)
    {
        return projectTasks.remove(projectTask) ? DaoResult.OP_OK : DaoResult.DAO_INUSE;
    }

}
