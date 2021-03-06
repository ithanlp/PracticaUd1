package com.cifprodolfoucha.restaurantguide.almacenamiento;
import android.provider.BaseColumns;

public final class PlatosDbModelo {



    private PlatosDbModelo() {

    }
    public static class PlatosDb implements BaseColumns {
        public static final String NOMBRE_TABLA = "platos.db";
        public static final String NOMBRE_COLUMNA_NOMBRE = "nombre";
        public static final String NOMBRE_COLUMNA_IMAGEN = "urlImagen";
        public static final String NOMBRE_COLUMNA_FAVORITO = "favorito";


        public static final String NOMBRE_TABLA2 = "restaurantes";
        public static final String NOMBRE_COLUMNA2_NOMBRE = "nombre";
        public static final String NOMBRE_COLUMNA2_LOCALIZACION = "localizacion";
        public static final String NOMBRE_COLUMNA2_LOCALIZACIONLON = "localizacionLon";
        public static final String NOMBRE_COLUMNA2_LOCALIZACIONLAT = "localizacionLat";
    }
}
