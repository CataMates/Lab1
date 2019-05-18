package mcir2140MV.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import mcir2140MV.model.base.Activity;
import mcir2140MV.model.repository.classes.RepositoryActivityMock;
import mcir2140MV.model.repository.interfaces.RepositoryActivity;

import org.junit.Before;
import org.junit.Test;

public class AddActivityTest {
    private Activity act;
    private RepositoryActivity rep;

    @Before
    public void setUp() throws Exception {
        rep = new RepositoryActivityMock();
    }

    @Test
    public void testCase1()
    {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        try {
            act = new Activity("name1",
                    df.parse("24/02/2019 12:00"),
                    df.parse("24/02/2019 12:00"),
                    null,
                    "Lunch break");
            rep.addActivity(act);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assertTrue(1 == rep.count());
    }

    @Test
    public void testCase2()
    {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        try{
            for (Activity a : rep.getActivities())
                rep.removeActivity(a);

            act = new Activity("name1",
                    df.parse("03/20/2013 12:00"),
                    df.parse("03/20/2013 13:00"),
                    null,
                    "Lunch break");
            rep.addActivity(act);

            act = new Activity("name1",
                    df.parse("03/21/2013 12:00"),
                    df.parse("03/21/2013 13:00"),
                    null,
                    "Lunch break");
            rep.addActivity(act);
        }
        catch(Exception e){}
        int c = rep.count();
        assertTrue( c == 2);
        for (Activity a : rep.getActivities())
            rep.removeActivity(a);
    }

    @Test
    public void testCase3()
    {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        try{
            for (Activity a : rep.getActivities())
                rep.removeActivity(a);

            act = new Activity("name1",
                    df.parse("03/20/2013 12:00"),
                    df.parse("03/20/2013 13:00"),
                    null,
                    "Lunch break");
            rep.addActivity(act);

            act = new Activity("name1",
                    df.parse("03/20/2013 12:30"),
                    df.parse("03/20/2013 13:30"),
                    null,
                    "Lunch break");
            assertFalse(rep.addActivity(act));
        }
        catch(Exception e){}
        assertTrue( 1 == rep.count());
        rep.saveActivities();
        for (Activity a : rep.getActivities())
            rep.removeActivity(a);
    }

    @Test
    public void testCase4()
    {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        try{
            for (Activity a : rep.getActivities())
                rep.removeActivity(a);

            act = new Activity("name1",
                    df.parse("03/20/2013 12:00"),
                    df.parse("03/20/2013 13:00"),
                    null,
                    "Lunch break");
            rep.addActivity(act);

            act = new Activity("name1",
                    df.parse("03/20/2013 13:30"),
                    df.parse("03/20/2013 14:00"),
                    null,
                    "Curs");
            rep.addActivity(act);

            act = new Activity("name1",
                    df.parse("03/20/2013 13:30"),
                    df.parse("03/20/2013 14:30"),
                    null,
                    "Lunch break");
            assertFalse(rep.addActivity(act));
        }
        catch(Exception e){}
        assertTrue( 2 == rep.count());
        for (Activity a : rep.getActivities())
            rep.removeActivity(a);
    }

    @Test
    public void testCase5()
    {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        try{
            for (Activity a : rep.getActivities())
                rep.removeActivity(a);

            act = new Activity("name1",
                    df.parse("03/20/2013 12:00"),
                    df.parse("03/20/2013 13:00"),
                    null,
                    "Lunch break");
            rep.addActivity(act);

            assertFalse(rep.addActivity(act));
        }
        catch(Exception e){}
        assertTrue( 1 == rep.count());
        for (Activity a : rep.getActivities())
            rep.removeActivity(a);
    }

    @Test
    public void testCase6()
    {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        try {
            act = new Activity("name1",
                    df.parse("24/02/2019 12:00"),
                    df.parse("24/02/2019 12:00"),
                    null,
                    "Lunch break");
            rep.addActivity(act);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assertTrue(1 == rep.count());
    }

    @Test
    public void testCase7()
    {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        try {
            act = new Activity("name1",
                    df.parse("24/02/2019 12:00"),
                    df.parse("24/02/2019 12:00"),
                    null,
                    "Lunch break");
            rep.addActivity(act);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assertTrue(1 == rep.count());
    }

    @Test
    public void testCase8()
    {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        try {
            act = new Activity("name1",
                    df.parse("15/02/2019 12:00"),
                    df.parse("16/02/2019 12:00"),
                    null,
                    "Lunch break");
            rep.addActivity(act);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assertTrue(1 == rep.count());
    }

    @Test
    public void testCase9()
    {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        try {
            act = new Activity("name1",
                    df.parse("20/02/2019 12:00"),
                    df.parse("21/02/2019 12:00"),
                    null,
                    "Lunch break");
            rep.addActivity(act);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assertTrue(1 == rep.count());
    }

    @Test
    public void testCase10()
    {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        try {
            act = new Activity("name1",
                    df.parse("19/02/2019 12:00"),
                    df.parse("21/02/2019 12:00"),
                    null,
                    "Lunch break");
            rep.addActivity(act);

            act = new Activity("name1",
                    df.parse("20/02/2019 10:00"),
                    df.parse("20/02/2019 12:00"),
                    null,
                    "Lunch break");
            rep.addActivity(act);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assertTrue(1 == rep.count());
    }
}
