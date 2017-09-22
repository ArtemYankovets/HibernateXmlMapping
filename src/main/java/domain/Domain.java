package domain;

import bl.HibernateUtil;
import entity.Address;
import entity.Employee;
import entity.Project;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.util.HashSet;
import java.util.Set;

public class Domain {

    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        Address address = new Address();
        address.setCountry("DC");
        address.setCity("Gothan City");
        address.setStreet("Arkham street 1");
        address.setPostCode("12345");

        Employee employee = new Employee();
        employee.setFirstName("James");
        employee.setLastName("Freeman");
        LocalDate birthday = LocalDate.of(1980, Month.JANUARY, 12);
        employee.setBirthday(Date.valueOf(birthday));
        employee.setAddress(address);

        Project project = new Project();
        project.setTitle("Gothan City Police Department Commissioner");

        Set<Project> projects = new HashSet<>();
        projects.add(project);
        employee.setProjects(projects);

        session.save(address);
        session.save(employee);
        session.save(project);

        session.getTransaction().commit();
        HibernateUtil.shutdown();
    }
}