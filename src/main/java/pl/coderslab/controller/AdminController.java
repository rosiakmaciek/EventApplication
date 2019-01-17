package pl.coderslab.controller;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.model.Room;
import pl.coderslab.model.User;
import pl.coderslab.repository.RoomRepository;
import pl.coderslab.repository.UserRepository;
import pl.coderslab.util.PasswordCreator;
import pl.coderslab.util.Report;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoomRepository roomRepository;

    @ModelAttribute("users")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @ModelAttribute("rooms")
    public List<Room> getRooms() {
        return roomRepository.findAll();
    }

    @ModelAttribute("allUsers")
    public List<User> getAllUsers() {

        List<User> listToDeleteFrom = userRepository.queryFindAllOrderByRoom();
        List<User> listToDisplay = new ArrayList<>();

        for (User foundUser : listToDeleteFrom) {
            if (!foundUser.isAdmin()) {
                listToDisplay.add(foundUser);
            }
        }

        return listToDisplay;
    }

    PasswordCreator passwordCreator = new PasswordCreator();

    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public String add(Model model) {

        model.addAttribute("user", new User());
        return "/admin/addUser";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(@ModelAttribute User user) {

        user.setLogin(user.getFirstName() + user.getLastName());

        for (User myUser : getUsers()) {
            int i = 1;
            while(user.getLogin().equals(myUser.getLogin())) {
                user.setLogin(user.getLogin() + "_" + i);
                i++;
            }
        }

        String password = passwordCreator.createPass(); // przed haszowaniem

        Report report = new Report();
        report.sendMail(user.getLogin(), user.getEmail(), password);

        user.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
        user.setAdmin(false);
        userRepository.save(user);
        return "redirect:/admin/addUser";
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
    public String delete(Model model) {

        model.addAttribute("user", new User());
        return "/admin/deleteUser";
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public String deleteUser(@RequestParam Long id) {

        User user = userRepository.findOne(id);
        userRepository.delete(user);

        return "redirect:/admin/deleteUser";
    }

    @RequestMapping(value = "/addRoom", method = RequestMethod.GET)
    public String addRoomGet(Model model) {

        model.addAttribute("room", new Room());
        return "/admin/addRoom";
    }

    @RequestMapping(value = "/addRoom", method = RequestMethod.POST)
    public String addRoomPost(@ModelAttribute Room room, @RequestParam int numberOfRooms) {

        int howManyRooms = roomRepository.queryFindByCapacity(room.getCapacity()).size();
        int myCapacity = room.getCapacity();

        for(int i = 0; i < numberOfRooms; i++) {

            Room newRoom = new Room();

            howManyRooms += 1;
            newRoom.setName(String.valueOf(myCapacity) + "-osobowy " + String.valueOf(howManyRooms));
            newRoom.setCapacity(myCapacity);
            roomRepository.save(newRoom);
        }
        return "redirect:/admin/addRoom";
    }

    @RequestMapping(value = "/deleteRoom", method = RequestMethod.GET)
    public String deleteRoomGet(Model model) {

        model.addAttribute("room", new Room());
        return "/admin/deleteRoom";
    }

    @RequestMapping(value = "/deleteRoom", method = RequestMethod.POST)
    public String deleteRoomPost(@RequestParam Long id) {

        Room room = roomRepository.findOne(id);
        roomRepository.delete(room);
        return "redirect:/admin/deleteRoom";
    }

    @RequestMapping(value = "/panel", method = RequestMethod.GET)
    public String redirectionToPanel() {

        return "/admin/panel";
    }

    @RequestMapping(value = "/raport", method = RequestMethod.GET)
    public String createRaport(HttpSession session) {

        return "/admin/raport";
    }

}
