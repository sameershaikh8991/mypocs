package com.crud.service;


import com.crud.model.Student;
import com.crud.repo.StudetRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class StudentService {

    @Autowired
    private StudetRepo studetRepo;


    /*
    all get method
     */
    public Flux<Student> getAllProduct(){
        return studetRepo.findAll();
    }

    public  Mono<Student> getById(int id){
        return studetRepo.findById(id);
    }

    public Mono<Student> saveProduct(Student studentMono){
        return studetRepo.save(studentMono);
    }

    public Mono<Void> deleteStudentById(int id){
        return studetRepo.deleteById(id);

    }


}
