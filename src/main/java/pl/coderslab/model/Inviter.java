package pl.coderslab.model;

import javax.persistence.*;

@Entity
@Table(name = "inviter")
public class Inviter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private User invitedWho;

    @ManyToOne
    private User invitedWhom;

    @ManyToOne
    private Room whichRoom;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getInvitedWho() {
        return invitedWho;
    }

    public void setInvitedWho(User invitedWho) {
        this.invitedWho = invitedWho;
    }

    public User getInvitedWhom() {
        return invitedWhom;
    }

    public void setInvitedWhom(User invitedWhom) {
        this.invitedWhom = invitedWhom;
    }

    public Room getWhichRoom() {
        return whichRoom;
    }

    public void setWhichRoom(Room whichRoom) {
        this.whichRoom = whichRoom;
    }
}
