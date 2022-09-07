package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.setLenientDateParsing;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void whenEmptyArray() {
        NameLoad load = new NameLoad();
        assertThatThrownBy(load::parse)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("array is empty");
    }

    @Test
    void whenNoKey() {
        NameLoad load = new NameLoad();
        String[] names = new String[]{"=Andrey", "Misha"};
        assertThatThrownBy(() -> load.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("=Andrey does not contain a key");
    }

    @Test
    void whenNoSymbol() {
        NameLoad load = new NameLoad();
        String[] names = new String[]{"Andrey", "Misha"};
        assertThatThrownBy(() -> load.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Andrey does not contain the symbol");
    }

    @Test
    void whenNoValue() {
        NameLoad load = new NameLoad();
        String[] names = new String[]{"Andrey=", "Misha"};
        assertThatThrownBy(() -> load.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Andrey= does not contain a value");
    }
}