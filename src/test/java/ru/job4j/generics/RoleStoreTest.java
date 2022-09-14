package ru.job4j.generics;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RoleStoreTest {
    @Test
    void whenAddAndFindThenRoleIsTeamLead() {
        RoleStore role = new RoleStore();
        role.add(new Role("1", "TeamLead"));
        Role result = role.findById("1");
        assertThat(result.getRoleName()).isEqualTo("TeamLead");
    }

    @Test
    void whenNoFoundSuchElementsThenFalse() {
        RoleStore role = new RoleStore();
        role.add(new Role("1", "TeamLead"));
        Role result = role.findById("2");
        assertThat(result).isNull();
    }

    @Test
    void whenElementExistsThenNoChangeWillBeDone() {
        RoleStore role = new RoleStore();
        role.add(new Role("1", "TeamLead"));
        role.add(new Role("1","SeniorDev"));
        Role result = role.findById("1");
        assertThat(result.getRoleName()).isEqualTo("TeamLead");
    }

    @Test
    void whenReplacedSuccessfully() {
        RoleStore role = new RoleStore();
        role.add(new Role("1", "TeamLead"));
        role.replace("1", new Role("1", "SeniorDev"));
        Role result = role.findById("1");
        assertThat(result.getRoleName()).isEqualTo("SeniorDev");
    }

    @Test
    void whenDeletedSuccessfully() {
        RoleStore role = new RoleStore();
        role.add(new Role("1", "TeamLead"));
        role.add(new Role("2", "MiddleDev"));
        role.add(new Role("3", "JuniorDev"));
        assertThat(role.delete("2")).isTrue();
        assertThat(role.findById("2")).isNull();
    }
}