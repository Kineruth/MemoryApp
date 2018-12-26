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
import memory.Memoryapp.Activity.PersonalDiary.PersonalDiaryActivity;
import memory.Memoryapp.Holder.PersonalDiaryDataHolder;
import memory.Memoryapp.Object.PersonalDiary;
import memory.Memoryapp.R;

/**
 * This class represent the personal diary of the user.
 */
public class PersonalDiaryAdapter extends RecyclerView.Adapter<PersonalDiaryAdapter.PersonalViewHolder> {
    private Context mContext;
    private List<PersonalDiary> personalDiaryList;

    /**
     * Parameterized Constructor.
     * @param mContext the context.
     * @param personalDiaryList the user's personal diary.
     */
    public PersonalDiaryAdapter(Context mContext, List<PersonalDiary> personalDiaryList) {
        this.mContext = mContext;
        this.personalDiaryList = personalDiaryList;
    }

    /**
     * Creates a new view holder when there are no existing view holders which the RecyclerView can reuse.
     * @param viewGroup a given viewGroup.
     * @param i a given index.
     * @return a PersonalViewHolder that has been created.
     */
    @NonNull
    @Override
    public PersonalViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.group_item, null);
        PersonalViewHolder holder = new PersonalViewHolder(view);
        return holder;
    }

    /**
     * While scrolling down the list, an old view is recycled and reused by binding new data to it.
     * @param personalViewHolder a given personalViewHolder.
     * @param i a given index.
     */
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

    /**
     *
     * @return the amount of the user's personal diaries.
     */
    @Override
    public int getItemCount() {
        return personalDiaryList.size();
    }

    /**
     * An inner class that represents the total look of the user's personal diary list.
     */
    public class PersonalViewHolder extends RecyclerView.ViewHolder{
        TextView personalName;
        CircleImageView personalImage;

        /**
         * Parameterized Constructor.
         * @param itemView the user's personal diary.
         */
        public PersonalViewHolder (@NonNull final View itemView) {
            super(itemView);
            personalName = itemView.findViewById(R.id.group_name);
            personalImage = itemView.findViewById(R.id.group_profile_image);
        }
    }
}
