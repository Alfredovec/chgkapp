package activities.preferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

import helpers.DatePreference;
import ru.chgkapp.R;

/**
 * Created by Sergey on 28.02.2015.
 */
public class RandomSIPreferenceActivity extends PreferenceActivity implements
        SharedPreferences.OnSharedPreferenceChangeListener
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        PreferenceManager.setDefaultValues(this, R.xml.randompreferencesi, false);
        addPreferencesFromResource(R.xml.randompreferencesi);
    }

    @Override
    protected void onResume() {
        super.onResume();

        resetTopLimit();
        resetBottomLimit();

        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key)
    {
        Preference pref = findPreference(key);
        if (pref instanceof CheckBoxPreference)
        {
            CheckBoxPreference checkBoxPreference = (CheckBoxPreference) pref;
            if (!checkBoxPreference.isChecked())
            {
                ((DatePreference) findPreference("dateFromSI")).setMinDefault();
                ((DatePreference) findPreference("dateToSI")).setMaxDefault();
            }
        }
        else if (pref instanceof DatePreference)
        {
            DatePreference datePreferenceFrom = (DatePreference) findPreference("dateFromSI");
            DatePreference datePreferenceTo = (DatePreference) findPreference("dateToSI");

            DatePreference datePreference = (DatePreference) pref;
            if (datePreference.getKey() == datePreferenceFrom.getKey())
                resetTopLimit();
            else if (datePreference.getKey() == datePreferenceTo.getKey())
                resetBottomLimit();

        }
    }

    protected void resetTopLimit()
    {
        DatePreference datePreferenceFrom = (DatePreference) findPreference("dateFromSI");
        DatePreference datePreferenceTo = (DatePreference) findPreference("dateToSI");
        datePreferenceTo.setMinDate
                (
                        datePreferenceFrom
                                .getDate()
                                .getTime()
                                .getTime()
                );
    }

    protected void resetBottomLimit()
    {
        DatePreference datePreferenceFrom = (DatePreference) findPreference("dateFromSI");
        DatePreference datePreferenceTo = (DatePreference) findPreference("dateToSI");
        datePreferenceFrom.setMaxDate
                (
                        datePreferenceTo
                                .getDate()
                                .getTime()
                                .getTime()
                );
    }
}
