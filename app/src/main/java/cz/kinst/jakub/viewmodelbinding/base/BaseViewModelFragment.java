package cz.kinst.jakub.viewmodelbinding.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cz.kinst.jakub.viewmodelbinding.BR;


/**
 * Created by jakubkinst on 10/11/15.
 */
public abstract class BaseViewModelFragment<T extends ViewDataBinding, S extends BaseViewModel> extends Fragment implements ViewInterface {
	private final ViewModelHelper<S> mViewModelHelper = new ViewModelHelper<>();

	private T mBinding;


	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mBinding = DataBindingUtil.inflate(inflater, getLayoutResource(), container, false);
		return mBinding.getRoot();
	}


	@Override
	public void onDestroyView() {
		mViewModelHelper.onDestroyView(this);
		super.onDestroyView();
	}


	@Override
	public void onDestroy() {
		mViewModelHelper.onDestroyView(this);
		super.onDestroy();
	}


	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		mViewModelHelper.onCreate(this, savedInstanceState, getViewModelClass());
		mBinding.setVariable(BR.viewModel, getViewModel());
	}


	@Override
	public Context getContext() {
		return getActivity();
	}


	public S getViewModel() {
		return mViewModelHelper.getViewModel();
	}


	public T getBinding() {
		return mBinding;
	}


	protected abstract Class<? extends BaseViewModel> getViewModelClass();

	protected abstract int getLayoutResource();
}
