package memory.Memoryapp;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabAccessAdapter extends FragmentPagerAdapter {

    public TabAccessAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i)
        {
            case 0:
                PersonalDiaryFragment personalDiaryFragment = new PersonalDiaryFragment();
                return personalDiaryFragment;
            case 1:
                GroupDiaryFragment groupDiaryFragment = new GroupDiaryFragment();
                return groupDiaryFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position)
        {
            case 0:
                return "Personal Diary";
            case 1:
                return "Group Diary";
            default:
                return null;
        }
    }
}
