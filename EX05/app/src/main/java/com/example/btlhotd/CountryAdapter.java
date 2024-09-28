package com.example.btlhotd;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter> {
    private List<Country> countries;

    public CountryAdapter(List<Country> countries){
        this.countries = countries;
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_country, parent, false);
        return new CountryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        Country currentItem = countries.get(position);
        holder.imageView.setImageResource(currentItem.getImageResId());
        holder.textView.setText(currentItem.getName());
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    static class CountryViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        CountryViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
        }
    }
}


