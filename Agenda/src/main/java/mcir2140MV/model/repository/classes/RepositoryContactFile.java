package mcir2140MV.model.repository.classes;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

import mcir2140MV.exceptions.InvalidFormatException;
import mcir2140MV.model.base.Contact;
import mcir2140MV.model.repository.interfaces.RepositoryContact;

public class RepositoryContactFile implements RepositoryContact {

//    private static final String filename = "bin\\files\\contacts.txt";
    private static final String filename = "files\\contacts.txt";
    private List<Contact> contacts;

    public RepositoryContactFile() throws Exception {
        contacts = new LinkedList<Contact>();
        BufferedReader br = null;
//		String currentDir = new File(".").getAbsolutePath();
//		System.out.println(currentDir);
        try {
            br = new BufferedReader(new InputStreamReader(
                    new FileInputStream(new File("/home/catalin/Facultate/Sem2/VVSS/Lab1/Agenda/src/main/java/files/contacts.txt"))));
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                Contact c = null;
                try {
                    c = Contact.fromString(line, "#");
//                    c = Contact.fromString(line, " ");
                } catch (InvalidFormatException e) {
                    throw new Exception("Error in file at line " + i,
                            new Throwable(e.getCause().getMessage()));
                }
                contacts.add(c);
                i++;
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally
        {
            if (br != null) br.close();
        }
    }

    @Override
    public List<Contact> getContacts() {
        return new LinkedList<Contact>(contacts);
    }

    @Override
    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    @Override
    public boolean removeContact(Contact contact) {
        int index = contacts.indexOf(contact);
        if (index < 0)
            return false;
        else
            contacts.remove(index);
        return true;
    }

    @Override
    public boolean saveContracts() {
        PrintWriter pw = null;
        try{
            pw = new PrintWriter(new FileOutputStream(filename));
            for(Contact c : contacts)
                pw.println(c.toString());
        }catch (Exception e)
        {
            return false;
        }
        finally{
            if (pw!=null) pw.close();
        }
        return true;
    }

    @Override
    public int count() {
        return contacts.size();
    }

    @Override
    public Contact getByName(String string) {
        for (Contact c : contacts)
            if (c.getName().equals(string))
                return c;
        return null;
    }

}
