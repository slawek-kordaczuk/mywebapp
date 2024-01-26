package com.mycompany.mywebapp.view;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.mycompany.mywebapp.common.ColumnDefinition;
import com.mycompany.mywebapp.contracts.Contact;

import java.util.List;

public interface EditContactView<T> extends IsWidget {

    public interface Presenter<T> {
        void onAddButtonClicked(Contact result);
        void onCancelButtonClicked();
        void goTo(Place place);
    }

    void setActivity(Presenter<T> activity);
    void setName(String name);
    Widget asWidget();
}
