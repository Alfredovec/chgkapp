package fragments;

/**
 * Created by Sergey on 15.01.2015.
 */

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import activities.GameSlideItem;
import models.entities.Question;
import ru.chgkapp.R;

/**
 * A fragment representing the front of the card.
 */
public class CardFrontFragment extends Fragment {

    private Question question;

    public CardFrontFragment()
    {

    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        question = (Question) getArguments().getSerializable("question");
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

        View view = inflater.inflate(R.layout.questionscreen, null);
        TextView tw = (TextView) view.findViewById(R.id.textView);
        ScrollView sv = (ScrollView) view.findViewById(R.id.scrollView3);

        tw.setText(question.getQuestion());

        return view;
    }
}