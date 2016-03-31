package uni.arrays.utilites;

import uni.course.Course;
import uni.course.exception.CourseNotFoundException;

public class ArraysUtility {
	
	/**
	 * 
	 * @param position The starting position at which the deleted element was located at.
	 * @param courseArray The array whose elements need to be reorganized.
	 * @return Returns a new copy of the array with the elements in order. All null elements, if any,
	 * are pushed to the back of the array.  
	 * @author Martin Kontilov F74466
	 */
	private static Course[] keepArrayElementsInOrder(int position, Course[] courseArray) {

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
	 * 
	 * @param coursesArray The array which elements need to be inspected.
	 * @param course The course which is to be added to the coursesArray.
	 * @return Returns a new array with the course added to it.
	 * @author Martin Kontilov F74466
	 */
	public static Course[] addCourse(Course[] coursesArray, Course course) {
		
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
	
	/**
	 * 
	 * @param coursesArray The array whose elements are to be printed. Prints each
	 * course name on a new line.
	 * @author Martin Kontilov F74466
	 */
	public static void printCourses(Course[] coursesArray) {

		if(coursesArray[0] == null) {

			System.out.println("None!");
			return;
		} 

		for(int i = 0; i < coursesArray.length; i++) {

			if(coursesArray[i] == null) break;
			System.out.println(coursesArray[i].getName());

		}
		
	}
	
	/**
	 * 
	 * @param coursesArray The array which contains the element that needs to be deleted.
	 * @param courseName The name of the course which needs to be deleted.
	 * @return A new ordered copy of the array with the course deleted from it.
	 * @throws CourseNotFoundException 
	 * 
	 * @author Martin Kontilov F74466
	 */
	public static Course[] deleteCourse(Course[] coursesArray, String courseName) throws CourseNotFoundException {
		
		 
		for(int i = 0; i < coursesArray.length; i++) {
			
			if(coursesArray[i] != null) {
				if(coursesArray[i].getName().equalsIgnoreCase(courseName)) {

					coursesArray[i] = null;
					coursesArray = keepArrayElementsInOrder(i, coursesArray);
					return coursesArray;
				}
			}
		}
		

		throw new CourseNotFoundException("No such element found!");
	}
	
	private static int getFirstAvailablePosition(Course[] course) {

		int pos = 0;
		while(course[pos] != null) {

			pos++;
		}

		return pos;
	}
	

}
