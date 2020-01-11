package org.hebrbot.bot.pojo.gresponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class AlternativeItem{

	@JsonProperty("score")
	private int score;

	@JsonProperty("attach_to_next_token")
	private boolean attachToNextToken;

	@JsonProperty("word_postproc")
	private String wordPostproc;

	@JsonProperty("has_preceding_space")
	private boolean hasPrecedingSpace;

	public void setScore(int score){
		this.score = score;
	}

	public int getScore(){
		return score;
	}

	public void setAttachToNextToken(boolean attachToNextToken){
		this.attachToNextToken = attachToNextToken;
	}

	public boolean isAttachToNextToken(){
		return attachToNextToken;
	}

	public void setWordPostproc(String wordPostproc){
		this.wordPostproc = wordPostproc;
	}

	public String getWordPostproc(){
		return wordPostproc;
	}

	public void setHasPrecedingSpace(boolean hasPrecedingSpace){
		this.hasPrecedingSpace = hasPrecedingSpace;
	}

	public boolean isHasPrecedingSpace(){
		return hasPrecedingSpace;
	}

	@Override
 	public String toString(){
		return 
			"AlternativeItem{" + 
			"score = '" + score + '\'' + 
			",attach_to_next_token = '" + attachToNextToken + '\'' + 
			",word_postproc = '" + wordPostproc + '\'' + 
			",has_preceding_space = '" + hasPrecedingSpace + '\'' + 
			"}";
		}
}