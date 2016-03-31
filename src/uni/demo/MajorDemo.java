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
		
		System.out.print("The IT major has a course in Chemistry? ");
		System.out.println(IT.hasCourse(Chemistry));
		
		try {
			Ivan.enrollIn(Calculus);
			System.out.println(Ivan.getFacultyNumber() + " has enrolled in " + Calculus.getName());
		} catch (CourseNotFoundException e) {
			System.err.println("Error: " + e.getMessage());
		}
		
		System.out.println("-------");
		
		try {
		
			Nasko.enrollIn(Calculus);
			Nasko.enrollIn(Physics);
			Nasko.setGrade(Calculus, 3);
			Nasko.setGrade(Physics, 3);
			double calculusGrade = Nasko.getGradeFor(Calculus);
			System.out.println("Calculus grade: " + calculusGrade);
			
			double physicsGrade = Nasko.getGradeFor(Physics);
			System.out.println("Physics grade: " + physicsGrade);
			
			System.out.print("Attempt to drop out of Physics course: ");
			Nasko.dropOutOf(Physics);
			System.out.println(Nasko.attendsCourse(Physics) ? "not successfull" : "succesfull");
			
			System.out.println("Nasko's credits: " + Nasko.calculateCredits());
			
			System.out.println("Has graduated? " + Nasko.hasGraduated());

			
		} catch (CourseNotFoundException e) {
			System.err.println("Error: " + e.getMessage());
		}
		
	
		
		
	}
}
