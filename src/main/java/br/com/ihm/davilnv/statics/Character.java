package br.com.ihm.davilnv.statics;

import lombok.Getter;

@Getter
public enum Character {
    CARETAKER("caretaker"),
    CHIEF_CURATOR("chief-curator"),
    DETECTIVE("detective"),
    DIRECTOR("director"),
    IT_EMPLOYEE("it-employee"),
    MUSEUM_VISITOR("museum-visitor"),
    NEWSCASTER("newscaster"),
    SECURITY_GUARD("security-guard");

    private final String role;

    Character(String role) {
        this.role = role;
    }

}