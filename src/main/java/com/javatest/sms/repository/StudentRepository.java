package com.javatest.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javatest.sms.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{

}
