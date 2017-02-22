package labbook;

import java.util.Map;


/**
 * @description POJO 
 * @author Colin Siew
 * @date 22/2/2017
 */
public class NoteEntry {
	
	private Map<Integer,String[]> noteEntries;
	
	
	NoteEntry(Map<Integer,String[]> noteEntries){
		this.noteEntries = noteEntries;
	}


	public Map<Integer, String[]> getNoteEntries() {
		return noteEntries;
	}


	public void setNoteEntries(Map<Integer, String[]> noteEntries) {
		this.noteEntries = noteEntries;
	}


}
