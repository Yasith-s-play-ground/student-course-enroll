package lk.ijse.dep12.jpa.relationship.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "enroll")
public class Enroll implements Serializable {
    @EmbeddedId
    private EnrollPK enrollPK;
    @Column(name = "registered_by")
    private String registeredBy;
    private Date date;

    @ManyToOne
//    @JoinColumn(name = "student_id", referencedColumnName = "id", insertable = false, updatable = false)
    @MapsId("studentId")
    private Student student;

    @ManyToOne
//    @JoinColumn(name = "course_code", referencedColumnName = "code", insertable = false, updatable = false)
    @MapsId("courseCode")
    private Course course;

    public Enroll(EnrollPK enrollPK, String registeredBy, Date date) {
        this.enrollPK = enrollPK;
        this.registeredBy = registeredBy;
        this.date = date;
    }

    public Enroll(String studentId, String courseCode, String registeredBy, Date date) {
        this.enrollPK = new EnrollPK(studentId, courseCode);
        this.registeredBy = registeredBy;
        this.date = date;
    }

    public Enroll(Student student, Course course, String registeredBy, Date date) {
        this.enrollPK = new EnrollPK(student.getId(), course.getCode());
        this.registeredBy = registeredBy;
        this.date = date;
        this.course = course;
        this.student = student;
    }
}
