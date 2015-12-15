package cz.kinst.jakub.viewmodelbinding;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;

import java.util.UUID;


public class ViewModelHelper<R extends BaseViewModel, T extends ViewDataBinding> {

	private String mScreenId;
	private R mViewModel;
	private boolean mModelRemoved;
	private boolean mOnSaveInstanceCalled;
	private T mBinding;
	private boolean mCreated;


	/**
	 * Call from {@link Activity#onCreate(Bundle)} or
	 * {@link Fragment#onCreate(Bundle)}
	 *
	 * @param savedInstanceState savedInstance state from {@link Activity#onCreate(Bundle)} or
	 *                           {@link Fragment#onCreate(Bundle)}
	 */
	public void onCreate(ViewInterface view, @Nullable Bundle savedInstanceState) {
		ViewModelBindingConfig config = view.getViewModelBindingConfig();
		if(view.getViewModelBindingConfig() == null)
			throw new IllegalStateException("View not configured.");
		if(mCreated) return;
		mCreated = true;
		if(view instanceof Activity) {
			mBinding = DataBindingUtil.setContentView(((Activity) view), config.getLayoutResource());
		} else if(view instanceof Fragment) {
			mBinding = DataBindingUtil.inflate(LayoutInflater.from(view.getContext()), config.getLayoutResource(), null, false);
		} else {
			throw new IllegalArgumentException("View must be an instance of Activity or Fragment (support-v4).");
		}

		// no viewmodel for this fragment
		if(config.getViewModelClass() == null) {
			mViewModel = null;
			return;
		}

		if(mScreenId == null) {
			// screen (activity/fragment) created for first time, attach unique ID
			if(savedInstanceState == null)
				mScreenId = UUID.randomUUID().toString();
			else
				mScreenId = savedInstanceState.getString(config.getViewModelClass().getName() + "identifier");
		}
		// get model instance for this screen
		final ViewModelProvider.ViewModelWrapper viewModelWrapper = ViewModelProvider.getInstance().getViewModel(mScreenId, config.getViewModelClass());
		//noinspection unchecked
		mViewModel = (R) viewModelWrapper.viewModel;
		mOnSaveInstanceCalled = false;


		mViewModel.bindView(view);
		mBinding.setVariable(config.getViewModelVariableName(), mViewModel);
		mViewModel.onViewAttached(viewModelWrapper.wasCreated);
	}


	public void onResume() {
		if(mViewModel != null) {
			mViewModel.onResume();
		}
	}


	public void onPause() {
		if(mViewModel != null) {
			mViewModel.onPause();
		}
	}


	/**
	 * Use in case this model is associated with an {@link Fragment}
	 * Call from {@link Fragment#onDestroyView()}. Use in case model is associated
	 * with Fragment
	 *
	 * @param fragment
	 */
	public void onDestroyView(@NonNull Fragment fragment) {
		if(mViewModel == null) {
			//no viewmodel for this fragment
			return;
		}
		if(fragment.getActivity() != null && fragment.getActivity().isFinishing()) {
			mViewModel.onViewDetached(true);
			removeViewModel();
		} else {
			mViewModel.onViewDetached(false);
			mCreated = false;
		}
	}


	/**
	 * Use in case this model is associated with an {@link Fragment}
	 * Call from {@link Fragment#onDestroy()}
	 *
	 * @param fragment
	 */
	public void onDestroy(@NonNull Fragment fragment) {
		if(mViewModel == null) {
			//no viewmodel for this fragment
			return;
		}
		if(fragment.getActivity().isFinishing()) {
			removeViewModel();
		} else if(fragment.isRemoving() && !mOnSaveInstanceCalled) {
			// The fragment can be still in backstack even if isRemoving() is true.
			// We check mOnSaveInstanceCalled - if this was not called then the fragment is totally removed.
			Log.d("model", "Removing viewmodel - fragment replaced");
			removeViewModel();
		}
		mCreated = false;
	}


	/**
	 * Use in case this model is associated with an {@link Activity}
	 * Call from {@link Activity#onDestroy()}
	 *
	 * @param activity
	 */
	public void onDestroy(@NonNull Activity activity) {
		if(mViewModel == null) {
			//no viewmodel for this activity
			return;
		}
		if(activity.isFinishing()) {
			mViewModel.onViewDetached(true);
			removeViewModel();
		} else
			mViewModel.onViewDetached(false);
		mCreated = false;
	}


	/**
	 * Call from {@link Fragment#onViewCreated(android.view.View, Bundle)}
	 * or {@link Activity#onCreate(Bundle)}
	 *
	 * @param view
	 */
	public void setView(@NonNull ViewInterface view) {
		if(mViewModel == null) {
			//no viewmodel for this fragment
			return;
		}
		mViewModel.bindView(view);
	}


	@Nullable
	public R getViewModel() {
		return mViewModel;
	}


	/**
	 * Call from {@link Activity#onSaveInstanceState(Bundle)}
	 * or {@link Fragment#onSaveInstanceState(Bundle)}.
	 * This allows the model to save its state.
	 *
	 * @param bundle
	 */
	public void onSaveInstanceState(@NonNull Bundle bundle) {
		bundle.putString(mViewModel.getClass().getName() + "identifier", mScreenId);
		if(mViewModel != null) {
			mOnSaveInstanceCalled = true;
		}
	}


	public T getBinding() {
		return mBinding;
	}


	private void removeViewModel() {
		if(!mModelRemoved) {
			ViewModelProvider.getInstance().remove(mScreenId);
			mViewModel.onModelRemoved();
			mModelRemoved = true;
			mCreated = false;
		}
	}
}