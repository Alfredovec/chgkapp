package activities;

import ru.chgkapp.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener
{
    @Override
	protected void onCreate(Bundle savedInstanceState) 
	{
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.mainscreen);
	    
	    Button b = (Button) findViewById(R.id.buttonCHGK);
	    b.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) 
	{
		switch (v.getId())
		{
		case R.id.buttonCHGK:
			Intent intent = new Intent(this, CHGKActivity.class);
			startActivity(intent);
			break;
		}
	}
}
