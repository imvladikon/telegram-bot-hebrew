package org.hebrbot.bot.pojo.gresponse;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Generated;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Generated("com.robohorse.robopojogenerator")
public class Response{

	@JsonProperty("target_inflections")
	private List<TargetInflectionsItem> targetInflections;

	@JsonProperty("alternative_translations")
	private List<AlternativeTranslationsItem> alternativeTranslations;

	@JsonProperty("query_inflections")
	private List<QueryInflectionsItem> queryInflections;

	@JsonProperty("src")
	private String src;

	@JsonProperty("sentences")
	private List<SentencesItem> sentences;

	@JsonProperty("ld_result")
	private LdResult ldResult;

	@JsonProperty("confidence")
	private int confidence;

	@JsonProperty("dict")
	private List<DictItem> dict;

	public void setTargetInflections(List<TargetInflectionsItem> targetInflections){
		this.targetInflections = targetInflections;
	}

	public List<TargetInflectionsItem> getTargetInflections(){
		return targetInflections;
	}

	public void setAlternativeTranslations(List<AlternativeTranslationsItem> alternativeTranslations){
		this.alternativeTranslations = alternativeTranslations;
	}

	public List<AlternativeTranslationsItem> getAlternativeTranslations(){
		return alternativeTranslations;
	}

	public void setQueryInflections(List<QueryInflectionsItem> queryInflections){
		this.queryInflections = queryInflections;
	}

	public List<QueryInflectionsItem> getQueryInflections(){
		return queryInflections;
	}

	public void setSrc(String src){
		this.src = src;
	}

	public String getSrc(){
		return src;
	}

	public void setSentences(List<SentencesItem> sentences){
		this.sentences = sentences;
	}

	public List<SentencesItem> getSentences(){
		return sentences;
	}

	public void setLdResult(LdResult ldResult){
		this.ldResult = ldResult;
	}

	public LdResult getLdResult(){
		return ldResult;
	}

	public void setConfidence(int confidence){
		this.confidence = confidence;
	}

	public int getConfidence(){
		return confidence;
	}

	public void setDict(List<DictItem> dict){
		this.dict = dict;
	}

	public List<DictItem> getDict(){
		return dict;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"target_inflections = '" + targetInflections + '\'' + 
			",alternative_translations = '" + alternativeTranslations + '\'' + 
			",query_inflections = '" + queryInflections + '\'' + 
			",src = '" + src + '\'' + 
			",sentences = '" + sentences + '\'' + 
			",ld_result = '" + ldResult + '\'' + 
			",confidence = '" + confidence + '\'' + 
			",dict = '" + dict + '\'' + 
			"}";
		}
}