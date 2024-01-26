package com.mycompany.mywebapp;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.mycompany.mywebapp.contracts.ContactsService;
import com.mycompany.mywebapp.contracts.ContactsServiceAsync;
import com.mycompany.mywebapp.mapper.AppActivityMapper;
import com.mycompany.mywebapp.mapper.AppPlaceHistoryMapper;
import com.mycompany.mywebapp.place.ContactPlace;

public class Contacts implements EntryPoint {

  private Place defaultPlace = new ContactPlace("list");
  private SimplePanel appWidget = new SimplePanel();

  public void onModuleLoad() {
    ContactsServiceAsync rpcService = GWT.create(ContactsService.class);
    ClientFactory clientFactory = GWT.create(ClientFactory.class);
    EventBus eventBus = clientFactory.getEventBus();
    PlaceController placeController = clientFactory.getPlaceController();

    // Start ActivityManager for the main widget with our ActivityMapper
    ActivityMapper activityMapper = new AppActivityMapper(clientFactory, rpcService);
    ActivityManager activityManager = new ActivityManager(activityMapper, eventBus);
    activityManager.setDisplay(appWidget);

    // Start PlaceHistoryHandler with our PlaceHistoryMapper
    AppPlaceHistoryMapper historyMapper= GWT.create(AppPlaceHistoryMapper.class);
    PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
    historyHandler.register(placeController, eventBus, defaultPlace);

    RootPanel.get().add(appWidget);
    // Goes to place represented on URL or default place
    historyHandler.handleCurrentHistory();
  }
 }
