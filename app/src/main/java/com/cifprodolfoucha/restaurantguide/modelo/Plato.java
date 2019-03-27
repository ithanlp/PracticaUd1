package com.cifprodolfoucha.restaurantguide.modelo;


public class Plato {
    private long id;
    private String nombre;
    private String imagen;
    private int favorito;

    public Plato() {
    }

    public Plato(long id, String nombre, String imagen, int favorito) {
        this.id = id;
        this.favorito=favorito;
        this.nombre = nombre;
        this.imagen = imagen;
    }


    public int getFavorito() {
        return favorito;
    }

    public void setFavorito(int favorito) {
        this.favorito = favorito;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return "Plato{" +
                "nombre='" + nombre + '\'' +
                '}';
    }
}
