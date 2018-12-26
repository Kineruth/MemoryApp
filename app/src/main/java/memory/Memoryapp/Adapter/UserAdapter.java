package memory.Memoryapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import memory.Memoryapp.Activity.Filter.MemberFilter;
import memory.Memoryapp.Activity.GroupDiary.ProfileActivity;
import memory.Memoryapp.Holder.ProfileDataHolder;
import memory.Memoryapp.Object.User;
import memory.Memoryapp.R;

/**
 *This class represents a user adapter.
 */
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private Context mContext;
    private List<User> userList;
    private List<User> filterUserList;
    private MemberFilter filter;

    /**
     * Parameterized Constructor.
     * @param mContext a given context.
     * @param groupDiaryList a given list of a group diary's users.
     */
    public UserAdapter(Context mContext, List<User> groupDiaryList) {
        this.mContext = mContext;
        this.userList = groupDiaryList;
        this.filterUserList = groupDiaryList;
        filter = new MemberFilter(this.userList, this);
    }

    /**
     *  The creation of the holder.
     * @param viewGroup a given viewGroup
     * @param i a given index.
     * @return a userViewHolder.
     */
    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.group_item, null);
        UserViewHolder holder = new UserViewHolder(view);
        return holder;
    }

    /**
     * While scrolling down the list, an old view is recycled and reused by binding new data to it.
     * @param userViewHolder
     * @param i a given index.
     */
    @Override
    public void onBindViewHolder(@NonNull UserViewHolder userViewHolder, int i) {
        final User user = userList.get(i);
        userViewHolder.userName.setText(user.getName());
        if(!user.getImage().isEmpty())
            Picasso.get().load(user.getImage()).into(userViewHolder.userImage);
        userViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProfileDataHolder.getUserDataHolder().getUser().setAll(user);
                Intent intent = new Intent(mContext, ProfileActivity.class);
                mContext.startActivity(intent);
            }
        });
    }

    /**
     * Sets the filterUserList.
     * @param list a given users list.
     */
    public void setList(List<User> list){
        this.filterUserList = list;
    }

    /**
     * Filters a users list that was given in a string format.
     * @param text a given users list.
     */
    public void filterList(String text){
        filter.filter(text);
    }

    /**
     *
     * @return the users list size after it was filtered.
     */
    @Override
    public int getItemCount() {
        return filterUserList.size();
    }

    /**
     * An inner class that represents the total look of the users list.
     */
    public class UserViewHolder extends RecyclerView.ViewHolder{

        TextView userName;
        CircleImageView userImage;

        /**
         * Parameterized Constructor.
         * @param itemView the user's view.
         */
        public UserViewHolder(@NonNull final View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.group_name);
            userImage = itemView.findViewById(R.id.group_profile_image);
        }
    }
}
