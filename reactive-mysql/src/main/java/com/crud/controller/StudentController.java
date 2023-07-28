package com.crud.controller;


import com.crud.model.Student;
import com.crud.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService service;

    @GetMapping("")
    public  String Home(){
        return "hello";
    }


    /*
     All get method
     */


    @GetMapping("/getallstudent")
    public Flux<Student> getAllStudent(){
        return service.getAllProduct();
    }

    @GetMapping("/getbyid")
    public  Mono<Student> getById(@RequestParam int id){
        return service.getById(id);
    }


    @PostMapping("/addstudent")
    public Mono<Student> addStudent(@RequestBody Student student){
        return service.saveProduct(student);
    }

    @DeleteMapping("/delete")
    public Mono<Void> deleteById(@RequestParam int id){
         return  service.deleteStudentById(id);
    }


}
