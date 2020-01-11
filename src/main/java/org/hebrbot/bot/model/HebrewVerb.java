package org.hebrbot.bot.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "verbs")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class HebrewVerb implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "vb_id", unique = true, nullable = false)
	Long id;

	@Column(name = "vb_name")
	String name;

	@Column(name = "vb_name_nek")
	String nameNekudot;

	@Column(name = "vb_binyan")
	@Enumerated(EnumType.STRING)
	VerbsBinyan binyan;

	@Column(name = "vb_type")
	String type;

	@Column(name = "vb_root")
	String root;

	@Column(name = "vb_gerund")
	String gerund;

	@Column(name = "vb_main")
	String main;

	@Column(name = "vb_en")
	String meaning;

	@Column(name = "vb_ru")
	String meaningRu;

	@Column(name = "vb_es")
	String meaningEs;

	@Column(name = "vb_trans_en")
	String transcription;

	@Column(name = "vb_trans_ru")
	String transcriptionRu;

	@Transient
	PartOfSpeech partOfSpeech = PartOfSpeech.VERB;

	@Transient
	VerbForm form = VerbForm.Infinitive;

//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "infinitive", cascade = CascadeType.ALL)
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "infinitive", cascade = CascadeType.ALL)
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	@JsonBackReference
	List<HebrewVerbInflection> inflections = new ArrayList<HebrewVerbInflection>();

	@Transient
	public List<HebrewVerbInflection> getActiveInflections() {
		return this.getInflections()
				.stream()
				.filter(i -> i.getActivePassive() == VerbActivePassive.Active)
				.collect(Collectors.toList());
	}

	@Transient
	public List<HebrewVerbInflection> getPassiveInflections() {
		return this.getInflections()
				.stream()
				.filter(i -> i.getActivePassive() == VerbActivePassive.Passive)
				.collect(Collectors.toList());
	}
}
