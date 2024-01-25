package com.mycompany.mywebapp.editor;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.mycompany.mywebapp.contracts.Contact;

public class EditContactEditor extends Composite implements Editor<Contact> {

    private static EditContactEditorUiBinder uiBinder = GWT
            .create(EditContactEditorUiBinder.class);

    interface EditContactEditorUiBinder extends UiBinder<Widget, EditContactEditor> {
    }

    @UiField
    TextBox firstName;

    @UiField
    TextBox lastName;

    @UiField
    TextBox emailAddress;

    @Ignore
    Label id;

    public EditContactEditor() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    public void resetValues() {
        firstName.setValue("");
        lastName.setValue("");
        emailAddress.setValue("");
    }

}
