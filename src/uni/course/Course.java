package uni.course;

import uni.arrays.utilites.ArraysUtility;
import uni.course.exception.CourseNotFoundException;

public class Course {
	
	private String name;
	private int credits;
	private int serialNumber;
	private static int counter = 0;
	private Course[] prerequisites;

	public Course(String name, int credits) {

		this.name = name;
		this.credits = credits;
		this.serialNumber = ++counter;
		this.prerequisites = new Course[0];

	}

	public String getName() {

		return this.name;
	}

	public int getCredits() {

		return this.credits;
	}

	public int getSerialNumber() {

		return this.serialNumber;
	}
	
	public void addPrerequisite(Course course) {
		
		this.prerequisites = ArraysUtility.addCourse(prerequisites, course);
	}

	public void deletePrerequisite(String courseName) throws CourseNotFoundException {

		try {
			
			this.prerequisites = ArraysUtility.deleteCourse(prerequisites, courseName);
			
		} catch(CourseNotFoundException e) {
			
			throw new CourseNotFoundException("No such element found!");
		}

	}

	public void printPrerequisites() {

		System.out.println("Prerequisites: ");

		ArraysUtility.printCourses(prerequisites);
		
	}
}

