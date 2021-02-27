package services;

import model.Contact;
import model.Group;

import java.util.List;

public interface GroupService {
    void addGroup(Group group);

    void deleteGroup(long[] ids);

    List<Group> listGroups();

    long count();

    List<Group> listGroup(Group group, int start, int count);

    Group findGroup(long id);

    List<Group> searchGroup(String pattern);
}
