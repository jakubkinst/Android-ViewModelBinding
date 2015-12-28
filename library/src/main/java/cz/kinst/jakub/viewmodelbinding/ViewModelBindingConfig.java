package cz.kinst.jakub.viewmodelbinding;

import android.support.annotation.LayoutRes;


/**
 * Use this to define a ViewModelBinding Config for a specific screen.
 * <p/>
 * Config contains layout resource ID, ViewModel class, ViewModel binding variable name
 */
public class ViewModelBindingConfig {

	@LayoutRes
	int mLayoutResource;
	Class mViewModelClass;
	int mViewModelVariableName;


	/**
	 * Create a ViewModelBinding Config object for an Activity/Fragment
	 *
	 * @param layoutResource        Layout resource ID
	 * @param viewModelClass        ViewModel class
	 * @param viewModelVariableName Data Binding variable name for injecting the ViewModel - use generated id (e.g. BR.mViewModel)
	 */
	public ViewModelBindingConfig(@LayoutRes int layoutResource, Class viewModelClass, int viewModelVariableName) {
		mLayoutResource = layoutResource;
		mViewModelClass = viewModelClass;
		mViewModelVariableName = viewModelVariableName;
	}


	public int getLayoutResource() {
		return mLayoutResource;
	}


	public Class getViewModelClass() {
		return mViewModelClass;
	}


	public int getViewModelVariableName() {
		return mViewModelVariableName;
	}
}
