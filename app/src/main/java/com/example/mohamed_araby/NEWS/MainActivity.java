package com.example.mohamed_araby.NEWS;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.mohamed_araby.fuck.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> newsList = new ArrayList<String>();
    private ArrayList<String> newsUrllList = new ArrayList<String>();
    private ListView newsListView;
    private ArrayAdapter<String> arrayAdapter;
    private Database db=db=new Database(this);
private static int  first_time=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.icon);



        if(is_network_available()){


           newsListView = (ListView)findViewById(R.id.newsListView);

           newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
               @Override
               public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                   String url = newsUrllList.get(position);
                   Intent i = new Intent(Intent.ACTION_VIEW);
                   i.setData(Uri.parse(url));
                   startActivity(i);
               }
           });
           arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, newsList);

          // fetchNews();
           fetchNew2();
           fetchNew3();
            fetchNew4();


           Timer timer = new Timer();

           TimerTask hourlyTask = new TimerTask () {
               @Override
               public void run () {
                 //  fetchNews();
                   fetchNew2();
                   fetchNew3();
                   fetchNew4();
               }
           };

           timer.schedule (hourlyTask, 0l, 1000*60*60);
/*
            if(first_time){
                Toast.makeText(getApplicationContext(),"first time  "+newsList.size(),Toast.LENGTH_LONG).show();


                first_time=false;
                for(int i=0; i<newsList.size(); i++){

                    boolean is = db.insert(newsList.get(i), newsUrllList.get(i));
                    ;
                    if (is == true) {
                        System.err.println("insert"+newsList.get(i)+"    "+newsUrllList.get(i));
                        Toast.makeText(getApplicationContext(),"insert"+newsList.get(i)+"    "+newsUrllList.get(i),Toast.LENGTH_LONG).show();

                    }
                }
            }*/

           if (savedInstanceState != null) {
               newsList = savedInstanceState.getStringArrayList("newHeaderArray");
               newsUrllList = savedInstanceState.getStringArrayList("newUrlArray");

               newsListView.setAdapter(arrayAdapter);
           }



        }else{
            System.out.println();
            System.out.println("WOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
           Toast.makeText(getApplicationContext(),"No INternet Connection ",Toast.LENGTH_LONG).show();

            Cursor cursor=db.getalldata();
            if(cursor.getCount()==0){
                Toast.makeText(getApplicationContext(),"No DATA ",Toast.LENGTH_LONG).show();
            }else{
                while (cursor.moveToNext()){
                    newsList.add(cursor.getString(0));


                }
                newsListView = (ListView)findViewById(R.id.newsListView);
                arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, newsList);
                newsListView.setAdapter(arrayAdapter);

            }


       }
    }
    /////////////////////////////////////////////////////////////////////////////////


    private void fetchNew3(){

        newsList.clear();
        newsUrllList.clear();
        DownloadTask3 task = new DownloadTask3();
        String url="https://newsapi.org/v1/articles?source=google-news&sortBy=top&apiKey=0c0743ca8d484014b2ca1ee6d16fc13e";
        url = url.replaceAll("\\s+","");
        task.execute(url);
    }


    public class DownloadTask3 extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;

            try {
                url = new URL(urls[0]);

                urlConnection = (HttpURLConnection) url.openConnection();

                InputStream in = urlConnection.getInputStream();

                InputStreamReader reader = new InputStreamReader(in);

                int data = reader.read();

                while (data != -1) {
                    char current = (char) data;

                    result += current;

                    data = reader.read();
                }



                /*********************************************************/






/***********************************************************************************/
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }



        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if (result != null) {
                try {
                    JSONObject jsonObject = new JSONObject(result);



                    JSONArray arr = jsonObject.getJSONArray("articles");

                    System.out.println("array " + arr);
                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject jsonPart = arr.getJSONObject(i);

                        String newsTitle = jsonPart.getString("title");
                        String newsLink = jsonPart.getString("url");
                        String author=jsonPart.getString("author");
                        if(author.equals("null")){
                            author="Anonymous";
                        }else if(author.contains("facebook")){
                            author="Facebook";
                        }
if(first_time<4){

    boolean is = db.insert(newsTitle, newsLink);
    ;
    if (is == true) {
        System.err.println("insert"+newsList.get(i)+"    "+newsUrllList.get(i));

    }

}
                        System.out.println("********************* " + newsTitle);
                        System.out.println(newsLink);

                        newsList.add(newsTitle+"."+"\n\n"+author);
                        newsUrllList.add(newsLink);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } else {
                Toast.makeText(getApplicationContext(), "No results found.", Toast.LENGTH_LONG).show();
            }
            first_time++;
        }
    }



    ///////////////////////////////////////////////////////////////////////////////////////////

