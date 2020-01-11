package org.hebrbot.bot.scrapers;

import org.hebrbot.bot.utils.HttpUtils;
import org.hebrbot.bot.model.*;
import lombok.SneakyThrows;
import org.hebrbot.bot.utils.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URL;
import java.text.NumberFormat;
import java.util.*;
import java.util.stream.Collectors;

import static org.hebrbot.bot.model.GrammaticalGender.*;
import static org.hebrbot.bot.model.GrammaticalNumber.*;
import static org.hebrbot.bot.model.HebrewPronouns.*;
import static org.hebrbot.bot.model.VerbForm.*;
import static org.hebrbot.bot.model.VerbActivePassive.*;
import static org.hebrbot.bot.utils.StreamUtils.*;
import static org.hebrbot.bot.utils.StringUtils.removeNiqqud;

public class PealimScraper {

	//TODO: for imperative, delete "!"
	//TODO: passive
	//TODO: "~"

	public static final String BASE_HOST = "www.pealim.com";
	public static final String ALL_WORDS_ROOT = "/dict";
	public static final String SEARCH_WORD = "/search/?q=";
	private static final Map<String, VerbsBinyan> BINYAN_MAP = new HashMap<String, VerbsBinyan>() {
		{
			put("PI'EL", VerbsBinyan.piel);
			put("PI'EL", VerbsBinyan.piel);
			put("PA'AL", VerbsBinyan.paal);
			put("PA'AL", VerbsBinyan.paal);
			put("HIF'IL", VerbsBinyan.hifil);
			put("HIF'IL", VerbsBinyan.hifil);
			put("HITPA'EL", VerbsBinyan.hitpael);
			put("HITPA'EL", VerbsBinyan.hitpael);
			put("HUF'AL", VerbsBinyan.hufal);
			put("HUF'AL", VerbsBinyan.hufal);
			put("NIF'AL", VerbsBinyan.nifal);
			put("NIF'AL", VerbsBinyan.nifal);
			put("PU'AL", VerbsBinyan.pual);
			put("PU'AL", VerbsBinyan.pual);
		}
	};
	private static final Map<String, VerbActivePassive> ACTIVE_PASSIVE_MAP =
			new HashMap<String, VerbActivePassive>() {
				{
					put("Active forms", VerbActivePassive.Active);
					put("Passive forms", VerbActivePassive.Passive);
					put("Действительный залог", VerbActivePassive.Active);
					put("Страдательный залог", VerbActivePassive.Passive);
					put("Las formas activas", VerbActivePassive.Active);
					put("Formas pasivas", VerbActivePassive.Passive);

				}
			};

	@SneakyThrows
	public void start() {
		String url = ab(ALL_WORDS_ROOT);
		Document doc = Jsoup.parse(HttpUtils.getHtml(url));
		String lastPageUrl = ab(doc.select(".pagination li:last-of-type a").attr("href"));
		int lastPage = Integer.parseInt(new URL(lastPageUrl).getQuery().replaceAll("page=", ""));
		dictPage(doc);
		for (int page = 2; page <= lastPage; page++) {
			String p =
					ab(ALL_WORDS_ROOT + "?page=" + NumberFormat.getIntegerInstance().format(page));
			dictPage(Jsoup.parse(HttpUtils.getHtml(p)));
		}
	}

	@SneakyThrows
	public HebrewVerb search(String word) {
		String url = ab(SEARCH_WORD + word);
		Document doc = Jsoup.parse(HttpUtils.getHtml(url));
		Elements links = doc.select(".btn-primary");
		String href = links.stream()
				.filter(l -> l.html().toLowerCase().contains("conjugation"))
				.map(link -> link.attr("href"))
				.findFirst()
				.orElse("");
		doc = Jsoup.parse(HttpUtils.getHtml(ab(href)));
		return verbPage(doc);
	}

	public String ab(String url) {
		return "https://" + BASE_HOST + url;
	}

	@SneakyThrows
	public void dictPage(Document doc) {
		Elements dict = doc.select(".dict-table-t");
		for (Element row : dict.select("tr")) {
			String href = row.select("td:first-of-type").select("a").attr("href");
			//Menukad    0
			//Transcription  0
			//Root  1
			//PartOfSpeech  2
			//Meaning  3
			for (Element cell : row.select("td")) {
				System.out.println(cell.text());
				cell.select(".menukad");
				cell.select(".dict-transcription");
				cell.select(".dict-meaning");
			}
			verbPage(Jsoup.parse(HttpUtils.getHtml(ab(href))));
		}
	}

