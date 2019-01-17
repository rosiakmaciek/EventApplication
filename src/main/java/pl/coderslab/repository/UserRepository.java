package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.model.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.login = ?1")
    User queryFindByLogin(String userLogin);

    @Query("select u from User u where u.id = ?1")
    User queryFindById(Long id);

    @Query("select u from User u order by u.room.id asc")
    List<User> queryFindAllOrderByRoom();

}
