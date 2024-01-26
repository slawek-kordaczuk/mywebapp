package com.mycompany.mywebapp.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.mycompany.mywebapp.ClientFactory;
import com.mycompany.mywebapp.common.ColumnDefinition;
import com.mycompany.mywebapp.common.SelectionModel;
import com.mycompany.mywebapp.contracts.ContactDetails;
import com.mycompany.mywebapp.contracts.ContactsServiceAsync;
import com.mycompany.mywebapp.place.ContactPlace;
import com.mycompany.mywebapp.view.ContactsView;

import java.util.ArrayList;
import java.util.List;

public class ContactActivity extends AbstractActivity implements ContactsView.Presenter<ContactDetails> {

    private ClientFactory clientFactory;
    private String name;

    private List<ContactDetails> contactDetails;
    private final ContactsServiceAsync rpcService;
    private final SelectionModel<ContactDetails> selectionModel;

    private ContactsView<ContactDetails> view;

    private List<ColumnDefinition<ContactDetails>> columnDefinitions;

    public ContactActivity(ClientFactory clientFactory, ContactPlace place, ContactsServiceAsync rpcService, List<ColumnDefinition<ContactDetails>> contactsColumnDefinitions) {
        this.clientFactory = clientFactory;
        this.name = place.getName();
        this.rpcService = rpcService;
        this.selectionModel = new SelectionModel<>();
        this.columnDefinitions = contactsColumnDefinitions;
    }

    @Override
    public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
        view = clientFactory.getContactView();
        view.setName(name);
        view.setActivity(this);
        containerWidget.setWidget(view.asWidget());
        view.setColumnDefinitions(columnDefinitions);
        fetchContactsDetails();
    }

    @Override
    public void onDeleteButtonClicked() {
        deleteSelectedContacts();
    }

    @Override
    public void onItemSelected(ContactDetails contactDetails) {
        if (selectionModel.isSelected(contactDetails)) {
            selectionModel.removeSelection(contactDetails);
        } else {
            selectionModel.addSelection(contactDetails);
        }
    }

    @Override
    public void goTo(Place place) {
        clientFactory.getPlaceController().goTo(place);
    }

    private void deleteSelectedContacts() {
        List<ContactDetails> selectedContacts = selectionModel.getSelectedItems();
        ArrayList<String> ids = new ArrayList<String>();

        for (int i = 0; i < selectedContacts.size(); ++i) {
            ids.add(selectedContacts.get(i).getId());
        }

        rpcService.deleteContacts(ids, new AsyncCallback<ArrayList<ContactDetails>>() {
            public void onSuccess(ArrayList<ContactDetails> result) {
                contactDetails = result;
                sortContactDetails();
                view.setRowData(contactDetails);
            }

            public void onFailure(Throwable caught) {
                System.out.println("Error deleting selected contacts");
            }
        });
    }

    private void fetchContactsDetails() {
        rpcService.getContactDetails(new AsyncCallback<ArrayList<ContactDetails>>() {
            public void onSuccess(ArrayList<ContactDetails> result) {
                contactDetails = result;
                sortContactDetails();
                view.setRowData(contactDetails);
            }

            public void onFailure(Throwable caught) {
                System.out.println("Error fetching contacts");
            }
        });
    }

    public void sortContactDetails() {

        // Yes, we could use a more optimized method of sorting, but the
        //  point is to create a test case that helps illustrate the higher
        //  level concepts used when creating MVP-based applications.
        //
        for (int i = 0; i < contactDetails.size(); ++i) {
            for (int j = 0; j < contactDetails.size() - 1; ++j) {
                if (contactDetails.get(j).getDisplayName().compareToIgnoreCase(contactDetails.get(j + 1).getDisplayName()) >= 0) {
                    ContactDetails tmp = contactDetails.get(j);
                    contactDetails.set(j, contactDetails.get(j + 1));
                    contactDetails.set(j + 1, tmp);
                }
            }
        }
    }
}
