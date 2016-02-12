package cz.kinst.jakub.viewmodelbinding;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public abstract class ViewModelDialogFragment<T extends ViewDataBinding, S extends ViewModel> extends DialogFragment implements ViewInterface<T, S> {
	private static final int VIEW_MODEL_GENERIC_TYPE_POSITION = 1;
	private final ViewModelBindingHelper<S, T> mViewModelBindingHelper = new ViewModelBindingHelper<>();


	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		mViewModelBindingHelper.onCreate(this, savedInstanceState, mViewModelBindingHelper.getViewModelClass(this, VIEW_MODEL_GENERIC_TYPE_POSITION));
		super.onCreate(savedInstanceState);
	}


	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mViewModelBindingHelper.onCreate(this, savedInstanceState, mViewModelBindingHelper.getViewModelClass(this, VIEW_MODEL_GENERIC_TYPE_POSITION));
		return mViewModelBindingHelper.getBinding().getRoot();
	}


	@Override
	public void onResume() {
		super.onResume();
		mViewModelBindingHelper.onResume();
	}


	@Override
	public void onPause() {
		super.onPause();
		mViewModelBindingHelper.onPause();
	}


	@Override
	public void onDestroyView() {
		mViewModelBindingHelper.onDestroyView(this);
		super.onDestroyView();
	}


	@Override
	public void onDestroy() {
		mViewModelBindingHelper.onDestroy(this);
		super.onDestroy();
	}


	@Nullable
	@Override
	public Bundle getBundle() {
		return getArguments();
	}


	@Override
	public void onSaveInstanceState(Bundle outState) {
		mViewModelBindingHelper.onSaveInstanceState(outState);
		super.onSaveInstanceState(outState);
	}


	@Override
	public Context getContext() {
		return getActivity();
	}


	@Override
	public int getViewModelDataBindingId() {
		return cz.kinst.jakub.viewmodelbinding.BR.viewModel;
	}


	public S getViewModel() {
		return mViewModelBindingHelper.getViewModel();
	}


	public T getBinding() {
		return mViewModelBindingHelper.getBinding();
	}
}
