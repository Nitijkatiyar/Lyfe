package com.bst.networkutils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.bst.utils.JSONUtils;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nitij on 20/04/2016.
 */
public class NetworkTools {


    private static NetworkTools ourInstance = new NetworkTools();
    private static String sessionId = "";

    private static final String TAG = "NetworkTool";

    public static NetworkTools getInstance() {
        return ourInstance;
    }

    private NetworkTools() {

    }

    public boolean checkNetworkConnection(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }


    public JSONObject getJsonData(final Activity context, String urlString) {


        Log.e(TAG, "requesturl" + urlString);
        HttpClient httpclient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(urlString);
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("type", "web_server"));

        JSONObject json = null;
        try {

            httpGet.setHeader("Content-Type",
                    "application/x-www-form-urlencoded");
            HttpResponse response = httpclient.execute(httpGet);
            Log.e(TAG, "POST response: " + response.getStatusLine());
            InputStream inputStream = null;

            inputStream = response.getEntity().getContent();
            String result = new JSONUtils(context)
                    .convertStreamToString(inputStream);
            json = new JSONObject(result);
            Log.e("response", "" + json);
            inputStream.close();
            if (json.getJSONObject("Result").getInt("Code") == 0) {

            }

        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return json;
    }

    public JSONObject postJsonData(final Activity context, String urlString, JSONObject jsonObject) {


        Log.e(TAG, "url" + urlString);
        Log.e(TAG, "requestjson" + jsonObject);
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(urlString);

        JSONObject json = null;
        try {
            if (jsonObject != null) {
                httpPost.setEntity(new StringEntity(jsonObject.toString(), "utf-8"));
            }
            httpPost.setHeader("Content-Type",
                    "application/x-www-form-urlencoded");
            HttpResponse response = httpclient.execute(httpPost);
            Log.e(TAG, "POST response: " + response.getStatusLine());
            InputStream inputStream = null;

            inputStream = response.getEntity().getContent();
            String result = new JSONUtils(context)
                    .convertStreamToString(inputStream);
            json = new JSONObject(result);

            inputStream.close();
            if (json.getJSONObject("Result").getInt("Code") == 0) {

            }

        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return json;
    }
}
