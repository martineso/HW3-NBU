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
		
		this.enrolledIn = new Course[0];
		this.grades = new String[2][enrolledIn.length + 1];
		
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
	
	public void setName(String name) {
		
		this.name = name;
	}
	
	public void changeMajor(Major major) {
		
		this.major = major;
	}
	
	public boolean attendsCourse(Course course) {
		
		String courseName = course.getName();
		
		for(int i = 0; i < enrolledIn.length; i++) {
			
			if(enrolledIn[i] != null && (courseName.equalsIgnoreCase(enrolledIn[i].getName()))) {
				
				return true;
			}
		}
		
		return false;
	}
	
	public double getGradeFor(Course course) throws CourseNotFoundException {
		
		if(!attendsCourse(course)) {
			throw new CourseNotFoundException("The student does not attend this course!"); 
		}
		
		double grade = 0;
		
		for(int i = 0; i < grades[0].length; i++) {
			
			if(course.getName().equalsIgnoreCase(grades[0][i])) {
				
				grade = Double.valueOf(grades[1][i]);
				return grade;
			}
		}
		
		return grade;
	}
	
	public void enrollIn(Course course) throws CourseNotFoundException {
		
		if(major.hasCourse(course)) {
			
			enrolledIn = ArraysUtility.addCourse(enrolledIn, course);
			addGradeAndCourse(course, 0);
			
		} else {
			
			throw new CourseNotFoundException("This course is not part of this programme.");
		}
		
	}
	
	public void dropOutOf(Course course) throws CourseNotFoundException {
		
		try {
			
			enrolledIn = ArraysUtility.deleteCourse(enrolledIn, course.getName());
			deleteGradeAndCourse(course);
			
		} catch(CourseNotFoundException e) {
			
			throw new CourseNotFoundException("Attempt to drop out of a course which the student has not enrolled in!");
		}
		
	}
	
	public void setGrade(Course course, double grade) throws CourseNotFoundException {
		
		if(major.hasCourse(course)) {
			
			for(int i = 0; i < grades[0].length; i++) {
				
				if(course.getName().equalsIgnoreCase(grades[0][i])) {
					
					grades[1][i] = "" + grade;
					return;
				}
			}
			
		} else {
			
			throw new CourseNotFoundException("This course is not part of this programme.");
		}
	}
	
	public int calculateCredits() {
		
		int credits = 0;
		
		for(int i = 0; i < enrolledIn.length; i++) {
			
			if(enrolledIn[i] != null) {
				credits += enrolledIn[i].getCredits();
			}
			
		}
		
		return credits;
	}
	
	private boolean checkGrades() {
		
		boolean isBigger = false;
		for(String grade : grades[1]) {
			
			if(Double.valueOf(grade) >= 3) {
				isBigger = true;
			} else {
				isBigger = false;
			}
		}
		
		return isBigger;
	}
	
	public void graduate() {
		
		hasGraduated = calculateCredits() > 240 && checkGrades() ? true : false;
	}
	
	private void addGradeAndCourse(Course course, double grade) {
		
		if(grades[0][0] == null) {
			
			grades[0][0] = course.getName();
			grades[1][0] = "" + grade;
			
		} else {
			
			int initialSize = grades[0].length;
			String[][] temp = new String[2][initialSize + 1];
			
			for(int i = 0; i < 2; i++) {
				
				System.arraycopy(grades[i], 0, temp[i], 0, initialSize);
			}
			
			temp[0][initialSize] = course.getName();
			temp[1][initialSize] = "" + grade;
			
			grades = temp;
			
		}
	}
	
	private void deleteGradeAndCourse(Course course) {
		
		int initialSize = grades[0].length;
		
		for(int i = 0; i < initialSize; i++) {
			
			if(course.getName().equalsIgnoreCase(grades[0][i])) {
				
				int pos = i;
				for(; pos < initialSize - 1; pos++) {
					
					grades[0][pos] = grades[0][pos + 1];
					grades[1][pos] = grades[1][pos + 1];
					
				}
				
				String[][] temp = new String[2][initialSize - 1];
				
				for(int j = 0; j < 2; j++) {
					
					System.arraycopy(grades[j], 0, temp[j], 0, initialSize - 1);
				}
				
				grades = temp;
				
			}
		}
		
	}
}
