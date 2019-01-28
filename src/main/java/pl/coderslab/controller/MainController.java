package pl.coderslab.controller;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private InviterRepository inviterRepository;

    @ModelAttribute("myInvitations")
    public List<Inviter> getMyInvitations(HttpSession session) {
        List<Inviter> result = null;
        User user = (User) session.getAttribute("user");

        if (user != null) {
            long currentUserId = user.getId();

            result = inviterRepository.queryFindByInvitedWhomId(currentUserId);
        }
        return result;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String login(Model model) {

        model.addAttribute("user", new User());

        return "/index";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String loginCheck(@Validated User user, BindingResult result, HttpSession session, Model model) {

        if (result.hasErrors()) {
            result.addError(new ObjectError("user", "Login lub hasło są niepoprawne"));
            return "/index";
        }

        boolean logFlag = false;
        for(User tempUser : userRepository.findAll()) {
            if(tempUser.getLogin().equals(user.getLogin())) {
                logFlag = true;
            }
        }

        if(!logFlag) {
            result.addError(new ObjectError("user", "Login lub hasło są niepoprawne"));
            return "/index";
        }

        String userLogin = user.getLogin();
        String userPass = user.getPassword();
        User presentUser = userRepository.queryFindByLogin(userLogin);
        String presentUserPassword = presentUser.getPassword();


        if (!BCrypt.checkpw(userPass, presentUserPassword)) {
            result.addError(new ObjectError("user", "Login lub hasło są niepoprawne"));
            return "/index";
        }

        session.setAttribute("user", presentUser);
        session.setAttribute("username", presentUser.getFirstName());

        if (presentUser.isAdmin()) {
            return "/admin/panel";
        }

        if (presentUser.getRoom() != null) {
            return "/user/roomResign";
        }

        model.addAttribute("myInvitations", getMyInvitations(session));
        return "/user/panel";
    }

    @RequestMapping("/add")
    @ResponseBody
    public String addUser() {

        User user = new User();
        user.setFirstName("admin");
        user.setLastName("admin");
        user.setLogin("admin");
        user.setEmail("admin@testadminadmin.com");
        user.setPassword(BCrypt.hashpw("admin", BCrypt.gensalt()));
        user.setAdmin(true);

        userRepository.save(user);

        User user1 = new User();
        user1.setFirstName("Marcin");
        user1.setLastName("Plawny");
        user1.setLogin("MarcinPlawny");
        user1.setEmail("marcin@testadminadmin.com");
        user1.setPassword(BCrypt.hashpw("marcin", BCrypt.gensalt()));
        user1.setAdmin(false);

        userRepository.save(user1);

        User user2 = new User();
        user2.setFirstName("Katarzyna");
        user2.setLastName("Plawny");
        user2.setLogin("KatarzynaPlawny");
        user2.setEmail("kasia@testadminadmin.com");
        user2.setPassword(BCrypt.hashpw("katarzyna", BCrypt.gensalt()));
        user2.setAdmin(false);

        userRepository.save(user2);

        User user3 = new User();
        user3.setFirstName("Maciej");
        user3.setLastName("Baka");
        user3.setLogin("MaciejBaka");
        user3.setEmail("maciej@testadminadmin.com");
        user3.setPassword(BCrypt.hashpw("maciej", BCrypt.gensalt()));
        user3.setAdmin(false);

        userRepository.save(user3);

        Room room = new Room();
        room.setCapacity(4);
        room.setName("4-osobowy 1");
        room.setUsers(new ArrayList<>());
        roomRepository.save(room);

        Room room2 = new Room();
        room2.setCapacity(4);
        room2.setName("4-osobowy 2");
        room2.setUsers(new ArrayList<>());
        roomRepository.save(room2);

        Room room3 = new Room();
        room3.setCapacity(3);
        room3.setName("3-osobowy 1");
        room3.setUsers(new ArrayList<>());
        roomRepository.save(room3);

        Room room4 = new Room();
        room4.setCapacity(2);
        room4.setName("2-osobowy 1");
        room4.setUsers(new ArrayList<>());
        roomRepository.save(room4);

        Room room5 = new Room();
        room5.setCapacity(2);
        room5.setName("2-osobowy 2");
        room5.setUsers(new ArrayList<>());
        roomRepository.save(room5);

        return "Dodano użytkowników i pokoje testowe";
    }

}
