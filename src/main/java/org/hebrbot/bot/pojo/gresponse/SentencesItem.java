package org.hebrbot.bot.pojo.gresponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;
import java.util.List;

@Generated("com.robohorse.robopojogenerator")
public class SentencesItem{

	@JsonProperty("src_translit")
	private String srcTranslit;

	@JsonProperty("orig")
	private String orig;

	@JsonProperty("translation_engine_debug_info")
	private List<TranslationEngineDebugInfoItem> translationEngineDebugInfo;

	@JsonProperty("backend")
	private int backend;

	@JsonProperty("trans")
	private String trans;

	public void setSrcTranslit(String srcTranslit){
		this.srcTranslit = srcTranslit;
	}

	public String getSrcTranslit(){
		return srcTranslit;
	}

	public void setOrig(String orig){
		this.orig = orig;
	}

	public String getOrig(){
		return orig;
	}

	public void setTranslationEngineDebugInfo(List<TranslationEngineDebugInfoItem> translationEngineDebugInfo){
		this.translationEngineDebugInfo = translationEngineDebugInfo;
	}

	public List<TranslationEngineDebugInfoItem> getTranslationEngineDebugInfo(){
		return translationEngineDebugInfo;
	}

	public void setBackend(int backend){
		this.backend = backend;
	}

	public int getBackend(){
		return backend;
	}

	public void setTrans(String trans){
		this.trans = trans;
	}

	public String getTrans(){
		return trans;
	}

	@Override
 	public String toString(){
		return 
			"SentencesItem{" + 
			"src_translit = '" + srcTranslit + '\'' + 
			",orig = '" + orig + '\'' + 
			",translation_engine_debug_info = '" + translationEngineDebugInfo + '\'' + 
			",backend = '" + backend + '\'' + 
			",trans = '" + trans + '\'' + 
			"}";
		}
}