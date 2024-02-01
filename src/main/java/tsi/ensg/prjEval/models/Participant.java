package tsi.ensg.prjEval.models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table
public class Participant {
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name = "increment")
    @Column(nullable = false)
    private long id;

    @Column private String firstName;
    @Column private String lastName;
    @Column private String email;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column private Date birthdate;
    @Column private String company;
    @Column private String comments;

    @ManyToMany(mappedBy = "participants")
    private List<Event> events;

    // ----------------------------------------------------------- //
    //                        CONSTRUCTORS                         //
    // ----------------------------------------------------------- //

    public Participant(String firstName, String lastName, String email, Date birthdate, String company, String comments, List<Event> events) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setEmail(email);
        this.setBirthdate(birthdate);
        this.setCompany(company);
        this.setComments(comments);
        this.setEvents(events);
    }
    public Participant(String firstName, String lastName, String email, Date birthdate, String company, String comments) {
        this(firstName, lastName, email, birthdate, company, comments, null);
    }
    public Participant() {
        this(null, null, null, null, null, null, null);
    }

    // ----------------------------------------------------------- //
    //                         ACCESSORS                           //
    // ----------------------------------------------------------- //

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public String getCompany() {
        return company;
    }

    public String getComments() {
        return comments;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = (firstName == null) ? "" : firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = (lastName == null) ? "" : lastName;
    }

    public void setEmail(String email) {
        this.email = (email == null) ? "" : email;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = (birthdate == null) ? new Date() : birthdate;
    }

    public void setCompany(String company) {
        this.company = (company == null) ? "" : company;
    }

    public void setComments(String comments) {
        this.comments = (comments == null) ? "" : comments;
    }

    public void setEvents(List<Event> events) {
        this.events = (events == null) ? new ArrayList<>() : events;
    }
    // ----------------------------------------------------------- //
    //                           OTHERS                            //
    // ----------------------------------------------------------- //

    @Override
    public String toString() {
        return "Participant{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", birthdate=" + birthdate +
                ", company='" + company + '\'' +
                ", comments='" + comments + '\'' +
                ", events=List(" + events.size() + ')' +
                '}';
    }

    public String getDisplay() {
        return this.getFirstName() + " " + this.getLastName();
    }
}
