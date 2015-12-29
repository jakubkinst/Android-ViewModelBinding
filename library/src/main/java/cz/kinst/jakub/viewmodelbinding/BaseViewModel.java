package cz.kinst.jakub.viewmodelbinding;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.ViewDataBinding;
import android.graphics.drawable.Drawable;
import android.support.annotation.CallSuper;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;


/**
 * ViewModel base class. Every ViewModel must extend this class
 *
 * @param <T> Layout Data Binding class
 */
public abstract class BaseViewModel<T extends ViewDataBinding> extends BaseObservable {
	private ViewInterface<T> mView;
	private String mViewModelId;


	public BaseViewModel() {
	}


	/**
	 * onResume callback from the View
	 */
	@CallSuper
	public void onResume() {

	}


	/**
	 * onPause callback from the View
	 */
	@CallSuper
	public void onPause() {

	}


	/**
	 * Bind a new View instance
	 *
	 * @param viewInterface View
	 */
	public void bindView(ViewInterface<T> viewInterface) {
		mView = viewInterface;
	}


	/**
	 * Called after the View is being destroyed and therefore detached from the ViewModel
	 *
	 * @param finalDetachment flag indicating whether it is final detachment and this instance of ViewModel will no longer be used
	 */
	@CallSuper
	public void onViewDetached(boolean finalDetachment) {
		mView = null;
	}


	/**
	 * Called after the View is attached to this ViewModel
	 *
	 * @param firstAttachment flag indicating whether the ViewModel was just created and attached for the first time
	 */
	@CallSuper
	public void onViewAttached(boolean firstAttachment) {
	}


	/**
	 * Called after this ViewModel instance was removed from cache
	 * <p/>
	 * This is the place to do any cleanup to avoid memory leaks
	 */
	@CallSuper
	public void onModelRemoved() {
		mView = null;
	}


	/**
	 * Getter for currently attached View
	 *
	 * @return currently attached View or null if no View is attached
	 */
	public ViewInterface<T> getView() {
		return mView;
	}


	/**
	 * Provides Context instance
	 *
	 * @return Context instance if a View is attached, null otherwise
	 */
	public Context getContext() {
		if(mView == null) return null;
		else return mView.getContext();
	}


	/**
	 * Provides an Activity instance
	 *
	 * @return Activity instance if a View is attached, null otherwise
	 */
	public Activity getActivity() {
		if(mView == null) return null;
		else return mView.getActivity();
	}


	/**
	 * Helper method to determine if View is attached at the moment
	 *
	 * @return true if View is currently attached
	 */
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


	@Deprecated
	public void startActivityForResult(Intent intent, int requestCode) {
		if(!hasViewAttached()) return;
		if(mView instanceof Activity) {
			((Activity) mView).startActivityForResult(intent, requestCode);
		} else if(mView instanceof Fragment) {
			((Fragment) mView).startActivityForResult(intent, requestCode);
		}
	}


	@Deprecated
	@SuppressWarnings("EmptyMethod")
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	}


	/**
	 * Convenience method for reaching View Binding instance
	 *
	 * @return Layout Data Binding instance
	 */
	protected T getBinding() {
		if(hasViewAttached())
			return getView().getBinding();
		else
			return null;
	}


	/**
	 * Convenience method to retrieve String resource from Context resources
	 * <p/>
	 * Warning: May return null if View is not attached
	 *
	 * @param resource Resource ID
	 * @return String from resources or null
	 */
	protected String getString(@StringRes int resource) {
		if(getContext() == null)
			return null;
		return getContext().getString(resource);
	}


	/**
	 * Convenience method to retrieve Drawable resource from Context resources
	 * <p/>
	 * Warning: May return null if View is not attached
	 *
	 * @param resource Resource ID
	 * @return Drawable from resources or null
	 */
	protected Drawable getDrawable(@DrawableRes int resource) {
		if(getContext() == null)
			return null;
		return ContextCompat.getDrawable(getContext(), resource);
	}


	/**
	 * Convenience method to retrieve Color resource from Context resources
	 * <p/>
	 * Warning: May return null if View is not attached
	 *
	 * @param resource Resource ID
	 * @return Color from resources or null
	 */
	protected int getColor(@ColorRes int resource) {
		if(getContext() == null)
			return -1;
		return ContextCompat.getColor(getContext(), resource);
	}
}
