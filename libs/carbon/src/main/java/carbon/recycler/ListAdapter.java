package carbon.recycler;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import carbon.widget.AutoCompleteEditText;

public abstract class ListAdapter<VH extends RecyclerView.ViewHolder, I> extends Adapter<VH, I> implements AutoCompleteEditText.AutoCompleteDataProvider<I> {
    private carbon.widget.RecyclerView.OnItemClickedListener<I> onItemClickedListener;
    private Map<Class<? extends I>, carbon.widget.RecyclerView.OnItemClickedListener<? extends I>> onItemClickedListeners = new HashMap<>();
    private boolean diff = true;
    private DiffListCallback<I> diffCallback;

    public ListAdapter() {
        items = new ArrayList<>();
    }

    public ListAdapter(List<I> items) {
        this.items = items;
    }

    protected List<I> items;

    public I getItem(int position) {
        return items.get(position);
    }

    @Override
    public String[] getItemWords(int position) {
        return new String[]{getItem(position).toString()};
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setDiffCallback(DiffListCallback<I> diffCallback) {
        this.diffCallback = diffCallback;
    }

    public void setItems(@NonNull List<I> items) {
        if (!diff) {
            this.items = items;
            return;
        }
        if (diffCallback == null)
            diffCallback = new DiffListCallback<>();
        diffCallback.setLists(this.items, items);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);
        this.items = items;
        diffResult.dispatchUpdatesTo(this);
    }

    public List<I> getItems() {
        return items;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setOnItemClickedListener(carbon.widget.RecyclerView.OnItemClickedListener<I> onItemClickedListener) {
        this.onItemClickedListener = onItemClickedListener;
    }

    public void setOnItemClickedListener(Class<? extends I> type, carbon.widget.RecyclerView.OnItemClickedListener<I> onItemClickedListener) {
        this.onItemClickedListeners.put(type, onItemClickedListener);
    }

    protected void fireOnItemClickedEvent(View view, int position) {
        I item = items.get(position);
        carbon.widget.RecyclerView.OnItemClickedListener<I> typeSpecificListener = (carbon.widget.RecyclerView.OnItemClickedListener<I>) onItemClickedListeners.get(item.getClass());
        if (typeSpecificListener != null)
            typeSpecificListener.onItemClicked(view, item, position);
        if (onItemClickedListener != null)
            onItemClickedListener.onItemClicked(view, item, position);
    }

    public void setDiffEnabled(boolean useDiff) {
        this.diff = useDiff;
    }

    public boolean isDiffEnabled() {
        return diff;
    }
}
