package helpers.view_adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SimpleAdapter;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import activities.GameActivitySI;
import activities.slideitems.GameSlideItemSI;
import ru.chgkapp.R;

/**
 * Created by Sergey on 27.02.2015.
 */
public class SIAdapterAnswers extends SimpleAdapter
{
    private GameSlideItemSI slideItem;

    public SIAdapterAnswers(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to, GameSlideItemSI slideItem) {
        super(context, data, resource, from, to);
        this.slideItem = slideItem;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = super.getView(position, convertView, parent);
        TextView tw = (TextView) v.findViewById(R.id.textViewCommentsSI);
        TextView twAns = (TextView) v.findViewById(R.id.textViewAnswerSI);
        if (tw.getText().length() == 0)
            tw.setVisibility(View.GONE);
        else
        {
            tw.setVisibility(View.VISIBLE);
            String comment = tw.getText().subSequence(3, tw.getText().length()-1).toString();
            tw.setText("Комментарий: " + comment);
        }
        if (slideItem.whichAnswers[position] == true) {
            twAns.setVisibility(View.VISIBLE);
        }
        else {
            tw.setVisibility(View.GONE);
            twAns.setVisibility(View.GONE);
        }
        return v;
    }
}
