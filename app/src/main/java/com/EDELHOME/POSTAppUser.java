//package com.EDELHOME;
//
//import android.os.AsyncTask;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.google.gson.Gson;
//
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClientBuilder;
//import org.json.JSONException;
//
//import java.io.IOException;
//
//public class POSTAppUser extends AsyncTask<AppUser, Integer, String>{
//
//        private AppUser appuser;
//
//        POSTAppUser(AppUser appuser)
//        {
//                this.appuser = appuser;
//        }
//        @Override
//protected String doInBackground(AppUser...AppUser) {
//        Gson gson=new Gson();
//        boolean x=true;
//        CloseableHttpClient httpClient= HttpClientBuilder.create().build();
//        try{
//        HttpPost request=new HttpPost("http://24e8-213-208-174-203.eu.ngrok.io/appusers");
//        StringEntity params=new StringEntity(gson.toJson(appuser));
//        request.addHeader("content-type","application/json");
//        request.setEntity(params);
//        httpClient.execute(request);
//// handle response here...
//        }catch(Exception ex){
//        Toast.makeText(getApplicationContext(),ex.toString(),Toast.LENGTH_LONG).show();
//        x=false;
//        // handle exception here
//        }finally{
//                try {
//                        httpClient.close();
//                } catch (IOException e) {
//                        e.printStackTrace();
//                }
//                return x;
//        }
//        }
//}
