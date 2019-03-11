package hr.ferit.davorlukic.makeup;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

public class ResultViewHolder extends RecyclerView.ViewHolder {

    private TextView mName;
    private TextView mPrice;
    private TextView mRating;
    private TextView mDescription;
    private SimpleDraweeView mImage;

    public ResultViewHolder(@NonNull View itemView) {
        super(itemView);
        mName = itemView.findViewById(R.id.tvName);
        mPrice = itemView.findViewById(R.id.tvPrice);
        mRating = itemView.findViewById(R.id.tvRating);
        mDescription = itemView.findViewById(R.id.tvDescription);
        mImage = itemView.findViewById(R.id.ivImage);
    }

    public void setName(String name){
        mName.setText(name);
    }

    public void setPrice(String price){
        mPrice.setText(price);
    }

    public void setDescription(String description) {
        mDescription.setText(description);
    }

    public void setRating(String rating) {
        mRating.setText(rating);
    }

    public void setImage(String image){
        mImage.setImageURI(Uri.parse(image));
    }
}
