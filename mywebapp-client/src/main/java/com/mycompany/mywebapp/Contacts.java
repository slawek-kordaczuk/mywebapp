package com.mycompany.mywebapp;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.RootPanel;
import com.mycompany.mywebapp.contracts.ContactsService;
import com.mycompany.mywebapp.contracts.ContactsServiceAsync;

public class Contacts implements EntryPoint {

  public void onModuleLoad() {
    ContactsServiceAsync rpcService = GWT.create(ContactsService.class);
    HandlerManager eventBus = new HandlerManager(null);
    AppController appViewer = new AppController(rpcService, eventBus);
    appViewer.go(RootPanel.get());
  }
 }
