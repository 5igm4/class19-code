package wsMessages;

public class PickleMessage extends Message  {
	String id; 
	
	public PickleMessage(String id) {
		this.id = id;
	}

	public String getID() { return id; }
}
