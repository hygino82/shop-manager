package br.dev.hygino.models;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Category {
    BEBIDAS_ALCOOLICAS,
    REFRIGERANTES,
    SUCOS,
    AGUAS,
    DOCES,
    SALGADOS,
    TABACO;

    @JsonValue
    public String toString() {
        return name().toUpperCase();
    }
}
