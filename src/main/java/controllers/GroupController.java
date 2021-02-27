package controllers;

import model.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import services.ContactService;
import services.GroupService;

@Controller
public class GroupController {
    static final int DEFAULT_GROUP_ID = -1;
    private static final int ITEMS_PER_PAGE = 6;

    @Autowired
    private ContactService contactService;
    @Autowired
    private GroupService groupService;


    @RequestMapping("/group")
    public String index(Model model, @RequestParam(required = false, defaultValue = "0") Integer page) {
        if (page < 0) page = 0;

        long totalCount = groupService.count();
        int start = page * ITEMS_PER_PAGE;
        long pageCount = (totalCount / ITEMS_PER_PAGE) +
                ((totalCount % ITEMS_PER_PAGE > 0) ? 1 : 0);

        model.addAttribute("groups",
                groupService.listGroup(null, start, ITEMS_PER_PAGE));
        model.addAttribute("pages", pageCount);
        return "groups";
    }

    @RequestMapping("/group_add_page")
    public String groupAddPage() {
        return "group_add_page";
    }

    @RequestMapping(value = "/group/add",
            method = RequestMethod.POST)
    public String groupAdd(@RequestParam String name) {
        groupService.addGroup(new Group(name));
        return "redirect:/group";
    }

    @RequestMapping("/group/{id}")
    public String listGroup(@PathVariable(value = "id") long groupId,
                            Model model) {
        Group group = (groupId != DEFAULT_GROUP_ID) ?
                groupService.findGroup(groupId) : null;

        model.addAttribute("groups", groupService.listGroups());
        model.addAttribute("contacts", contactService.listContacts(group));

        return "index";
    }

    @RequestMapping(value = "/group/delete", method = RequestMethod.POST)
    public ResponseEntity<Void> delete(
            @RequestParam(value = "toDelete[]", required = false)
                    long[] toDelete) {
        if (toDelete != null && toDelete.length > 0)
            groupService.deleteGroup(toDelete);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
