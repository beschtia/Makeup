package hr.ferit.davorlukic.makeup;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<ResultViewHolder> {

    private List<String> nameList = new ArrayList<>();
    private List<String> priceList = new ArrayList<>();
    private List<String> ratingList = new ArrayList<>();
    private List<String> descriptionList = new ArrayList<>();
    private List<String> imageList = new ArrayList<>();

    @NonNull
    @Override
    public ResultViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View resultView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.result,viewGroup,false);
        return new ResultViewHolder(resultView);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultViewHolder resultViewHolder, int position) {
        resultViewHolder.setName(nameList.get(position));
        resultViewHolder.setPrice(priceList.get(position));
        resultViewHolder.setRating(ratingList.get(position));
        resultViewHolder.setDescription(descriptionList.get(position));
        resultViewHolder.setImage(imageList.get(position));
    }

    @Override
    public int getItemCount() {
        return nameList.size();
    }

    public void addNames(List<String> names){
        this.nameList.clear();
        this.nameList.addAll(names);
        notifyDataSetChanged();
    }

    public void addPrices(List<String> prices){
        this.priceList.clear();
        this.priceList.addAll(prices);
        notifyDataSetChanged();
    }

    public void addRatings(List<String> ratings){
        this.ratingList.clear();
        this.ratingList.addAll(ratings);
        notifyDataSetChanged();
    }

    public void addDescriptions(List<String> descriptions){
        this.descriptionList.clear();
        this.descriptionList.addAll(descriptions);
        notifyDataSetChanged();
    }

    public void addImages(List<String> images){
        this.imageList.clear();
        this.imageList.addAll(images);
        notifyDataSetChanged();
    }
}
