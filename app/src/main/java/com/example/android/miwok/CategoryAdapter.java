package com.example.android.miwok;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by wwk on 17/4/16.
 */

public class CategoryAdapter extends FragmentPagerAdapter{

    private Context mContext;

    /**
     * Create a new {@link CategoryAdapter} object.
     * @param fm is the fragment manager that will keep each fragment's state in the adapter
     * across swipes.
     */
    public CategoryAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }


    /**
     * Return the {@link Fragment} that should be displayed for the given page number.
     *
     */
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new JapaneseWordsListOneFragment();
        } else if (position == 1) {
            return new JapaneseWordsListTwoFragment();
        } else if (position == 2) {
            return new JapaneseWordsListThreeFragment();
        } else {
            return new JapaneseWordsListFourFragment();
        }
    }

    /**
     * Return the total number of pages.
     */
    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return mContext.getString(R.string.category_listOne);
        } else if (position == 1) {
            return mContext.getString(R.string.category_listTwo);
        } else if (position == 2) {
            return mContext.getString(R.string.category_listThree);
        } else {
            return mContext.getString(R.string.category_listFour);
        }
    }
}
