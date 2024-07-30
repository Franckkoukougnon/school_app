package com.example.school_bdd.exception;

public class EtablissementNotFoundException extends RuntimeException{
    public EtablissementNotFoundException(Long id) {
        super("L'établissement avec l'ID " + id + " n'a pas été trouvé.");

    }
}
