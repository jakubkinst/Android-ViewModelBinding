package cz.kinst.jakub.viewmodelbinding;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


public abstract class ViewModelFragment<T extends ViewDataBinding, S extends ViewModel> extends Fragment implements ViewInterface<T> {

	private final ViewModelBindingHelper<S, T> mViewModelBindingHelper = new ViewModelBindingHelper<>();


	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		Class<S> viewModelClass = (Class<S>) ReflectionUtil.findViewModelClassDefinition(getClass(), 1);
		if (viewModelClass != null) {
			mViewModelBindingHelper.onCreate(this, savedInstanceState, viewModelClass);
		} else {
			throw new IllegalStateException("Generic classes definition (binding and viewmodel) is not provided for " +
					getClass().getName() + ". If you don't need viewmodel for this fragment, consider extending Fragment class");
		}
		super.onCreate(savedInstanceState);
	}


	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {		Type type = getClass().getGenericSuperclass();
		Class<S> viewModelClass = (Class<S>) ReflectionUtil.findViewModelClassDefinition(getClass(), 1);
		if (viewModelClass != null) {
			mViewModelBindingHelper.onCreate(this, savedInstanceState, viewModelClass);
		} else {
			throw new IllegalStateException("Generic classes definition (binding and viewmodel) is not provided for " +
					getClass().getName() + ". If you don't need viewmodel for this fragment, consider extending Fragment class");
		}
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
