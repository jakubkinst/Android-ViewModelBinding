package cz.kinst.jakub.sample.viewmodelbinding;

import android.databinding.ObservableField;

import cz.kinst.jakub.viewmodelbinding.ViewModel;


public class SampleDialogViewModel extends ViewModel {
	public final ObservableField<String> name = new ObservableField<>();

	private SampleDialogListener mListener;


	public interface SampleDialogListener {
		void onToastRequested();
	}


	public void showSnackbar() {
		if(mListener != null)
			mListener.onToastRequested();
	}


	public void setListener(SampleDialogListener listener) {
		mListener = listener;
	}
}
