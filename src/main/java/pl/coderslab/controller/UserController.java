package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.model.Inviter;
import pl.coderslab.model.Room;
import pl.coderslab.model.User;
import pl.coderslab.repository.InviterRepository;
import pl.coderslab.repository.RoomRepository;
import pl.coderslab.repository.UserRepository;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private InviterRepository inviterRepository;

    @ModelAttribute("rooms")
    public List<Room> getRooms() {
        return roomRepository.queryFindAvailable();
    }

    @ModelAttribute("peopleToChoose")
    public List<User> getOtherUsers(HttpSession session) {

        User currentUser = (User) session.getAttribute("user");

        List<User> listToDeleteFrom = userRepository.findAll();
        List<User> listToDisplay = new ArrayList<>();

        for (User foundUser : listToDeleteFrom) {
            if (!foundUser.isAdmin() && (currentUser == null || currentUser.getId() != foundUser.getId())) {
                listToDisplay.add(foundUser);

            }
        }

        return listToDisplay;
    }

    @ModelAttribute("myInvitations")
    public List<Inviter> getMyInvitations(HttpSession session) {

        long currentUserId = ((User) session.getAttribute("user")).getId();

        List<Inviter> result =  inviterRepository.queryFindByInvitedWhomId(currentUserId);

        return result;
    }

    @RequestMapping(value = "/chooseRoom", method = RequestMethod.GET)
    public String chooseRoomGet(Model model, HttpSession session) {

        User user = (User) session.getAttribute("user");
        model.addAttribute("room", new Room());

        return "/user/chooseRoom";
    }

    @RequestMapping(value = "/chooseRoom", method = RequestMethod.POST)
    public String chooseRoomPost(@RequestParam Long id, HttpSession session) {

        User user = (User) session.getAttribute("user");
        Room room = roomRepository.findOne(id);

        room.getUsers().add(user);
        room.setGuests(room.getGuests() + 1);

        user.setRoom(room);
        roomRepository.save(room);

        return "/user/roomResign";
    }

    @RequestMapping(value = "/roomResign", method = RequestMethod.GET)
    public String roomResignGet(HttpSession session) {

        User user = (User) session.getAttribute("user");
        Room room = user.getRoom();
        room.setGuests(user.getRoom().getGuests() - 1);
        user.setRoom(null);

        userRepository.save(user);
        roomRepository.save(room);

        return "/user/success";
    }

    @RequestMapping(value = "/sendInvite", method = RequestMethod.GET)
    public String sendInviteGet(Model model) {

        model.addAttribute("user", new User());

        return "/user/sendInvite";
    }

    @RequestMapping(value = "/sendInvite", method = RequestMethod.POST)
    public String sendInvitePost(@RequestParam Long id, HttpSession session) {  //dostajemy id zaproszonego, pokoj nasz i uzytkownik nasz w sesji

        User invitedWho = (User) session.getAttribute("user");
        User invitedWhom = userRepository.queryFindById(id);
        Room whichRoom = invitedWho.getRoom();

        Inviter inviter = new Inviter();
        inviter.setInvitedWho(invitedWho);
        inviter.setInvitedWhom(invitedWhom);
        inviter.setWhichRoom(whichRoom);

        inviterRepository.save(inviter);

        return "redirect:/user/sendInvite";
    }

    @RequestMapping(value = "/addFromInvite", method = RequestMethod.GET)
    public String addFromInviteGet(@RequestParam long id, HttpSession session) {

        User user = (User) session.getAttribute("user");
        Room room = roomRepository.findOne(id);

        room.getUsers().add(user);
        room.setGuests(room.getGuests() + 1);

        user.setRoom(room);
        roomRepository.save(room);

        if(room.getCapacity() == room.getGuests()) {

            List<Inviter> redundantInviters = inviterRepository.queryFindByWhichRoomId(room.getId());
            for(Inviter inviter : redundantInviters) {
                inviterRepository.delete(inviter);
            }

        }

        return "/user/roomChosen";
    }

}
