/*
 * Copyright 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package activities.slideitems;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import fragments.AnswerFragment;
import fragments.QuestionFragment;
import models.entities.Question;
import ru.chgkapp.R;

public class GameSlideItemCHGK extends Fragment implements View.OnClickListener
{
    /**
     * The argument key for the page number this fragment represents.
     */
    public boolean mShowingBack = false;
    private Question question;
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
    public static GameSlideItemCHGK create(int pageNumber, int questionsNum, Question question)
    {
        GameSlideItemCHGK fragment = new GameSlideItemCHGK();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        args.putInt(QUEST_NUM, questionsNum);
        args.putSerializable("question", question);
        fragment.setArguments(args);
        return fragment;
    }

    public GameSlideItemCHGK() { }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mPageNumber = getArguments().getInt(ARG_PAGE);
        questionsNum = getArguments().getInt(QUEST_NUM);
        question = (Question) getArguments().getSerializable("question");

        QuestionFragment card = new QuestionFragment();
        Bundle args = new Bundle();
        args.putSerializable("question", question);
        card.setArguments(args);

        getChildFragmentManager()
                .beginTransaction()
                .replace(R.id.answer_question_container, card)
                .commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout containing a title and body text.
        rootView = (ViewGroup) inflater
                .inflate(R.layout.game_slide_page_chgk, container, false);

        Button button = (Button) rootView.findViewById(R.id.buttonAnsQuest);
        button.setOnClickListener(this);

        TextView twPrev = (TextView) rootView.findViewById(R.id.textViewPrev);
        if (mPageNumber > 1)
            twPrev.setText(String.valueOf(mPageNumber - 1));
        else twPrev.setVisibility(View.INVISIBLE);

        TextView twCurr = (TextView) rootView.findViewById(R.id.textViewCurr);
        twCurr.setText("Вопрос " + String.valueOf(mPageNumber) + " из " + String.valueOf(questionsNum));

        TextView twNext = (TextView) rootView.findViewById(R.id.textViewNext);
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

    public void flipCard() {
        Fragment fragment;
        fragment = mShowingBack ? new QuestionFragment() : new AnswerFragment();

        Bundle args = new Bundle();
        args.putSerializable("question", question);
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
                .replace(R.id.answer_question_container, new Fragment())
                .replace(R.id.answer_question_container, fragment)

                        // Commit the transaction.
                .commit();
    }

    public void InvalidateButton()
    {
        Button button = (Button) rootView.findViewById(R.id.buttonAnsQuest);
        button.setText(getResources().getString(R.string.to_answer));
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.buttonAnsQuest:
                this.flipCard();
                Button button = (Button) rootView.findViewById(R.id.buttonAnsQuest);
                if (button.getText() == getResources().getString(R.string.to_answer))
                    button.setText(getResources().getString(R.string.to_question));
                else
                    button.setText(getResources().getString(R.string.to_answer));
        }

    }
}

