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
import Projects.IProjectDAO;
import Projects.Project;

import java.util.ArrayList;
import java.util.List;

public class MockProjectDao implements IProjectDAO
{
    private final ArrayList<Project> projects;
    private final ArrayList<Project> projectsUpdating; // list of Employee instances that are being updated.

    private final IDataSource dataSource;

    public MockProjectDao(IDataSource dataSource)
    {
        projects = new ArrayList<>();
        projectsUpdating = new ArrayList<>();
        projects.add(new Project(this,1,"Project A",1,
                true,"CUA",dataSource.customerDao().findById(1)));
        projects.add(new Project(this,2,"Project B",1,
                true,"CUA",dataSource.customerDao().findById(1)));

        this.dataSource = dataSource;
    }

    @Override
    public List<Project> findAll()
    {
        return projects;
    }

    @Override
    public Project findById(int id)
    {
        for(Project project : projects)
            if(project.getId() == id) return project;
        return null;
    }

    @Override
    public DaoResult insertProject(Project project)
    {
        return projects.add(project) ? DaoResult.OP_OK : DaoResult.DAO_INUSE;
    }

    @Override
    public DaoResult updateProject(Project project)
    {
        return DaoResult.OP_OK;
    }

    @Override
    public DaoResult deleteProject(Project project)
    {
        return projects.remove(project) ? DaoResult.OP_OK : DaoResult.DAO_INUSE;
    }

}
