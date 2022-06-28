package crm.client.serializer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class PresupuestoOutput {
	
	private FileOutputStream file;
	private ObjectOutputStream output;
	
	public void open() throws IOException{
		if((System.getProperties().getProperty("os.name").toLowerCase().substring(0,3)).equals("win"))
			file = new FileOutputStream("c:/ser.ser");
		else{
			File folder = new File("/crm");
			folder.mkdirs();
			file = new FileOutputStream("/crm/ser.ser");
		}
		output = new ObjectOutputStream(file);		
	}
	
	public void close() throws IOException{
		if(output != null){
			output.close();
		}
	}
	
	public void save(PresupuestoXML ppto) throws IOException{
		if(output != null){
			output.writeObject(ppto);
		}
	}
}
