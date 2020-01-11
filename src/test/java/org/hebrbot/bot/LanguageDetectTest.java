package org.hebrbot.bot;

import lombok.SneakyThrows;
import org.hebrbot.bot.utils.HttpUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class LanguageDetectTest {

    @Test
    @SneakyThrows
    public void testGoogleTranslate() {
        String url = "https://translate.google.com/#view=home&op=translate&sl=auto&tl=iw&text=predict";
        Document doc = Jsoup.parse(HttpUtils.getHtml(url));
        System.out.println(doc.select(".result-shield-container.result-rtl .translation").text());
    }

    @Test
    public void
    givenLanguageDictionary_whenLanguageDetect_thenLanguageIsDetected()
            throws FileNotFoundException, IOException {

//        InputStreamFactory dataIn
//                = new MarkableFileInputStreamFactory(
//                new File("src/main/resources/models/DoccatSample.txt"));
//        ObjectStream lineStream = new PlainTextByLineStream(dataIn, "UTF-8");
//        LanguageDetectorSampleStream sampleStream
//                = new LanguageDetectorSampleStream(lineStream);
//        TrainingParameters params = new TrainingParameters();
//        params.put(TrainingParameters.ITERATIONS_PARAM, 100);
//        params.put(TrainingParameters.CUTOFF_PARAM, 5);
//        params.put("DataIndexer", "TwoPass");
//        params.put(TrainingParameters.ALGORITHM_PARAM, "NAIVEBAYES");
//
//        LanguageDetectorModel model = LanguageDetectorME
//                .train(sampleStream, params, new LanguageDetectorFactory());
//
//        LanguageDetector ld = new LanguageDetectorME(model);
//        Language[] languages = ld
//                .predictLanguages("estava em uma marcenaria na Rua Bruno");
//        assertThat(Arrays.asList(languages))
//                .extracting("lang", "confidence")
//                .contains(
//                        tuple("pob", 0.9999999950605625),
//                        tuple("ita", 4.939427661577956E-9),
//                        tuple("spa", 9.665954064665144E-15),
//                        tuple("fra", 8.250349924885834E-25)));
    }
}
