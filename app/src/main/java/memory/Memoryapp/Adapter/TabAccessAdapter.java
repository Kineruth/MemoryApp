package memory.Memoryapp.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import memory.Memoryapp.Fragment.MemoryFragment;
import memory.Memoryapp.Fragment.RequestFragment;

public class TabAccessAdapter extends FragmentPagerAdapter {

    public TabAccessAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                MemoryFragment memoryFragment = new MemoryFragment();
                return memoryFragment;
            case 1:
                RequestFragment requestFragment = new RequestFragment();
                return requestFragment;
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
        switch (position){
            case 0:
                return "Memory";
            case 1:
                return "Request";
            default:
                return null;
        }
    }
}
