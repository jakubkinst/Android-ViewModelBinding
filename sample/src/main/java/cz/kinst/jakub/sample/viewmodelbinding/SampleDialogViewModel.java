package cz.kinst.jakub.sample.viewmodelbinding;

import android.view.View;

import cz.kinst.jakub.sample.viewmodelbinding.databinding.DialogSampleBinding;
import cz.kinst.jakub.viewmodelbinding.ViewModel;


public class SampleDialogViewModel extends ViewModel<DialogSampleBinding> {
	SampleDialogListener mListener;


	public interface SampleDialogListener {
		void onButtonClicked();
	}


	public void onClickedButton(View v) {
		if(mListener != null)
			mListener.onButtonClicked();
	}


	public void setListener(SampleDialogListener listener) {
		mListener = listener;
	}
}
