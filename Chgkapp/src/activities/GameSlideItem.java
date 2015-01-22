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

package activities;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import fragments.CardBackFragment;
import fragments.CardFrontFragment;
import models.entities.Question;
import ru.chgkapp.R;

/**
 * A fragment representing a single step in a wizard. The fragment shows a dummy title indicating
 * the page number, along with some dummy text.
 *
 * <p>This class is used by the {@link CardFlipActivity} and {@link
 * ScreenSlideActivity} samples.</p>
 */
public class GameSlideItem extends Fragment {
    /**
     * The argument key for the page number this fragment represents.
     */
    public boolean mShowingBack = false;
    private Question question;

    public static final String ARG_PAGE = "page";

    /**
     * The fragment's page number, which is set to the argument value for {@link #ARG_PAGE}.
     */
    private int mPageNumber;

    /**
     * Factory method for this fragment class. Constructs a new fragment for the given page number.
     */
    public static GameSlideItem create(int pageNumber, Question question)
    {
        GameSlideItem fragment = new GameSlideItem();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        args.putSerializable("question", question);
        fragment.setArguments(args);
        return fragment;
    }

    public GameSlideItem() { }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mPageNumber = getArguments().getInt(ARG_PAGE);
        question = (Question) getArguments().getSerializable("question");

        CardFrontFragment card = new CardFrontFragment();
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
        ViewGroup rootView = (ViewGroup) inflater
                .inflate(R.layout.game_slide_page, container, false);

        //mShowingBack = false;
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
        fragment = mShowingBack ? new CardFrontFragment() : new CardBackFragment();

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
}

