package cz.kinst.jakub.sample.viewmodelbinding;

import android.os.Bundle;
import android.support.annotation.Nullable;

import cz.kinst.jakub.sample.viewmodelbinding.databinding.DialogSampleBinding;
import cz.kinst.jakub.viewmodelbinding.ViewModelDialogFragment;


public class SampleDialogFragment extends ViewModelDialogFragment<DialogSampleBinding, SampleDialogViewModel> {

	private static final String EXTRA_NAME = "name";

	private SampleDialogViewModel.SampleDialogListener mListener;


	public static SampleDialogFragment newInstance(String name) {
		Bundle args = new Bundle();
		SampleDialogFragment fragment = new SampleDialogFragment();
		args.putString(EXTRA_NAME, name);
		fragment.setArguments(args);
		return fragment;
	}


	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		setupViewModel(R.layout.dialog_sample, SampleDialogViewModel.class);
		super.onCreate(savedInstanceState);

		initViewModel();
	}


	public void setListener(SampleDialogViewModel.SampleDialogListener listener) {
		mListener = listener;
	}


	private void initViewModel() {
		getViewModel().name.set(getArguments().getString(EXTRA_NAME));
		getViewModel().setListener(mListener);
	}
}
