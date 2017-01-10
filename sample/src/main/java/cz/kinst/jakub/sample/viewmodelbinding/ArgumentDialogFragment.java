package cz.kinst.jakub.sample.viewmodelbinding;

import android.os.Bundle;
import android.support.annotation.Nullable;

import cz.kinst.jakub.sample.viewmodelbinding.databinding.DialogArgumentBinding;
import cz.kinst.jakub.viewmodelbinding.ViewModelDialogFragment;


public class ArgumentDialogFragment extends ViewModelDialogFragment<DialogArgumentBinding, ArgumentDialogViewModel> {

	private static final String EXTRA_NAME = "name";

	private ArgumentDialogViewModel.SampleDialogListener mListener;


	public static ArgumentDialogFragment newInstance(String name) {
		Bundle args = new Bundle();
		ArgumentDialogFragment fragment = new ArgumentDialogFragment();
		args.putString(EXTRA_NAME, name);
		fragment.setArguments(args);
		return fragment;
	}


	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		setupViewModel(R.layout.dialog_argument, ArgumentDialogViewModel.class);
		super.onCreate(savedInstanceState);
	}


	@Override
	public void onViewModelInitialized(ArgumentDialogViewModel viewModel) {
		super.onViewModelInitialized(viewModel);
		viewModel.name.set(getArguments().getString(EXTRA_NAME));
		viewModel.setListener(mListener);
	}


	public void setListener(ArgumentDialogViewModel.SampleDialogListener listener) {
		mListener = listener;
	}
}
