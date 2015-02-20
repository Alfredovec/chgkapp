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
 * Created by Sergey on 01.02.2015.
 */
public class RandomPreferenceActivity extends PreferenceActivity implements
        SharedPreferences.OnSharedPreferenceChangeListener
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        PreferenceManager.setDefaultValues(this, R.xml.randompreference, false);
        addPreferencesFromResource(R.xml.randompreference);

        SharedPreferences sp = getPreferenceScreen().getSharedPreferences();
        ListPreference complexityPref = (ListPreference) findPreference("complexity");
        complexityPref
                .setSummary(complexityPref.getEntries()[Integer.parseInt(complexityPref.getValue())]);
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
        if (pref instanceof ListPreference)
        {
           ListPreference complexity = (ListPreference) pref;
           pref.setSummary(complexity.getEntries()[Integer.parseInt(complexity.getValue())]);
        }
        else if (pref instanceof CheckBoxPreference)
        {
            CheckBoxPreference checkBoxPreference = (CheckBoxPreference) pref;
            if (!checkBoxPreference.isChecked())
            {
                ((DatePreference) findPreference("dateFrom")).setMinDefault();
                ((DatePreference) findPreference("dateTo")).setMaxDefault();
            }
        }
        else if (pref instanceof DatePreference)
        {
            DatePreference datePreferenceFrom = (DatePreference) findPreference("dateFrom");
            DatePreference datePreferenceTo = (DatePreference) findPreference("dateTo");

            DatePreference datePreference = (DatePreference) pref;
            if (datePreference.getKey() == datePreferenceFrom.getKey())
                resetTopLimit();
            else if (datePreference.getKey() == datePreferenceTo.getKey())
                resetBottomLimit();

        }
    }

    protected void resetTopLimit()
    {
        DatePreference datePreferenceFrom = (DatePreference) findPreference("dateFrom");
        DatePreference datePreferenceTo = (DatePreference) findPreference("dateTo");
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
        DatePreference datePreferenceFrom = (DatePreference) findPreference("dateFrom");
        DatePreference datePreferenceTo = (DatePreference) findPreference("dateTo");
        datePreferenceFrom.setMaxDate
                (
                        datePreferenceTo
                                .getDate()
                                .getTime()
                                .getTime()
                );
    }
}
