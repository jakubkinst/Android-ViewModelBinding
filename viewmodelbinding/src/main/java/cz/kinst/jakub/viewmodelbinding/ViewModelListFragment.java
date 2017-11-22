package cz.kinst.jakub.viewmodelbinding;

import android.content.Context;
import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public abstract class ViewModelListFragment<T extends ViewDataBinding, S extends ViewModel> extends ListFragment implements ViewInterface<T, S>, OnViewModelInitializedCallback<S> {

    @NonNull
    private final ViewModelBindingHelper<S, T> viewModelBindingHelper = new ViewModelBindingHelper<>();

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        viewModelBindingHelper.onCreate(this, savedInstanceState, this);
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        viewModelBindingHelper.onCreate(this, savedInstanceState, this);
        return viewModelBindingHelper.getBinding().getRoot();
    }


    @Override
    public void onResume() {
        super.onResume();
        viewModelBindingHelper.onResume();
    }


    @Override
    public void onPause() {
        super.onPause();
        viewModelBindingHelper.onPause();
    }


    @Override
    public void onDestroyView() {
        viewModelBindingHelper.onDestroyView(this);
        super.onDestroyView();
    }


    @Override
    public void onDestroy() {
        viewModelBindingHelper.onDestroy(this);
        super.onDestroy();
    }


    @Nullable
    @Override
    public Bundle getBundle() {
        return getArguments();
    }


    @Override
    public void onSaveInstanceState(@NonNull final Bundle outState) {
        viewModelBindingHelper.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }


    @Override
    public Context getContext() {
        return getActivity();
    }


    public T getBinding() {
        return viewModelBindingHelper.getBinding();
    }


    @Override
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        viewModelBindingHelper.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void onRequestPermissionsResult(final int requestCode, @NonNull final String[] permissions, @NonNull final int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        viewModelBindingHelper.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    @Override
    public void onViewModelInitialized(final S viewModel) {

    }


    @Nullable
    public S getViewModel() {
        return viewModelBindingHelper.getViewModel();
    }


    protected void setupViewModel(@LayoutRes final int layoutResourceId, final Class<S> viewModelClass) {
        viewModelBindingHelper.setup(layoutResourceId, viewModelClass);
    }
}
