package com.javior.taskmanager.api;

import com.google.common.collect.Lists;
import com.javior.taskmanager.repo.jpa.TasksDao;
import com.javior.taskmanager.repo.jpa.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
public class TaskController extends BaseController{

    @Autowired
    TasksDao tasksDao;

    @RequestMapping(value = "tasks", method = RequestMethod.GET)
    public ApiResponse tasks(){
        Iterable<Task> tasks = tasksDao.findAll(Sort.by("id").ascending());
        List<Task> taskList = Lists.newArrayList(tasks);
        return success(taskList);

    }

    @RequestMapping(value = "tasks/new", method = RequestMethod.POST)
    public ApiResponse newTask(Task task){
        task.setCreatedTime(LocalDateTime.now());
        Task result = tasksDao.save(task);
        return success(result);
    }

    @RequestMapping(value = "tasks", method = RequestMethod.POST)
    public ApiResponse updateTaskProgress(Long id, Integer progress, @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate start, @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate end){
        Optional<Task> task = tasksDao.findById(id);
        if (task.isPresent()){
            Task t = task.get();
            if (progress != null) t.setProgress(progress);
            if (start != null) t.setStart(start);
            if (end != null) t.setEnd(end);
            t = tasksDao.save(t);
            return success(t);
        }

        return fail("cant't find task with id: " + id);
    }
}
