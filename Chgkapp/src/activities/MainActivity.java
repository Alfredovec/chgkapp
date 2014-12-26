package activities;

import java.util.ArrayList;
import java.util.Date;

import models.entities.Question;
import models.entities.Tour;

import businesslogic.Context;

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
	    
	    Date from = new Date();
	    from.setDate(10);
	    from.setMonth(10);
	    from.setYear(2000);
	    
	    Date to = new Date();
	    to.setDate(10);
	    to.setMonth(10);
	    to.setYear(2010);
	    
	    Context context = new Context();
	    Tour tour = context.getRandomPackageCHGK(from, to, 1);
	    
	    
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
