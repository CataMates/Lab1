package mcir2140MV.model.repository.interfaces;

import java.util.List;

import mcir2140MV.model.base.Contact;

public interface RepositoryContact {

    List<Contact> getContacts();
    void addContact(Contact contact);
    boolean removeContact(Contact contact);
    boolean saveContracts();
    int count();
    Contact getByName(String string);
}