/*
    private void fetchNews(){
        newsList.clear();
        newsUrllList.clear();
        DownloadTask task = new DownloadTask();
        String urlEndPoint = "https://content.guardianapis.com/search?q=android&api-key=0b2b7b96-1be8-4401-99d0-86eace8e00c0";

        urlEndPoint = urlEndPoint.replaceAll("\\s+","");
        task.execute(urlEndPoint);
    }

    public class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;

            try {
                url = new URL(urls[0]);

                urlConnection = (HttpURLConnection) url.openConnection();

                InputStream in = urlConnection.getInputStream();

                InputStreamReader reader = new InputStreamReader(in);

                int data = reader.read();

                while (data != -1) {
                    char current = (char) data;

                    result += current;

                    data = reader.read();
                }

                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if (result != null) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONObject responseData = jsonObject.getJSONObject("response");


                    JSONArray arr = responseData.getJSONArray("results");

                    System.out.println("array " + arr);
                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject jsonPart = arr.getJSONObject(i);

                        String newsTitle = jsonPart.getString("webTitle");
                        String newsLink = jsonPart.getString("webUrl");

                        System.out.println("title " + newsTitle);
                        System.out.println(newsLink);
                        if(first_time<4){
                            first_time++;
                            boolean is = db.insert(newsTitle, newsLink);
                            ;
                            if (is == true) {
System.out.println("insert");
                            }

                        }
                        newsList.add(newsTitle);
                        newsUrllList.add(newsLink);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            } else {
                Toast.makeText(getApplicationContext(), "No results found.", Toast.LENGTH_LONG).show();
            }
        }
    }


*/
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void fetchNew2(){

        newsList.clear();
        newsUrllList.clear();
        DownloadTask2 task = new DownloadTask2();
        String url="https://newsapi.org/v1/articles?source=al-jazeera-english&sortBy=top&apiKey=0c0743ca8d484014b2ca1ee6d16fc13e";
        url = url.replaceAll("\\s+","");
        task.execute(url);
    }


    public class DownloadTask2 extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;

            try {
                url = new URL(urls[0]);

                urlConnection = (HttpURLConnection) url.openConnection();

                InputStream in = urlConnection.getInputStream();

                InputStreamReader reader = new InputStreamReader(in);

                int data = reader.read();

                while (data != -1) {
                    char current = (char) data;

                    result += current;

                    data = reader.read();
                }

                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }



        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if (result != null) {
                try {
                    JSONObject jsonObject = new JSONObject(result);



                    JSONArray arr = jsonObject.getJSONArray("articles");

                    System.out.println("array " + arr);
                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject jsonPart = arr.getJSONObject(i);

                        String newsTitle = jsonPart.getString("title");
                        String newsLink = jsonPart.getString("url");
                        String author=jsonPart.getString("author");
                        if(author.equals("null")){
                            author="Anonymous";
                        }else if(author.contains("facebook")){
                            author="Facebook";
                        }

                        System.out.println("********************* " + newsTitle);
                        System.out.println(newsLink);
                        if(first_time<4){
                            boolean is = db.insert(newsTitle, newsLink);
                            ;
                            if (is == true) {
                                System.out.println("insert");

                            }

                        }
                        newsList.add(newsTitle+"."+"\n\n"+author);
                        newsUrllList.add(newsLink);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } else {
                Toast.makeText(getApplicationContext(), "No results found.", Toast.LENGTH_LONG).show();
            }
            first_time++;

        }
    }







    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////



/////////////////////////////////////////////////////////////////////////////////////////////








    private void fetchNew4(){

        newsList.clear();
        newsUrllList.clear();
        DownloadTask4 task = new DownloadTask4();
        String url="https://newsapi.org/v1/articles?source=metro&sortBy=latest&apiKey=0c0743ca8d484014b2ca1ee6d16fc13e";
        url = url.replaceAll("\\s+","");
        task.execute(url);
    }


    public class DownloadTask4 extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;

            try {
                url = new URL(urls[0]);

                urlConnection = (HttpURLConnection) url.openConnection();

                InputStream in = urlConnection.getInputStream();

                InputStreamReader reader = new InputStreamReader(in);

                int data = reader.read();

                while (data != -1) {
                    char current = (char) data;

                    result += current;

                    data = reader.read();
                }

                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }



        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if (result != null) {
                try {
                    JSONObject jsonObject = new JSONObject(result);



                    JSONArray arr = jsonObject.getJSONArray("articles");

                    System.out.println("array " + arr);
                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject jsonPart = arr.getJSONObject(i);

                        String newsTitle = jsonPart.getString("title");
                        String newsLink = jsonPart.getString("url");
                        String author=jsonPart.getString("author");
                        if(author.equals("null")){
                            author="Anonymous";
                        }else if(author.contains("facebook")){
                            author="Facebook";
                        }
                        if(first_time<4){

                            boolean is = db.insert(newsTitle, newsLink);
                            ;
                            if (is == true) {
                                System.out.println("insert");

                            }

                        }
                        System.out.println("********************* " + newsTitle);
                        System.out.println(newsLink);

                        newsList.add(newsTitle+"."+"\n\n"+author);
                        newsUrllList.add(newsLink);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                newsListView.setAdapter(arrayAdapter);

            } else {
                Toast.makeText(getApplicationContext(), "No results found.", Toast.LENGTH_LONG).show();
            }
            first_time++;
        }
    }









    //////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    protected void onSaveInstanceState(Bundle outState) {super.onSaveInstanceState(outState);outState.putSerializable("newHeaderArray", newsList);outState.putSerializable("newUrlArray", newsUrllList);
    }




    public boolean is_network_available(){
        ConnectivityManager mn=(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nt=mn.getActiveNetworkInfo();
        if(nt!=null&&nt.isConnected()){
            return true;
        }
        else{
            return false;
        }
    }

















    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.Exit) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            final RatingBar rating = new RatingBar(MainActivity.this);
            rating.setMax(5);
            builder.setTitle("Vote!! ");
            builder.setView(rating);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });

            builder.setNegativeButton("Cancel", null);
            AlertDialog alter = builder.create();
            alter.show();
        }
        if (id == R.id.more_option) {
            Intent intent=new Intent(MainActivity.this,Main2Activity.class);
            startActivity(intent);
        }
        if (id == R.id.About_us) {
            Intent intent=new Intent(MainActivity.this,About.class);
          startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}