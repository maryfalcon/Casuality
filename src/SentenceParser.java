import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SentenceParser {
	private static String bindWords;
	
	public static void initialize(){
		getBindWords();
	}
	
	private static void getBindWords(){
		bindWords="";
		try {
			BufferedReader reader=new BufferedReader(new FileReader(new File("resources//words.txt")));
			while(reader.ready()){
			if(!bindWords.equals(""))
				bindWords+="|";
			bindWords+=reader.readLine();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
		System.out.println(bindWords);
	}
	
	
	private static WordPosition getWordPosition(String sentence, String word){
		Pattern pattern=Pattern.compile(word);
		Matcher matcher=pattern.matcher(sentence);
		if(matcher.find())
			return new WordPosition(matcher.start(),matcher.end());
		else
			return WordPosition.UNDEFINED;
	}
	
	private static boolean checkExistingCause(String sentence, HashMap<String,Integer> causeMap){
		String causes="";
		Iterator i=causeMap.entrySet().iterator();
		boolean causePresence=false;
		while(i.hasNext()){
			HashMap.Entry pair = (HashMap.Entry)i.next();
			if(sentence.contains((String)pair.getKey())){
					pair.setValue((Integer)pair.getValue()+1);
					causePresence=true;
			}
		}
		return causePresence;
	}
	
	private static String findNewCause(String sentence){
		return null;
	}
	
	public static void getCause(String sentence, String result, HashMap<String,HashMap<String,Integer>> causeMap){
		WordPosition bindWordP=getWordPosition(sentence,bindWords);
		if (bindWordP.equals(WordPosition.UNDEFINED)){
			return;
		}
		WordPosition resultP=getWordPosition(sentence, result);
		String sentencePart=null;
		if(resultP.start>bindWordP.start)
			sentencePart=sentence.substring(0, bindWordP.start);
		if(resultP.start<bindWordP.start)
			sentencePart=sentence.substring(bindWordP.end);
		if(causeMap.get(result)==null)
			causeMap.put(result, new HashMap<String,Integer>());
		if(checkExistingCause(sentencePart, causeMap.get(result)))
			return;
		String newCause=findNewCause(sentencePart);
		if(newCause!=null)
			causeMap.get(result).put(newCause,1); ///?
	}
}
