package com.example.models;

import java.time.LocalDate;
import java.util.ArrayList;
import com.example.enums.Categoria;

public class Articulo {
    private static Integer idCounter = 0;
    private Integer idArticulo;
    private Integer dniAutor;
    private String titulo;
    private String detalle;
    private LocalDate fecha;
    private ArrayList<Comentario> comentarios;
    private Categoria categoria;

    Articulo(Integer dniAutor, String titulo, String detalle, Categoria categoria){
        idArticulo = idCounter;
        idCounter++;
        this.dniAutor = dniAutor;
        this.titulo = titulo;
        this.detalle = detalle;
        fecha = LocalDate.now();
        comentarios = new ArrayList<Comentario>();
        this.categoria = categoria; 
    }

    // otro constructor para cuando se lee

    public void agregarComentario(Comentario comentario){
        comentarios.add(comentario);
    }
    
}