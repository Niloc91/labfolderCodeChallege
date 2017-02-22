package labbook;

import java.util.List;
import java.util.Map;


/**
 * @description POJO 
 * @author Colin Siew
 * @date 22/2/2017
 */
public class Similar {

	private Map<Integer,List<Integer>> similarWords;
	private Map<Integer,List<Integer>> sameWords;
	
	
	public Similar(Map<Integer,List<Integer>> similarWords,Map<Integer,List<Integer>> sameWords){
		this.similarWords =similarWords;
		this.sameWords = sameWords;
	}

	public Map<Integer, List<Integer>> getSimilarWords() {
		return similarWords;
	}

	public void setSimilarWords(Map<Integer, List<Integer>> similarWords) {
		this.similarWords = similarWords;
	}

	public Map<Integer, List<Integer>> getSameWords() {
		return sameWords;
	}

	public void setSameWords(Map<Integer, List<Integer>> sameWords) {
		this.sameWords = sameWords;
	}
	
	
}
