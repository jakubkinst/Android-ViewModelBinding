package cz.kinst.jakub.itemviewmodelbinding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;


public class BindingRecyclerViewAdapter<T> extends RecyclerView.Adapter<BindingViewHolder<T>> {
	private RecyclerViewConfig mConfig;



	@Override
	public BindingViewHolder<T> onCreateViewHolder(ViewGroup parent, int viewType) {
		ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), mConfig.getLayoutResource(viewType), null, false);
		return new BindingViewHolder<>(binding);
	}


	@Override
	public int getItemViewType(int position) {
		return mConfig.getItems().get(position).getViewType();
	}


	public List<ItemViewModel<?>> getItems() {
		return mConfig.getItems();
	}


	@Override
	public void onBindViewHolder(BindingViewHolder<T> holder, int position) {
		holder.setItemViewModel(mConfig.getItems().get(position), mConfig.getViewModelBindingVariable(getItemViewType(position)));
	}


	@Override
	public int getItemCount() {
		return mConfig.getItems().size();
	}


	public RecyclerViewConfig getConfig() {
		return mConfig;
	}


	public void setConfig(RecyclerViewConfig config) {
		mConfig = config;
	}
}
