package ru.job4j.io;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Mars");
    }

    @Test
    void whenNoKey() {
        String path = "./data/pair_without_key.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenNoValue() {
        String path = "./data/pair_without_value.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenNoSignThenNotAdded() {
        String path = "./data/without_equals_sign.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenOnlyCommentsAndEmptyRows() {
        String path = "./data/comments_only.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("")).isNull();
    }

    @Test
    void whenFewEqualsSigns() {
        String path = "./data/few_equals_signs.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("mars=1");
    }
}