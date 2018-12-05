package restaurantguide.cifprodolfoucha.com.restaurantguide.modelo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.io.IOException;
import java.util.ArrayList;

import restaurantguide.cifprodolfoucha.com.restaurantguide.R;
import restaurantguide.cifprodolfoucha.com.restaurantguide.controlador.ConexionBD;
import restaurantguide.cifprodolfoucha.com.restaurantguide.vista.Principal;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new  android.widget.AbsListView.LayoutParams(120, 120));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(0, 0, 0, 0);
        } else {
            imageView = (ImageView) convertView;
        }

        ArrayList<ImagenFav> favs = ConexionBD.getInstance(this.mContext).imagenesFavs();
        String ruta = "";

        for(ImagenFav f : favs){
            ruta = f.getUri();
            System.out.println(ruta);
        }

        //imageView.setImageResource(mThumbIds[position]);
        try {
            BitmapRegionDecoder bmr = BitmapRegionDecoder.newInstance(ruta,false);
            Bitmap bm = bmr.decodeRegion(new Rect(0,0,bmr.getWidth(),bmr.getHeight()),null);
            imageView.setImageBitmap(bm);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageView;
    }

    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.logo, R.drawable.logo2,
            R.drawable.logo3, R.drawable.portada



    };
}