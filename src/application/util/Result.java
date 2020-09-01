package application.util;

/**
 * Class Result
 * Object for storing result information.
 */
public class Result 
{
	private Boolean status;
	private String message;
	
	/**
	 * Get status
	 * @return boolean
	 */
	public boolean getStatus()
	{
		return this.status;
	}
	
	/**
	 * Set status
	 * @param status
	 */
	public void setStatus(Boolean status)
	{
		this.status = status;
	}
	
	/**
	 * Get message
	 * @return String
	 */
	public String getMessage()
	{
		return message == null ? "" : message;
	}
	
	/**
	 * Set message
	 * @param message
	 */
	public void setMessage(String message)
	{
		this.message = message;
	}
}
