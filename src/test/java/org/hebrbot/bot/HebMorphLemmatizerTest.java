package org.hebrbot.bot;

import com.code972.hebmorph.DescFlag;
import com.code972.hebmorph.HebrewToken;
import com.code972.hebmorph.Reference;
import com.code972.hebmorph.Tokenizer;
import lombok.SneakyThrows;
import org.hebrbot.bot.utils.StringUtils;
import org.junit.Test;

import java.io.StringReader;
import java.util.List;
import java.util.Optional;

import static org.hebrbot.bot.HebMorphLemmatizer.getLemmatizerForm;
import static org.junit.Assert.*;

public class HebMorphLemmatizerTest {

	@Test
	public void getLemmatizer1() {
		//TODO:sound - conversion
		final List<HebrewToken> tokens = HebMorphLemmatizer.getLemmatizer().lemmatize("ללכת");
		//                .stream()
		//                .filter(Objects::nonNull)
		//                .findFirst()
		//                .map(HebrewToken::getLemma)
		//                .orElse("");
		System.out.println("");

	}

	@Test
	public void getLemmatizer2() {
		//TODO:sound - conversion
		final String form = Optional.ofNullable(getLemmatizerForm("למדנו"))
				.map(HebrewToken::getLemma)
				.orElse("");
		System.out.println(form);

	}

	@Test
	@SneakyThrows
	public void getLemmatizer3() {
		//TODO:sound - conversion
		//        final String form = getLemmatizerForm("למדנו");
		//        System.out.println(form);

		String text =
				"מגדירים כלל, באופן הבא: מספרים זוגיים יש לחלק בשתיים, בעוד שמספרים אי-זוגיים יש להכפיל בשלוש ולהוסיף לתוצאה אחת. ההשערה היא שהפעלה חוזרת של כלל זה, על מספר טבעי כלשהו, תביא בסופו של דבר למספר 1, ואין זה משנה מהי נקודת ההתחלה.";
		Tokenizer tokenizer = new Tokenizer(null, HebMorphLemmatizer.getDictionary().getPref());
		Reference<String> test = new Reference<String>("");
		tokenizer.reset(new StringReader(text));

		while (tokenizer.nextToken(test) > 0) {
			final String word =
					text.substring(	tokenizer.getOffset(),
									tokenizer.getOffset() + tokenizer.getLengthInSource());
			//            if(getLemmatizerForm(word)!=null && getLemmatizerForm(word))
            final HebrewToken token = getLemmatizerForm(word);
            final DescFlag partOfSpeech = Optional.ofNullable(token).map(HebrewToken::getMask).orElse(null);
            if(partOfSpeech != null && partOfSpeech == DescFlag.D_VERB) {
                System.out.println(token.getLemma());
            }
		}

	}

}