package pratikthapa.example.com.nepstrademo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarTab;
import com.roughike.bottombar.OnTabSelectListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import pratikthapa.example.com.nepstrademo.Adapter.AllProductAdapter;
import pratikthapa.example.com.nepstrademo.Adapter.MycustomAdapter;
import pratikthapa.example.com.nepstrademo.Pojo.AllProducts;

import static java.security.AccessController.getContext;

public class WomensActivity extends AppCompatActivity {
    BottomBar bottomBar;

    MycustomAdapter customAdapter;
    ArrayList<HashMap<String, String>> data_list;
    private ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item);
        data_list = new ArrayList<>();
        lv = (ListView) findViewById(R.id.list);
        new ProductAsyncTask().execute();






    }
    class ProductAsyncTask extends AsyncTask<String, String, String> {
        ProgressDialog mprogressDialog;
        RecyclerView mrecyclerView;
        int flag;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mprogressDialog = new ProgressDialog(WomensActivity.this);
            mprogressDialog.setMessage("Please wait");
            mprogressDialog.setCancelable(false);
            mprogressDialog.show();

        }

        @Override
        protected String doInBackground(String... params) {
            HashMap<String, String> loginHashMap = new HashMap<>();
            JsonParser jsonParser = new JsonParser();
            JSONObject jsonObject = jsonParser.performPostCI("https://nepstra.com/api/android/products.php", loginHashMap);
            String request = "";

            try {
                if (jsonObject == null) {
                    flag = 1;
                } else {

                    if (jsonObject.getString("status").equals("success")) {

                        JSONArray jsonArray = jsonObject.getJSONArray("data");


                      for (int i = 0; i < jsonArray.length(); i++) {
                            Log.e("forkatichoti", "khaikhai");

                            JSONObject dataObject = jsonArray.getJSONObject(i);



                          if(dataObject.getString("categories").equals("101")) {

                              String item_name = dataObject.getString("name");
                              String i_src = dataObject.getString("src");

                              // tmp hashmap for single contact
                              HashMap<String, String> products = new HashMap<String, String>();

                              // adding each child node to HashMap key => value
                              products.put("name", item_name);
                              products.put("src", i_src);



                              // adding contact to contact list
                              data_list.add(products);
                              flag= 2;
                          }
                      }
                       /*     if(id.equals("101"))
                            {
                                String name = dataObject.getString("name");
                                String i_src = dataObject.getString("src");

                                // tmp hashmap for single contact
                                HashMap<String, String> products = new HashMap<String, String>();

                                // adding each child node to HashMap key => value
                                products.put("name", name);
                                products.put("src", i_src);



                                // adding contact to contact list
                                data_list.add(products);
                                flag= 2;
                            }*/


                    }
                    else {
                        flag = 3;
                    }
                }
            } catch (JSONException e) {

            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            mprogressDialog.dismiss();
            if (flag == 1) {
                Toast.makeText(WomensActivity.this, "Server/Network issue", Toast.LENGTH_SHORT).show();

            } else if (flag == 2) {
                ListAdapter adapter = new SimpleAdapter(
                        WomensActivity.this, data_list,
                        R.layout.list_item, new String[]{"name", "src"},
                        new int[]{R.id.name,
                        R.id.imagewomen});

                lv.setAdapter(adapter);

            } else {
                Toast.makeText(WomensActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
