package org.hebrbot.bot.actions;

import org.junit.Test;

import java.util.HashMap;
import java.util.stream.IntStream;

public class HashMapMemoryUsageTest {

    @Test
    public void test1() {
        Runtime runtime = Runtime.getRuntime();
        long usedMemoryBefore = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Used Memory before:" + usedMemoryBefore);
        HashMap<String, String> m = new HashMap<>();
        IntStream.range(1, 1_000_000).forEach(i -> m.put(String.valueOf(i), String.valueOf(i)));
        long usedMemoryAfter = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Memory increased:" + (usedMemoryAfter-usedMemoryBefore));

    }
}
