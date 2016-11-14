package cz.kinst.jakub.sample.viewmodelbinding;

import android.os.Bundle;
import android.support.annotation.Nullable;

import cz.kinst.jakub.sample.viewmodelbinding.databinding.DialogSampleBinding;
import cz.kinst.jakub.viewmodelbinding.ViewModelDialogFragment;


public class SampleDialogFragment extends ViewModelDialogFragment<DialogSampleBinding, SampleDialogViewModel> {

	private static final String EXTRA_NUMBER = "number";

	private SampleDialogViewModel.SampleDialogListener mListener;


	public static SampleDialogFragment newInstance(int number) {
		Bundle args = new Bundle();
		SampleDialogFragment fragment = new SampleDialogFragment();
		args.putInt(EXTRA_NUMBER, number);
		fragment.setArguments(args);
		return fragment;
	}


	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		setupViewModel(R.layout.dialog_sample, SampleDialogViewModel.class);
		super.onCreate(savedInstanceState);
		getViewModel().number.set(getArguments().getInt(EXTRA_NUMBER));
		getViewModel().setListener(mListener);
	}


	public void setListener(SampleDialogViewModel.SampleDialogListener listener) {
		mListener = listener;
	}
}
