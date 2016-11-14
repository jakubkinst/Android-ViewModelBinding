package cz.kinst.jakub.sample.viewmodelbinding;

import android.databinding.ObservableInt;
import android.view.View;

import cz.kinst.jakub.viewmodelbinding.ViewModel;


public class SampleDialogViewModel extends ViewModel {
	public final ObservableInt number = new ObservableInt();
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
