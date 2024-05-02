package com.manish.cruddemo.dao;

import com.manish.cruddemo.entity.Course;
import com.manish.cruddemo.entity.Instructor;
import com.manish.cruddemo.entity.InstructorDetail;

import java.util.List;

public interface AppDao {

    void save(Instructor theInstructor);

    Instructor findInstructorById(int theId);

    void deleteInstructorById(int theId);

    InstructorDetail findInstructorDetailById(int theId);

    void deleteInstructorDetailById(int theId);

    List<Course> findCoursesWithInstructorId(int theId);

    Instructor findInstructorWithCourseByFetchJoin(int theId);

    void updateInstructor(Instructor tempInstructor);

    void updateCourse(Course course);

    Course findCourseById(int theId);

    void deleteCourseById(int theId);


}
