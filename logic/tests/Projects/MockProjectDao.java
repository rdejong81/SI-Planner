/*
 * Copyright © 2020 Richard de Jong.
 *
 * This file is part of logic
 *
 * logic is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * logic is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with logic.  If not, see <https://www.gnu.org/licenses/>.
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
        projects.add(new Project(this,1,"Customer A",1,
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
