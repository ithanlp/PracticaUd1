package restaurantguide.cifprodolfoucha.com.restaurantguide.modelo;

public class Restaurante {
    private long id;
    private String nombre;
    private String localizacion;
    private float localizacionLon;
    private float localizacionLat;

    public Restaurante() {
    }

    public Restaurante(long id, String nombre, String localizacion, float localizacionLon, float localizacionLat) {
        this.id = id;
        this.nombre = nombre;
        this.localizacion = localizacion;
        this.localizacionLon = localizacionLon;
        this.localizacionLat = localizacionLat;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    public float getLocalizacionLon() {
        return localizacionLon;
    }

    public void setLocalizacionLon(float localizacionLon) {
        this.localizacionLon = localizacionLon;
    }

    public float getLocalizacionLat() {
        return localizacionLat;
    }

    public void setLocalizacionLat(float localizacionLat) {
        this.localizacionLat = localizacionLat;
    }
}
