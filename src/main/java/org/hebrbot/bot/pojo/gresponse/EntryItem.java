package org.hebrbot.bot.pojo.gresponse;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class EntryItem{

	@JsonProperty("reverse_translation")
	private List<String> reverseTranslation;

	@JsonProperty("word")
	private String word;

	public void setReverseTranslation(List<String> reverseTranslation){
		this.reverseTranslation = reverseTranslation;
	}

	public List<String> getReverseTranslation(){
		return reverseTranslation;
	}

	public void setWord(String word){
		this.word = word;
	}

	public String getWord(){
		return word;
	}

	@Override
 	public String toString(){
		return 
			"EntryItem{" + 
			"reverse_translation = '" + reverseTranslation + '\'' + 
			",word = '" + word + '\'' + 
			"}";
		}
}