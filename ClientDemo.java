package com.klef.jfsd.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ClientDemo {
    public static void main(String[] args) {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        cfg.addAnnotatedClass(Client.class);

        SessionFactory factory = cfg.buildSessionFactory();

        // Insert Records
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        Client client1 = new Client();
        client1.setName("Alice");
        client1.setGender("Female");
        client1.setAge(28);
        client1.setLocation("New York");
        client1.setEmail("alice@example.com");
        client1.setMobileNumber("1234567890");

        session.save(client1);
        transaction.commit();

        // Retrieve and Print All Records
        session = factory.openSession();
        List<Client> clients = session.createQuery("from Client", Client.class).getResultList();
        for (Client client : clients) {
            System.out.println(client.getId() + " " + client.getName() + " " + client.getGender() + " "
                    + client.getAge() + " " + client.getLocation() + " " + client.getEmail() + " "
                    + client.getMobileNumber());
        }
        session.close();
        factory.close();
    }
}
