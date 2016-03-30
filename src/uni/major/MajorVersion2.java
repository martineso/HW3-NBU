package uni.major;

import uni.arrays.utilites.ArraysUtility;
import uni.course.Course;
import uni.course.exception.CourseNotFoundException;

public class MajorVersion2 {
	
	private String name;
	private int serialNumber;
	@SuppressWarnings("unused")
	private boolean isMajor;
	private Course[] mandatoryCourses, electiveCourses;

	private static int counter = 0;
	
	public MajorVersion2(String name, boolean isMajor) {
		
		this.name = name;
		this.isMajor = isMajor;
		
		this.serialNumber = ++counter;
		mandatoryCourses = new Course[0];
		electiveCourses = new Course[0];
	}
	

	
	public Course[] getElectiveCourses() {
		return electiveCourses;
	}
	
	public Course[] getMandatoryCourses() {
		return mandatoryCourses;
	}
	
	public String getName() {
		return name;
	}
	
	public int getSerialNumber() {
		return serialNumber;
	}
	
	public void addMandatoryCourse(Course mandatoryCourse) {
		
		this.mandatoryCourses = ArraysUtility.addCourse(mandatoryCourses, mandatoryCourse);
	}
	
	public void addElectiveCourse(Course optionalCourse) {
		
		this.electiveCourses = ArraysUtility.addCourse(electiveCourses, optionalCourse);
	}
	
	public void deleteMadatoryCourse(String courseName) throws CourseNotFoundException {
		
		this.mandatoryCourses = ArraysUtility.deleteCourse(this.mandatoryCourses, courseName);
	}
	
	public void deleteElectiveCourse(String courseName) throws CourseNotFoundException {
		
		this.electiveCourses = ArraysUtility.deleteCourse(this.electiveCourses, courseName);
		
	}
	
	public boolean hasCourse(Course course) {
		
		boolean hasElectiveCourse = false;
		boolean hasMandatoryCourse = false;
		
		for(int i = 0; i < this.mandatoryCourses.length; i++) {
			
			if(mandatoryCourses[i] == null) break;
			
			if(mandatoryCourses[i].getName().equalsIgnoreCase(course.getName())) {
				hasMandatoryCourse = true;
				break;
			}
		}
		
		for(int i = 0; i < this.electiveCourses.length; i++) {
			
			if(electiveCourses[i] == null) break;
			
			if(electiveCourses[i].getName().equalsIgnoreCase(course.getName())) {
				hasElectiveCourse = true;
				break;
			}
		}
		
		return (hasMandatoryCourse || hasElectiveCourse);
	}
	
	public void printMandatoryCourses() {
		
		System.out.println("The mandatory courses of " + this.getName() + " major are:");
		ArraysUtility.printCourses(mandatoryCourses);
	}
	
	public void printElectiveCourses() {
		
		System.out.println("The elective courses of " + this.getName() + " major are:");
		ArraysUtility.printCourses(electiveCourses);
		
	}
	
	
}