	public HebrewVerb verbPage(Document doc) {
		final Elements title = doc.select("h2.page-header");
		final String nameNekudot = StringUtils.removeAll(	title.text(),
															"Conjugation of",
															"Спряжение глагола",
															"Conjugación de",
															title.select("span").text());
		final Elements p1 = title.next();
		final VerbsBinyan binyanMain =
				BINYAN_MAP.get(StringUtils.removeAll(p1.text(), "Verb", "Глагол", "Verbo", "–")
						.toUpperCase());
		final Elements p2 = p1.next();
		final String root =
				StringUtils.removeAll(p2.text(), "Корень", "Raíz", "Root", ":", "-", " ");
		final Elements p3 = p2.next();
		final String note = p3.text(); //TODO: lang specific
		//        doc.select(		"h3.page-header").first();
		final String meaning =
				Optional.ofNullable(doc.select(".lead")).map(Elements::text).orElse("");//tranlationEn
		final HebrewVerb verb = HebrewVerb.builder()
				.binyan(binyanMain)
				.form(VerbForm.Infinitive)
				.meaning(meaning)
				.name(removeNiqqud(nameNekudot))
				.nameNekudot(nameNekudot)
				.partOfSpeech(PartOfSpeech.VERB)
				.root(root)
				.transcription("")
				.transcriptionRu("")
				.build();

		List<HebrewVerbInflection> inflections = new ArrayList<>();
		for (Element table : doc.select(".conjugation-table")) {
			final String titleTable = Optional.ofNullable(table.previousElementSibling())
					.map(Element::text)
					.orElse("");
			final VerbsBinyan binyan = BINYAN_MAP.entrySet()
					.stream()
					.filter(entry -> titleTable.toUpperCase().contains(entry.getKey()))
					.findFirst()
					.map(Map.Entry::getValue)
					.orElse(binyanMain);
			final VerbActivePassive verbActivePassive = ACTIVE_PASSIVE_MAP.entrySet()
					.stream()
					.filter(entry -> titleTable.contains(entry.getKey()))
					.findFirst()
					.map(Map.Entry::getValue)
					.orElse(Active);
			for (Map.Entry<String, ConjugationRow> entry : getVerbCategories().entrySet()) {
				String categoryName = entry.getKey();
				ConjugationRow row = entry.getValue();
				final Elements elements = table.select("#" + categoryName);
				if (elements == null || elements.size() == 0) {
					continue;
				}
				Elements nameElement = elements.select(".chaser");
				if (nameElement == null || nameElement.size() == 0) {
					nameElement = elements.select(".menukad");
				}
				final String nameNekInflection = Optional.ofNullable(nameElement)
						.map(Elements::text)
						.map(s -> StringUtils.removeAll(s, "*", "!", "~"))
						.orElse("");
				//TODO: check multiple cases of name, for some tenses forms
				HebrewVerbInflection inflection = HebrewVerbInflection.builder()
						.activePassive(verbActivePassive)
						.binyan(binyan)
						.form(row.getForm())
						.gender(row.getGender())
						.number(row.getNumber())
						.person(row.getPerson())
						.activePassive(row.getActivePassive())
						.pronoun(row.getPronoun())
						.translation(Optional.ofNullable(elements.select(".meaning"))
								.map(Elements::text)
								.map(s -> StringUtils.removeAll(s, "*", "!", "~"))
								.orElse(""))
						.transcription(Optional.ofNullable(elements.select(".transcription"))
								.map(Elements::text)
								.map(s -> StringUtils.removeAll(s, "*", "!", "~"))
								.orElse(""))
						.name(removeNiqqud(nameNekInflection))
						.nameNekudot(nameNekInflection)
						.partOfSpeech(PartOfSpeech.VERB)
						.root(root)
						.build();
				inflections.add(inflection);
			}
		}
		verb.setInflections(inflections);
		verb.setMain(verb.getActiveInflections()
				.stream()
				.filter(i -> i.getPronoun() == Hu && i.getForm() == Past)
				.findFirst()
				.map(HebrewVerbInflection::getName)
				.orElse(""));
		//		verb.setTranscription();
		Elements related_words_node = doc.select(".dict-table-t");
		for (Element element : related_words_node.select("tr")) {
			for (Element cell : element.select("td")) {
				//td class="conj-td"
				//                System.out.println(cell.text());
				cell.select(".menukad");
				cell.select(".dict-transcription");
				cell.select(".dict-meaning");
				//#s #p #sc #pc
			}
		}
		return verb;
	}

	public void adjectivePage(Document doc) {

	}

	public void nounPage(Document doc) {

	}

	public static Map<String, ConjugationRow> getVerbCategories() {
		return getVerbCategories(VerbForm.None);
	}

