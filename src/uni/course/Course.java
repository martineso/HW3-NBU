package uni.course;

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

	private int getFirstAvailablePosition() {

		int pos = 0;
		while(this.prerequisites[pos] != null) {

			pos++;
		}

		return pos;
	}

	public void addPrerequisite(Course course) {

		if(this.prerequisites.length != 0 && (this.prerequisites[this.prerequisites.length - 1] == null)) {

			int availablePos = getFirstAvailablePosition();
			this.prerequisites[availablePos] = course;
			return;
			
		} else {
			
			//
			Course[] temp = new Course[this.prerequisites.length + 1];
			System.arraycopy(this.prerequisites, 0, temp, 0, this.prerequisites.length);
			this.prerequisites = temp;
			this.prerequisites[this.prerequisites.length - 1] = course;
		}

	}

	public void deletePrerequisite(String courseName) throws CourseNotFoundException {

		for(int i = 0; i < this.prerequisites.length; i++) {

			if(this.prerequisites[i].getName().equalsIgnoreCase(courseName)) {


				this.prerequisites[i] = null;
				keepArrayElementsInOrder(i);
				return;

			}

		}

		throw new CourseNotFoundException("No such element found!");

	}

	private void keepArrayElementsInOrder(int position) {

		if(position == this.prerequisites.length - 1) {

			// the deleted element is the last element thus
			// the array is still in order
			return;

		}
		else {


			while(position < this.prerequisites.length - 1) {
				
				// Bring the elements a position forward
				
				this.prerequisites[position] = this.prerequisites[position + 1];
				position++;

			} 
			// Set the last element to be null
			// so that there are no duplicate elements in the 
			// prerequisites array
			
			this.prerequisites[position] = null;

		}
	}

	public void printPrerequisites() {

		System.out.println("Prerequisites: ");

		if(this.prerequisites[0] == null) {

			System.out.println("None!");
			return;
		} 

		for(int i = 0; i < this.prerequisites.length; i++) {

			if(this.prerequisites[i] == null) break;
			System.out.println(this.prerequisites[i].getName());

		}
	}
}

