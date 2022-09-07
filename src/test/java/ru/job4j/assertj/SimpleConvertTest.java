package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "second", "three", "four", "five");
        assertThat(list)
                .hasSize(5)
                .isNotEmpty()
                .first()
                .isNotEqualTo("second");
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("1", "2", "3", "4", "5");
        assertThat(set)
                .containsOnly("3", "2", "1", "4", "5")
                .doesNotContain("12")
                .endsWith("5")
                .containsSequence("4", "5")
                .filteredOn(x -> x.contains("5"))
                .filteredOn(x -> Integer.parseInt(x) < 8);
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("1", "2", "3");
        assertThat(map)
                .hasSize(3)
                .containsKeys("1")
                .doesNotContainKey("0");
    }
}