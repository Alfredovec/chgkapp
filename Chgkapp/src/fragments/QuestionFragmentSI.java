package fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import helpers.Parser;
import models.entities.Question;
import ru.chgkapp.R;

/**
 * Created by Sergey on 20.02.2015.
 */
public class QuestionFragmentSI extends Fragment {

    private Question question;
    final String ATTRIBUTE_NAME_QUESTION = "question";
    final String ATTRIBUTE_NAME_CHECKED = "checked";

    public QuestionFragmentSI() {
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
        View view = inflater.inflate(R.layout.fragment_si_question, container, false);


        ArrayList<Question> questionArrayList = Parser.parseSvoyakQuestions(question);

        ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>(
                questionArrayList.size());
        Map<String, Object> m;
        for (int i = 0; i < questionArrayList.size(); i++) {
            m = new HashMap<String, Object>();
            m.put(ATTRIBUTE_NAME_QUESTION, questionArrayList.get(i).getQuestion());
            m.put(ATTRIBUTE_NAME_CHECKED, questionArrayList.get(i));
            data.add(m);
        }

        // массив имен атрибутов, из которых будут читаться данные
        String[] from = {ATTRIBUTE_NAME_QUESTION, ATTRIBUTE_NAME_CHECKED };
        // массив ID View-компонентов, в которые будут вставлять данные
        int[] to = { R.id.si_without_question, R.id.si_without_checkbox };

        // создаем адаптер
        SimpleAdapter sAdapter = new SimpleAdapter(getActivity(), data, R.layout.si_list_item_without,
                from, to);

        // определяем список и присваиваем ему адаптер
        ListView lvSimple = (ListView) view.findViewById(R.id.listView_si_question);
        lvSimple.setAdapter(sAdapter);

        return view;
    }
}
