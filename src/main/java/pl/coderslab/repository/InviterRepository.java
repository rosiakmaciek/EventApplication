package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.model.Inviter;

import java.util.List;

public interface InviterRepository extends JpaRepository<Inviter, Long> {

    @Query("select i from Inviter i where i.id = ?1")
    Inviter queryFindById(Long id);

    @Query("select i from Inviter i where i.invitedWhom.id = ?1")
    List<Inviter> queryFindByInvitedWhomId(Long id);

    @Query("select i from Inviter i where i.whichRoom.id = ?1")
    List<Inviter> queryFindByWhichRoomId(Long id);

}
