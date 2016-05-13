package cz.kinst.jakub.itemviewmodelbinding;

import android.support.annotation.LayoutRes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RecyclerViewConfig {
	Map<Integer, ViewType> mViewTypes = new HashMap<>();
	private List<ItemViewModel<?>> mItems;


	public static RecyclerViewConfig create() {
		return new RecyclerViewConfig();
	}


	private RecyclerViewConfig() {
	}


	public RecyclerViewConfig addViewType(int id, @LayoutRes int layoutResource) {
		return addViewType(id, layoutResource, cz.kinst.jakub.itemviewmodelbinding.BR.viewModel);
	}


	public RecyclerViewConfig addViewType(int id, @LayoutRes int layoutResource, int viewModelBindingVariable) {
		mViewTypes.put(id, new ViewType(layoutResource, viewModelBindingVariable));
		return this;
	}


	public int getLayoutResource(int viewType) {
		return mViewTypes.get(viewType).getLayoutResource();
	}


	public int getViewModelBindingVariable(int viewType) {
		return mViewTypes.get(viewType).getViewModelBindingVariable();
	}


	public List<ItemViewModel<?>> getItems() {
		return mItems;
	}


	public void setItems(List<ItemViewModel<?>> items) {
		this.mItems = items;
	}


	class ViewType {
		@LayoutRes
		int layoutResource;
		int viewModelBindingVariable;


		public ViewType(int layoutResource, int viewModelBindingVariable) {
			this.layoutResource = layoutResource;
			this.viewModelBindingVariable = viewModelBindingVariable;
		}


		public int getLayoutResource() {
			return layoutResource;
		}


		public int getViewModelBindingVariable() {
			return viewModelBindingVariable;
		}
	}
}
