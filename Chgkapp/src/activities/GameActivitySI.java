package activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

import activities.slideitems.GameSlideItemCHGK;
import activities.slideitems.GameSlideItemSI;
import fragments.QuestionFragment;
import fragments.QuestionFragmentSI;
import models.entities.Tour;
import models.entities.Tournament;
import ru.chgkapp.R;

/**
 * Created by Sergey on 18.02.2015.
 */
public class GameActivitySI extends FragmentActivity
{
    private static int NUM_PAGES;

    private Tour tour;
    private Tournament tournament;
    private ViewPager mPager;
    private int currentItemNum;
    private GamePagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        currentItemNum = 0;

        Bundle extras = getIntent().getExtras();
        tour = (Tour) extras.getSerializable("tour");
        tournament = (Tournament) extras.getSerializable("tournament");

        NUM_PAGES = tour.getQuestionsNum() + 1;

        //Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.game_pager);
        mPagerAdapter = new GamePagerAdapter(getFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        mPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position)
            {
                if (currentItemNum != 0)
                {
                    Fragment fragment = mPagerAdapter.getRegisteredFragment(currentItemNum);

                    GameSlideItemCHGK item = (GameSlideItemCHGK) fragment;
                    FragmentTransaction ft = item.getChildFragmentManager().beginTransaction();

                    QuestionFragmentSI fragmentCard = new QuestionFragmentSI();
                    item.mShowingBack = false;
                    Bundle args = new Bundle();
                    args.putSerializable("question", tour.getQuestions().get(currentItemNum - 1));
                    fragmentCard.setArguments(args);

                    ft.replace(R.id.answer_question_container_si, fragmentCard);
                    ft.commit();

                    item.InvalidateButton();
                }

                currentItemNum = position % NUM_PAGES;

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
                (currentItemNum == NUM_PAGES - 1)
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
            if (position == 0)
                return TournamentInfoItem.create(tournament);
            else
                return GameSlideItemSI.create(position, NUM_PAGES - 1, tour.getQuestions().get(position - 1));
        }

        @Override
        public int getCount()
        {
            return Integer.MAX_VALUE;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position)
        {
            int virtualPosition = position % NUM_PAGES;
            Fragment fragment = (Fragment) super.instantiateItem(container, virtualPosition);
            registeredFragments.put(virtualPosition, fragment);
            return fragment;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object)
        {
            int virtualPosition = position % NUM_PAGES;
            registeredFragments.remove(virtualPosition);
            super.destroyItem(container, virtualPosition, object);
        }

        public Fragment getRegisteredFragment(int position)
        {
            return registeredFragments.get(position);
        }
    }
}