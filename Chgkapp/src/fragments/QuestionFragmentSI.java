package fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import activities.GameActivitySI;
import activities.slideitems.GameSlideItemSI;
import helpers.Parser;
import helpers.view_adapters.SIAdapterQuestionsWithout;
import models.entities.Question;
import models.entities.SvoyakTheme;
import ru.chgkapp.R;

/**
 * Created by Sergey on 20.02.2015.
 */
public class QuestionFragmentSI extends Fragment {

    private SvoyakTheme theme;
    private GameSlideItemSI slideItem;
    final String ATTRIBUTE_NAME_QUESTION = "question";
    final String ATTRIBUTE_NAME_CHECKED = "checked";

    public QuestionFragmentSI() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        Bundle bundle = this.getArguments();
        theme = (SvoyakTheme) this.getArguments().getSerializable("theme");
        slideItem = (GameSlideItemSI) this.getArguments().getParcelable("slide_item");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        try {
            View view = inflater.inflate(R.layout.fragment_si_question, container, false);

            ArrayList<Question> questionArrayList = null;

            questionArrayList = Parser.parseSvoyakQuestions(theme);

            ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>(
                    questionArrayList.size());
            Map<String, Object> m;
            for (int i = 0; i < questionArrayList.size(); i++) {
                m = new HashMap<String, Object>();
                m.put(ATTRIBUTE_NAME_QUESTION, questionArrayList.get(i).getQuestion());
                boolean value = slideItem.whichAnswers[i];
                m.put(ATTRIBUTE_NAME_CHECKED, value);
                data.add(m);
            }

            CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkBoxAll);
            checkBox.setChecked(slideItem.allChecked);
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    slideItem.isClassicView = false;
                    slideItem.allChecked = !slideItem.allChecked;
                    for (int i = 0; i < slideItem.questionsCount; i++)
                        slideItem.whichAnswers[i] = slideItem.allChecked;
                    slideItem.checkButtonVisibility();
                    slideItem.setFront();
                }
            });

            // массив имен атрибутов, из которых будут читаться данные
            String[] from = {ATTRIBUTE_NAME_QUESTION, ATTRIBUTE_NAME_CHECKED };
            // массив ID View-компонентов, в которые будут вставлять данные
            int[] to = { R.id.si_without_question, R.id.si_without_checkbox };

            // создаем адаптер
            SIAdapterQuestionsWithout sAdapter = new SIAdapterQuestionsWithout(getActivity(), data, R.layout.si_list_item_without,
                    from, to, slideItem);

            // определяем список и присваиваем ему адаптер
            ListView lvSimple = (ListView) view.findViewById(R.id.listView_si_question);
            lvSimple.setAdapter(sAdapter);

            return view;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
