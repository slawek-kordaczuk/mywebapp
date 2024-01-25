package com.mycompany.mywebapp.event;

import com.google.gwt.event.shared.EventHandler;

public interface AddContactEventHandler extends EventHandler {
  void onAddContact(AddContactEvent event);
}
