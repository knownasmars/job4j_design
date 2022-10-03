package ru.job4j.iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.*;

class ListUtilsTest {

    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddAfterWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenRemoveIfNumbersHigherThanOne() {
        ListUtils.removeIf(input, x -> x > 1);
        assertThatList(input).hasSize(1);
    }

    @Test
    void whenReplaceIfNumbersEquals() {
        ListUtils.replaceIf(input, x -> x.equals(3), 999);
        assertThatList(input).containsSequence(1, 999);
    }

    @Test
    void whenSimilarElementsRemoved() {
        ListUtils.removeAll(input, new ArrayList<>(List.of(2, 3)));
        assertThatList(input).hasSize(1);
        ListUtils.removeAll(input, new ArrayList<>(List.of(0, 1)));
        assertThatList(input).isEmpty();
    }

    @Test
    void whenNoSimilarElements() {
        ListUtils.removeAll(input, new ArrayList<>(List.of(0, 0)));
        assertThatList(input).hasSize(2);
    }
}