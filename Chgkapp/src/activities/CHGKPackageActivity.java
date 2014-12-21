package activities;

import ru.chgkapp.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class CHGKPackageActivity extends Activity 
{
	protected void onCreate(Bundle savedInstanceState) 
	{
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.chgkpackagescreen);
	    
	    TextView tw = (TextView) findViewById(R.id.textViewPackageName);
	    String name = this.getIntent().getStringExtra("name");
	    tw.setText(name);
	}
}
