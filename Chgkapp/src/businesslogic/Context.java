package businesslogic;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.SerializableEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.os.AsyncTask;

import models.entities.Question;
import models.entities.Tour;
import models.requests.CHGKRandomRequest;

public class Context implements Serializable
{
	String result;
	
	Question q;
	
	Tour resultTour;
	
	CHGKRandomRequest request;
	
	private static final long serialVersionUID = 3474846514992754866L;

	public Tour getRandomPackageCHGK(Date from, Date to, int complexity)
	{
		request = new CHGKRandomRequest(from, to, complexity);
		
		String s = "http://db.chgk.info/random/from_"+ request.getMinDate().getYear()+"-"+request.getMinDate().getMonth()+"-"+request.getMinDate().getDay()+"/to_"+request.getMaxDate().getYear()+"-"+request.getMaxDate().getMonth()+"-"+
				request.getMaxDate().getDay()+"/types1/complexity1"+100000000+"/limit1";
		
		try {
			String str_result = new DownloadRandomPackageTask().execute("http://kharkiv-trainss.rhcloud.com/chgkapp/").get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultTour;
	}
	
	private class DownloadRandomPackageTask extends AsyncTask<String, Void, String> 
	{
        @Override
        protected String doInBackground(String... urls) 
        {
            try {
                return downloadRandomPackageUrl(urls[0]);
            } catch (IOException e) {
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }
        @Override
        protected void onPostExecute(String result) { }
    }
    
	private String downloadRandomPackageUrl(String myurl) throws IOException 
    {
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost("http://kharkiv-trainss.rhcloud.com/chgkapp/");
	    List<NameValuePair> params = new ArrayList<NameValuePair>(2);
	    params.add(new BasicNameValuePair("method", "3"));
	    CHGKRandomRequest request = new CHGKRandomRequest();
	    params.add(new BasicNameValuePair("link", request.toString()));
	    httppost.setEntity(new SerializableEntity(request, false));
	    httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
	    HttpResponse response = httpclient.execute(httppost);
	    HttpEntity entity = response.getEntity();
 
	    ObjectInputStream in = new ObjectInputStream(entity.getContent());
	    try 
	    {
               resultTour = (Tour)in.readObject();
        } 
	    catch (ClassNotFoundException e) 
	    {
               e.printStackTrace();
        }
	    in.close();
	    return "1";
   }

}
