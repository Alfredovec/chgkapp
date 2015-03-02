package helpers.view_adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.List;
import java.util.Map;

import activities.GameActivitySI;
import activities.slideitems.GameSlideItemSI;
import ru.chgkapp.R;

/**
 * Created by Sergey on 28.02.2015.
 */
public class SIAdapterQuestionsWithout extends SimpleAdapter
{
    private GameSlideItemSI slideItem;

    public SIAdapterQuestionsWithout(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to, GameSlideItemSI slideItem) {
        super(context, data, resource, from, to);
        this.slideItem = slideItem;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        View v = super.getView(position, convertView, parent);
        CheckBox cb = (CheckBox) v.findViewById(R.id.si_without_checkbox);
        cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slideItem.whichAnswers[position] = !slideItem.whichAnswers[position];
                slideItem.checkButtonVisibility();
                slideItem.isClassicView = false;
            }
        });
        return v;
    }
}
