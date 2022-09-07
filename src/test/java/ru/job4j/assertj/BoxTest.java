package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        assertThat(box.whatsThis()).isEqualTo("Sphere");
    }

    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 10);
        assertThat(box.whatsThis()).isEqualTo("Tetrahedron");
    }

    @Test
    void isUnknownObject() {
        Box box = new Box(3, 0);
        assertThat(box.whatsThis()).isEqualTo("Unknown object");
    }

    @Test
    void whenFigureExist() {
        Box box = new Box(4, 4);
        assertThat(box.isExist()).isTrue();
    }

    @Test
    void gettingNumberOfVerticises() {
        Box box = new Box(8, 4);
        assertThat(box.getNumberOfVertices()).isNotNegative().isNotZero().isEqualTo(8);
    }

    @Test
    void getArea() {
        Box box = new Box(8, 2);
        assertThat(box.getArea()).isLessThan(25).isEqualTo(24);
    }

    @Test
    void whenGettingAreaIsDefault() {
        Box box = new Box(5, 2);
        assertThat(box.getArea()).isGreaterThan(-1).isLessThan(1);
    }

    @Test
    void whenWhenVertexIsNegative() {
        Box box = new Box(3, 0);
        assertThat(box.getNumberOfVertices()).isNotNull().isNegative().isEqualTo(-1);
    }
}