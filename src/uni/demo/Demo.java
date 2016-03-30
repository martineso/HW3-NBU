package uni.demo;

import uni.course.Course;

public class Demo {
	public static void main(String[] args) {
		
		Course course1 = new Course("CT101", 12);
    	Course course2 = new Course("BCT101", 4);
    	Course course3 = new Course("TC205", 2);
    	Course course4 = new Course("AB201", 4);
    	
    	course1.addPrerequisite(course2);
    	course1.addPrerequisite(course3);
    	course1.addPrerequisite(course4);
    	
    	course1.printPrerequisites();
    	
    	System.out.println("Attempt to delete BCT101");
    	try {
    		
    		course1.deletePrerequisite("BCT101");
    		course1.printPrerequisites();
    		
    	} catch(Exception e) {
    		
    		System.out.println("Error: " + e.getMessage());
    		
    	}
	}
}
