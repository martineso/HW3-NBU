package uni.major;
import uni.course.Course;
import uni.course.exception.CourseNotFoundException;

public class Major {
	
	private String name;
	private int serialNumber;
	@SuppressWarnings("unused")
	private boolean isMajor;
	private Course[] mandatoryCourses, electiveCourses;

	private static int counter = 0;
	
	public Major(String name, boolean isMajor) {
		
		this.name = name;
		this.isMajor = isMajor;
		
		this.serialNumber = ++counter;
		mandatoryCourses = new Course[0];
		electiveCourses = new Course[0];
	}
	
	// Utility methods
	
	private int getFirstAvailablePosition(Course[] course) {

		int pos = 0;
		while(course[pos] != null) {

			pos++;
		}

		return pos;
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
	
	/**
	 * 
	 * @param position The starting position at which the deleted element was located at.
	 * @param courseArray The array whose elements need to be reorganized.
	 * @return Returns a new copy of the array with the elements in order. All null elements, if any,
	 * are pushed to the back of the array.  
	 * 
	 */
	private Course[] keepArrayElementsInOrder(int position, Course[] courseArray) {

		if(position == courseArray.length - 1) {

			// the deleted element is the last element thus
			// the array is still in order
			return courseArray;

		} else {


			while(position < courseArray.length - 1) {
				
				// Bring the elements a position forward
				
				courseArray[position] = courseArray[position + 1];
				position++;

			} 
			// Set the last element to be null
			// so that there are no duplicate elements in the 
			// prerequisites array
			
			courseArray[position] = null;
			
			return courseArray;

		}
	}
	
	/**
	 * @param coursesArray The array which elements need to be inspected.
	 * @param course The course which is to be added to the coursesArray.
	 * @return Returns a new array with the course added to it.
	 */
	private Course[] addCourse(Course[] coursesArray, Course course) {
		
		if(coursesArray.length != 0 && (coursesArray[coursesArray.length - 1] == null)) {

			int availablePos = getFirstAvailablePosition(coursesArray);
			coursesArray[availablePos] = course;
			return coursesArray;
			
		} else {
			
			Course[] temp = new Course[coursesArray.length + 1];
			System.arraycopy(coursesArray, 0, temp, 0, coursesArray.length);
			coursesArray = temp;
			coursesArray[coursesArray.length - 1] = course;
			return coursesArray;
		}
	}
	
	private void printCourses(Course[] coursesArray, String whichCourseArray) {
		
		System.out.println(whichCourseArray + ": ");

		if(coursesArray[0] == null) {

			System.out.println("None!");
			return;
		} 

		for(int i = 0; i < coursesArray.length; i++) {

			if(coursesArray[i] == null) break;
			System.out.println(coursesArray[i].getName());

		}
		
	}
	
	public void addMandatoryCourse(Course mandatoryCourse) {
		
		this.mandatoryCourses = addCourse(mandatoryCourses, mandatoryCourse);
	}
	
	public void addElectiveCourse(Course optionalCourse) {
		
		this.electiveCourses = addCourse(electiveCourses, optionalCourse);
	}
	
	public void deleteMandatoryCourse(String courseName) throws CourseNotFoundException {
		
		for(int i = 0; i < this.mandatoryCourses.length; i++) {

			if(this.mandatoryCourses[i].getName().equalsIgnoreCase(courseName)) {


				this.mandatoryCourses[i] = null;
				this.mandatoryCourses = keepArrayElementsInOrder(i, this.mandatoryCourses);
				return;

			}

		}

		throw new CourseNotFoundException("No such element found!");
	}
	
	public void deleteElectiveCourse(String courseName) throws CourseNotFoundException {
		
		for(int i = 0; i < this.electiveCourses.length; i++) {

			if(this.electiveCourses[i].getName().equalsIgnoreCase(courseName)) {


				this.electiveCourses[i] = null;
				this.electiveCourses = keepArrayElementsInOrder(i, this.electiveCourses);
				return;

			}

		}

		throw new CourseNotFoundException("No such element found!");
		
	}
	
	public void printMandatoryCourses() {
		
		printCourses(mandatoryCourses, "Mandatory courses");
	}
	
	public void printElectiveCourses() {
		
		printCourses(electiveCourses, "Elective courses");
		
	}

}
