//package pratikthapa.example.com.nepstrademo.Fragment;
//
//
//
///**
// * Created by Prakriti on 9/19/2017.
// */
//
//import android.app.ProgressDialog;
//import android.content.Context;
//import android.graphics.Typeface;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.util.Log;
//import android.view.Gravity;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.Button;
//import android.widget.ImageButton;
//import android.widget.ListView;
//import android.widget.SearchView;
//import android.widget.SearchView.OnQueryTextListener;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import org.json.JSONArray;
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//
//import pratikthapa.example.com.nepstrademo.JsonParser;
//import pratikthapa.example.com.nepstrademo.MainActivity;
//import pratikthapa.example.com.nepstrademo.Pojo.AllProducts;
//import pratikthapa.example.com.nepstrademo.R;
//
//
//public class Search extends Fragment
//{
//    View myFragmentView;
//    SearchView search;
//    ImageButton buttonBarcode;
//    ImageButton buttonAudio;
//    Typeface type;
//    ListView searchResults;
//    String found = "N";
//
//
//    //This arraylist will have data as pulled from server. This will keep cumulating.
//    ArrayList<AllProducts> productResults = new ArrayList<AllProducts>();
//    //Based on the search string, only filtered products will be moved here from productResults
//    ArrayList<AllProducts> filteredProductResults = new ArrayList<AllProducts>();
//
//    @Override
//    public void onCreate(Bundle savedInstanceState)
//    {
//        super.onCreate(savedInstanceState);
//    }
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState)
//    {
//        //get the context of the HomeScreen Activity
//        final MainActivity activity = (MainActivity) getActivity();
//
//        //define a typeface for formatting text fields and listview.
//
////        type= Typeface.createFromAsset(activity.getAssets(),"fonts/book.TTF");
//        myFragmentView = inflater.inflate(R.layout.fragment_search, container, false);
//
//        search=(SearchView) myFragmentView.findViewById(R.id.searchView1);
//        search.setQueryHint("Start typing to search...");
//
//        searchResults = (ListView) myFragmentView.findViewById(R.id.listview_search);
//        buttonBarcode = (ImageButton) myFragmentView.findViewById(R.id.imageButton2);
//        buttonAudio = (ImageButton) myFragmentView.findViewById(R.id.imageButton1);
//        new myAsyncTask().execute();
//
//
//        //this part of the code is to handle the situation when user enters any search criteria, how should the
//        //application behave?
//
//        search.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener()
//        {
//
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                // TODO Auto-generated method stub
//
//                //Toast.makeText(activity, String.valueOf(hasFocus),Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        search.setOnQueryTextListener(new OnQueryTextListener()
//        {
//
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                // TODO Auto-generated method stub
//
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//
//                if (newText.length() > 5)
//                {
//
//                    searchResults.setVisibility(myFragmentView.VISIBLE);
//                    myAsyncTask m= (myAsyncTask) new myAsyncTask().execute(newText);
//                }
//                else
//                {
//
//                    searchResults.setVisibility(myFragmentView.INVISIBLE);
//                }
//
//
//
//                return false;
//            }
//
//        });
//        return myFragmentView;
//    }
//
//    //this filters products from productResults and copies to filteredProductResults based on search text
//
//    public void filterProductArray(String newText)
//    {
//
//        String pName;
//
//        filteredProductResults.clear();
//        for (int i = 0; i < productResults.size(); i++)
//        {
//            pName = productResults.get(i).getName().toLowerCase();
//            if ( pName.contains(newText.toLowerCase()) ||
//                    productResults.get(i).getName().contains(newText))
//            {
//                filteredProductResults.add(productResults.get(i));
//                Log.e("Monkey","pppppppppp");
//            }
//        }
//
//    }
//
//    //in this myAsyncTask, we are fetching data from server for the search string entered by user.
//    class myAsyncTask extends AsyncTask<String, String, String> {
//        JsonParser jParser;
//        JSONArray productList;
//        String url = new String();
//        String textSearch;
//        ProgressDialog pd;
//        int flag;
//
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            productList = new JSONArray();
//            jParser = new JsonParser();
//            pd = new ProgressDialog(getActivity());
//            pd.setCancelable(false);
//            pd.setMessage("Searching...");
//            pd.getWindow().setGravity(Gravity.CENTER);
//            pd.show();
//        }
//
//        @Override
//        protected String doInBackground(String... params) {
//            HashMap<String, String> loginHashMap = new HashMap<>();
//            JsonParser jsonParser = new JsonParser();
//            JSONObject jsonObject = jsonParser.performPostCI("https://nepstra.com/api/android/searchproduct.php?data=", loginHashMap);
//            AllProducts tempProduct = new AllProducts();
//            String matchFound = "N";
//
//
//            /*url = "https://nepstra.com/api/android/searchproduct.php?data=Banarasi/" + sText[0];
//            String returnResult = getProductList(url);
//            this.textSearch = sText[0];
//            return returnResult;*/
//
//
//        try {
//
//    /*    public String jsonObject(String url) {
//
//            AllProducts tempProduct = new AllProducts();
//            String matchFound = "N";
//
//            //productResults is an arraylist with all product details for the search criteria
//            //productResults.clear();*/
//
//            if (jsonObject == null) {
//                Toast.makeText(getActivity(), "Unable to connect to server,please try later", Toast.LENGTH_LONG).show();
//
//            } else if (jsonObject.getString("status").equals("success")) {
//
//                    JSONArray jsonArray = jsonObject.getJSONArray("data");
//
//                    System.out.println(jsonArray.length());
//
//
//                    for (int i = 0; i < jsonArray.length(); i++) {
//
//
//                        JSONObject dataObject = jsonArray.getJSONObject(i);
//
//
//
//                        Integer id = dataObject.getInt("id");
//                        String name = dataObject.getString("name");
//                        String slug = dataObject.getString("slug");
//
//                        String permalink = dataObject.getString("permalink");
//
//                        String date_created = dataObject.getString("date_created");
//                        String date_created_gmt = dataObject.getString("date_created_gmt");
//                        String date_modified = dataObject.getString("date_modified");
//                        String date_modified_gmt = dataObject.getString("date_modified_gmt");
//
//
//                        String type = dataObject.getString("type");
//                        String status = dataObject.getString("status");
//                        Boolean featured = dataObject.getBoolean("featured");
//                        String catalog_visibility = dataObject.getString("catalog_visibility");
//                        String description = dataObject.getString("description");
//                        String short_description = dataObject.getString("short_description");
//                        String sku = dataObject.getString("sku");
//                        String price = dataObject.getString("price");
//                        String regular_price = dataObject.getString("regular_price");
//
//                        String sale_price = dataObject.getString("sale_price");
//
//                        Boolean date_on_sale_from = dataObject.isNull("date_on_sale_from");
//                        Boolean date_on_sale_from_gmt = dataObject.isNull("date_on_sale_from_gmt");
//                        Boolean date_on_sale_to = dataObject.isNull("date_on_sale_to");
//                        Boolean date_on_sale_to_gmt = dataObject.isNull("date_on_sale_to_gmt");
//
//                        String price_html = dataObject.getString("price_html");
//
//                        Boolean on_sale = new Integer(1).equals(dataObject.getBoolean("on_sale"));
//                        Boolean purchasable = new Integer(1).equals(dataObject.getBoolean("purchasable"));
//
//                        Integer total_sales = dataObject.getInt("total_sales");
//                        Boolean virtual = dataObject.getBoolean("virtual");
//                        Boolean downloadable = dataObject.getBoolean("downloadable");
//
//
//                        JSONArray downloads = dataObject.getJSONArray("downloads");
//                        Integer download_limit = dataObject.getInt("download_limit");
//                        Integer download_expiry = dataObject.getInt("download_expiry");
//                        String external_url = dataObject.getString("external_url");
//                        String button_text = dataObject.getString("button_text");
//                        String tax_status = dataObject.getString("tax_status");
//                        String tax_class = dataObject.getString("tax_class");
//
//                        Boolean manage_stock = dataObject.getBoolean("manage_stock");
//                        Boolean stock_quantity = dataObject.isNull("stock_quantity");
//                        Boolean in_stock = dataObject.getBoolean("in_stock");
//
//                        String backorders = dataObject.getString("backorders");
//                        Boolean backorders_allowed = dataObject.getBoolean("backorders_allowed");
//                        Boolean backordered = dataObject.getBoolean("backordered");
//                        Boolean sold_individually = dataObject.getBoolean("sold_individually");
//
//                        String weight = dataObject.getString("weight");
//
//                        JSONObject dimensionObject = dataObject.getJSONObject("dimensions");
//                        String length = dimensionObject.getString("length");
//                        String width = dimensionObject.getString("width");
//                        String height = dimensionObject.getString("height");
//
//
//                        Boolean shipping_required = dataObject.getBoolean("shipping_required");
//                        Boolean shipping_taxable = dataObject.getBoolean("shipping_taxable");
//
//                        String shipping_class = dataObject.getString("shipping_class");
//                        Integer shipping_class_id = dataObject.getInt("shipping_class_id");
//                        Boolean reviews_allowed = dataObject.getBoolean("reviews_allowed");
//                        String average_rating = dataObject.getString("average_rating");
//                        Integer rating_count = dataObject.getInt("rating_count");
//                        JSONArray related_ids = dataObject.getJSONArray("related_ids");
//
//                        JSONArray upsell_ids = dataObject.getJSONArray("upsell_ids");
//                        JSONArray cross_sell_ids = dataObject.getJSONArray("cross_sell_ids");
//                        Integer parent_id = dataObject.getInt("parent_id");
//                        String purchase_note = dataObject.getString("purchase_note");
//
//                        JSONArray categories_array = dataObject.getJSONArray("categories");
//                        Integer c_id = null;
//                        String c_name = null;
//                        String c_slug = null;
//                        for (int j = 0; j < categories_array.length(); j++) {
//                            c_id = categories_array.getJSONObject(j).getInt("id");
//                            c_name = categories_array.getJSONObject(j).getString("name");
//                            c_slug = categories_array.getJSONObject(j).getString("slug");
//                            Log.e("catogory", "catogory");
//                        }
//
//                        JSONArray tags = dataObject.getJSONArray("tags");
//                        Integer tag_id = null;
//                        String tag_name = null;
//                        String tag_slug = null;
//                        for (int o = 0; o < tags.length(); o++) {
//                            tag_id = tags.getJSONObject(o).getInt("id");
//                            tag_name = tags.getJSONObject(o).getString("name");
//                            tag_slug = tags.getJSONObject(o).getString("slug");
//                        }
//
//                        JSONArray image_array = dataObject.getJSONArray("images");
//                        Integer i_id = null;
//                        Integer i_position = null;
//                        String i_date_created = null;
//                        String i_date_created_gmt = null;
//                        String i_date_modified = null;
//                        String i_date_modified_gmt = null;
//                        String i_src = null;
//                        String i_name = null;
//                        String i_alt = null;
//                        for (int k = 0; k < image_array.length(); k++) {
//                            i_id = image_array.getJSONObject(k).getInt("id");
//                            i_date_created = image_array.getJSONObject(k).getString("date_created");
//                            i_date_created_gmt = image_array.getJSONObject(k).getString("date_created_gmt");
//                            i_date_modified = image_array.getJSONObject(k).getString("date_modified");
//                            i_date_modified_gmt = image_array.getJSONObject(k).getString("date_modified_gmt");
//                            i_src = image_array.getJSONObject(k).getString("src");
//                            i_name = image_array.getJSONObject(k).getString("name");
//                            i_alt = image_array.getJSONObject(k).getString("alt");
//                            i_position = image_array.getJSONObject(k).getInt("position");
//                            Log.e("imagevitra", "imagevitra");
//                        }
//                        JSONArray attributes = dataObject.getJSONArray("attributes");
//                        JSONArray default_attributes = dataObject.getJSONArray("default_attributes");
//                        JSONArray variations = dataObject.getJSONArray("variations");
//                        JSONArray grouped_products = dataObject.getJSONArray("grouped_products");
//
//                        Integer menu_order = dataObject.getInt("menu_order");
//
//                        JSONArray meta_data_array = dataObject.getJSONArray("meta_data");
//                        Integer m_id = null;
//                        String m_key = null;
//                        String m_value = null;
//                        for (int l = 0; l < meta_data_array.length(); l++) {
//                            m_id = meta_data_array.getJSONObject(l).getInt("id");
//                            m_key = meta_data_array.getJSONObject(l).getString("key");
//                            m_value = meta_data_array.getJSONObject(l).getString("value");
//                            Log.e("metadata", "metadata");
//                        }
//
//                        JSONObject _links = dataObject.getJSONObject("_links");
//
//                        JSONArray self_array = _links.getJSONArray("self");
//                        String self_href = null;
//                        for (int m = i; m < self_array.length(); m++) {
//                            self_href = self_array.getJSONObject(0).getString("href");
//                        }
//                        JSONArray collection_array = _links.getJSONArray("collection");
//                        String collection_href = null;
//                        for (int n = i; n < collection_array.length(); n++) {
//                            collection_href = collection_array.getJSONObject(0).getString("href");
//
//                        }
//                        AllProducts allProducts =
//                                new AllProducts(id, total_sales, download_limit, download_expiry, shipping_class_id, rating_count, parent_id, c_id, i_id, i_position, menu_order, m_id, tag_id, price, name, slug, permalink, date_created, date_created_gmt, date_modified, date_modified_gmt, type, status, weight, catalog_visibility, description, short_description, sku, regular_price, sale_price, price_html, external_url, button_text, tag_id, tax_status, tax_class, backorders, length, width, height, shipping_class, c_name, c_slug, i_date_created, i_date_created_gmt, i_date_modified, i_date_modified_gmt, i_src, i_name, i_alt, self_href, collection_href, tag_name, tag_slug, downloads, related_ids, upsell_ids, cross_sell_ids, tags, attributes, default_attributes, variations, grouped_products, featured, date_on_sale_from, date_on_sale_from_gmt, date_on_sale_to, date_on_sale_to_gmt, on_sale, purchasable, virtual, downloadable, manage_stock, stock_quantity, in_stock, backorders_allowed, backordered, sold_individually, shipping_required, shipping_taxable, reviews_allowed);
//
//                        //check if this product is already there in productResults, if yes, then don't add it again.
//                        matchFound = "N";
//
//                        for (int j = 0; j < productResults.size(); j++) {
//
//                            if (productResults.get(j).getId().equals(tempProduct.getId())) {
////
//                                matchFound = "Y";
//                            }
//                        }
//                        if (matchFound == "N") {
//                            productResults.add(tempProduct);
//                        }
//
//                    }
//
//                    return ("OK");
//
//                }
//            } catch (Exception e) {
//                e.printStackTrace();}
//                return ("Exception Caught");
//            }
//        @Override
//        protected void onPostExecute(String result) {
//          super.onPostExecute(result);
//
//            if(result.equalsIgnoreCase("Exception Caught"))
//            {
//                Toast.makeText(getActivity(), "Unable to connect to server,please try later", Toast.LENGTH_LONG).show();
//
//                pd.dismiss();
//            }
//            else
//            {
//
//
//                //calling this method to filter the search results from productResults and move them to
//                //filteredProductResults
//                filterProductArray(textSearch);
//                searchResults.setAdapter(new SearchResultsAdapter(getActivity(),filteredProductResults));
//                pd.dismiss();
//            }}
//    }}
//
//class SearchResultsAdapter extends BaseAdapter
//{
//    private LayoutInflater layoutInflater;
//
//    private ArrayList<AllProducts> productDetails=new ArrayList<AllProducts>();
//    int count;
//    Typeface type;
//    Context context;
//
//    //constructor method
//    public SearchResultsAdapter(Context context, ArrayList<AllProducts> product_details) {
//
//        layoutInflater = LayoutInflater.from(context);
//
//        this.productDetails=product_details;
//        this.count= product_details.size();
//        this.context = context;
//      //  type= Typeface.createFromAsset(context.getAssets(),"fonts/book.TTF");
//    }
//    @Override
//    public int getCount() {
//        return count;
//    }
//
//    @Override
//    public Object getItem(int arg0) {
//        return productDetails.get(arg0);
//    }
//
//    @Override
//    public long getItemId(int arg0) {
//        return arg0;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent)
//    {
//
//        ViewHolder holder;
//        AllProducts tempProduct = productDetails.get(position);
//
//        if (convertView == null)
//        {
//            convertView = layoutInflater.inflate(R.layout.listtwo_searchresults, null);
//            holder = new ViewHolder();
//            holder.product_name = (TextView) convertView.findViewById(R.id.product_name);
//            holder.product_mrp = (TextView) convertView.findViewById(R.id.product_mrp);
//            holder.product_mrpvalue = (TextView) convertView.findViewById(R.id.product_mrpvalue);
//       //     holder.product_bb = (TextView) convertView.findViewById(R.id.product_bb);
//            holder.product_bbvalue = (TextView) convertView.findViewById(R.id.product_bbvalue);
//           // holder.addToCart = (Button) convertView.findViewById(R.id.add_cart);
//
//            convertView.setTag(holder);
//        }
//        else
//        {
//            holder = (ViewHolder) convertView.getTag();
//        }
//
//
//        holder.product_name.setText(tempProduct.getName());
//        holder.product_name.setTypeface(type);
//
//        holder.product_mrp.setTypeface(type);
//
//        holder.product_mrpvalue.setText(tempProduct.getPrice());
//        holder.product_mrpvalue.setTypeface(type);
//
//        holder.product_bb.setTypeface(type);
//
//        holder.product_bbvalue.setText(tempProduct.getSale_price());
//        holder.product_bbvalue.setTypeface(type);
//
//        return convertView;
//    }
//
//    static class ViewHolder
//    {
//        TextView product_name;
//        TextView product_mrp;
//        TextView product_mrpvalue;
//        TextView product_bb;
//        TextView product_bbvalue;
//        TextView product_savings;
//        TextView product_savingsvalue;
//        TextView qty;
//        TextView product_value;
//        Button addToCart;
//    }}
//
