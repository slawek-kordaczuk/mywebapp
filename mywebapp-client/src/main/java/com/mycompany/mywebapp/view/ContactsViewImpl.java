package com.mycompany.mywebapp.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.*;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;
import com.mycompany.mywebapp.common.ColumnDefinition;
import com.mycompany.mywebapp.contracts.ContactDetails;
import com.mycompany.mywebapp.place.EditContactPlace;

import java.util.List;

public class ContactsViewImpl<T> extends Composite implements ContactsView<T> {

    @UiTemplate("ContactsView.ui.xml")
    interface ContactsViewUiBinder extends UiBinder<Widget, ContactsViewImpl> {
    }

    private static final ContactsViewUiBinder uiBinder =
            GWT.create(ContactsViewUiBinder.class);

    private String name;

    @UiField
    HTML contactsTable;
    @UiField
    Button addButton;
    @UiField
    Button deleteButton;

    private Presenter<T> activity;
    private List<ColumnDefinition<T>> columnDefinitions;
    private List<T> rowData;

    public ContactsViewImpl() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public void setActivity(Presenter<T> activity) {
        this.activity = activity;
    }

    public void setColumnDefinitions(
            List<ColumnDefinition<T>> columnDefinitions) {
        this.columnDefinitions = columnDefinitions;
    }

    public void setRowData(List<T> rowData) {
        this.rowData = rowData;

        TableElement table = Document.get().createTableElement();
        TableSectionElement tbody;
        table.appendChild(tbody = Document.get().createTBodyElement());

        for (int i = 0; i < rowData.size(); ++i) {
            TableRowElement row = tbody.insertRow(-1);
            T t = rowData.get(i);

            for (int j = 0; j < columnDefinitions.size(); ++j) {
                TableCellElement cell = row.insertCell(-1);
                StringBuilder sb = new StringBuilder();
                columnDefinitions.get(j).render(t, sb);
                cell.setInnerHTML(sb.toString());

                // TODO: Really total hack! There's gotta be a better way...
                Element child = cell.getFirstChildElement();
                if (child != null) {
                    Event.sinkEvents(child, Event.ONFOCUS | Event.ONBLUR);
                }
            }
        }

        contactsTable.setHTML(table.getInnerHTML());
    }

    @UiHandler("addButton")
    void onAddButtonClicked(ClickEvent event) {
        if (activity != null) {
            activity.goTo(new EditContactPlace(name));
        }
    }

    @UiHandler("deleteButton")
    void onDeleteButtonClicked(ClickEvent event) {
        if (activity != null) {
            activity.onDeleteButtonClicked();
        }
    }

    private TableCellElement findNearestParentCell(Node node) {
        while ((node != null)) {
            if (Element.is(node)) {
                Element elem = Element.as(node);

                String tagName = elem.getTagName();
                if ("td".equalsIgnoreCase(tagName) || "th".equalsIgnoreCase(tagName)) {
                    return elem.cast();
                }
            }
            node = node.getParentNode();
        }
        return null;
    }


    @UiHandler("contactsTable")
    void onTableClicked(ClickEvent event) {
        if (activity != null) {
            EventTarget target = event.getNativeEvent().getEventTarget();
            Node node = Node.as(target);
            TableCellElement cell = findNearestParentCell(node);
            if (cell == null) {
                return;
            }

            TableRowElement tr = TableRowElement.as(cell.getParentElement());
            int row = tr.getSectionRowIndex();

            if (cell != null) {
                if (shouldEditItem(cell)) {
                    activity.goTo(new EditContactPlace(name, (ContactDetails) rowData.get(row)));
                }
                if (shouldSelectItem(cell)) {
                    activity.onItemSelected(rowData.get(row));
                }
            }
        }
    }

    private boolean shouldEditItem(TableCellElement cell) {
        boolean shouldEditItem = false;

        if (cell != null) {
            ColumnDefinition<T> columnDefinition =
                    columnDefinitions.get(cell.getCellIndex());

            if (columnDefinition != null) {
                shouldEditItem = columnDefinition.isClickable();
            }
        }

        return shouldEditItem;
    }

    private boolean shouldSelectItem(TableCellElement cell) {
        boolean shouldSelectItem = false;

        if (cell != null) {
            ColumnDefinition<T> columnDefinition =
                    columnDefinitions.get(cell.getCellIndex());

            if (columnDefinition != null) {
                shouldSelectItem = columnDefinition.isSelectable();
            }
        }

        return shouldSelectItem;
    }

    public Widget asWidget() {
        return this;
    }
}
