package uni.demo;

import uni.course.Course;
import uni.course.exception.CourseNotFoundException;
import uni.major.Major;
import uni.student.Student;

public class MajorDemo {
	public static void main(String[] args) {
		
		Course AnalyticalGeometry = new Course("CT101", 12);
    	Course Physics = new Course("BCT101", 4);
    	Course Chemistry = new Course("TC205", 2);
    	Course Calculus = new Course("AB201", 4);
    	
		Major IT = new Major("IT", true);
		
		Student Ivan = new Student("F74532", IT);
		Student Nasko = new Student("F74552", IT);
		
		IT.addElectiveCourse(Physics);
		IT.addElectiveCourse(Chemistry);
		IT.addMandatoryCourse(AnalyticalGeometry);
		IT.addMandatoryCourse(Calculus);
		
		IT.printMandatoryCourses();
		IT.printElectiveCourses();
		
		try {
			IT.deleteElectiveCourse("TC205");
			System.out.println("Chemistry has been deleted from the elective courses.");
		} catch (CourseNotFoundException e) {
			System.err.println(e.getMessage());
		}
		
		IT.printElectiveCourses();
		
		System.out.println(IT.hasCourse(Chemistry));
		
		try {
			Ivan.enrollIn(Calculus);
			System.out.println(Ivan.getFacultyNumber() + " has enrolled in " + Calculus.getName());
		} catch (CourseNotFoundException e) {
			System.err.println("Error: " + e.getMessage());
		}
		
		try {
			Nasko.enrollIn(Calculus);
		} catch (CourseNotFoundException e) {
			System.err.println("Error: " + e.getMessage());
		}
		
	
		
		
	}
}
