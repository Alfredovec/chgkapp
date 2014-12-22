package businesslogic;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import ua.kture.pi1311.context.TrainContext.DownloadStationTask;

import android.os.AsyncTask;

import models.entities.Question;
import models.requests.CHGKRandomRequest;

public class Context implements Serializable
{
	String result;
	
	private static final long serialVersionUID = 3474846514992754866L;

	public ArrayList<Question> getRandomPackageCHGK(Date from, Date to, int complexity)
	{
		CHGKRandomRequest request = new CHGKRandomRequest(from, to, complexity);
		
		String str_result = new DownloadRandomPackageTask().execute("http://178.150.137.228:8080/chgkapp/").get();
		
		return new ArrayList<Question>();
	}
	
	private class DownloadRandomPackageTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) 
        {
            try {
                return downloadStationUrl(urls[0]);
            } catch (IOException e) {
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }
        @Override
        protected void onPostExecute(String result) { }
    }
    private String downloadStationUrl(String myurl) throws IOException 
    {
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost("http://178.150.137.228:8080/chgkapp/");
	    List<NameValuePair> params = new ArrayList<NameValuePair>(2);
	    params.add(new BasicNameValuePair("method", "1"));
	    CHGKRandomRequest request = new CHGKRandomRequest();
	    params.add(new BasicNameValuePair("link", request.toString()));
	    httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
	    HttpResponse response = httpclient.execute(httppost);
	    HttpEntity entity = response.getEntity();
 
	    ObjectInputStream in = new ObjectInputStream(entity.getContent());
	    try 
	    {
               result = (String)in.readObject();
        } 
	    catch (ClassNotFoundException e) 
	    {
               e.printStackTrace();
        }
	    in.close();
	    return "1";
   }
}
