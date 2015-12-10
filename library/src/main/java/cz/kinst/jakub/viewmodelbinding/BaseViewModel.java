package cz.kinst.jakub.viewmodelbinding;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;


/**
 * Created by jakubkinst on 10/11/15.
 */
public class BaseViewModel<T> extends BaseObservable {
    @Nullable
    private ViewInterface<T> mView;
    private String mUniqueIdentifier;


    public BaseViewModel() {
    }


    public void onResume() {

    }


    public void onPause() {

    }


    public void bindView(ViewInterface viewInterface) {
        mView = viewInterface;
    }


    @SuppressWarnings("EmptyMethod")
    public void onViewDetached(boolean finalDetachment) {
        mView = null;
    }


    @SuppressWarnings("EmptyMethod")
    public void onViewAttached(boolean firstAttachment) {
    }


    @SuppressWarnings("EmptyMethod")
    public void onModelRemoved() {
        mView = null;
    }


    @Nullable
    public ViewInterface<T> getView() {
        return mView;
    }


    @Nullable
    public Context getContext() {
        if (mView == null) return null;
        else return mView.getContext();
    }


    @Nullable
    public Activity getActivity() {
        if (mView == null) return null;
        else return mView.getActivity();
    }


    public boolean hasViewAttached() {
        return mView != null;
    }


    /**
     * @return An app unique identifier for the current viewmodel instance (will be kept during orientation
     * change). This identifier will be reset in case the corresponding activity is killed.
     */
    public String getUniqueIdentifier() {
        return mUniqueIdentifier;
    }

    void setUniqueIdentifier(String uniqueIdentifier) {
        mUniqueIdentifier = uniqueIdentifier;
    }

    public void startActivityForResult(Intent intent, int requestCode) {
        if (!hasViewAttached()) return;
        if (mView instanceof Activity) {
            ((Activity) mView).startActivityForResult(intent, requestCode);
        } else if (mView instanceof Fragment) {
            ((Fragment) mView).startActivityForResult(intent, requestCode);
        }
    }
}
