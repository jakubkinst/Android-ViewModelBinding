package cz.kinst.jakub.viewmodelbinding;

import org.junit.Test;

import cz.kinst.jakub.viewmodelbinding.mock.BasicViewModel;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class ViewModelProviderTest {
	@Test
	public void viewModelRetrieving_isCorrect() throws Exception {
		String viewModelId = "viewmodelid";

		ViewModelProvider.ViewModelWrapper viewModelWrapper = ViewModelProvider.getInstance().getViewModel(viewModelId, BasicViewModel.class);
		assertTrue(viewModelWrapper.wasCreated());
		assertTrue(viewModelWrapper.getViewModel() instanceof BasicViewModel);

		ViewModelProvider.ViewModelWrapper viewModelWrapper2 = ViewModelProvider.getInstance().getViewModel(viewModelId, BasicViewModel.class);
		assertFalse(viewModelWrapper2.wasCreated());
		assertTrue(viewModelWrapper.getViewModel() instanceof BasicViewModel);

		ViewModelProvider.getInstance().removeViewModel(viewModelId);
		ViewModelProvider.ViewModelWrapper viewModelWrapper3 = ViewModelProvider.getInstance().getViewModel(viewModelId, BasicViewModel.class);
		assertTrue(viewModelWrapper3.wasCreated());
		assertTrue(viewModelWrapper.getViewModel() instanceof BasicViewModel);
	}
}
