package carbon.component;

import android.view.ViewGroup;

import carbon.R;
import carbon.databinding.CarbonBottomsheetRowBinding;
import carbon.widget.ImageView;

public class BottomSheetRow extends DataBindingComponent<MenuItem> {

    public BottomSheetRow(ViewGroup parent) {
        super(parent, R.layout.carbon_bottomsheet_row);
    }

    @Override
    public void bind(MenuItem data) {
        super.bind(data);
        ImageView itemIcon = ((CarbonBottomsheetRowBinding) this.getBinding()).carbonItemIcon;
        itemIcon.setImageDrawable(data.getIcon(getView().getContext()));
        if (data.getIconTint() != null)
            itemIcon.setTintList(data.getIconTint());
    }

}