	public static Map<String, ConjugationRow> getVerbCategories(VerbForm form) {
		//maybe it would be easier parse string and use bit mask, but simpler like this
		Map<String, ConjugationRow> result = new HashMap<String, ConjugationRow>() {
			{
				put("passive-AP-ms",
					ConjugationRow
							.create(SINGULAR, Masculine, GrammaticalPerson.None, Present, Passive));
				put("passive-AP-fs",
					ConjugationRow
							.create(SINGULAR, Feminine, GrammaticalPerson.None, Present, Passive));
				put("passive-AP-mp",
					ConjugationRow
							.create(PLURAL, Masculine, GrammaticalPerson.None, Present, Passive));
				put("passive-AP-fp",
					ConjugationRow
							.create(PLURAL, Feminine, GrammaticalPerson.None, Present, Passive));
				put("passive-PERF-1s", ConjugationRow.create(Ani, Past, Passive));
				put("passive-PERF-1p", ConjugationRow.create(Anahnu, Past, Passive));
				put("passive-PERF-2ms", ConjugationRow.create(Ata, Past, Passive));
				put("passive-PERF-2fs", ConjugationRow.create(At, Past, Passive));
				put("passive-PERF-3ms", ConjugationRow.create(Hem, Past, Passive));
				put("passive-PERF-3fs", ConjugationRow.create(Hen, Past, Passive));
				put("passive-PERF-3p", ConjugationRow.create(HemHen, Past, Passive));
				put("passive-PERF-2fp", ConjugationRow.create(Aten, Past, Passive));
				put("passive-PERF-2mp", ConjugationRow.create(Atem, Past, Passive));
				put("passive-IMPF-1s", ConjugationRow.create(Ani, Future, Passive));
				put("passive-IMPF-1p", ConjugationRow.create(Anahnu, Future, Passive));
				put("passive-IMPF-2ms", ConjugationRow.create(Ata, Future, Passive));
				put("passive-IMPF-2fs", ConjugationRow.create(At, Future, Passive));
				put("passive-IMPF-2mp", ConjugationRow.create(Atem, Future, Passive));
				put("passive-IMPF-2fp", ConjugationRow.create(Aten, Future, Passive));
				put("passive-IMPF-3ms", ConjugationRow.create(Hu, Future, Passive));
				put("passive-IMPF-3fs", ConjugationRow.create(He, Future, Passive));
				put("passive-IMPF-3mp", ConjugationRow.create(Hem, Future, Passive));
				put("passive-IMPF-3fp", ConjugationRow.create(Hen, Future, Passive));
				put("INF-L",
					ConjugationRow.create(	NONE,
											GrammaticalGender.None,
											GrammaticalPerson.None,
											Infinitive));
				put("AP-ms",
					ConjugationRow.create(SINGULAR, Masculine, GrammaticalPerson.None, Present));
				put("AP-fs",
					ConjugationRow.create(SINGULAR, Feminine, GrammaticalPerson.None, Present));
				put("AP-mp",
					ConjugationRow.create(PLURAL, Masculine, GrammaticalPerson.None, Present));
				put("AP-fp",
					ConjugationRow.create(PLURAL, Feminine, GrammaticalPerson.None, Present));
				put("PERF-1s", ConjugationRow.create(Ani, Past));
				put("PERF-1p", ConjugationRow.create(Anahnu, Past));
				put("PERF-2ms", ConjugationRow.create(Ata, Past));
				put("PERF-2fs", ConjugationRow.create(At, Past));
				put("PERF-2mp", ConjugationRow.create(Atem, Past));
				put("PERF-2fp", ConjugationRow.create(Aten, Past));
				put("PERF-3ms", ConjugationRow.create(Hu, Past));
				put("PERF-3fs", ConjugationRow.create(He, Past));
				put("PERF-3p", ConjugationRow.create(HemHen, Past));
				put("IMPF-1s", ConjugationRow.create(Ani, Future));
				put("IMPF-1p", ConjugationRow.create(Anahnu, Future));
				put("IMPF-2ms", ConjugationRow.create(Ata, Future));
				put("IMPF-2fs", ConjugationRow.create(At, Future));
				put("IMPF-2mp", ConjugationRow.create(Atem, Future));
				put("IMPF-2fp", ConjugationRow.create(Aten, Future));
				put("IMPF-3ms", ConjugationRow.create(Hu, Future));
				put("IMPF-3fs", ConjugationRow.create(He, Future));
				put("IMPF-3mp", ConjugationRow.create(Hem, Future));
				put("IMPF-3fp", ConjugationRow.create(Hen, Future));
				put("IMP-2ms", ConjugationRow.create(Ata, Imperative));
				put("IMP-2fs", ConjugationRow.create(At, Imperative));
				put("IMP-2mp", ConjugationRow.create(Atem, Imperative));
				put("IMP-2fp", ConjugationRow.create(Aten, Imperative));
			}
		};
		switch (form) {
		case None:
			return result;
		case Imperative:
			return result.entrySet()
					.stream()
					.filter(s -> s.getKey().startsWith("IMP-"))
					.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
		case Future:
			return result.entrySet()
					.stream()
					.filter(s -> s.getKey().startsWith("IMPF-"))
					.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
		case Present:
			return result.entrySet()
					.stream()
					.filter(s -> s.getKey().startsWith("AP-"))
					.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
		case Past:
			return result.entrySet()
					.stream()
					.filter(s -> s.getKey().startsWith("PERF-"))
					.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
		}
		return result;
	}

}
