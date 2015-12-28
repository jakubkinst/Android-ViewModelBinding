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


/**
 * The core class of the framework connecting the View with the ViewModel and incorporating Data Binding.
 * The View (either Fragment or Activity) should call pass appropriate callbacks to an instance of {@link ViewModelBindingHelper}.
 * <p/>
 * See {@link BaseViewModelActivity} or {@link BaseViewModelFragment} for an example of usage
 *
 * @param <R> ViewModel type
 * @param <T> generated Data Binding class representing the layout
 */
public class ViewModelBindingHelper<R extends BaseViewModel, T extends ViewDataBinding> {

	private static final String LOG_TAG = "ViewModelBindingHelper";
	private String mViewModelId;
	private R mViewModel;
	private boolean mModelRemoved;
	private boolean mOnSaveInstanceCalled;
	private T mBinding;
	private boolean mAlreadyCreated;
	private ViewModelBindingConfig mViewModelConfig;


	/**
	 * Call from {@link Activity#onCreate(Bundle)} or {@link Fragment#onCreate(Bundle)} to initialize ViewModel
	 * <p/>
	 * The ViewModel instance will be either restored from memory or instantiated via {@link ViewModelProvider}
	 *
	 * @param savedInstanceState savedInstance state from {@link Activity#onCreate(Bundle)} or
	 *                           {@link Fragment#onCreate(Bundle)}
	 */
	public void onCreate(ViewInterface view, @Nullable Bundle savedInstanceState) {
		// get ViewModelBinding config
		mViewModelConfig = view.getViewModelBindingConfig();
		if(mViewModelConfig == null)
			throw new IllegalStateException("View not configured. Provide valid ViewModelBindingConfig in your View.");

		// skip if already created
		if(mAlreadyCreated) return;

		// perform Data Binding initialization
		mAlreadyCreated = true;
		if(view instanceof Activity)
			mBinding = DataBindingUtil.setContentView(((Activity) view), mViewModelConfig.getLayoutResource());
		else if(view instanceof Fragment)
			mBinding = DataBindingUtil.inflate(LayoutInflater.from(view.getContext()), mViewModelConfig.getLayoutResource(), null, false);
		else
			throw new IllegalArgumentException("View must be an instance of Activity or Fragment (support-v4).");


		// handle case when ViewModel is not desired
		if(mViewModelConfig.getViewModelClass() == null) {
			mViewModel = null;
			return;
		}

		// obtain unique ViewModelId
		if(mViewModelId == null) { // screen (activity/fragment) created for first time, attach unique ID
			if(savedInstanceState == null)
				mViewModelId = UUID.randomUUID().toString();
			else
				mViewModelId = savedInstanceState.getString(getViewModelIdFieldName());
		}

		// get ViewModel instance for this screen
		final ViewModelProvider.ViewModelWrapper viewModelWrapper = ViewModelProvider.getInstance().getViewModel(mViewModelId, mViewModelConfig.getViewModelClass());
		mViewModel = (R) viewModelWrapper.getViewModel();
		mOnSaveInstanceCalled = false;

		// bind all together
		mViewModel.bindView(view);
		mBinding.setVariable(mViewModelConfig.getViewModelVariableName(), mViewModel);

		// call ViewModel callback
		mViewModel.onViewAttached(viewModelWrapper.wasCreated());
	}


	/**
	 * Call from {@link Activity#onResume()} or {@link Fragment#onResume()}
	 */
	public void onResume() {
		if(mViewModel != null) mViewModel.onResume();
	}


	/**
	 * Call from {@link Activity#onPause()} or {@link Fragment#onPause()}
	 */
	public void onPause() {
		if(mViewModel != null) mViewModel.onPause();
	}


	/**
	 * Use in case this model is associated with an {@link Fragment}
	 * Call from {@link Fragment#onDestroyView()}. Use in case model is associated
	 * with Fragment
	 *
	 * @param fragment Fragment instance
	 */
	public void onDestroyView(@NonNull Fragment fragment) {
		if(mViewModel == null) return;

		if(fragment.getActivity() != null && fragment.getActivity().isFinishing()) {
			mViewModel.onViewDetached(true);
			removeViewModel();
		} else {
			mViewModel.onViewDetached(false);
			mAlreadyCreated = false;
		}
	}


	/**
	 * Use in case this model is associated with an {@link Fragment}
	 * Call from {@link Fragment#onDestroy()}
	 *
	 * @param fragment
	 */
	public void onDestroy(@NonNull Fragment fragment) {
		if(mViewModel == null) return;

		if(fragment.getActivity().isFinishing()) {
			removeViewModel();
		} else if(fragment.isRemoving() && !mOnSaveInstanceCalled) {
			// The fragment can be still in backstack even if isRemoving() is true.
			// We check mOnSaveInstanceCalled - if this was not called then the fragment is totally removed.
			Log.d(LOG_TAG, "Removing viewmodel - fragment replaced");
			removeViewModel();
		}
		mAlreadyCreated = false;
	}


	/**
	 * Use in case this model is associated with an {@link Activity}
	 * Call from {@link Activity#onDestroy()}
	 *
	 * @param activity
	 */
	public void onDestroy(@NonNull Activity activity) {
		if(mViewModel == null) return;

		if(activity.isFinishing()) {
			mViewModel.onViewDetached(true);
			removeViewModel();
		} else
			mViewModel.onViewDetached(false);
		mAlreadyCreated = false;
	}


	/**
	 * Getter for the ViewModel
	 *
	 * @return ViewModel instance
	 */
	@Nullable
	public R getViewModel() {
		return mViewModel;
	}


	/**
	 * Call from {@link Activity#onSaveInstanceState(Bundle)}
	 * or {@link Fragment#onSaveInstanceState(Bundle)}.
	 * This allows the model to save its state.
	 *
	 * @param bundle InstanceState bundle
	 */
	public void onSaveInstanceState(@NonNull Bundle bundle) {
		bundle.putString(getViewModelIdFieldName(), mViewModelId);
		if(mViewModel != null) {
			mOnSaveInstanceCalled = true;
		}
	}


	/**
	 * Getter for the Data Binding instance
	 *
	 * @return Data Binding instance
	 */
	public T getBinding() {
		return mBinding;
	}


	/**
	 * This method defines a key under which the ViewModel ID will be stored inside SavedInstanceState of the Activity/Fragment.
	 * <p/>
	 * The key should be unique enough to avoid collision with other user-defined keys
	 *
	 * @return key
	 */
	@NonNull
	private String getViewModelIdFieldName() {
		return "__vm_id_" + mViewModelConfig.getViewModelClass().getName();
	}


	/**
	 * Remove ViewModel instance from memory and cleanup
	 */
	private void removeViewModel() {
		if(!mModelRemoved) {
			ViewModelProvider.getInstance().removeViewModel(mViewModelId);
			mViewModel.onModelRemoved();
			mModelRemoved = true;
			mAlreadyCreated = false;
		}
	}
}