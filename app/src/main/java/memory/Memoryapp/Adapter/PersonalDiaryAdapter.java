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
import memory.Memoryapp.Activity.PersonalDiaryActivity;
import memory.Memoryapp.Holder.PersonalDiaryDataHolder;
import memory.Memoryapp.Object.PersonalDiary;
import memory.Memoryapp.R;

public class PersonalDiaryAdapter extends RecyclerView.Adapter<PersonalDiaryAdapter.PersonalViewHolder> {
    private Context mContext;
    private List<PersonalDiary> personalDiaryList;

    public PersonalDiaryAdapter(Context mContext, List<PersonalDiary> personalDiaryList) {
        this.mContext = mContext;
        this.personalDiaryList = personalDiaryList;
    }

    @NonNull
    @Override
    public PersonalViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.group_item, null);
        PersonalViewHolder holder = new PersonalViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PersonalViewHolder personalViewHolder, int i) {
        final PersonalDiary personalDiary = this.personalDiaryList.get(i);
        personalViewHolder.personalName.setText(personalDiary.getName());
        if(!personalDiary.getImage().isEmpty())
            Picasso.get().load(personalDiary.getImage()).into(personalViewHolder.personalImage);
        personalViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PersonalDiaryDataHolder.getPersonalDiaryDataHolder().getPersonalDiary().setAll(personalDiary);
                Intent intent = new Intent(mContext, PersonalDiaryActivity.class);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return personalDiaryList.size();
    }

    public class PersonalViewHolder extends RecyclerView.ViewHolder{
        TextView personalName;
        CircleImageView personalImage;

        public PersonalViewHolder (@NonNull final View itemView) {
            super(itemView);
            personalName = itemView.findViewById(R.id.group_name);
            personalImage = itemView.findViewById(R.id.group_profile_image);
        }
    }
}
