package org.hebrbot.bot.pojo.gresponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class TargetInflectionsItem{

	@JsonProperty("features")
	private Features features;

	@JsonProperty("written_form")
	private String writtenForm;

	public void setFeatures(Features features){
		this.features = features;
	}

	public Features getFeatures(){
		return features;
	}

	public void setWrittenForm(String writtenForm){
		this.writtenForm = writtenForm;
	}

	public String getWrittenForm(){
		return writtenForm;
	}

	@Override
 	public String toString(){
		return 
			"TargetInflectionsItem{" + 
			"features = '" + features + '\'' + 
			",written_form = '" + writtenForm + '\'' + 
			"}";
		}
}