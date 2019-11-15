package com.example.nyt.fragments;

import android.app.DownloadManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.nyt.ArticleAdapter;
import com.example.nyt.FakeAPI;
import com.example.nyt.FakeDatabase;
import com.example.nyt.activities.MainActivity;
import com.example.nyt.R;
import com.example.nyt.model.Article;
import com.example.nyt.model.CatResponse;
import com.example.nyt.model.TopStoriesResponse;
import com.google.gson.Gson;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ArticleRecyclerFragment extends Fragment {

    private RecyclerView recyclerView;
    public EditText breedSearch;
    public Button srchBtn;

    public ArticleRecyclerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_article_recycler, container, false);
        recyclerView = view.findViewById(R.id.rv_main);
        srchBtn = view.findViewById(R.id.search_button);
        breedSearch = view.findViewById(R.id.breed_search_bar);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);



       final ArticleAdapter articleAdapter = new ArticleAdapter();
       final RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
       String url = "https://api.thecatapi.com/v1/breeds";


        // This part is converting the JSON string into a TopStoriesResponse object, because we
        // have written the TopStoriesResponse class to match the structure of the JSON.
        // Within the TopStoriesResponse object ("topStoriesResponse"), it has the field "results",
        // which is an ArrayList<Article>.
        //
        // Because my Article class is also written to match how one article is represented in the
        // JSON, Gson will also automatically populate the "results" ArrayList with Article objects
        // using the data from the JSON.
        //
        // Thus, when I access topStoriesResponse.results, I get an ArrayList of Articles.
        // I can then give this to my recyclerView adapter.




        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                CatResponse[] catResponse = gson.fromJson(response, CatResponse[].class);
                List<CatResponse> cResponse = new ArrayList<>();
                for(int i=0; i< catResponse.length; i++){
                    cResponse.add(catResponse[i]);
                }

                articleAdapter.setData((ArrayList<CatResponse>) cResponse);
                //FakeDatabase.saveArticlesToFakeDatabase(topStoriesResponse.results);
                recyclerView.setAdapter(articleAdapter);
                System.out.println(cResponse.size());
                requestQueue.stop();
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),"The request failed: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                requestQueue.stop();
            }
        };


        final StringRequest stringRequest;


        stringRequest = new StringRequest(Request.Method.GET, url, responseListener, errorListener) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> customHeaders = new HashMap<>();

                customHeaders.put("x-api-key", "b90ec6a2-5c54-4fa4-be71-c2700d6e3aed");
                return customHeaders;
            }
        };

        requestQueue.add(stringRequest);

        srchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newSearch();
            }
        });




        return view;




    }




       // requestQueue.add(stringRequest);

       /* Gson gson = new Gson();
        String jsonString = FakeAPI.getMostViewedStoriesJsonString();
        TopStoriesResponse topStoriesResponse = gson.fromJson(jsonString, TopStoriesResponse.class);
        articleAdapter.setData(topStoriesResponse.results);*/




        // We have reworked FakeDatabase to act as a place to store these Articles, such that we
        // can access them via their ID. This will allow our intents to the DetailView to keep
        // functioning.
       // FakeDatabase.saveArticlesToFakeDatabase(topStoriesResponse.results);

      //  recyclerView.setAdapter(articleAdapter);

      //  return view;
   // }

    // This is just an example of a way that the Fragment can communicate with the parent Activity.
    // Specifically, this is using a method belonging to the parent.
    @Override
    public void onResume() {
        super.onResume();
        MainActivity parent = (MainActivity) getActivity();
        parent.showCoolMessage("cool (from ArticleRecyclerFragment onResume)");

    }

    public void newSearch(){
        System.out.println("hail hydra");
        final ArticleAdapter articleAdapter = new ArticleAdapter();
        final RequestQueue queue = Volley.newRequestQueue(getContext());
        String txt = breedSearch.getText().toString();
        String urlSecond ="https://api.thecatapi.com/v1/breeds/search?q=" + txt;
        System.out.println(txt);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlSecond,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        CatResponse[] catResponses = gson.fromJson(response, CatResponse[].class);
                        ArrayList<CatResponse> catsSearch = new ArrayList<>();
                        for(int i=0; i< catResponses.length; i++){
                            catsSearch.add(catResponses[i]);
                        }
                        //List<Article> catsSearch = Arrays.asList(article);
                        articleAdapter.setData(catsSearch);
                        recyclerView.setAdapter(articleAdapter);

                        //FakeDatabase.saveArticlesToFakeDatabase(catsSearch);
                        System.out.println(catResponses.length);
                        queue.stop();
                    }


                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        System.out.println("Error");
                        queue.stop();
                    }
                });
        queue.add(stringRequest);

    }
}
