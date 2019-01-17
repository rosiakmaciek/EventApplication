package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.model.Room;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query("select r from Room r where r.capacity = ?1")
    List<Room> queryFindByCapacity(int roomCapacity);

    @Query("select r from Room r where r.id = ?1")
    Room queryFindById(Long id);

    @Query("select r from Room r where r.capacity > r.guests ")
    List<Room> queryFindAvailable();

}
