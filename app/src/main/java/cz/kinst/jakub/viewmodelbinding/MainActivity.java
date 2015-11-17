package cz.kinst.jakub.viewmodelbinding;

import android.app.Activity;

import cz.kinst.jakub.viewmodelbinding.base.BaseViewModel;
import cz.kinst.jakub.viewmodelbinding.base.BaseViewModelActivity;
import cz.kinst.jakub.viewmodelbinding.databinding.ActivityMainBinding;


/**
 * Created by jakubkinst on 10/11/15.
 */
public class MainActivity extends BaseViewModelActivity<ActivityMainBinding, MainViewModel> {

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected Class<? extends BaseViewModel> getViewModelClass() {
        return MainViewModel.class;
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void bindView() {
        getViewModel().bindView(this);
    }
}
