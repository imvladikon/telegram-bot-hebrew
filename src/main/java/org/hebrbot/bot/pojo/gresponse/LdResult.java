package org.hebrbot.bot.pojo.gresponse;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class LdResult{

	@JsonProperty("extended_srclangs")
	private List<String> extendedSrclangs;

	@JsonProperty("srclangs")
	private List<String> srclangs;

	@JsonProperty("srclangs_confidences")
	private List<Integer> srclangsConfidences;

	public void setExtendedSrclangs(List<String> extendedSrclangs){
		this.extendedSrclangs = extendedSrclangs;
	}

	public List<String> getExtendedSrclangs(){
		return extendedSrclangs;
	}

	public void setSrclangs(List<String> srclangs){
		this.srclangs = srclangs;
	}

	public List<String> getSrclangs(){
		return srclangs;
	}

	public void setSrclangsConfidences(List<Integer> srclangsConfidences){
		this.srclangsConfidences = srclangsConfidences;
	}

	public List<Integer> getSrclangsConfidences(){
		return srclangsConfidences;
	}

	@Override
 	public String toString(){
		return 
			"LdResult{" + 
			"extended_srclangs = '" + extendedSrclangs + '\'' + 
			",srclangs = '" + srclangs + '\'' + 
			",srclangs_confidences = '" + srclangsConfidences + '\'' + 
			"}";
		}
}