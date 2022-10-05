package ru.job4j.set;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SimpleSetTest {

    @Test
    void whenAddNonNull() {
        Set<Integer> set = new SimpleSet<>();
        assertThat(set.add(1)).isTrue();
        assertThat(set.contains(1)).isTrue();
        assertThat(set.add(1)).isFalse();
    }

    @Test
    void whenAddNull() {
        Set<Integer> set = new SimpleSet<>();
        assertThat(set.add(null)).isTrue();
        assertThat(set.contains(null)).isTrue();
        assertThat(set.add(null)).isFalse();
    }

    @Test
    void whenAddManyElements() {
        Set<String> set = new SimpleSet<>();
        set.add("1");
        set.add("2");
        set.add("3");
        assertThat(set.contains("2")).isTrue();
        assertThat(set.add("1")).isFalse();
    }

    @Test
    void whenIterable() {
        Set<String> set = new SimpleSet<>();
        set.add("1");
        set.add("2");
        set.add("3");
        Iterator<String> it = set.iterator();
        it.next();
        it.next();
        assertThat(it.next()).isEqualTo("3");
    }

    @Test
    void whenNextIsNull() {
        Set<String> set = new SimpleSet<>();
        set.add("1");
        Iterator<String> it = set.iterator();
        it.next();
        assertThatThrownBy(it::next)
                .isInstanceOf(NoSuchElementException.class);
    }
}