package uni.course.exception;


/**
 * 
 * @author Martin Kontilov F74466
 *
 */
public class CourseNotFoundException extends Exception {

	private static final long serialVersionUID = -2321288625104989781L;
	
	public CourseNotFoundException() {
		super();
	}
	
	public CourseNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public CourseNotFoundException(String message) {
		super(message);
	}
	
	public CourseNotFoundException(Throwable cause) {
		
		super(cause);
	}
}
