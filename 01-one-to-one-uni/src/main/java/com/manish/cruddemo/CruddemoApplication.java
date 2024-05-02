package com.manish.cruddemo;

import com.manish.cruddemo.dao.AppDao;
import com.manish.cruddemo.entity.Instructor;
import com.manish.cruddemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDao appDao){
		return runner->{
//			createInstructor(appDao);

//			findInstructor(appDao);

			deleteInstructor(appDao);
		};
	}

	private void deleteInstructor(AppDao appDao) {
		int theId=2;
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

















