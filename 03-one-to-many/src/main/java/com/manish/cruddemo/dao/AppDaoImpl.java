package com.manish.cruddemo.dao;

import com.manish.cruddemo.entity.Course;
import com.manish.cruddemo.entity.Instructor;
import com.manish.cruddemo.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AppDaoImpl implements AppDao{

    // define fields for entity manager
    private EntityManager entityManager;

    // inject entity manager using constructor injection
    @Autowired
    public AppDaoImpl(EntityManager theEntityManager){
        this.entityManager=theEntityManager;
    }


    @Override
    @Transactional
    public void save(Instructor theInstructor) {
        entityManager.persist(theInstructor);
    }

    @Override
    public Instructor findInstructorById(int theId) {
        return entityManager.find(Instructor.class,theId);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int theId) {
        Instructor tempInstructor=entityManager.find(Instructor.class,theId);

        // get the courses
        List<Course> tempCourses = tempInstructor.getCourses();

        // remove the association with the courses
        for(Course course:tempCourses){
            course.setInstructor(null);
        }

        entityManager.remove(tempInstructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int theId) {
        return entityManager.find(InstructorDetail.class,theId);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int theId) {
        InstructorDetail instructorDetail=entityManager.find(InstructorDetail.class,theId);

        // breaking the bidirectional link between instructor and instructor details
        instructorDetail.getInstructor().setInstructorDetail(null);

        entityManager.remove(instructorDetail);
    }

    @Override
    public List<Course> findCoursesWithInstructorId(int theId) {
        TypedQuery<Course> query= entityManager.createQuery(
                "from Course where instructor.id = :data", Course.class);
        query.setParameter("data",theId);
        List<Course> course = query.getResultList();
        return course;
    }

    @Override
    public Instructor findInstructorWithCourseByFetchJoin(int theId) {
        TypedQuery<Instructor> query= entityManager.createQuery(
                "select i from Instructor i JOIN FETCH i.courses where i.id=:data", Instructor.class);
        query.setParameter("data",theId);
        Instructor instructor=query.getSingleResult();
        return instructor;
    }

    @Override
    @Transactional
    public void updateInstructor(Instructor tempInstructor) {
        entityManager.merge(tempInstructor);
    }

    @Override
    @Transactional
    public void updateCourse(Course course) {
        entityManager.merge(course);
    }

    @Override
    public Course findCourseById(int theId) {
        return entityManager.find(Course.class,theId);
    }

    @Override
    @Transactional
    public void deleteCourseById(int theId) {
        Course tempCourse=entityManager.find(Course.class,theId);
        entityManager.remove(tempCourse);
    }
}