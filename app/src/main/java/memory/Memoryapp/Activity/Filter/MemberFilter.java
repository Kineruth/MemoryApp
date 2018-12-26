package memory.Memoryapp.Activity.Filter;

import android.widget.Filter;

import java.util.ArrayList;
import java.util.List;

import memory.Memoryapp.Adapter.UserAdapter;
import memory.Memoryapp.Object.User;

/**
 * This class represents a filter option for finding members.
 */
public class MemberFilter extends Filter {

    private List<User> userList;
    private List<User> filterUserList;
    private UserAdapter adapter;

    /**
     * Parameterized Constructor.
     * @param userList a given user list.
     * @param adapter
     */
    public MemberFilter(List<User> userList, UserAdapter adapter) {
        this.userList = userList;
        this.adapter = adapter;
        this.filterUserList = new ArrayList<>();
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        filterUserList.clear();
        final FilterResults results = new FilterResults();
        for(final User item: userList){
            if(item.getName().toLowerCase().trim().contains(constraint))
                filterUserList.add(item);
        }
        results.values = filterUserList;
        results.count = filterUserList.size();
        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        adapter.setList(filterUserList);
        adapter.notifyDataSetChanged();
    }
}
