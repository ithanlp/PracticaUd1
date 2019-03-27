package com.cifprodolfoucha.restaurantguide.modelo;

public class ImagenFav {
    String uri;
    String nombrePlato;
    int idRestaurante;
    String descripcion;
    int favorito;

    public ImagenFav() {

    }

    public ImagenFav(String uri, String nombrePlato, int idRestaurante, String descripcion, int favorito) {
        this.uri = uri;
        this.nombrePlato = nombrePlato;
        this.idRestaurante = idRestaurante;
        this.descripcion = descripcion;
        this.favorito = favorito;
    }

    @Override
    public String toString() {
        return "ImagenFav{" +
                "uri=" + uri +
                ", nombrePlato=" + nombrePlato +
                ", idRestaurante=" + idRestaurante +
                ", descripcion=" + descripcion +
                ", favorito=" + favorito +
                '}';
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getNombrePlato() {
        return nombrePlato;
    }

    public void setNombrePlato(String nombrePlato) {
        this.nombrePlato = nombrePlato;
    }

    public int getIdRestaurante() {
        return idRestaurante;
    }

    public void setIdRestaurante(int idRestaurante) {
        this.idRestaurante = idRestaurante;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int isFavorito() {
        return favorito;
    }

    public int getFavorito() {
        return favorito;
    }
    public void setFavorito(int favorito) {
        this.favorito = favorito;
    }

    public int getId() {
        return nombrePlato.hashCode();
    }
}
