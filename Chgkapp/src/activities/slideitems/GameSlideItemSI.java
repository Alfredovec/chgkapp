package activities.slideitems;

import android.app.Fragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.io.Serializable;

import activities.GameActivitySI;
import fragments.AnswerFragment;
import fragments.AnswerFragmentSI;
import fragments.QuestionFragment;
import fragments.QuestionFragmentSI;
import helpers.Parser;
import models.entities.Question;
import models.entities.SvoyakTheme;
import models.entities.Tour;
import ru.chgkapp.R;

/**
 * Created by Sergey on 18.02.2015.
 */
public class GameSlideItemSI extends Fragment implements View.OnClickListener, Parcelable
{
    /**
     * The argument key for the page number this fragment represents.
     */
    public boolean mShowingBack = false;
    public boolean[] whichAnswers;
    public boolean isClassicView = true;
    public int classicViewPageNum = 0;
    public boolean allChecked = false;
    public int questionsCount;

    private SvoyakTheme theme;
    private GameSlideItemSI slideItem;
    private ViewGroup rootView;

    public static final String ARG_PAGE = "page";
    public static final String QUEST_NUM = "questNum";

    /**
     * The fragment's page number, which is set to the argument value for {@link #ARG_PAGE}.
     */
    private int mPageNumber;
    private int questionsNum;

    /**
     * Factory method for this fragment class. Constructs a new fragment for the given page number.
     */
    public static GameSlideItemSI create(int pageNumber, int questionsNum, SvoyakTheme theme)
    {
        GameSlideItemSI fragment = new GameSlideItemSI();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        args.putInt(QUEST_NUM, questionsNum);
        args.putSerializable("theme", theme);
        fragment.setArguments(args);
        return fragment;
    }

    public GameSlideItemSI() { }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mPageNumber = getArguments().getInt(ARG_PAGE);
        questionsNum = getArguments().getInt(QUEST_NUM);
        theme = (SvoyakTheme) getArguments().getSerializable("theme");

        questionsCount = Parser.parseQuestionsNum(theme);

        whichAnswers = new boolean[10];
        whichAnswers[0] = true;

        setFront();

    }

    public void setFront()
    {
        QuestionFragmentSI card = new QuestionFragmentSI();
        Bundle args = new Bundle();
        args.putSerializable("theme", theme);
        //args.putSerializable("slide_item", this);
        args.putParcelable("slide_item", this);
        card.setArguments(args);

        getChildFragmentManager()
                .beginTransaction()
                .replace(R.id.answer_question_container_si, card)
                .commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout containing a title and body text.
        rootView = (ViewGroup) inflater
                .inflate(R.layout.game_slide_page_si, container, false);

        Button button = (Button) rootView.findViewById(R.id.buttonAnsQuestSI);
        button.setOnClickListener(this);

        checkButtonVisibility();

        TextView twPrev = (TextView) rootView.findViewById(R.id.textViewPrevSI);
        if (mPageNumber > 1)
            twPrev.setText(String.valueOf(mPageNumber - 1));
        else twPrev.setVisibility(View.INVISIBLE);

        TextView twCurr = (TextView) rootView.findViewById(R.id.textViewCurrSI);
        twCurr.setText(theme.getThemeName());

        TextView twNext = (TextView) rootView.findViewById(R.id.textViewNextSI);
        if (mPageNumber < questionsNum)
            twNext.setText(String.valueOf(mPageNumber + 1));
        else twNext.setVisibility(View.INVISIBLE);

        return rootView;
    }

    /**
     * Returns the page number represented by this fragment object.
     */
    public int getPageNumber() {
        return mPageNumber;
    }

    public void checkButtonVisibility()
    {
        boolean visible = false;
        for (int i = 0; i < whichAnswers.length; i++)
            visible |= whichAnswers[i];
        if (visible)
            ((Button) rootView.findViewById(R.id.buttonAnsQuestSI)).setVisibility(View.VISIBLE);
        else
            ((Button) rootView.findViewById(R.id.buttonAnsQuestSI)).setVisibility(View.INVISIBLE);



    }
    public void flipCard() {
        Fragment fragment;
        fragment = mShowingBack ? new QuestionFragmentSI() : new AnswerFragmentSI();

        Bundle args = new Bundle();
        args.putSerializable("theme", theme);
        //args.putSerializable("slide_item", this);
        args.putParcelable("slide_item", this);
        fragment.setArguments(args);

        // Flip to the back.

        mShowingBack = !mShowingBack;

        // Create and commit a new fragment transaction that adds the fragment for the back of
        // the card, uses custom animations, and is part of the fragment manager's back stack.

        getChildFragmentManager()
                .beginTransaction()

                        // Replace the default fragment animations with animator resources representing
                        // rotations when switching to the back of the card, as well as animator
                        // resources representing rotations when flipping back to the front (e.g. when
                        // the system Back button is pressed).
                .setCustomAnimations(
                        R.animator.card_flip_right_in, R.animator.card_flip_right_out,
                        R.animator.card_flip_left_in, R.animator.card_flip_left_out)

                        // Replace any fragments currently in the container view with a fragment
                        // representing the next page (indicated by the just-incremented currentPage
                        // variable).
                .replace(R.id.answer_question_container_si, new Fragment())
                .replace(R.id.answer_question_container_si, fragment)

                        // Commit the transaction.
                .commit();
    }

    public void InvalidateButton()
    {
        Button button = (Button) rootView.findViewById(R.id.buttonAnsQuestSI);
        button.setText(getResources().getString(R.string.to_answers));
    }

    @Override
    public void onClick(View v)
    {
        try {
            switch (v.getId())
            {
                case R.id.buttonAnsQuestSI:
                        this.flipCard();
                        Button button = (Button) rootView.findViewById(R.id.buttonAnsQuestSI);
                        if (button.getText() == getResources().getString(R.string.to_answers)) {
                            button.setText(getResources().getString(R.string.to_questions));
                        }
                        else
                        {
                            if (isClassicView && classicViewPageNum < 6)
                                whichAnswers[++classicViewPageNum] = true;
                            button.setText(getResources().getString(R.string.to_answers));
                        }
                    break;
            }
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeBooleanArray(whichAnswers);
        dest.writeValue(isClassicView);
        dest.writeInt(classicViewPageNum);
    }
}