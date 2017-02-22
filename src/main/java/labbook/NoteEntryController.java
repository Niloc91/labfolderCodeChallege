package labbook;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import info.debatty.java.stringsimilarity.*;


/**
 * @description Controller class for application 
 * @author Colin Siew
 * @date 22/2/2017
 */
@RestController()
public class NoteEntryController {

    
    //storage
    private static Map<Integer,String[]> noteEntries = new HashMap<Integer,String[]>();
    private static Map<Integer,String> noteNames = new HashMap<Integer,String>();
    
    
   
    /** Returns all note entries
     * 
     * @return JSON representation of NoteEntry object with note id and string array 
     */
    @RequestMapping( method = RequestMethod.GET, value = "/notes")
    public NoteEntry getNotes() {
    	
        return new NoteEntry(noteEntries);
    }
    
    /**
     * return note with id specified
     * 
     * @param note_id - integer reference to note entry
     * @return JSON representation of string array contained at note_id
     */
    @RequestMapping( method = RequestMethod.GET, value = "/notes/{note_id}")
    public NoteEntry getNotesId(@PathVariable int note_id) {
    	
    	Map<Integer,String[]> noteEntry = new HashMap<Integer,String[]>();
    	
    	noteEntry.put(note_id,noteEntries.get(note_id));
    	
        return new NoteEntry(noteEntry);
    }
    
    
    /**
     * Gets all note names set
     * each note_id can be set to a corresponding string
     * 
     * @return JSON representation of all id to name mappings
     */
    @RequestMapping( method = RequestMethod.GET, value = "/notesNames")
    public NoteEntryName getNotesNames() {
    	
        return new NoteEntryName(noteNames);
    }
    
    /**
     * 
     * gets name of certain note_id
     * 
     * @param note_id integer reference to note id
     * @return JSON representation of name mapped to given note_id
     */
    @RequestMapping( method = RequestMethod.GET, value = "/notesNames/{note_id}")
    public NoteEntryName getNotesNamesId(@PathVariable int note_id) {
    	
    	Map<Integer,String> noteName = new HashMap<Integer,String>();
    	noteName.put(note_id, noteNames.get(note_id));
    	
    	
        return new NoteEntryName(noteName);
    }
    
    //Populate dummy data
    @RequestMapping( method = RequestMethod.GET, value = "/test")
    public NoteEntry test() {
    	
    	//Map<Integer,String[]> noteEntry = new HashMap<Integer,String[]>();
    	String [] s = {"apple","apple2","apple3","banana"};
    	String [] s2 ={"apple","banana2","bananad","banana"};
    	noteEntries.put(1,s);
    	noteEntries.put(2,s2);
    	noteNames.put(1, "Apple list");
    	noteNames.put(2, "banana list");
    	
    	
        return new NoteEntry(noteEntries);
    }
    
    
    
    /**
     * Method to add in new lab entries
     * 
     * @note_id - id of note represented as an integer
     * @note_contents - String array of lab entry contents
     * @return status code ok if successful
     * 
     */
    @RequestMapping( method = RequestMethod.PUT, value = "/notes/{note_id}/{note_contents}")
    public ResponseEntity<String> putNotesId(@PathVariable int note_id,@PathVariable String[] note_contents) {
    	
    	noteEntries.put(note_id, note_contents);
    	
        return new ResponseEntity<String>(HttpStatus.OK);
    }
    
    /**
     * Method to map note names to entries
     * 
     * @param note_id - id of node represented as an integer
     * @param note_name - string representation of note name
     * @return status code ok if successful
     */
    @RequestMapping( method = RequestMethod.PUT, value = "/notesNames/{note_id}/{note_name}")
    public ResponseEntity<String> putNotesNamesId(@PathVariable int note_id,@PathVariable String note_name) {
    	
    	
    	noteNames.put(note_id, note_name);
    	
    	return new ResponseEntity<String>(HttpStatus.OK);
    }
    
    /**
     * Method to delete lab entries
     * 
     * @param note_id - note id to be removed
     * @return status code ok if successful
     */
    @RequestMapping( method = RequestMethod.DELETE, value = "/notes/{note_id}")
    public ResponseEntity<String> putNotesId(@PathVariable int note_id) {
    	
    	if(noteNames.containsKey(note_id)){
    		noteNames.remove(note_id);
    	}
    	
    	if(noteEntries.containsKey(note_id)){
    		noteEntries.remove(note_id);
    		return new ResponseEntity<String>(HttpStatus.OK);
    	}else{
    		return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
    	}
        
    }
    
    
    /**
     * Endpoint is designed to return array positions of all of the same words and 
     * similar words in lab entries.
     * 
     * 
     * @param comp_word -String value of the comparison word
     * @return JSON representation of 2 lists, same being the same words as the comp_word
     * similar being the Levenshtein distance of 1 or less.  
     */
    @RequestMapping( method = RequestMethod.GET, value = "/getSimilar/{comp_word}")
    public Similar compareWord(@PathVariable String comp_word) {
    	
    	Levenshtein l = new Levenshtein();
    	Map<Integer,List<Integer>> similarWords = new HashMap<Integer,List<Integer>>();
    	Map<Integer,List<Integer>> sameWords = new HashMap<Integer,List<Integer>>();
    	List<Integer> similar = new ArrayList<Integer>();
    	List<Integer> same = new ArrayList<Integer>();
    	
    	for (Entry<Integer, String[]> entry : noteEntries.entrySet()) {
    		for(int i=0;i<entry.getValue().length;i++){
    			
    			if(l.distance(comp_word,entry.getValue()[i])==0){
    				same.add(i);
    			}else if(l.distance(comp_word,entry.getValue()[i])>0 && l.distance(comp_word,entry.getValue()[i]) <=1){
    				similar.add(i);
    			}
    		}
    		
    		similarWords.put(entry.getKey(), similar);
    		sameWords.put(entry.getKey(), same);
    		similar =new ArrayList<Integer>();
    		same = new ArrayList<Integer>();
    		
    	}
    	
    	
        return new Similar(similarWords,sameWords);
    }
    
}