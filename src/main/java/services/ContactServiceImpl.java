package services;


import dao.ContactDAO;
import dao.GroupDAO;
import model.Contact;
import model.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {
    @Autowired
    private ContactDAO contactDAO;
    @Autowired
    private GroupDAO groupDAO;

    @Transactional //(rollbackFor = RuntimeException.class)
    public void addContact(Contact contact) {
        contactDAO.add(contact);
    }


    @Transactional
    public void deleteContact(long[] ids) {
        contactDAO.delete(ids);
    }

    @Transactional(readOnly = true)
    public List<Group> listGroups() {
        return groupDAO.list();
    }


    @Transactional(readOnly = true)
    public List<Contact> listContacts(Group group, int start, int count) {
        return contactDAO.list(group, start, count);
    }

    @Transactional(readOnly = true)
    public List<Contact> listContacts(Group group) {
        return contactDAO.list(group, 0, 0);
    }

    @Transactional(readOnly = true)
    public long count() {
        return contactDAO.count();
    }

    @Transactional(readOnly = true)
    public Group findGroup(long id) {
        return groupDAO.findOne(id);
    }

    @Transactional(readOnly = true)
    public List<Contact> searchContacts(String pattern) {
        return contactDAO.list(pattern);
    }
}
