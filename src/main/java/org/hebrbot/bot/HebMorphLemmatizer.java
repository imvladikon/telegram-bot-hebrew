package org.hebrbot.bot;

import com.code972.hebmorph.*;
import com.code972.hebmorph.datastructures.DictHebMorph;
import com.code972.hebmorph.hspell.HSpellDictionaryLoader;
import lombok.SneakyThrows;
import org.hebrbot.bot.utils.ReflectUtils;
import org.hebrbot.bot.utils.StringUtils;

import java.util.Arrays;
import java.util.List;

public class HebMorphLemmatizer {
    private static StreamLemmatizer m_lemmatizer;
    public static String DICT_PATH = "./hspell-data-files/";
    private static DictHebMorph dict;
    private static Lemmatizer lemmatizer;

    @SneakyThrows
    static synchronized Lemmatizer getLemmatizer() {
        if (lemmatizer == null) {
            lemmatizer = new Lemmatizer(getDictionary());
        }
        return lemmatizer;
    }

    @SneakyThrows
    static synchronized DictHebMorph getDictionary() {
        if (dict == null) {
            dict = new HSpellDictionaryLoader().loadDictionaryFromPath(DICT_PATH);
        }
        return dict;
    }

    static synchronized HebrewToken getLemmatizerForm(String word) {
        final String s = Lemmatizer.removeNiqqud(word);
        List<HebrewToken> hebrewTokens = getLemmatizer().lemmatize(word);
        if (hebrewTokens.size() == 0) {
            return null;
        }
        hebrewTokens.sort(HebrewToken::compareTo);
        HebrewToken w = hebrewTokens.get(0);
        for(HebrewToken t:hebrewTokens) {
            if (t.getMask() == DescFlag.D_VERB) {
                w = t;
                break;
            }
        }
        return w;
    }
}
