package com.example.models;

public class Comentario {
    private Integer dniUsuario;
    private String texto;
    
    Comentario(Integer dniUsuario, String texto){
        this.dniUsuario = dniUsuario;
        this.texto = texto;
    }

    public String toCsv() {
        return dniUsuario + "," + texto;
    }
}