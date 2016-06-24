package com.example.lguti.nytimesearch.activites;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.ShareActionProvider;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.example.lguti.nytimesearch.model.Article;
import com.example.lguti.nytimesearch.adapter.ArticleArrayAdapter;
import com.example.lguti.nytimesearch.extra.EndlessScrollListener;
import com.example.lguti.nytimesearch.R;
import com.example.lguti.nytimesearch.model.SearchFilters;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class SearchActivity extends AppCompatActivity {

    //EditText etQuery;
    GridView gvResults;
    //Button btnSearch;

    ArrayList<Article> articles;
    ArticleArrayAdapter adapter;

    String query;

    SearchFilters filter;

    private final int REQUEST_CODE = 20;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        filter= new SearchFilters("20160202");

        setupView();





    }

    public void setupView(){
        //etQuery = (EditText) findViewById(R.id.etQuery);
        gvResults = (GridView) findViewById(R.id.gvResults);
        //btnSearch = (Button) findViewById(R.id.btnSearch);
        articles = new ArrayList<>();
        adapter = new ArticleArrayAdapter(this, articles);
        gvResults.setAdapter(adapter);

        //set up click listener for grid click
        gvResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // create intent to display article

                Intent i = new Intent(getApplicationContext(), ArticleActivity.class);


                //get the article to display

                Article article = articles.get(position);


                //pass into article to intent
                i.putExtra("article", article);

                //launch the activity
                startActivity(i);
            }
        });

        gvResults.setOnScrollListener(new EndlessScrollListener() {
            @Override
            public boolean onLoadMore(int page, int totalItemsCount) {

                //Loads more pages onto the screen!
                customLoadMoreDataFromApi(page, query);
                return true;
            }
        });


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchItem.expandActionView();
        searchView.requestFocus();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String new_query) {
                //clears out data when next search is done
                articles.clear();
                //perform query here
                query = new_query;
                customLoadMoreDataFromApi(0, query);

                // workaround to avoid issues with some emulators and keyboard devices firing twice if a keyboard enter is used
                // see https://code.google.com/p/android/issues/detail?id=24599

                searchView.clearFocus();

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


        return true;




    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    //I get this method is part of the super Oncreate method? It's probably being executed somewhere.
    /*
    public void onArticleSearch(View view) {
        //defaults to 0 first!
       customLoadMoreDataFromApi(0);
    } */






    /*

     */


    public void customLoadMoreDataFromApi (int offset, String query) {
        //Toast.makeText(this, "Search for " + query, Toast.LENGTH_SHORT).show();
        // This is instantiating AsyncHTTPClient. Remember to use dependencies and make sure Internet is on
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "https://api.nytimes.com/svc/search/v2/articlesearch.json";

;
        //What are Query parameters? Oh like the words used for a query search. (Think about it like a search engine)
        RequestParams params = new RequestParams();
        params.put("api-key", "1d306c0b070d482ca19cf71f537479ca");
        params.put("page", offset);
        params.put("q", query);

        //Applying Filters
        params.put("end_date", filter.getBegin_date());




        //make network request
        client.get(url, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.d("DEBUG", response.toString()); // checks if there is infact a response.
                JSONArray articleJsonResults = null;

                try{
                    articleJsonResults = response.getJSONObject("response").getJSONArray("docs");
                    //This automatically updates the adapter every single time!
                    adapter.addAll(Article.fromJSONArray(articleJsonResults));

                    Log.d("DEBUG", articles.toString());

                } catch(JSONException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {

                super.onFailure(statusCode, headers, throwable, errorResponse);
            }

        });
    }


    public void Filter(MenuItem item) {
        //Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();
        if (filter == null) {

        }


        Intent i = new Intent(this, FilterActivity.class);
        i.putExtra("filter", filter);


        //Better to use this because it would simply come back to the SearchActivity Activity after Filters are done
        startActivityForResult(i, REQUEST_CODE);



    }

    //This actually retrieves the updated object.
    //ERROR: new_data is Null!
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent new_data) {
        // REQUEST_CODE is defined above
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            // Extract name value from result extras
            //WHY IS MY DATA NULL???????????


             filter = (SearchFilters) new_data.getParcelableExtra("filter");
            //We then have to globalize this new filter that we updated.


            Toast.makeText(this, filter.getBegin_date(), Toast.LENGTH_SHORT).show();

        }




    }
}
