package activities;

import ru.chgkapp.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class CHGKActivity extends Activity implements OnClickListener
{
	protected void onCreate(Bundle savedInstanceState) 
	{
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.chgkscreen);
	    
	    Button b = (Button) findViewById(R.id.buttonGetRandomCHGK);
	    b.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) 
	{
		switch (v.getId())
		{
		case R.id.buttonGetRandomCHGK:
			Intent intent = new Intent(this, CHGKPackageActivity.class);
			intent.putExtra("name", "2Л Харькова");
			startActivity(intent);
			break;
		}
	}
}
