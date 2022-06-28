package crm.client.validacion;

public class ErrorMessage {
	String message;
	
	public ErrorMessage(){
		message = "No definido";
	}
	
	public ErrorMessage(String message){
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public String toString(){
		return message;
	}
}
