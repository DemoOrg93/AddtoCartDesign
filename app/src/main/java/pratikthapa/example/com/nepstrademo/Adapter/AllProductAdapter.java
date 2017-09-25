package pratikthapa.example.com.nepstrademo.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import pratikthapa.example.com.nepstrademo.DetailsActivity;
import pratikthapa.example.com.nepstrademo.Pojo.AllProducts;
import pratikthapa.example.com.nepstrademo.R;


public class AllProductAdapter extends RecyclerView.Adapter<AllProductAdapter.AllProductHolder> {
    public Context context;
    private List<AllProducts> allProductList;

    //  private List<AllProducts> modelItemList;
    ArrayList<HashMap<String, String>> data;
    // private Context context;

    private ArrayList<AllProducts> arraylistitem;
    LayoutInflater inflater;
   // Food_Activity main;

    public AllProductAdapter(Context context, List<AllProducts> allproductList) {
        this.context = context;
        this.allProductList = allproductList;
    }

    @Override
    public AllProductHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate
                (R.layout.all_productlist, parent, false);

        return new AllProductHolder(view);
    }

    @Override
    public void onBindViewHolder(AllProductHolder allholder, final int position) {

        allholder.productName.setText(allProductList.get(position).getName());
        //allholder.allproductName1.setText(allProductList.get(position).getI_name());
        Picasso.with(context).load(allProductList.get(position).getI_src()).into(allholder.productImage);
        //Glide.with(context).load(allProductList.get(position).getI_src()).into(allholder.allproductImage);
        //Glide.with(context).load(allProductList.get(position).getI_date_created()).into(allholder.allproductImage);
        allholder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AllProducts intentprod = allProductList.get(position);
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("hello", intentprod);
                context.startActivity(intent);
            }
        });

        Log.e("chankhey", "monkey");
    }

    @Override
    public int getItemCount() {
        //Log.e("sanjeev", String.valueOf(allProductList.size()));
        return allProductList.size();
    }

    public class AllProductHolder extends RecyclerView.ViewHolder {
        public ImageView productImage;
        public TextView productName;

        public AllProductHolder(View itemView) {
            super(itemView);
            productName = (TextView) itemView.findViewById(R.id.all_product_name);
            productImage = (ImageView) itemView.findViewById(R.id.all_product_image);


        }
    }
}





