package com.mycompany.mywebapp.view;

import com.gargoylesoftware.htmlunit.javascript.host.Console;
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

  private static EditContactViewUiBinder uiBinder =
          GWT.create(EditContactViewUiBinder.class);

  private Presenter<T> presenter;

  Contact contact;

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
    presenter.onCancelButtonClicked();
  }

  @UiHandler("saveButton")
  void onClickSave(ClickEvent e) {
    Contact flush = driver.flush();
    logger.log(Level.SEVERE, String.valueOf(flush));
    if (driver.hasErrors()) {
      Window.alert("There are errors!");
    }
    presenter.onAddButtonClicked(flush);
  }

  @Override
  public void setPresenter(Presenter<T> presenter) {
    this.presenter = presenter;
  }

  public Widget asWidget() {
    return this;
  }
}
