package uni.student;

import uni.arrays.utilites.ArraysUtility;
import uni.course.Course;
import uni.course.exception.CourseNotFoundException;
import uni.major.Major;

public class Student {
	
	private String name, fNumber;
	private Major major;
	private Course[] enrolledIn;
	public String[][] grades;
	private boolean hasGraduated;
	
	public Student(String facultyNumber, Major major) {
		
		this.fNumber = facultyNumber;
		this.major = major;
		
		this.enrolledIn = new Course[5];
		this.grades = new String[2][enrolledIn.length];
		
	}
	
	public Course[] getCoursesEnrolledIn() {
		return enrolledIn;
	}
	
	public String getFacultyNumber() {
		return fNumber;
	}
	
	public Major getMajor() {
		return major;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean hasGraduated() {
		return hasGraduated;
	}
	
	public void enrollIn(Course course) throws CourseNotFoundException {
		
		if(major.hasCourse(course)) {
			
			enrolledIn = ArraysUtility.addCourse(enrolledIn, course);
			
		} else {
			
			throw new CourseNotFoundException("This course is not part of this programme.");
		}
		
	}
	
	public void dropOutOf(Course course) throws CourseNotFoundException {
		
		try {
			
			enrolledIn = ArraysUtility.deleteCourse(enrolledIn, course.getName());
			
		} catch(CourseNotFoundException e) {
			
			throw new CourseNotFoundException("Cannot delete a course which the student is not enrolled in!");
		}
		
	}
	
	
}
