package cz.kinst.jakub.viewmodelbinding;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.databinding.BaseObservable;
import android.databinding.ViewDataBinding;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.annotation.CallSuper;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.view.View;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;


/**
 * ViewModel base class. Every ViewModel must extend this class
 *
 * @param <T> Layout Data Binding class
 */
public abstract class ViewModel<T extends ViewDataBinding> extends BaseObservable {
	private ViewInterface<T, ? extends ViewModel> mView;
	private String mViewModelId;
	private Handler mHandler = new Handler();
	private Thread mUiThread;
	private boolean mRunning;
	private Queue<Runnable> mUiThreadTaskQueue = new ConcurrentLinkedQueue<>();


	public ViewModel() {
	}


	/**
	 * onResume callback from the View
	 */
	@CallSuper
	public void onResume() {
		mRunning = true;
	}


	/**
	 * onPause callback from the View
	 */
	@CallSuper
	public void onPause() {
		mRunning = false;
	}


	/**
	 * Returns true if the Activity/Fragment is in running state(not paused) at the moment
	 *
	 * @return true if running
	 */
	public boolean isRunning() {
		return hasViewAttached() && mRunning;
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
	 * Called after this ViewModel instance was destroyed and removed from cache
	 * <p>
	 * This is a place to do any cleanup to avoid memory leaks
	 */
	@CallSuper
	public void onViewModelDestroyed() {
		mView = null;
		mUiThreadTaskQueue.clear();
	}


	/**
	 * Getter for currently attached View
	 *
	 * @return currently attached View or null if no View is attached
	 */
	public ViewInterface<T, ? extends ViewModel> getView() {
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


	/**
	 * Called after the ViewModel is instantiated
	 */
	@CallSuper
	public void onViewModelCreated() {
		mUiThread = Thread.currentThread();
	}


	/**
	 * Convenience method for reaching View Binding instance
	 *
	 * @return Layout Data Binding instance
	 */
	public T getBinding() {
		if(hasViewAttached())
			return getView().getBinding();
		else
			return null;
	}


	/**
	 * Convenience method for binding's root View
	 *
	 * @return Root View
	 */
	public View getRootView() {
		return getBinding().getRoot();
	}


	/**
	 * Runs the specified action on the UI thread. If the current thread is the UI
	 * thread, then the action is executed immediately. If the current thread is
	 * not the UI thread, the action is posted to the event queue of the UI thread.
	 *
	 * @param action the action to run on the UI thread
	 */
	public final void runOnUiThread(Runnable action) {
		internalRunOnUiThreadNow(action);
	}


	/**
	 * Convenience method for Handler.postDelayed()
	 *
	 * @param runnable Runnable to run
	 * @param delayMs  Delay in ms
	 */
	public void postDelayed(Runnable runnable, long delayMs) {
		mHandler.postDelayed(runnable, delayMs);
	}


	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

	}


	/**
	 * Bind a new View instance
	 *
	 * @param viewInterface View
	 */
	protected void bindView(ViewInterface<T, ? extends ViewModel> viewInterface) {
		mView = viewInterface;
	}


	/**
	 * Convenience method to retrieve Resources from Context resources
	 * <p>
	 * Warning: May return null if View is not attached
	 *
	 * @return Resources or null
	 */
	protected Resources getResources() {
		if(getContext() == null)
			return null;
		return getContext().getResources();
	}


	/**
	 * Convenience method to retrieve String resource from Context resources
	 * <p>
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
	 * Convenience method to retrieve String resource from Context resources
	 * <p>
	 * Warning: May return null if View is not attached
	 *
	 * @param resource   Resource ID
	 * @param formatArgs Formatting arguments
	 * @return String from resources or null
	 */
	protected String getString(@StringRes int resource, Object... formatArgs) {
		if(getContext() == null)
			return null;
		return getContext().getString(resource, formatArgs);
	}


	/**
	 * Convenience method to retrieve Drawable resource from Context resources
	 * <p>
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
	 * <p>
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


	void internalRunAllUiTasksInQueue() {
		while(!mUiThreadTaskQueue.isEmpty()) {
			internalRunOnUiThreadNow(mUiThreadTaskQueue.poll());
		}
	}


	private void internalRunOnUiThreadNow(Runnable action) {
		if(Thread.currentThread() != mUiThread) {
			mHandler.post(() -> {
				if(hasViewAttached())
					action.run();
				else
					mUiThreadTaskQueue.add(action);
			});
		} else {
			if(hasViewAttached())
				action.run();
			else
				mUiThreadTaskQueue.add(action);
		}
	}


}
