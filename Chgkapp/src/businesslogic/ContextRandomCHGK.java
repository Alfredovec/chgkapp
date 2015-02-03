package businesslogic;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.SerializableEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.os.AsyncTask;

import models.entities.Tour;
import models.entities.Tournament;
import models.requests.CHGKRandomRequest;

public class ContextRandomCHGK
{
	Tour resultTour;
	CHGKRandomRequest request;

	public Tour get (Date from, Date to, int complexity) throws IOException, ClassNotFoundException
    {
		request = new CHGKRandomRequest(from, to, complexity, 5, 25);
        HttpClient httpclient = new DefaultHttpClient();

        HttpPost httppost = new HttpPost("http://kharkiv-trainss.rhcloud.com/chgkapp/");
        //HttpPost httppost = new HttpPost("http://178.150.137.228:8080/chgkapp/");

        httppost.setEntity(new SerializableEntity(request, false));
        httppost.setHeader("type", "CHGKRequest");

        HttpResponse response = httpclient.execute(httppost);

        HttpEntity entity = response.getEntity();

        ObjectInputStream in = new ObjectInputStream(entity.getContent());

        resultTour = (Tour) in.readObject();

        in.close();

//		try
//      {
//			//String str_result = new DownloadRandomPackageTask().execute("http://178.150.137.228:8080/chgkapp/").get();
//            String str_result = new DownloadTask().execute("http://kharkiv-trainss.rhcloud.com/chgkapp/").get();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ExecutionException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		return resultTour;
	}

	private String download (String myUrl) throws IOException, ClassNotFoundException {
        HttpClient httpclient = new DefaultHttpClient();

        HttpPost httppost = new HttpPost("http://kharkiv-trainss.rhcloud.com/chgkapp/");
        //HttpPost httppost = new HttpPost("http://178.150.137.228:8080/chgkapp/");

	    httppost.setEntity(new SerializableEntity(request, false));
        httppost.setHeader("type", "CHGKRequest");

        HttpResponse response = httpclient.execute(httppost);

	    HttpEntity entity = response.getEntity();

	    ObjectInputStream in = new ObjectInputStream(entity.getContent());
        try
        {
            resultTour = (Tour) in.readObject();
        }
        catch (Exception e)
        {
            int a = 5;
        }

        in.close();
	    return "1";
   }

    private class DownloadTask extends AsyncTask<String, Void, String>
    {
        @Override
        protected String doInBackground(String... urls)
        {
            try {
                return download(urls[0]);
            } catch (IOException e) {
                return "Unable to retrieve web page. URL may be invalid.";
            } catch (ClassNotFoundException e) {
                return "Class not found";
            }
        }
        @Override
        protected void onPostExecute(String result) { }
    }

}
