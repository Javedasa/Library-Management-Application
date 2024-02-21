package com.example.Library.Management.System.Repository;

import com.example.Library.Management.System.Entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
    //incase you write your own queries
    //that is written here
}
