package crm.client.serializer;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;


public class PresupuestoInput {

	private FileInputStream file;
	private ObjectInputStream input;
	
	public void open() throws IOException{
		file = new FileInputStream("c:/ser.ser");
		input = new ObjectInputStream(file);		
	}
	
	public void close() throws IOException{
		if(input != null){
			input.close();
		}
	}
	
	public PresupuestoXML recover() throws IOException{
		PresupuestoXML ppto = null;
		
		if(input != null){
			try {
				ppto = (PresupuestoXML)input.readObject();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return ppto;
	}

}
