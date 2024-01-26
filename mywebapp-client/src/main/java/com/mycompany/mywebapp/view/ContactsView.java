package com.mycompany.mywebapp.view;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.mycompany.mywebapp.common.ColumnDefinition;

import java.util.List;

public interface ContactsView<T> extends IsWidget {

  public interface Presenter<T> {
    void onDeleteButtonClicked();
    void onItemSelected(T selectedItem);
    void goTo(Place place);
  }

  void setName(String name);
  void setActivity(Presenter<T> activity);
  void setColumnDefinitions(List<ColumnDefinition<T>> columnDefinitions);
  void setRowData(List<T> rowData);
  Widget asWidget();
}
