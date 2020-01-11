package org.hebrbot.bot.pojo.gresponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class TranslationEngineDebugInfoItem{

	@JsonProperty("model_tracking")
	private ModelTracking modelTracking;

	public void setModelTracking(ModelTracking modelTracking){
		this.modelTracking = modelTracking;
	}

	public ModelTracking getModelTracking(){
		return modelTracking;
	}

	@Override
 	public String toString(){
		return 
			"TranslationEngineDebugInfoItem{" + 
			"model_tracking = '" + modelTracking + '\'' + 
			"}";
		}
}