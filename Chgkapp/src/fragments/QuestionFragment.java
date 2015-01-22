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

import models.entities.Question;
import ru.chgkapp.R;

/**
 * A fragment representing the front of the card.
 */
public class QuestionFragment extends Fragment {

    private Question question;

    public QuestionFragment()
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

        View view = inflater.inflate(R.layout.question, null);
        TextView tw = (TextView) view.findViewById(R.id.textViewQuestion);
        ScrollView sv = (ScrollView) view.findViewById(R.id.scrollView3);

        tw.setText(question.getQuestion());

        return view;
    }
}