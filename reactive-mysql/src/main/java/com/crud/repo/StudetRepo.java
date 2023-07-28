package com.crud.repo;

import com.crud.model.Student;


import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudetRepo extends R2dbcRepository<Student,Integer> {
}
