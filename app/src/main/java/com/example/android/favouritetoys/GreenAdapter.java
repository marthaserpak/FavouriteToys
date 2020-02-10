package com.example.android.favouritetoys;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GreenAdapter extends RecyclerView.Adapter<GreenAdapter.NumberViewHolder> {

    private static final String TAG = GreenAdapter.class.getSimpleName();

    private static int viewHolderCount; //variable hold the total number of VH that r created
    private final ListItemClickListener mOnClickListener;

    private int mNumberItems;

    public interface ListItemClickListener {
        void onListItemClicked(int clickedItemIndex);
    }

    /* Constructor for GreenAdapter that accepts a number of items
     * to display and the specification
     * for the ListItemClickListener.
     *
     * @param numberOfItems Number of items to display in list */
    GreenAdapter(int numberOfItems, ListItemClickListener listener) {
        mNumberItems = numberOfItems;
        mOnClickListener = listener;
        /* When new GreenAdapter is created, set the viewHolderCount to 0; */
        viewHolderCount = 0;
    }

    /* This gets called when each new ViewHolder is created. This happens
     *  when the RecyclerView is laid out. Enough ViewHolders will be created to fill the screen
     *  and allow for scrolling.
     * @param viewGroup The ViewGroup that these ViewHolders are contained within.
     * @param viewType  If your RecyclerView has more than one type of item (which ours doesn't) you
     *                  can use this viewType integer to provide a different layout. See
     *                  {@link android.support.v7.widget.RecyclerView.Adapter#getItemViewType(int)}
     *                  for more details.
     * @return A new NumberViewHolder that holds the View for each list item */
    @NonNull
    @Override
    public NumberViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup,
                                               int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.number_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutIdForListItem, viewGroup,
                false);
        NumberViewHolder viewHolder = new NumberViewHolder(view);

        viewHolder.index.setText("ViewHolder index: " + viewHolderCount);

        int backgroundColorForViewHolder = ColorUtils
                .getViewHolderBackgroundColorFromInstance(context, viewHolderCount);

        viewHolder.itemView.setBackgroundColor(backgroundColorForViewHolder);

        viewHolderCount++;
        Log.d(TAG, "onCreateViewHolder: number of ViewHolders created: " +
                viewHolderCount);

        return viewHolder;
    }

    /* OnBindViewHolder is called by the RecyclerView to display the data at the specified position.
     * In this method, we update the contents of the ViewHolder to display the correct indices
     * in the list for this particular position, using the "position" argument
     * that is conveniently passed into us.
     * @param holder The ViewHolder which should be updated to represent the contents of the item
     *                               at the given position in the data set.
     * @param position The position of the item within the adapter's data set.  */
    @Override
    public void onBindViewHolder(@NonNull GreenAdapter.NumberViewHolder holder, int position) {
        Log.d(TAG, "#" + position);
        holder.bind(position);
    }

    /* This method simply returns the number of items to display. It is used behind the scenes  to help
     * layout  our Views and for animations.
     *
     * @return The number of  items available in our forecast. */
    @Override
    public int getItemCount() {
        return mNumberItems;
    }

    class NumberViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener {

        /* Will display the position in the list, ie 0 through getItemCount() - 1 */
        private TextView listItemNumberView;

        private TextView index;

        NumberViewHolder(View itemView) {
            super(itemView);
            listItemNumberView = itemView.findViewById(R.id.tv_item_number);
            index = itemView.findViewById(R.id.tv_view_holder_instance);

            itemView.setOnClickListener(this);
        }

        private void bind(int listIndex) {
            listItemNumberView.setText(String.valueOf(listIndex));
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClicked(clickedPosition);
        }
    }
}
