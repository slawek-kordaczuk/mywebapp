package com.mycompany.mywebapp.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.mycompany.mywebapp.contracts.Contact;
import com.mycompany.mywebapp.editor.EditContactEditor;

import java.util.logging.Level;
import java.util.logging.Logger;

public class EditContactViewImpl<T> extends Composite implements EditContactView<T> {


  @UiTemplate("EditContactView.ui.xml")
  interface EditContactViewUiBinder extends UiBinder<Widget, EditContactViewImpl> {
  }

  private static final EditContactViewUiBinder uiBinder =
          GWT.create(EditContactViewUiBinder.class);

  private Presenter<T> activity;

  Contact contact;

  private String name;

  @UiField
  EditContactEditor contactEditor;

  interface Driver extends SimpleBeanEditorDriver<Contact, EditContactEditor> {
  }

  Driver driver = GWT.create(Driver.class);

  public EditContactViewImpl() {
    contact = new Contact();
    initWidget(uiBinder.createAndBindUi(this));
    driver.initialize(contactEditor);
    driver.edit(contact);
  }

  @UiField
  Button saveButton;

  @UiField
  Button cancelButton;

  Logger logger = Logger.getLogger("EditContactViewImpl");

  @UiHandler("cancelButton")
  void onClickCancel(ClickEvent e) {
    contactEditor.resetValues();
    activity.onCancelButtonClicked();
  }

  @UiHandler("saveButton")
  void onClickSave(ClickEvent e) {
    Contact contact = driver.flush();
    if (driver.hasErrors()) {
      Window.alert("There are errors!");
    }
    activity.onAddButtonClicked(contact);
  }

  @Override
  public void setActivity(Presenter<T> activity) {
    this.activity = activity;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  public Widget asWidget() {
    return this;
  }
}
