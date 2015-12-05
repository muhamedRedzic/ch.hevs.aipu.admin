package ch.hevs.aipu.admin.service;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by Muhamed on 03.12.2015.
 */
public class EMF {
    private static final EntityManagerFactory emfInstance =
            Persistence.createEntityManagerFactory("transactions-optional");

    private EMF() {}

    public static EntityManagerFactory get() {
        return emfInstance;
    }
}
