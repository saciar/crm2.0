package crm.client.validacion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ErrorList implements Iterable<ErrorMessage> {
	List<ErrorMessage> errors;
	
	public ErrorList(){
		errors = new ArrayList<ErrorMessage>();
	}

	public boolean addError(ErrorMessage o) {
		return errors.add(o);
	}

	public boolean addErrors(ErrorList c) {
		if (c == null)
			return false;
		return errors.addAll(c.errors);
	}

	public void clear() {
		errors.clear();
	}

	public boolean equals(ErrorMessage o) {
		return errors.equals(o);
	}

	public ErrorMessage get(int index) {
		return errors.get(index);
	}

	public int hashCode() {
		return errors.hashCode();
	}

	public boolean isEmpty() {
		return errors.isEmpty();
	}

	public Iterator<ErrorMessage> iterator() {
		return errors.iterator();
	}

	public ErrorMessage remove(int index) {
		return errors.remove(index);
	}

	public boolean remove(ErrorMessage o) {
		return errors.remove(o);
	}

	public int size() {
		return errors.size();
	}

	public void addError(String msg) {
		addError(new ErrorMessage(msg));
	}
}
