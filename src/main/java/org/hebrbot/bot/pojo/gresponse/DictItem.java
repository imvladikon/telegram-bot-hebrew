package org.hebrbot.bot.pojo.gresponse;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class DictItem{

	@JsonProperty("entry")
	private List<EntryItem> entry;

	@JsonProperty("pos_enum")
	private int posEnum;

	@JsonProperty("pos")
	private String pos;

	@JsonProperty("terms")
	private List<String> terms;

	@JsonProperty("base_form")
	private String baseForm;

	public void setEntry(List<EntryItem> entry){
		this.entry = entry;
	}

	public List<EntryItem> getEntry(){
		return entry;
	}

	public void setPosEnum(int posEnum){
		this.posEnum = posEnum;
	}

	public int getPosEnum(){
		return posEnum;
	}

	public void setPos(String pos){
		this.pos = pos;
	}

	public String getPos(){
		return pos;
	}

	public void setTerms(List<String> terms){
		this.terms = terms;
	}

	public List<String> getTerms(){
		return terms;
	}

	public void setBaseForm(String baseForm){
		this.baseForm = baseForm;
	}

	public String getBaseForm(){
		return baseForm;
	}

	@Override
 	public String toString(){
		return 
			"DictItem{" + 
			"entry = '" + entry + '\'' + 
			",pos_enum = '" + posEnum + '\'' + 
			",pos = '" + pos + '\'' + 
			",terms = '" + terms + '\'' + 
			",base_form = '" + baseForm + '\'' + 
			"}";
		}
}