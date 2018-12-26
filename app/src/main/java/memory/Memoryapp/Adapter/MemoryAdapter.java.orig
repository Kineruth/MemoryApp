package memory.Memoryapp.Adapter;

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
import memory.Memoryapp.Object.Memory;
import memory.Memoryapp.Object.User;
import memory.Memoryapp.R;

/**
 * This class represents a memory adapter.
 */
public class MemoryAdapter extends RecyclerView.Adapter<MemoryAdapter.MemoryViewHolder>{
    private Context mContext;
    private DatabaseReference mData;
    private List<Memory> memoryList;

    /**
     * Parameterized Constructor.
     * @param mContext the context.
     * @param memoryList the memories list.
     */
    public MemoryAdapter(Context mContext, List<Memory> memoryList) {
        this.mContext = mContext;
        this.memoryList = memoryList;
<<<<<<< HEAD
        initFireBase();
    }

    /**
     *
     */
    private void initFireBase(){
        mData = FirebaseDatabase.getInstance().getReference();
=======
>>>>>>> master
    }

    /**
     * Creates a new view holder when there are no existing view holders which the RecyclerView can reuse.
     * @param viewGroup a given viewGroup.
     * @param i agiven index.
     * @return the memory holder that was created.
     */
    @NonNull
    @Override
    public MemoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.memory_item, null);
        MemoryAdapter.MemoryViewHolder holder = new MemoryAdapter.MemoryViewHolder(view);
        return holder;
    }

    /**
     * While scrolling down the list, an old view is recycled and reused by binding new data to it.
     * @param memoryViewHolder
     * @param i a given index.
     */
    @Override
    public void onBindViewHolder(@NonNull final MemoryViewHolder memoryViewHolder, int i) {
        Memory memory = memoryList.get(i);
<<<<<<< HEAD
        mData.child("Users").child(memory.getUserID()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                memoryViewHolder.userName.setText(user.getImage());
                if (!user.getImage().isEmpty())
                    Picasso.get().load(user.getImage()).into(memoryViewHolder.profileImage);
            }

            /**
             * Catches all the database errors .
             * @param databaseError an error that has occurred.
             */
=======
        memoryViewHolder.textView.setText(memory.getMemoryName());
        Picasso.get().load(memory.getImage()).into(memoryViewHolder.imageView);
        memoryViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
>>>>>>> master
            @Override
            public void onClick(View v) {

            }
        });
    }

    /**
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return 0;
    }

    public class MemoryViewHolder extends RecyclerView.ViewHolder {
       private ImageView imageView;
       private TextView textView;

        public MemoryViewHolder(@NonNull View itemView) {
          super(itemView);
          imageView = itemView.findViewById(R.id.memory_img_id);
          textView = itemView.findViewById(R.id.memory_title_id);
        }
    }
}
