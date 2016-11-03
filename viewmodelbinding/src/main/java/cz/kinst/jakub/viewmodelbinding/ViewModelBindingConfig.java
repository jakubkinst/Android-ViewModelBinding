package cz.kinst.jakub.viewmodelbinding;

import android.support.annotation.LayoutRes;


/**
 * Use this to define a ViewModelBinding Config for a specific screen.
 * <p>
 * Config contains layout resource ID, ViewModel class, ViewModel binding variable name
 */
public class ViewModelBindingConfig<T extends ViewModel> {

	@LayoutRes
	int mLayoutResource;
	Class<T> mViewModelClass;
	int mViewModelVariableName;
	int mViewVariableName;


	/**
	 * Create a ViewModelBinding Config object for an Activity/Fragment
	 *
	 * @param layoutResource        Layout resource ID
	 * @param viewModelClass        ViewModel class
	 * @param viewModelVariableName Data Binding variable name for injecting the ViewModel - use generated id (e.g. BR.mViewModel)
	 */
	public ViewModelBindingConfig(@LayoutRes int layoutResource, Class<T> viewModelClass, int viewModelVariableName, int viewVariableName) {
		mLayoutResource = layoutResource;
		mViewModelClass = viewModelClass;
		mViewModelVariableName = viewModelVariableName;
		mViewVariableName = viewVariableName;
	}


	/**
	 * Create a ViewModelBinding Config object for an Activity/Fragment
	 *
	 * @param layoutResource Layout resource ID
	 * @param viewModelClass ViewModel class
	 */
	public ViewModelBindingConfig(@LayoutRes int layoutResource, Class<T> viewModelClass) {
		this(layoutResource, viewModelClass, cz.kinst.jakub.viewmodelbinding.BR.viewModel, cz.kinst.jakub.viewmodelbinding.BR.view);
	}


	public int getLayoutResource() {
		return mLayoutResource;
	}


	public Class<T> getViewModelClass() {
		return mViewModelClass;
	}


	public int getViewModelVariableName() {
		return mViewModelVariableName;
	}


	public int getViewVariableName() {
		return mViewVariableName;
	}
}
