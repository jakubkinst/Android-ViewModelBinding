package cz.kinst.jakub.viewmodelbinding;

import android.support.annotation.NonNull;

import java.util.HashMap;


/**
 * Create and keep this class inside your Activity. Store it
 * in {@link android.support.v4.app.FragmentActivity#onRetainCustomNonConfigurationInstance()
 * and restore in {@link android.support.v4.app.Activity#onCreate(android.os.Bundle)} before
 * calling the super implemenentation.
 */
public class ViewModelProvider {

	private static final ViewModelProvider sInstance = new ViewModelProvider();

	private final HashMap<String, BaseViewModel<? extends ViewInterface>> mViewModelCache;


	public static ViewModelProvider getInstance() {
		return sInstance;
	}


	private ViewModelProvider() {
		mViewModelCache = new HashMap<>();
	}


	public synchronized void remove(String modeIdentifier) {
		mViewModelCache.remove(modeIdentifier);
	}


	@SuppressWarnings("unchecked")
	@NonNull
	public synchronized <T extends ViewInterface> ViewModelWrapper<T> getViewModel(String modelIdentifier, @NonNull Class<? extends BaseViewModel> viewModelClass) {
		BaseViewModel<T> instance = (BaseViewModel<T>) mViewModelCache.get(modelIdentifier);
		if(instance != null) {
			return new ViewModelWrapper<>(instance, false);
		}

		try {
			instance = viewModelClass.newInstance();
			instance.setUniqueIdentifier(modelIdentifier);
			mViewModelCache.put(modelIdentifier, instance);
			return new ViewModelWrapper<>(instance, true);
		} catch(Exception ex) {
			throw new RuntimeException(ex);
		}
	}


	public static class ViewModelWrapper<T extends ViewInterface> {
		@NonNull
		public final BaseViewModel<T> viewModel;
		public final boolean wasCreated;


		private ViewModelWrapper(@NonNull BaseViewModel<T> mViewModel, boolean mWasCreated) {
			this.viewModel = mViewModel;
			this.wasCreated = mWasCreated;
		}
	}
}
