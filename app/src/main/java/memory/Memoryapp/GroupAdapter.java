package memory.Memoryapp;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.GroupViewHolder> {
    private Context mContext;
    private List<Group> groupList;

    public GroupAdapter(Context mContext, List<Group> groupList) {
        this.mContext = mContext;
        this.groupList = groupList;
    }

    @NonNull
    @Override
    public GroupViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.group_item, null);
        GroupViewHolder holder = new GroupViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull GroupViewHolder groupViewHolder, int i) {
        Group group = groupList.get(i);
        groupViewHolder.groupName.setText(group.name);
        if(!group.image.isEmpty())
            Picasso.get().load(group.image).into(groupViewHolder.groupImage);

    }

    @Override
    public int getItemCount() {
        return groupList.size();
    }

    public class GroupViewHolder extends RecyclerView.ViewHolder {
        TextView groupName;
        CircleImageView groupImage;

        public GroupViewHolder(@NonNull View itemView) {
            super(itemView);
            groupName = itemView.findViewById(R.id.group_name);
            groupImage = itemView.findViewById(R.id.group_profile_image);
        }
    }
}
