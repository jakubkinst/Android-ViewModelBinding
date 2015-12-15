package cz.kinst.jakub.viewmodelbinding;

import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;

import java.util.HashMap;


public class ViewModelProvider {

	private static ViewModelProvider sInstance;
	private final HashMap<String, BaseViewModel<? extends ViewDataBinding>> mViewModelCache;


	private ViewModelProvider() {
		mViewModelCache = new HashMap<>();
	}


	public static ViewModelProvider getInstance() {
		if(sInstance == null)
			sInstance = new ViewModelProvider();
		return sInstance;
	}


	public synchronized void removeViewModel(String viewModelId) {
		mViewModelCache.remove(viewModelId);
	}


	@SuppressWarnings("unchecked")
	@NonNull
	public synchronized ViewModelWrapper getViewModel(String viewModelId, @NonNull Class<? extends BaseViewModel> viewModelClass) {
		BaseViewModel instance = mViewModelCache.get(viewModelId);
		if(instance != null)
			return new ViewModelWrapper(instance, false);

		try {
			instance = viewModelClass.newInstance();
			instance.setViewModelId(viewModelId);
			mViewModelCache.put(viewModelId, instance);
			return new ViewModelWrapper(instance, true);
		} catch(Exception ex) {
			throw new RuntimeException(ex);
		}
	}


	public static class ViewModelWrapper {
		@NonNull
		private final BaseViewModel mViewModel;
		private final boolean mWasCreated;


		private ViewModelWrapper(@NonNull BaseViewModel viewModel, boolean wasCreated) {
			this.mViewModel = viewModel;
			this.mWasCreated = wasCreated;
		}


		@NonNull
		public BaseViewModel getViewModel() {
			return mViewModel;
		}


		public boolean wasCreated() {
			return mWasCreated;
		}
	}
}
