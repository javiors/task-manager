package com.javior.taskmanager.repo.jpa;

import com.javior.taskmanager.repo.jpa.entity.Task;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TasksDao extends PagingAndSortingRepository<Task, Long> {
    List<Task> findAllByProjectId(Long id);
}
