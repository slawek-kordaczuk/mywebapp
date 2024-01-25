package com.mycompany.mywebapp.common;


import com.mycompany.mywebapp.contracts.ContactDetails;

import java.util.ArrayList;
import java.util.List;

public class ContactsColumnDefinitionsFactory<T> {
  public static List<ColumnDefinition<ContactDetails>>
      getContactsColumnDefinitions() {
    return ContactsColumnDefinitionsImpl.getInstance();
  }

  public static List<ColumnDefinition<ContactDetails>>
      getTestContactsColumnDefinitions() {
    return new ArrayList<ColumnDefinition<ContactDetails>>();
  }
}
