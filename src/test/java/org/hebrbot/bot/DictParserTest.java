package org.hebrbot.bot;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.SneakyThrows;
import org.hebrbot.bot.pojo.test.ResponseDict;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class DictParserTest {

	@Test
    @SneakyThrows
    public void parser1() {
        String path = "C:\\dev\\telegram\\telegram-spring-boot-starter-master\\misc\\dict-he-en.json";
        ObjectMapper m = new ObjectMapper();
        TypeReference<List<ResponseDict>> typeRef = new TypeReference<List<ResponseDict>>() {
        };
//        SimpleModule testModule
//                = new SimpleModule("test", m.version()).addDeserializer(String.class,
//                new UnEscapedSerializaer());
//
//        m.registerModule(testModule);

        final List<ResponseDict> list = m.readValue(new File(path), typeRef);
        final List<ResponseDict> dicts = list.stream().filter(s -> s.getInflections().size() != 0).collect(Collectors.toList());
        for(ResponseDict t: dicts) {
            System.out.println("");
        }
    }

    @Test
    @SneakyThrows
    public void parser2() {
        String path = "C:\\dev\\telegram\\telegram-spring-boot-starter-master\\misc\\dict-en-he.json";
        ObjectMapper m = new ObjectMapper();
        TypeReference<List<ResponseDict>> typeRef = new TypeReference<List<ResponseDict>>() {
        };
        final List<ResponseDict> list = m.readValue(new File(path), typeRef);
        System.out.println("");
    }


}

class UnEscapedSerializaer extends JsonDeserializer<String> {

	@Override
	public String deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		String s = jp.getValueAsString();
		return s.replaceAll("\"", "\\\"").replaceAll("\\", "\\\\");

	}
}