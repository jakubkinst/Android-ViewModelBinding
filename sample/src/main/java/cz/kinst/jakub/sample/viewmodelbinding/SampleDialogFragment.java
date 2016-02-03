package cz.kinst.jakub.sample.viewmodelbinding;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import cz.kinst.jakub.sample.viewmodelbinding.databinding.DialogSampleBinding;
import cz.kinst.jakub.viewmodelbinding.ViewModelBindingConfig;
import cz.kinst.jakub.viewmodelbinding.ViewModelDialogFragment;


public class SampleDialogFragment extends ViewModelDialogFragment<DialogSampleBinding, SampleDialogViewModel> {

	private SampleDialogViewModel.SampleDialogListener mListener;


	public static SampleDialogFragment newInstance() {
		Bundle args = new Bundle();
		SampleDialogFragment fragment = new SampleDialogFragment();
		fragment.setArguments(args);
		return fragment;
	}


	@Override
	public ViewModelBindingConfig getViewModelBindingConfig() {
		return new ViewModelBindingConfig(R.layout.dialog_sample, SampleDialogViewModel.class);
	}


	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		getViewModel().setListener(mListener);
	}


	public void setListener(SampleDialogViewModel.SampleDialogListener listener) {
		mListener = listener;
	}
}
