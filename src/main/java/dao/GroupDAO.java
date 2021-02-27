package dao;


import model.Contact;
import model.Group;

import java.util.List;

public interface GroupDAO {


    void add(Group group);
    void delete(long[] ids);
    Group findOne(long id);
    List<Group> list();
    List<Group> list(Group group, int start,
                       int count);
    long count();
}
