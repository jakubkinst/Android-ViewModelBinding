package cz.kinst.jakub.viewmodelbinding.extensions.retrofit;

import android.databinding.ViewDataBinding;

import java.util.HashMap;
import java.util.Map;

import cz.kinst.jakub.viewmodelbinding.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;


/**
 * Created by jakubkinst on 02/12/15.
 */
public abstract class RetrofitCallViewModel<S extends ViewDataBinding> extends ViewModel<S> {

	private Map<String, Call> mCalls = new HashMap<>();


	@Override
	public void onModelRemoved() {
		super.onModelRemoved();
		clearCalls();
	}


	protected <T> void enqueueCall(String callId, Call<T> call, Callback<T> callback) {
		Call existingCall = mCalls.get(callId);
		if(existingCall != null)
			new Thread(existingCall::cancel).start();
		mCalls.put(callId, call);
		call.enqueue(callback);
	}


	protected void clearCalls() {
		for(String callId : mCalls.keySet()) {
			new Thread(mCalls.get(callId)::cancel).start();
		}
		mCalls.clear();
	}
}
