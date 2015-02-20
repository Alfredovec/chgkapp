package businesslogic;

import android.os.AsyncTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.SerializableEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import models.entities.Tournament;

/**
 * Created by Sergey on 30.01.2015.
 */
public class ContextTournament {
    Tournament resultTournament;
    String tourName;

    public Tournament get(String tourName) throws IOException, ClassNotFoundException {
        this.tourName = tourName;

        HttpClient httpclient = new DefaultHttpClient();

        HttpPost httppost = new HttpPost("http://kharkiv-trainss.rhcloud.com/chgkapp/");
        //HttpPost httppost = new HttpPost("http://178.150.137.228:8080/chgkapp/");

        httppost.setEntity(new SerializableEntity(tourName, false));
        httppost.setHeader("type", "getTournamentByTourName");

        HttpResponse response = httpclient.execute(httppost);

        HttpEntity entity = response.getEntity();

        ObjectInputStream in = new ObjectInputStream(entity.getContent());
        resultTournament = (Tournament) in.readObject();

        in.close();

        return resultTournament;
    }
}