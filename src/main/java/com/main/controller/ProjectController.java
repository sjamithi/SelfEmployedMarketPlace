package com.main.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.main.dao.ProjectDAO;
import com.main.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    public String index() {
        return "From project controller";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ProjectDAO create(@RequestBody  ProjectDAO request) {
        ObjectMapper mapper = new ObjectMapper();
        ProjectDAO projectDAO =  mapper.convertValue(request, ProjectDAO.class);
        return projectService.create(projectDAO);
    }

    @RequestMapping(value = "/findOne")
    public ProjectDAO findOneById(String id) {
        return projectService.findOne(id);
    }

    @RequestMapping(value = "/findAll")
    public List<ProjectDAO> findAll() {
        return projectService.findAll();
    }

    @RequestMapping(value = "/getById")
    public ProjectDAO getProjectById(Long id) {
        return projectService.projectStatusById(id);
    }

    @RequestMapping(value = "/findAllByStatus")
    public List<ProjectDAO> getProjectsByStatus(String status) {
        return projectService.findAllByStatuses(status);
    }
}
