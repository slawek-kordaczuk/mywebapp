package com.mycompany.mywebapp.view;

import com.google.gwt.user.client.ui.Widget;
import com.mycompany.mywebapp.common.ColumnDefinition;
import com.mycompany.mywebapp.contracts.Contact;

import java.util.List;

public interface EditContactView <T>{

    public interface Presenter<T> {
        void onAddButtonClicked(Contact result);
        void onCancelButtonClicked();
    }

    void setPresenter(Presenter<T> presenter);
    Widget asWidget();
}
