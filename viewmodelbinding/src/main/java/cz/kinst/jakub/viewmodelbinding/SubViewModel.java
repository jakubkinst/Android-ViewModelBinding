package cz.kinst.jakub.viewmodelbinding;

import android.content.Intent;

/**
 * A sub-view to be used by the AggregateViewModel. This only exists to make onActivityResult() and bindView() available
 * to the aggregate to call during setup.
 */
public class SubViewModel extends ViewModel {

    @Override
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void bindView(final ViewInterface<?, ? extends cz.kinst.jakub.viewmodelbinding.ViewModel> viewInterface) {
        super.bindView(viewInterface);
    }

}
