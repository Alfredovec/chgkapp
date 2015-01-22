package fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import models.entities.Question;
import ru.chgkapp.R;

/**
 * Created by Sergey on 15.01.2015.
 */
public class AnswerFragment extends Fragment {

    private Question question;

    public AnswerFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        question = (Question) getArguments().getSerializable("question");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.answer, container, false);

        StyleSpan bold = new StyleSpan(android.graphics.Typeface.BOLD);

        SpannableStringBuilder sb = new SpannableStringBuilder("Ответ: " + question.getAnswer());
        sb.setSpan(bold, 0, 6, Spannable.SPAN_INCLUSIVE_INCLUSIVE);

        ((TextView) view.findViewById(R.id.textViewAnswer)).setText(sb);

        if (question.getAnswer() != null && question.getAnswer().length() != 0)
        {
            sb = new SpannableStringBuilder("Автор(ы): " + question.getAuthors());
            sb.setSpan(bold, 0, 9, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            ((TextView) view.findViewById(R.id.textViewAuthors)).setText(sb);
        }
        else
            ((TextView) view.findViewById(R.id.textViewAuthors)).setHeight(0);

        if (question.getComments() != null && question.getComments().length() != 0)
        {
            sb = new SpannableStringBuilder("Комментарий: " + question.getComments());
            sb.setSpan(bold, 0, 12, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            ((TextView) view.findViewById(R.id.textViewComment)).setText(sb);
        }
        else
            ((TextView) view.findViewById(R.id.textViewComment)).setHeight(0);

        if (question.getPassCriteria() != null && question.getPassCriteria().length() != 0)
        {
            sb = new SpannableStringBuilder("Зачёт: " + question.getPassCriteria());
            sb.setSpan(bold, 0, 6, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            ((TextView) view.findViewById(R.id.textViewPass)).setText(sb);
        }
        else
            ((TextView) view.findViewById(R.id.textViewPass)).setHeight(0);

        if (question.getSources() != null && question.getSources().length() != 0)
        {
            sb = new SpannableStringBuilder("Источник(и): " + question.getSources());
            sb.setSpan(bold, 0, 12, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            ((TextView) view.findViewById(R.id.textViewSource)).setText(sb);
        }
        else
            ((TextView) view.findViewById(R.id.textViewSource)).setHeight(0);


        return view;
    }
}
