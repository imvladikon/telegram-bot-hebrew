package org.hebrbot.bot.pojo.gresponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Features{

	@JsonProperty("nonfinite_form")
	private int nonfiniteForm;

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@JsonProperty("gender")
	private String gender;

	@JsonProperty("number")
	private int number;

	@JsonProperty("tense")
	private int tense;

	@JsonProperty("person")
	private int person;

	public void setNonfiniteForm(int nonfiniteForm){
		this.nonfiniteForm = nonfiniteForm;
	}

	public int getNonfiniteForm(){
		return nonfiniteForm;
	}

	public void setNumber(int number){
		this.number = number;
	}

	public int getNumber(){
		return number;
	}

	public void setTense(int tense){
		this.tense = tense;
	}

	public int getTense(){
		return tense;
	}

	public void setPerson(int person){
		this.person = person;
	}

	public int getPerson(){
		return person;
	}

	@Override
 	public String toString(){
		return 
			"Features{" + 
			"nonfinite_form = '" + nonfiniteForm + '\'' + 
			",number = '" + number + '\'' + 
			",tense = '" + tense + '\'' + 
			",person = '" + person + '\'' + 
			"}";
		}
}