package labbook;

import java.util.Map;

/**
 * @description POJO 
 * @author Colin Siew
 * @date 22/2/2017
 */
public class NoteEntryName {
	
	private Map<Integer,String> names;
	
	public NoteEntryName(Map<Integer,String> names){
		this.names = names;
	}

	public Map<Integer, String> getNames() {
		return names;
	}

	public void setNames(Map<Integer, String> names) {
		this.names = names;
	}
	
	
}
