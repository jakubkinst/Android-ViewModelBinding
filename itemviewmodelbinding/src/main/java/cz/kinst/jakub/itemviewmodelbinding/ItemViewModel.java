package cz.kinst.jakub.itemviewmodelbinding;

import android.view.View;


public class ItemViewModel<T> {
	private T mItem;
	private ItemHandler<T> mHandler;
	private int mViewType;


	public interface ItemHandler<S> {
		void onItemClicked(View view, S item);
	}


	public ItemViewModel(int viewType, T item, ItemHandler<T> handler) {
		mViewType = viewType;
		mItem = item;
		mHandler = handler;
	}


	public T getItem() {
		return mItem;
	}


	public void setItem(T item) {
		mItem = item;
	}


	public ItemHandler<T> getHandler() {
		return mHandler;
	}


	public void setHandler(ItemHandler<T> handler) {
		mHandler = handler;
	}


	public void onClick(View view) {
		mHandler.onItemClicked(view, getItem());
	}


	public int getViewType() {
		return mViewType;
	}
}
