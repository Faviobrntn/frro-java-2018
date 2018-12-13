package util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// log rotate para control de logs

public class AppDataException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Throwable innerException;
	private String message;
	
	
	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}

	public AppDataException(String message){
		this.setMessage(message);
	}
	
	public AppDataException(Throwable e, String message){
		this.innerException=e;
		this.setMessage(message);
	}
	
	public AppDataException(Throwable e, String message, Level errorLevel){
		this(e,message);
		Logger logger = LogManager.getLogger(getClass());
		logger.log(errorLevel,message);
	}

}