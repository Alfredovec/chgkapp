package activities;


import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

import java.util.Date;

import businesslogic.Context;
import fragments.CardBackFragment;
import fragments.CardFrontFragment;
import models.entities.Tour;
import ru.chgkapp.R;


public class GameActivity extends FragmentActivity
{
    private static int NUM_PAGES;

    private Tour tour;
    private ViewPager mPager;
    private int currentItemNum;
    private GamePagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        currentItemNum = 0;

        Date from = new Date();
        from.setDate(10);
        from.setMonth(10);
        from.setYear(2000);

        Date to = new Date();
        to.setDate(10);
        to.setMonth(10);
        to.setYear(2010);

        Context context = new Context();
        tour = new Tour();
        for (int i = 0; i < 10 && tour.getQuestionsNum() == 0; i++)
            tour = context.getRandomPackageCHGK(from, to, 1);

        NUM_PAGES = tour.getQuestionsNum();

        //Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.game_pager);
        mPagerAdapter = new GamePagerAdapter(getFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        mPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position)
            {
                Fragment fragment = mPagerAdapter.getRegisteredFragment(currentItemNum);
                GameSlideItem item = (GameSlideItem) fragment;
                FragmentTransaction ft = item.getChildFragmentManager().beginTransaction();

                CardFrontFragment fragmentCard = new CardFrontFragment();
                item.mShowingBack = false;
                Bundle args = new Bundle();
                args.putSerializable("question", tour.getQuestions().get(currentItemNum));
                fragmentCard.setArguments(args);

                ft.replace(R.id.answer_question_container, fragmentCard);
                ft.commit();

                currentItemNum = position;

                invalidateOptionsMenu();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.activity_screen_slide, menu);

        menu.findItem(R.id.action_previous).setEnabled(mPager.getCurrentItem() > 0);

        // Add either a "next" or "finish" button to the action bar, depending on which page
        // is currently selected.
        MenuItem item = menu.add(Menu.NONE, R.id.action_next, Menu.NONE,
                (mPager.getCurrentItem() == mPagerAdapter.getCount() - 1)
                        ? R.string.action_finish
                        : R.string.action_next);
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                NavUtils.navigateUpTo(this, new Intent(this, MainActivity.class));
                return true;

            case R.id.action_previous:
                mPager.setCurrentItem(mPager.getCurrentItem() - 1);
                return true;

            case R.id.action_next:
                mPager.setCurrentItem(mPager.getCurrentItem() + 1);
                return true;

            case R.id.action_answer:
                Fragment fragment = mPagerAdapter.getRegisteredFragment(mPager.getCurrentItem());
                GameSlideItem GSItem = (GameSlideItem) fragment;
                GSItem.flipCard();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class GamePagerAdapter extends FragmentStatePagerAdapter
    {
        public GamePagerAdapter(FragmentManager fm)
        {
            super(fm);
        }

        SparseArray<Fragment> registeredFragments = new SparseArray<Fragment>();

        @Override
        public Fragment getItem(int position)
        {
            return GameSlideItem.create(position, tour.getQuestions().get(position));
        }

        @Override
        public int getCount()
        {
            return NUM_PAGES;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position)
        {
            Fragment fragment = (Fragment) super.instantiateItem(container, position);
            registeredFragments.put(position, fragment);
            return fragment;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object)
        {
            registeredFragments.remove(position);
            super.destroyItem(container, position, object);
        }

        public Fragment getRegisteredFragment(int position)
        {
            return registeredFragments.get(position);
        }
    }
}
