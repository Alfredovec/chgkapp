/*
 * Copyright 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package activities;

import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import activities.preferences.RandomCHGKPreferenceActivity;
import activities.preferences.RandomSIPreferenceActivity;
import helpers.HelpMethods;
import helpers.tasks.DownloadRandomCHGKTask;
import helpers.tasks.DownloadRandomSITask;
import ru.chgkapp.R;

/**
 * The launchpad activity for this sample project. This activity launches other activities that
 * demonstrate implementations of common animations.
 */
public class MainActivity extends ListActivity {

    /**
     * This class describes an individual sample (the sample title, and the activity class that
     * demonstrates this sample).
     */
    private class Sample {
        private CharSequence title;
        private Class<? extends Activity> activityClass;

        public Sample(int titleResId, Class<? extends Activity> activityClass) {
            this.activityClass = activityClass;
            this.title = getResources().getString(titleResId);
        }

        @Override
        public String toString() {
            return title.toString();
        }
    }

    /**
     * The collection of all samples in the app. This gets instantiated in {@link
     * #onCreate(android.os.Bundle)} because the {@link activities.MainActivity.Sample} constructor needs access to {@link
     * android.content.res.Resources}.
     */
    private static Sample[] mSamples;
    ProgressDialog progressDialog;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instantiate the list of samples.
        mSamples = new Sample[]
        {
                new Sample(R.string.title_random_chgk, GameActivityCHGK.class),
                new Sample(R.string.title_random_si, GameActivitySI.class)
        };

        ArrayAdapter<Sample> adapter = new ArrayAdapter<Sample>(this,
                R.layout.list_item_menu,
                android.R.id.text1,
                mSamples)
        {
            @Override
            public View getView(final int position, View convertView, ViewGroup parent)
            {
                View view = super.getView(position, convertView, parent);
                ImageButton ib = (ImageButton) view.findViewById(R.id.preference_button);
                ib.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = null;
                        switch (position) {
                            case 0:
                                intent = new Intent(getApplicationContext(), RandomCHGKPreferenceActivity.class);
                                break;
                            case 1:
                                intent = new Intent(getApplicationContext(), RandomSIPreferenceActivity.class);
                                break;
                        }
                        startActivity(intent);
                    }
                });
                ib.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        return v.onTouchEvent(event);
                    }
                });
                return view;
            }
        };

        setListAdapter(adapter);

    }

    @Override
    protected void onListItemClick(ListView listView, View view, int position, long id)
    {
        try {
            if (HelpMethods.isOnline(this))
            {
                switch (position) {
                    case 0:
                        DownloadRandomCHGKTask taskCHGK = new DownloadRandomCHGKTask(this, GameActivityCHGK.class);
                        taskCHGK.execute();
                        break;
                    case 1:
                        DownloadRandomSITask taskSI = new DownloadRandomSITask(this, GameActivitySI.class);
                        taskSI.execute();

                }
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Проверьте соединение с сетью", Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }

}
