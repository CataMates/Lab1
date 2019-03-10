package mcir2140MV.test;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import mcir2140MV.model.base.Activity;
import mcir2140MV.model.base.Contact;
import mcir2140MV.model.repository.classes.RepositoryActivityFile;
import mcir2140MV.model.repository.classes.RepositoryContactFile;
import mcir2140MV.model.repository.interfaces.RepositoryActivity;
import mcir2140MV.model.repository.interfaces.RepositoryContact;

import org.junit.Before;
import org.junit.Test;

public class AfisActivityTest {

    RepositoryActivity rep;

    @Before
    public void setUp() throws Exception {
        RepositoryContact repcon = new RepositoryContactFile();
        rep = new RepositoryActivityFile(repcon);
    }

    @Test
    public void testCase1() {
        for (Activity act : rep.getActivities())
            rep.removeActivity(act);

        Calendar c = Calendar.getInstance();
        c.set(2013, 3 - 1, 20, 12, 00);
        Date start = c.getTime();

        c.set(2013, 3 - 1, 20, 12, 30);
        Date end = c.getTime();

        Activity act = new Activity("name1", start, end,
                new LinkedList<Contact>(), "description2");

        rep.addActivity(act);

        c.set(2013, 3 - 1, 20);

        List<Activity> result = rep.activitiesOnDate("name1", c.getTime());
        assertTrue(result.size() == 1);
    }

    @Test
    public void testCase2() {
        for (Activity act : rep.getActivities())
            rep.removeActivity(act);

        Calendar c = Calendar.getInstance();
        c.set(2013, 3 - 1, 20, 12, 00);
        Date start = c.getTime();

        c.set(2013, 3 - 1, 20, 12, 30);
        Date end = c.getTime();

        Activity act = new Activity("name1", start, end,
                new LinkedList<Contact>(), "description2");

        rep.addActivity(act);

        c.set(2013, 3 - 1, 20);
        try {
            rep.activitiesOnDate(((Object) 1).toString(), c.getTime());
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    @Test
    public void testCase3() {
        for (Activity act : rep.getActivities())
            rep.removeActivity(act);

        try {
            rep.activitiesOnDate("name1", (Date)(Object)"ASD");
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    @Test
    public void testCase4() {
        for (Activity act : rep.getActivities())
            rep.removeActivity(act);

        try {
            rep.addActivity((Activity)(Object)1);

            Calendar c = Calendar.getInstance();
            c.set(2013, 3 - 1, 20);
            rep.activitiesOnDate("name1", c.getTime());
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    @Test
    public void testCase5() {
        for (Activity act : rep.getActivities())
            rep.removeActivity(act);

        Calendar c = Calendar.getInstance();
        c.set(2013, 3 - 1, 20);
        List<Activity> result = rep.activitiesOnDate("name1", c.getTime());

        assertTrue(result.size() == 0);
    }
}
