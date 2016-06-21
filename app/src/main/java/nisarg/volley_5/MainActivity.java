package nisarg.volley_5;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<DataModel> data;
    Context c;

    private static ArrayList<Integer> removedItems;

    TextView results;
    String JsonURL = "https://api.myjson.com/bins/238kj";
    //ImageView imageView = (ImageView) findViewById(R.id.image);
    ArrayList<String> userArray = new ArrayList<String>();
    ArrayList<String> description = new ArrayList<String>();
    ArrayList<String> img_handle = new ArrayList<String>();

    String link = "http://";
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        c = MainActivity.this;


        requestQueue = Volley.newRequestQueue(this);


        //results = (TextView) findViewById(R.id.jsonData);


        JsonArrayRequest arrayreq = new JsonArrayRequest(JsonURL,

                new Response.Listener<JSONArray>() {

                    // Takes the response from the JSON request
                    @Override
                    public void onResponse(JSONArray response) {
                        try {

                           // JSONObject colorObj = response.getJSONObject(0);

                            //JSONArray colorArry = colorObj.getJSONArray(0);


                            for (int i = 0; i < response.length(); i++) {
                               // Glide.with(MainActivity.this).load("http://goo.gl/gEgYUd").into(imageView);


                                JSONObject jsonObject = response.getJSONObject(i);


                                String color = jsonObject.getString("username");
                                if (color.isEmpty())
                                {
                                    break;
                                }

                                userArray.add(color);
                                String hex = jsonObject.getString("description");
                                description.add(hex);
                                String img_url =jsonObject.getString("Image");
                                img_url=link.concat(img_url);
                                img_handle.add(img_url);


                                //data += "User Number:" + (i + 1) + "\nUser Name: " + color +
                                   //     "\nDescription : " + hex + "\nImage:" + img_url +"\n\n\n";



                            }
                           // Glide.with(MainActivity.this).load("http://goo.gl/gEgYUd").into(imageView);

                            data = new ArrayList<DataModel>();
                            for (int i = 0; i < userArray.size(); i++) {
                                data.add(new DataModel(
                                        userArray.get(i),
                                        description.get(i),
                                        img_handle.get(i)

                                ));
                            }

                            adapter = new CustomAdapter(data,c);
                            recyclerView.setAdapter(adapter);


                            }

                        catch (JSONException e) {

                            e.printStackTrace();
                        }
                    }
                },


                new Response.ErrorListener() {
                    @Override
                    // Handles errors that occur due to Volley
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley", "Error");
                    }
                }
        );
        // Adds the JSON array request "arrayreq" to the request queue
        requestQueue.add(arrayreq);


    }


}