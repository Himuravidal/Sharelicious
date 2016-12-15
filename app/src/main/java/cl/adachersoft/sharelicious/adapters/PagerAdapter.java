package cl.adachersoft.sharelicious.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import cl.adachersoft.sharelicious.R;
import cl.adachersoft.sharelicious.views.MyDeliciousFragment;
import cl.adachersoft.sharelicious.views.ShareliciousFragment;

/**
 * Created by cristian on 12-12-2016.
 */

public class PagerAdapter extends FragmentPagerAdapter {

    private Context context;

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public PagerAdapter(FragmentManager fm, Context contex) {
        super(fm);
        this.context = contex;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return ShareliciousFragment.newInstance();
            case 1:
                return MyDeliciousFragment.newInstance();
            default:
                return ShareliciousFragment.newInstance();

        }

    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return context.getString(R.string.Sharelicious_Title);
            case 1:
                return context.getString(R.string.MyDelicious_Title);

            default:
                return context.getString(R.string.Sharelicious_Title);
        }
    }

}


