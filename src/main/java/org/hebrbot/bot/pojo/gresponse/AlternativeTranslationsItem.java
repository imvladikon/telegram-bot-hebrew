package org.hebrbot.bot.pojo.gresponse;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class AlternativeTranslationsItem{

	@JsonProperty("srcunicodeoffsets")
	private List<SrcunicodeoffsetsItem> srcunicodeoffsets;

	@JsonProperty("alternative")
	private List<AlternativeItem> alternative;

	@JsonProperty("end_pos")
	private int endPos;

	@JsonProperty("src_phrase")
	private String srcPhrase;

	@JsonProperty("start_pos")
	private int startPos;

	@JsonProperty("raw_src_segment")
	private String rawSrcSegment;

	public void setSrcunicodeoffsets(List<SrcunicodeoffsetsItem> srcunicodeoffsets){
		this.srcunicodeoffsets = srcunicodeoffsets;
	}

	public List<SrcunicodeoffsetsItem> getSrcunicodeoffsets(){
		return srcunicodeoffsets;
	}

	public void setAlternative(List<AlternativeItem> alternative){
		this.alternative = alternative;
	}

	public List<AlternativeItem> getAlternative(){
		return alternative;
	}

	public void setEndPos(int endPos){
		this.endPos = endPos;
	}

	public int getEndPos(){
		return endPos;
	}

	public void setSrcPhrase(String srcPhrase){
		this.srcPhrase = srcPhrase;
	}

	public String getSrcPhrase(){
		return srcPhrase;
	}

	public void setStartPos(int startPos){
		this.startPos = startPos;
	}

	public int getStartPos(){
		return startPos;
	}

	public void setRawSrcSegment(String rawSrcSegment){
		this.rawSrcSegment = rawSrcSegment;
	}

	public String getRawSrcSegment(){
		return rawSrcSegment;
	}

	@Override
 	public String toString(){
		return 
			"AlternativeTranslationsItem{" + 
			"srcunicodeoffsets = '" + srcunicodeoffsets + '\'' + 
			",alternative = '" + alternative + '\'' + 
			",end_pos = '" + endPos + '\'' + 
			",src_phrase = '" + srcPhrase + '\'' + 
			",start_pos = '" + startPos + '\'' + 
			",raw_src_segment = '" + rawSrcSegment + '\'' + 
			"}";
		}
}