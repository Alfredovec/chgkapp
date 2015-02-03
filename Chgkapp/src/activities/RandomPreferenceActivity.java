package activities;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

import ru.chgkapp.R;

/**
 * Created by Sergey on 01.02.2015.
 */
public class RandomPreferenceActivity extends PreferenceActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        PreferenceManager.setDefaultValues(this, R.xml.randompreference, false);
        addPreferencesFromResource(R.xml.randompreference);
    }
}
