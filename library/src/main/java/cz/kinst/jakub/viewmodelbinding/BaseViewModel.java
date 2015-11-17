package cz.kinst.jakub.viewmodelbinding;

import android.databinding.BaseObservable;
import android.support.annotation.Nullable;


/**
 * Created by jakubkinst on 10/11/15.
 */
public class BaseViewModel<T> extends BaseObservable {
	@Nullable
	private ViewInterface<T> mView;
	private String mUniqueIdentifier;


	public BaseViewModel() {
	}


	public void bindView(ViewInterface viewInterface) {
		mView = viewInterface;
	}


	@SuppressWarnings("EmptyMethod")
	public void onViewDetached(boolean finalDetachment) {
		mView = null;
	}


	@SuppressWarnings("EmptyMethod")
	public void onViewAttached(boolean firstAttachment) {
	}


	@SuppressWarnings("EmptyMethod")
	public void onModelRemoved() {
		mView = null;
	}


	@Nullable
	public ViewInterface<T> getView() {
		return mView;
	}


	public boolean hasViewAttached() {
		return mView != null;
	}


	/**
	 * @return An app unique identifier for the current viewmodel instance (will be kept during orientation
	 * change). This identifier will be reset in case the corresponding activity is killed.
	 */
	public String getUniqueIdentifier() {
		return mUniqueIdentifier;
	}


	void setUniqueIdentifier(String uniqueIdentifier) {
		mUniqueIdentifier = uniqueIdentifier;
	}
}
