package org.hebrbot.bot.pojo.test;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ResponseDict {
	private List<String> synonyms;
	@JsonProperty("part_of_speech")
	private String partOfSpeech;
	private List<String> translation;
	private String translated;
	private String id;
	private List<Object> samples;
	private List<Object> inflections;

	public void setSynonyms(List<String> synonyms) {
		this.synonyms = synonyms;
	}

	public List<String> getSynonyms() {
		return synonyms;
	}

	public void setPartOfSpeech(String partOfSpeech) {
		this.partOfSpeech = partOfSpeech;
	}

	public String getPartOfSpeech() {
		return partOfSpeech;
	}

	public void setTranslation(List<String> translation) {
		this.translation = translation;
	}

	public List<String> getTranslation() {
		return translation;
	}

	public void setTranslated(String translated) {
		this.translated = translated;
	}

	public String getTranslated() {
		return translated;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setSamples(List<Object> samples) {
		this.samples = samples;
	}

	public List<Object> getSamples() {
		return samples;
	}

	public void setInflections(List<Object> inflections) {
		this.inflections = inflections;
	}

	public List<Object> getInflections() {
		return inflections;
	}

	@Override
	public String toString() {
		return "Response{" + "synonyms = '" + synonyms + '\'' + ",part_of_speech = '" + partOfSpeech
			+ '\'' + ",translation = '" + translation + '\'' + ",translated = '" + translated + '\''
			+ ",id = '" + id + '\'' + ",samples = '" + samples + '\'' + ",inflections = '"
			+ inflections + '\'' + "}";
	}
}