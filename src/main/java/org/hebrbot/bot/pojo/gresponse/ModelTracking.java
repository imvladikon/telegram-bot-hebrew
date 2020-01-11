package org.hebrbot.bot.pojo.gresponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class ModelTracking{

	@JsonProperty("checkpoint_md5")
	private String checkpointMd5;

	@JsonProperty("launch_doc")
	private String launchDoc;

	public void setCheckpointMd5(String checkpointMd5){
		this.checkpointMd5 = checkpointMd5;
	}

	public String getCheckpointMd5(){
		return checkpointMd5;
	}

	public void setLaunchDoc(String launchDoc){
		this.launchDoc = launchDoc;
	}

	public String getLaunchDoc(){
		return launchDoc;
	}

	@Override
 	public String toString(){
		return 
			"ModelTracking{" + 
			"checkpoint_md5 = '" + checkpointMd5 + '\'' + 
			",launch_doc = '" + launchDoc + '\'' + 
			"}";
		}
}