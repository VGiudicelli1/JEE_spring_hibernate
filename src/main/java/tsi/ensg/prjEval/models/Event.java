package tsi.ensg.prjEval.models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table
public class Event {
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name = "increment")
    @Column(nullable = false)
    private long id;

    @Column private String title;

    @Column private String content;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column private Date date;

    @Column private Double duration; // duration in day

    @Column private int nbUsersMax;

    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(
            name = "Event_Participant",
            joinColumns = { @JoinColumn(name = "event_id") },
            inverseJoinColumns = { @JoinColumn(name = "participant_id") }
    )
    private List<Participant> participants;

    // ----------------------------------------------------------- //
    //                        CONSTRUCTORS                         //
    // ----------------------------------------------------------- //

    public Event(String title, String content, Date date, double duration, int nbUsersMax, List<Participant> participants) {
        this.setTitle(title);
        this.setContent(content);
        this.setDate(date);
        this.setDuration(duration);
        this.setNbUsersMax(nbUsersMax);
        this.setParticipants(participants);
    }

    public Event() {
        this(null, null, null, 0, 0, null);
    }

    // ----------------------------------------------------------- //
    //                         ACCESSORS                           //
    // ----------------------------------------------------------- //

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Date getDate() {
        return date;
    }

    public Double getDuration() {
        return  duration;
    }

    public int getNbUsersMax() {
        return nbUsersMax;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = (title == null) ? "" : title;
    }

    public void setContent(String content) {
        this.content = (content == null) ? "" : content;
    }

    public void setDate(Date date) {
        this.date = (date == null) ? new Date() : date;
    }

    public void setDuration(Double duration) {
        this.duration = (duration < 0) ? 0 : duration;
    }

    public void setNbUsersMax(int nbUsersMax) {
        this.nbUsersMax = Math.max(0, nbUsersMax);
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = (participants == null) ? new ArrayList<>() : participants;
    }

    // ----------------------------------------------------------- //
    //                           OTHERS                            //
    // ----------------------------------------------------------- //

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", date=" + date +
                ", duration=" + duration +
                ", nbUsersMax=" + nbUsersMax +
                ", participants=List(" + participants.size() + ')' +
                '}';
    }

    public String getDisplay() {
        return this.title;
    }
}
