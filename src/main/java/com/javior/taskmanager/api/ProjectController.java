package com.javior.taskmanager.api;

import com.javior.taskmanager.controllers.entity.PageResult;
import com.javior.taskmanager.repo.jpa.ProjectsDao;
import com.javior.taskmanager.repo.jpa.TasksDao;
import com.javior.taskmanager.repo.jpa.entity.Project;
import com.javior.taskmanager.repo.jpa.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class ProjectController extends BaseController{
    @Autowired
    ProjectsDao projectsDao;

    @Autowired
    TasksDao tasksDao;

    @RequestMapping(value = "projects/new", method = RequestMethod.POST)
    public ApiResponse newProject(Project project){
        project.setCreatedTime(LocalDateTime.now());
        Project res = projectsDao.save(project);
        return success(res);
    }

    @RequestMapping(value = "projects", method = RequestMethod.GET)
    public ApiResponse projects(@RequestParam(defaultValue = "0") Integer page){
        PageRequest pageRequest = PageRequest.of(page, 15, Sort.by("id").descending());
        Page<Project> projectPage = projectsDao.findAllByIdNotNull(pageRequest);
        projectPage.forEach(pro -> {
            List<Task> subTasks = tasksDao.findAllByProjectId(pro.getId());
            pro.setProgress(new Double(subTasks.stream().mapToInt(Task::getProgress).average().orElse(0d)).intValue());
        });
        return success(new PageResult<>(
                projectPage.getContent(),
                projectPage.getTotalPages(),
                projectPage.getPageable().getPageNumber(),
                projectPage.getTotalElements()));
    }
}
