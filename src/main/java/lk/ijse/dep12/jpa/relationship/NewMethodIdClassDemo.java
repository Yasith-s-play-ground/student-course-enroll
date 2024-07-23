package lk.ijse.dep12.jpa.relationship;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import lk.ijse.dep12.jpa.relationship.entity.Course;
import lk.ijse.dep12.jpa.relationship.entity.Enroll;
import lk.ijse.dep12.jpa.relationship.entity.Student;
import lk.ijse.dep12.jpa.relationship.util.JpaUtil;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class NewMethodIdClassDemo {
    public static void main(String[] args) {
        try (EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();
             EntityManager em = emf.createEntityManager()) {
            EntityTransaction tx = em.getTransaction();

            try {
                tx.begin();
                Student s001 = new Student("S001", "Kasun Sampath", "Galle", "071-1234567", Date.valueOf("2000-01-01"));
                Student s002 = new Student("S002", "Nuwan Ramindu", "Matara", "011-1234567", Date.valueOf("1998-01-01"));

                Course c001 = new Course("C001", "DEP", "6 months");
                Course c002 = new Course("C002", "CMJD", "9 months");
                Course c003 = new Course("C003", "GDSE", "2 years");

                Enroll en1 = new Enroll(s001, c001, "Tharindu", Date.valueOf(LocalDate.now()));
                Enroll en2 = new Enroll(s002, c002, "Tharindu", Date.valueOf(LocalDate.now()));
                Enroll en3 = new Enroll(s002, c003, "Saman", Date.valueOf(LocalDate.now()));

                List.of(c001, c002, c003, s001, s002, en1, en2, en3).forEach(em::persist);
                tx.commit();
            } catch (Throwable t) {
                tx.rollback();
                t.printStackTrace();
            }

        }
    }
}
