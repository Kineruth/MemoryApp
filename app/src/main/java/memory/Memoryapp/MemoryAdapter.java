package memory.Memoryapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import de.hdodenhof.circleimageview.CircleImageView;
import memory.Memoryapp.DatabaseAPI.ViewPagerAdapter;

public class MemoryAdapter extends RecyclerView.Adapter<MemoryAdapter.MemoryViewHolder>{
    private Context mContext;
    private DatabaseReference mData;
    private List<Memory> memoryList;

    public MemoryAdapter(Context mContext, List<Memory> memoryList) {
        this.mContext = mContext;
        this.memoryList = memoryList;
        initFireBase();
    }

    private void initFireBase(){
        mData = FirebaseDatabase.getInstance().getReference();
    }

    @NonNull
    @Override
    public MemoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.memory_item, null);
        MemoryAdapter.MemoryViewHolder holder = new MemoryAdapter.MemoryViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MemoryViewHolder memoryViewHolder, int i) {
        Memory memory = memoryList.get(i);
        mData.child("Users").child(memory.getUserID()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                memoryViewHolder.userName.setText(user.name);
                if (!user.image.isEmpty())
                    Picasso.get().load(user.image).into(memoryViewHolder.profileImage);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        memoryViewHolder.memoryName.setText(memory.getMemoryName());
        memoryViewHolder.memoryDesciption.setText(memory.getDescription());
        ViewPagerAdapter adapter = new ViewPagerAdapter(this.mContext, memory.getImages());
        memoryViewHolder.memoryImages.setAdapter(adapter);
        Date currentDate = new Date();
        long diffInMillies = currentDate.getTime() - memory.getCreationTime().getTime();
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        memoryViewHolder.memoryTimePosted.setText(diff + " DAYS AGO");
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MemoryViewHolder extends RecyclerView.ViewHolder {
        CircleImageView profileImage;
        TextView userName;
        TextView memoryName;
        ViewPager memoryImages;
        TextView memoryDesciption;
        TextView memoryTimePosted;

        public MemoryViewHolder(@NonNull View itemView) {
            super(itemView);
            profileImage = itemView.findViewById(R.id.profile_iv);
            userName = itemView.findViewById(R.id.username_tv);
            memoryName = itemView.findViewById(R.id.memory_name_tv);
            memoryImages = itemView.findViewById(R.id.memory_images_vp);
            memoryDesciption = itemView.findViewById(R.id.memory_description_tv);
            memoryTimePosted = itemView.findViewById(R.id.memory_time_posted_tv);
        }
    }
}
