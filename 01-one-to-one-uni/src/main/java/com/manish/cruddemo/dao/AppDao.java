package com.manish.cruddemo.dao;

import com.manish.cruddemo.entity.Instructor;

public interface AppDao {

    void save(Instructor theInstructor);

    Instructor findInstructorById(int theId);

    void deleteInstructorById(int theId);
}
