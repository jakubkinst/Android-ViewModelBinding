package cz.kinst.jakub.viewmodelbinding

import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.app.AppCompatActivity


abstract class ViewModelActivity<T : ViewDataBinding, S : ViewModel<T>> : AppCompatActivity(), ViewInterface<T> {
    companion object {
        private val VIEW_MODEL_GENERIC_TYPE_POSITION = 1
    }

    private val mViewModelBindingHelper = ViewModelBindingHelper<S, T>()

    fun getViewModel() = mViewModelBindingHelper.viewModel

    override fun getContext() = activity


    override fun getActivity() = this


    override fun getBinding() = mViewModelBindingHelper.binding


    override fun getBundle() = intent?.extras


    override fun getViewModelDataBindingId() = BR.viewModel


    override fun onSaveInstanceState(outState: Bundle) {
        mViewModelBindingHelper.onSaveInstanceState(outState)
        super.onSaveInstanceState(outState)
    }

    public override fun onDestroy() {
        mViewModelBindingHelper.onDestroy(this)
        super.onDestroy()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModelBindingHelper.onCreate(this, savedInstanceState, mViewModelBindingHelper.getViewModelClass(this, VIEW_MODEL_GENERIC_TYPE_POSITION))
    }


    override fun onResume() {
        super.onResume()
        mViewModelBindingHelper.onResume()
    }


    override fun onPause() {
        super.onPause()
        mViewModelBindingHelper.onPause()
    }
}
