package services;

import dao.GroupDAO;
import model.Contact;
import model.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {
    @Autowired
    private GroupDAO groupDAO;

    @Transactional
    public void addGroup(Group group) {
        groupDAO.add(group);
    }

    @Transactional
    public void deleteGroup(long[] ids) {
        groupDAO.delete(ids);
    }

    @Transactional
    public List<Group> listGroups() {
        return groupDAO.list();
    }

    @Transactional(readOnly = true)
    public long count() {
        return groupDAO.count();
    }

    @Transactional(readOnly = true)
    public List<Group> listGroup(Group group, int start, int count) {
        return groupDAO.list(group, start, count);
    }

    @Transactional(readOnly = true)
    public List<Group> listGroup(Group group) {
        return groupDAO.list(group, 0, 0);
    }

    @Transactional(readOnly = true)
    public Group findGroup(long id) {
        return groupDAO.findOne(id);
    }

    @Transactional
    public List<Group> searchGroup(String pattern) {
        return null;
    }
}
