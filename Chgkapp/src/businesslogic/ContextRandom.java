package businesslogic;

import java.io.IOException;
import java.io.ObjectInputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.SerializableEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import models.entities.Tour;
import models.requests.RandomRequest;

public class ContextRandom
{
	public Tour get (RandomRequest request) throws IOException, ClassNotFoundException {
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost("http://kharkiv-trainss.rhcloud.com/chgkapp/");
        //HttpPost httppost = new HttpPost("http://178.150.137.228:8080/chgkapp/");

        httppost.setEntity(new SerializableEntity(request, false));
        String s = request.toString();
        httppost.setHeader("type", request.toString());

        HttpResponse response = httpclient.execute(httppost);
        HttpEntity entity = response.getEntity();

        ObjectInputStream in = new ObjectInputStream(entity.getContent());

        Tour resultTour = null;
        try {
            resultTour = (Tour) in.readObject();
        } catch (Exception e) {
            int a = 5;
        }

        in.close();
        return resultTour;
    }

}
