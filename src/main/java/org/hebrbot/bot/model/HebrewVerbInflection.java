package org.hebrbot.bot.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "verbs_infl")
public class HebrewVerbInflection implements HebrewWord {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "vi_id")
	Long id;

	@Column(name = "vi_name")
	String name;

	@Column(name = "vi_name_nek")
	String nameNekudot;

	@Column(name = "vi_root")
	String root;

	@Column(name = "vi_trans_en")
	String transcription;

	@Column(name = "vi_trans_ru")
	String transcriptionRu;

	@Column(name = "vi_transla_en")
	String translation;

	@Column(name = "vi_number")
	GrammaticalNumber number;

	@Column(name = "vi_gender")
	GrammaticalGender gender;

	@Column(name = "vi_person")
	GrammaticalPerson person;

	@Column(name = "vi_pronoun")
	@Enumerated(EnumType.STRING)
	HebrewPronouns pronoun;

	@Column(name = "vi_form")
	@Enumerated(EnumType.STRING)
	VerbForm form;

	@Column(name = "vi_actpass")
    @Enumerated(EnumType.STRING)
	VerbActivePassive activePassive;

	@Column(name = "vi_binyan")
    @Enumerated(EnumType.STRING)
	VerbsBinyan binyan;

	@Transient
	PartOfSpeech partOfSpeech;

	@JoinColumn(name = "vi_vb_inf", foreignKey = @ForeignKey(name="fk_infl_verbs"))
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JsonManagedReference
	HebrewVerb infinitive;

	@Override
	@Transient
	public String getMeaning() {
		return this.getInfinitive().getMeaning();
	}

	@Override
	public PartOfSpeech getPartOfSpeech() {
		return PartOfSpeech.VERB;
	}
}
