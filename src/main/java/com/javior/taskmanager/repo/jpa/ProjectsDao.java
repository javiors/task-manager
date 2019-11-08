package com.javior.taskmanager.repo.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.javior.taskmanager.repo.jpa.entity.Project;


@Repository
public interface ProjectsDao extends PagingAndSortingRepository<Project, Long> {
    Page<Project> findAllByIdNotNull(Pageable pageable);
}
