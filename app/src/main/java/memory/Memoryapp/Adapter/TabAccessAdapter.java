package memory.Memoryapp.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import memory.Memoryapp.Fragment.MemoryFragment;
import memory.Memoryapp.Fragment.RequestFragment;

/**
 * This class represents a tab Access adapter.
 */
public class TabAccessAdapter extends FragmentPagerAdapter {
    /**
     * Parameterized Constructor.
     * @param fm a given fragment.
     */
    public TabAccessAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * Gets the requested fragment : memory or request.
     * @param i a given index.
     * @return the requested fragment.
     */
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

    /**
     *
     * @return the amount of fragments in the view, which is always 2.
     */
    @Override
    public int getCount() {
        return 2;
    }

    /**
     *
     * @param position 0 for memory, 1 for request, otherwise does not exists = null.
     * @return the title of a specific fragment we are on.
     */
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
