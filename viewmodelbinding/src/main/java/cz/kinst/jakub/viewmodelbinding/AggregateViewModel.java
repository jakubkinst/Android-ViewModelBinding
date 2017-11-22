package cz.kinst.jakub.viewmodelbinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import android.content.Intent;
import android.support.annotation.NonNull;

/**
 * A view model that aggregates and passes messages to contained sub-views.
 */
public class AggregateViewModel extends ViewModel {

    @NonNull
    private final List<SubViewModel> subviewModels = new ArrayList<>();

    /**
     * Returns an unmodifiable copied list of the subviews. Use the appropriate add or remove methods
     * to change the list of subviews.
     * @return a copied, unmodifiable list of subviews
     */
    public List<SubViewModel> getSubviews() {
        return Collections.unmodifiableList(subviewModels);
    }

    /**
     * Adds the provided viewmodels to the list of subviews.
     * @param viewModels the viewmodels to add
     */
    public void addSubviews(@NonNull final SubViewModel... viewModels) {
        subviewModels.addAll(Arrays.asList(viewModels));
    }

    /**
     * Removes the provided viewmodels from the view.
     * @param viewModels the viewmodels to remove
     */
    public void removeSubviews(@NonNull final SubViewModel... viewModels) {
        subviewModels.removeAll(Arrays.asList(viewModels));
    }

    // region ViewModel

    @Override
    public void onViewModelCreated() {
        super.onViewModelCreated();
        for (final SubViewModel vm : subviewModels) {
            vm.onViewModelCreated();
        }
    }

    @Override
    public void onViewModelDestroyed() {
        super.onViewModelDestroyed();
        for (final SubViewModel vm : subviewModels) {
            vm.onViewModelDestroyed();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        for (final SubViewModel vm : subviewModels) {
            vm.onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        for (final SubViewModel vm : subviewModels) {
            vm.onPause();
        }
    }

    @Override
    public void onViewDetached(final boolean finalDetachment) {
        super.onViewDetached(finalDetachment);
        for (final SubViewModel vm : subviewModels) {
            vm.onViewDetached(finalDetachment);
        }
    }

    @Override
    public void onViewAttached(final boolean firstAttachment) {
        super.onViewAttached(firstAttachment);
        for (final SubViewModel vm : subviewModels) {
            if (firstAttachment) {
                vm.bindView(getView());
            }
            vm.onViewAttached(firstAttachment);
        }
    }

    @Override
    public void onRequestPermissionsResult(final int requestCode, final String[] permissions, final int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for (final SubViewModel vm : subviewModels) {
            vm.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        for (final SubViewModel vm : subviewModels) {
            vm.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    protected void bindView(final ViewInterface<?, ? extends cz.kinst.jakub.viewmodelbinding.ViewModel> viewInterface) {
        super.bindView(viewInterface);
        for (final SubViewModel vm : subviewModels) {
            vm.bindView(viewInterface);
        }
    }

    // endregion

}
