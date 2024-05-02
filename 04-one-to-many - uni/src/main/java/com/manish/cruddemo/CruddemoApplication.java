package com.manish.cruddemo;

import com.manish.cruddemo.dao.AppDao;
import com.manish.cruddemo.entity.Course;
import com.manish.cruddemo.entity.Instructor;
import com.manish.cruddemo.entity.InstructorDetail;
import com.manish.cruddemo.entity.Review;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.zip.CheckedOutputStream;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDao appDao){
		return runner->{
//			createCourseAndReviews(appDao);
//			findCourseAndReviewByCourseId(appDao);
			deleteCourseWithReviewsById(appDao);
		};
	}

	private void deleteCourseWithReviewsById(AppDao appDao) {
		int theId=10;
		System.out.println("Deleting the course with the id : "+theId);
		appDao.deleteCourseById(theId);
		System.out.println("Done!!!");
	}

	private void findCourseAndReviewByCourseId(AppDao appDao) {
		int theId=10;
		System.out.println("Finding course with the course id : "+theId);
		Course tempCourse=appDao.findCourseAndReviewsByCourseId(theId);
		System.out.println("Course : "+tempCourse);
		System.out.println("Reviews : "+tempCourse.getReviews());
	}

	private void createCourseAndReviews(AppDao appDao) {

//		create a course
		Course tempCourse= new Course("html and css");

//		add some Reviews
		tempCourse.addReviews(new Review("Great course"));
		tempCourse.addReviews(new Review("Very helpful"));
		tempCourse.addReviews(new Review("Good course"));
		tempCourse.addReviews(new Review("Cool course"));
		tempCourse.addReviews(new Review("dumb course"));


//		save the course
		appDao.save(tempCourse);

		System.out.println("Course Saved. Done!!!");

	}

	private void deleteCourse(AppDao appDao) {
		int theId=12;
		System.out.println("Deleting course with the id : "+theId);
		appDao.deleteCourseById(theId);
		System.out.println("Done!!!");
	}

	private void updateCourse(AppDao appDao) {
		int theId=11;
		System.out.println("Finding course with the Id : "+theId);
		Course tempCourse=appDao.findCourseById(theId);
		System.out.println("The course : "+tempCourse);
		System.out.println("Updating course....");
		tempCourse.setTitle("Full Stack web development");
		appDao.updateCourse(tempCourse);
		System.out.println("Done!!!");

	}

	private void updateInstructor(AppDao appDao) {
		int theId=1;
		System.out.println("Finding the instructor : "+ theId);
		Instructor instructor = appDao.findInstructorById(theId);

		System.out.println("Updating the instructor ");
		instructor.setLastName("Tester");

		appDao.updateInstructor(instructor);

		System.out.println("Done!!!");
	}

	private void findInstructorWithJoinFetch(AppDao appDao) {
		int theId=1;
		Instructor tempInstructor = appDao.findInstructorWithCourseByFetchJoin(theId);
		System.out.println("Instructor : "+tempInstructor);
		System.out.println("Associated courses are  : "+ tempInstructor.getCourses());
		System.out.println("Done!!!");
	}

	private void findCourseWithInstructorId(AppDao appDao) {
		int theId=1;
		Instructor instructor = appDao.findInstructorById(theId);
		System.out.println("The instructor : "+instructor);

		System.out.println("Finding course with the instructor id : "+theId);
		List<Course> course=appDao.findCoursesWithInstructorId(theId);
		instructor.setCourses(course);
		System.out.println("Associated course are : "+instructor.getCourses());

	}

	private void findInstructorWithCourses(AppDao appDao) {
		int theId=1;
		System.out.println("Finding the instructor with id : "+ theId);

		Instructor instructor = appDao.findInstructorById(theId);
		System.out.println("The Instructor : "+ instructor);
		System.out.println("The associated courses are : "+ instructor.getCourses());
		System.out.println("done!");
	}

	private void createInstructorWithCourses(AppDao appDao) {
		Instructor tempInstructor=
				new Instructor("Manish","chaudhary","manish@gmail.com");

		InstructorDetail tempInstructorDetail=
				new InstructorDetail("http://www.youtube.com","cricket and coding");

		tempInstructor.setInstructorDetail(tempInstructorDetail);

		// creating some new courses.
		Course tempCourse1= new Course("Core Java - The Ultimate guide.");
		Course tempCourse2= new Course("Python - The Ultimate guide.");
		Course tempCourse3= new Course("Java Script - The Ultimate guide.");

		tempInstructor.addCourse(tempCourse1);
		tempInstructor.addCourse(tempCourse2);
		tempInstructor.addCourse(tempCourse3);

		// save the instructor.
		System.out.println("Saving the instructor..." + tempInstructor);
		System.out.println("The courses : "+tempInstructor.getCourses());

		appDao.save(tempInstructor);


	}

	private void deleteInstructorDetail(AppDao appDao) {
		int theId=4;
		System.out.println("deleting instructor details by ID : "+theId);

		appDao.deleteInstructorDetailById(theId);

		System.out.println("Done!");
	}

	private void findInstructorDetail(AppDao appDao) {
		//get the instructor detail object
		int theId=3;
		InstructorDetail instructorDetail=appDao.findInstructorDetailById(theId);

		//print the instructor details
		System.out.println("temp instructor details : "+instructorDetail);

		//print the associate method
		System.out.println("associate instructor : "+instructorDetail.getInstructor());

		System.out.println("Done!");
	}

	private void deleteInstructor(AppDao appDao) {
		int theId=1;
		System.out.println("deleting instructor by ID :" + theId);
		appDao.deleteInstructorById(theId);
		System.out.println("Done!!!");
	}

	private void findInstructor(AppDao appDao) {
		int theId=1;
		System.out.println("Finding Instructor by ID : "+ theId);

		Instructor tempInstructor=appDao.findInstructorById(theId);
		System.out.println("temp Instructor : " + tempInstructor);
		System.out.println("associated instructor details : " + tempInstructor.getInstructorDetail());
	}

	private void createInstructor(AppDao appDao) {
		// create the instructor
		/*
		Instructor tempInstructor=
				new Instructor("Manish","chaudhary","manish@gmail.com");

		InstructorDetail tempInstructorDetail=
				new InstructorDetail("http://www.youtube.com","cricket and coding");
		 */
		Instructor tempInstructor=
				new Instructor("Vivek","Dixit","vivek@gmail.com");

		InstructorDetail tempInstructorDetail=
				new InstructorDetail("http://www.youtube.com","front-end");

		tempInstructor.setInstructorDetail(tempInstructorDetail);

		// save the instructor

		System.out.println("Saving the instructor : "+tempInstructor);
		appDao.save(tempInstructor);

		System.out.println("Done!!!");
	}

}

















