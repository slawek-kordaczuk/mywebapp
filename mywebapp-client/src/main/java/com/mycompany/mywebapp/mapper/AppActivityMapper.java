package com.mycompany.mywebapp.mapper;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.mycompany.mywebapp.ClientFactory;
import com.mycompany.mywebapp.activity.ContactActivity;
import com.mycompany.mywebapp.activity.EditContactActivity;
import com.mycompany.mywebapp.common.ContactsColumnDefinitionsFactory;
import com.mycompany.mywebapp.contracts.ContactsServiceAsync;
import com.mycompany.mywebapp.place.ContactPlace;
import com.mycompany.mywebapp.place.EditContactPlace;

public class AppActivityMapper implements ActivityMapper {

    private final ClientFactory clientFactory;

    private final ContactsServiceAsync rpcService;

    /**
     * AppActivityMapper associates each Place with its corresponding
     * {@link Activity}
     *
     * @param clientFactory Factory to be passed to activities
     */
    public AppActivityMapper(ClientFactory clientFactory, ContactsServiceAsync rpcService) {
        super();
        this.clientFactory = clientFactory;
        this.rpcService = rpcService;
    }

    /**
     * Map each Place to its corresponding Activity. This would be a great use
     * for GIN.
     */
    @Override
    public Activity getActivity(Place place) {
        if (place instanceof ContactPlace)
            return new ContactActivity(clientFactory, (ContactPlace) place, rpcService, ContactsColumnDefinitionsFactory
                    .getContactsColumnDefinitions());
        else if (place instanceof EditContactPlace)
            return new EditContactActivity(clientFactory, (EditContactPlace) place, rpcService);

        return null;
    }

}
