package memory.Memoryapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class PersonalAdapter extends RecyclerView.Adapter<PersonalAdapter.PersonalViewHolder> {
    private Context mContext;
    private Personal personal;

    public PersonalAdapter(Context mContext, Personal personal) {
        this.mContext = mContext;
        this.personal = personal;
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
        final Personal personal = this.personal;
        personalViewHolder.personalName.setText(personal.getName());
        if(!personal.getImage().isEmpty())
            Picasso.get().load(personal.getImage()).into(personalViewHolder.personalImage);
        personalViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, GroupDiaryActivity.class);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 1;
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
