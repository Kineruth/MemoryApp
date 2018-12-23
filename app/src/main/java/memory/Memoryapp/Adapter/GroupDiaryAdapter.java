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
import memory.Memoryapp.Holder.GroupDiaryDataHolder;
import memory.Memoryapp.Activity.GroupDiaryActivity;
import memory.Memoryapp.Object.GroupDiary;
import memory.Memoryapp.R;

public class GroupDiaryAdapter extends RecyclerView.Adapter<GroupDiaryAdapter.GroupViewHolder> {
    private Context mContext;
    private List<GroupDiary> groupDiaryList;

    public GroupDiaryAdapter(Context mContext, List<GroupDiary> groupDiaryList) {
        this.mContext = mContext;
        this.groupDiaryList = groupDiaryList;
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
        final GroupDiary groupDiary = groupDiaryList.get(i);
        groupViewHolder.groupName.setText(groupDiary.getName());
        if(!groupDiary.getImage().isEmpty())
            Picasso.get().load(groupDiary.getImage()).into(groupViewHolder.groupImage);
        groupViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GroupDiaryDataHolder.getGroupDataHolder().getGroupDiary().setAll(groupDiary);
                Intent intent = new Intent(mContext, GroupDiaryActivity.class);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return groupDiaryList.size();
    }

    public class GroupViewHolder extends RecyclerView.ViewHolder {
        TextView groupName;
        CircleImageView groupImage;

        public GroupViewHolder(@NonNull final View itemView) {
            super(itemView);
            groupName = itemView.findViewById(R.id.group_name);
            groupImage = itemView.findViewById(R.id.group_profile_image);
        }
    }
}
