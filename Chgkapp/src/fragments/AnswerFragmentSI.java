package fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import helpers.Parser;
import models.entities.Question;
import ru.chgkapp.R;

/**
 * Created by Sergey on 20.02.2015.
 */
public class AnswerFragmentSI extends Fragment {

    private Question question;
    final String ATTRIBUTE_NAME_ANSWER = "question";
    final String ATTRIBUTE_NAME_COMMENT = "comment";
    final String ATTRIBUTE_NAME_CHECKED = "checked";

    public AnswerFragmentSI() {
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
        View view = inflater.inflate(R.layout.fragment_si_answer, container, false);

        ArrayList<Question> questionArrayList = Parser.parseSvoyakQuestions(question);

        ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>(
                questionArrayList.size());
        Map<String, Object> m;
        for (int i = 0; i < questionArrayList.size(); i++) {
            m = new HashMap<String, Object>();
            m.put(ATTRIBUTE_NAME_ANSWER, questionArrayList.get(i).getAnswer());
            m.put(ATTRIBUTE_NAME_COMMENT, questionArrayList.get(i).getComments());
            m.put(ATTRIBUTE_NAME_CHECKED, questionArrayList.get(i));
            data.add(m);
        }

        // массив имен атрибутов, из которых будут читаться данные
        String[] from = {ATTRIBUTE_NAME_ANSWER, ATTRIBUTE_NAME_COMMENT, ATTRIBUTE_NAME_CHECKED };
        // массив ID View-компонентов, в которые будут вставлять данные
        int[] to = { R.id.textViewAnswerSI, R.id.textViewCommentsSI, R.id.si_without_checkbox };

        // создаем адаптер
        SimpleAdapter sAdapter = new SimpleAdapter(getActivity(), data, R.layout.si_list_item_answers,
                from, to);

        // определяем список и присваиваем ему адаптер
        ListView lvSimple = (ListView) view.findViewById(R.id.listView_si_answer);
        lvSimple.setAdapter(sAdapter);
        return view;
    }
}
