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
import memory.Memoryapp.Activity.ProfileActivity;
import memory.Memoryapp.Holder.ProfileDataHolder;
import memory.Memoryapp.Object.User;
import memory.Memoryapp.R;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private Context mContext;
    private List<User> userList;

    public UserAdapter(Context mContext, List<User> groupDiaryList) {
        this.mContext = mContext;
        this.userList = groupDiaryList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.group_item, null);
        UserViewHolder holder = new UserViewHolder(view);
        return holder;
    }

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

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{

        TextView userName;
        CircleImageView userImage;

        public UserViewHolder(@NonNull final View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.group_name);
            userImage = itemView.findViewById(R.id.group_profile_image);
        }
    }
}
