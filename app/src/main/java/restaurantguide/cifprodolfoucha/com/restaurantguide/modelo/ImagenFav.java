package restaurantguide.cifprodolfoucha.com.restaurantguide.modelo;

import android.text.Editable;
import android.widget.ImageView;

public class ImagenFav {
    String uri;
    Editable nombrePlato;
    Editable nombreRestaurante;
    Editable descripcion;
    boolean favorito;

    public ImagenFav() {

    }

    public ImagenFav(String uri, Editable nombrePlato, Editable nombreRestaurante, Editable descripcion, boolean favorito) {
        this.uri = uri;
        this.nombrePlato = nombrePlato;
        this.nombreRestaurante = nombreRestaurante;
        this.descripcion = descripcion;
        this.favorito = favorito;
    }

    @Override
    public String toString() {
        return "ImagenFav{" +
                "uri=" + uri +
                ", nombrePlato=" + nombrePlato +
                ", nombreRestaurante=" + nombreRestaurante +
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

    public Editable getNombrePlato() {
        return nombrePlato;
    }

    public void setNombrePlato(Editable nombrePlato) {
        this.nombrePlato = nombrePlato;
    }

    public Editable getNombreRestaurante() {
        return nombreRestaurante;
    }

    public void setNombreRestaurante(Editable nombreRestaurante) {
        this.nombreRestaurante = nombreRestaurante;
    }

    public Editable getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(Editable descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isFavorito() {
        return favorito;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }

    public int getId() {
        return nombrePlato.hashCode();
    }
}
