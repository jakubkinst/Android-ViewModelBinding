package cz.kinst.jakub.itemviewmodelbinding;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;


public class BindingViewHolder<S> extends RecyclerView.ViewHolder {
	ViewDataBinding mBinding;


	public BindingViewHolder(ViewDataBinding binding) {
		super(binding.getRoot());
		mBinding = binding;
	}


	public void setItemViewModel(ItemViewModel<?> item, int variableName) {
		mBinding.setVariable(variableName, item);
	}
}
