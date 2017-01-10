package cz.kinst.jakub.viewmodelbinding;

interface OnViewModelInitializedCallback<T extends ViewModel> {
	void onViewModelInitialized(T viewModel);
}
