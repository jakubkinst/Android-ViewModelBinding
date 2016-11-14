package cz.kinst.jakub.viewmodelbinding;

import android.databinding.BindingAdapter;
import android.view.View;


public class BindingAdapters {

	@BindingAdapter("show")
	public static void setShow(View view, boolean visible) {
		view.setVisibility(visible ? View.VISIBLE : View.GONE);
	}


	@BindingAdapter("hide")
	public static void setHide(View view, boolean visible) {
		setShow(view, !visible);
	}


	@BindingAdapter("invisible")
	public static void setInvisible(View view, boolean invisible) {
		view.setVisibility(invisible ? View.INVISIBLE : View.VISIBLE);
	}
}
