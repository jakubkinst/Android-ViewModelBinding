package cz.kinst.jakub.viewmodelbinding;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.ViewDataBinding;
import android.support.annotation.CallSuper;
import android.support.v4.app.Fragment;


/**
 * Created by jakubkinst on 10/11/15.
 */
public class BaseViewModel<T extends ViewDataBinding> extends BaseObservable {
	private ViewInterface<T> mView;
	private String mViewModelId;


	public BaseViewModel() {
	}


	@CallSuper
	public void onResume() {

	}


	@CallSuper
	public void onPause() {

	}


	public void bindView(ViewInterface<T> viewInterface) {
		mView = viewInterface;
	}


	@CallSuper
	@SuppressWarnings("EmptyMethod")
	public void onViewDetached(boolean finalDetachment) {
		mView = null;
	}


	@CallSuper
	@SuppressWarnings("EmptyMethod")
	public void onViewAttached(boolean firstAttachment) {
	}


	@CallSuper
	@SuppressWarnings("EmptyMethod")
	public void onModelRemoved() {
		mView = null;
	}


	public ViewInterface<T> getView() {
		return mView;
	}


	public Context getContext() {
		if(mView == null) return null;
		else return mView.getContext();
	}


	public Activity getActivity() {
		if(mView == null) return null;
		else return mView.getActivity();
	}


	public boolean hasViewAttached() {
		return mView != null;
	}


	/**
	 * @return An app unique identifier for the current viewmodel instance (will be kept during orientation
	 * change). This identifier will be reset in case the corresponding activity is killed.
	 */
	public String getViewModelId() {
		return mViewModelId;
	}


	void setViewModelId(String viewModelId) {
		mViewModelId = viewModelId;
	}


	public void startActivityForResult(Intent intent, int requestCode) {
		if(!hasViewAttached()) return;
		if(mView instanceof Activity) {
			((Activity) mView).startActivityForResult(intent, requestCode);
		} else if(mView instanceof Fragment) {
			((Fragment) mView).startActivityForResult(intent, requestCode);
		}
	}


	@SuppressWarnings("EmptyMethod")
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	}
}
