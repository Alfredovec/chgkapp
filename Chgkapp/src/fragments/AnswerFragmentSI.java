package fragments;

import android.app.Fragment;
import android.opengl.Visibility;
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

import activities.GameActivitySI;
import activities.slideitems.GameSlideItemSI;
import helpers.Parser;
import helpers.view_adapters.SIAdapterAnswers;
import models.entities.Question;
import models.entities.SvoyakTheme;
import ru.chgkapp.R;

/**
 * Created by Sergey on 20.02.2015.
 */
public class AnswerFragmentSI extends Fragment {

    private SvoyakTheme theme;
    private GameSlideItemSI slideItem;
    final String ATTRIBUTE_NAME_ANSWER = "question";
    final String ATTRIBUTE_NAME_COMMENT = "comment";

    public AnswerFragmentSI() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        theme = (SvoyakTheme) getArguments().getSerializable("theme");
        slideItem = (GameSlideItemSI) getArguments().getParcelable("slide_item");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_si_answer, container, false);

        ArrayList<Question> questionArrayList = Parser.parseSvoyakQuestions(theme);

        ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>(
                questionArrayList.size());
        Map<String, Object> m;
        for (int i = 0; i < questionArrayList.size(); i++) {
            m = new HashMap<String, Object>();
            m.put(ATTRIBUTE_NAME_ANSWER, questionArrayList.get(i).getAnswer());
            m.put(ATTRIBUTE_NAME_COMMENT, questionArrayList.get(i).getComments());
            data.add(m);
    }

        // массив имен атрибутов, из которых будут читаться данные
        String[] from = {ATTRIBUTE_NAME_ANSWER, ATTRIBUTE_NAME_COMMENT };
        // массив ID View-компонентов, в которые будут вставлять данные
        int[] to = { R.id.textViewAnswerSI, R.id.textViewCommentsSI };

        // создаем адаптер
        SIAdapterAnswers sAdapter = new SIAdapterAnswers(getActivity(), data, R.layout.si_list_item_answers,
                from, to, slideItem);

        // определяем список и присваиваем ему адаптер
        ListView lvSimple = (ListView) view.findViewById(R.id.listView_si_answer);
        lvSimple.setAdapter(sAdapter);

//        for (int i = 0; i < lvSimple.getAdapter().getCount(); i++)
//        {
//            HashMap<String, Object> o = (HashMap<String, Object>) lvSimple.getAdapter().getItem(i);
//            View v = lvSimple.getAdapter().getView(i, null, lvSimple);
//            int vis = (String)o.get(ATTRIBUTE_NAME_COMMENT) == null || (String)o.get(ATTRIBUTE_NAME_COMMENT) == ""
//                    ? View.GONE
//                    : View.VISIBLE;
//            ((TextView)v.findViewById(R.id.textViewCommentsSI)).setText("sdsfsd");
//            CharSequence a = ((TextView)v.findViewById(R.id.textViewAnswerSI)).getText();
//            int gg = 0;
//        }

        return view;
    }
}
