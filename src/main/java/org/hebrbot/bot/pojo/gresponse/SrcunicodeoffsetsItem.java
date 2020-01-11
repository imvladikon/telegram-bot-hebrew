package org.hebrbot.bot.pojo.gresponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class SrcunicodeoffsetsItem{

	@JsonProperty("end")
	private int end;

	@JsonProperty("begin")
	private int begin;

	public void setEnd(int end){
		this.end = end;
	}

	public int getEnd(){
		return end;
	}

	public void setBegin(int begin){
		this.begin = begin;
	}

	public int getBegin(){
		return begin;
	}

	@Override
 	public String toString(){
		return 
			"SrcunicodeoffsetsItem{" + 
			"end = '" + end + '\'' + 
			",begin = '" + begin + '\'' + 
			"}";
		}
}