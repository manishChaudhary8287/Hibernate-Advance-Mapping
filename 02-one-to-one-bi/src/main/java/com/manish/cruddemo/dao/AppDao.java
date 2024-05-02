package com.manish.cruddemo.dao;

import com.manish.cruddemo.entity.Instructor;
import com.manish.cruddemo.entity.InstructorDetail;

public interface AppDao {

    void save(Instructor theInstructor);

    Instructor findInstructorById(int theId);

    void deleteInstructorById(int theId);

    InstructorDetail findInstructorDetailById(int theId);

    void deleteInstructorDetailById(int theId);
}
