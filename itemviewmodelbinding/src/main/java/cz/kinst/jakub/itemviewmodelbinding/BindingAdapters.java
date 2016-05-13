package cz.kinst.jakub.itemviewmodelbinding;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;


public class BindingAdapters {
	@SuppressWarnings("unchecked")
	@BindingAdapter(value = {"itemConfig"}, requireAll = false)
	public static <T> void setAdapter(RecyclerView recyclerView, RecyclerViewConfig itemConfig) {
		BindingRecyclerViewAdapter<T> adapter = (BindingRecyclerViewAdapter<T>) recyclerView.getAdapter();
		if(adapter == null) {
			adapter = new BindingRecyclerViewAdapter<T>();
			adapter.setConfig(itemConfig);
			recyclerView.setAdapter(adapter);
		} else {
			adapter.setConfig(itemConfig);
		}
	}
}
