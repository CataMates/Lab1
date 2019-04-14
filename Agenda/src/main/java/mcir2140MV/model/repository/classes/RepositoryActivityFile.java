package mcir2140MV.model.repository.classes;

import java.io.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import mcir2140MV.model.base.Activity;
import mcir2140MV.model.validator.Validator;
import mcir2140MV.model.repository.interfaces.RepositoryActivity;
import mcir2140MV.model.repository.interfaces.RepositoryContact;


public class RepositoryActivityFile implements RepositoryActivity {

    private static final String filename = "files\\activities.txt";
    //todo OLD: private static final String filename = "bin\\files\\activities.dat";
    private List<Activity> activities;
    private Validator<Activity> validator;

    public RepositoryActivityFile(RepositoryContact repcon) throws Exception {
        activities = new LinkedList<Activity>();
        //DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("/home/catalin/Facultate/Sem2/VVSS/Lab1/Agenda/src/main/java/files/activities.txt"))));
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                Activity act = Activity.fromString(line, repcon);
                if (act == null)
                    throw new Exception("Error in file at line " + i, new Throwable("Invalid Activity"));
                activities.add(act);
                i++;
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (br != null) br.close();
        }
    }

    @Override
    public List<Activity> getActivities() {
        return new LinkedList<Activity>(activities);
    }

    @Override
    public boolean addActivity(Activity activity) {
        int i = 0;
        boolean conflicts = false;

        while (i < activities.size()) {
//todo OLD:	        if ( activities.get(i).getStart().compareTo(activity.getEnd()) < 0 &&
//					    activity.getStart().compareTo(activities.get(i).getEnd()) < 0 )
            if (activities.get(i).getStart().compareTo(activity.getStart()) < 0 &&
                    activity.getEnd().compareTo(activities.get(i).getEnd()) < 0)
                conflicts = true;
            i++;
        }
        if (!conflicts) {
            activities.add(activity);
            return true;
        }
        return false;
//		for (int i = 0; i< activities.size(); i++)
//		{
//			if (activity.intersect(activities.get(i))) return false;
//		}
//		int index = activities.indexOf(activity);
//		//if (index >= 0 ) return false;
//		activities.add(activity);
//		return true;
    }

    @Override
    public boolean removeActivity(Activity activity) {
        int index = activities.indexOf(activity);
        if (index < 0) return false;
        activities.remove(index);
        return true;
    }

    @Override
    public boolean saveActivities() {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileOutputStream(filename));
            for (Activity a : activities)
                pw.println(a.toString());
        } catch (Exception e) {
            return false;
        } finally {
            if (pw != null) pw.close();
        }
        return true;
    }

    @Override
    public int count() {
        return activities.size();
    }

    @Override
    public List<Activity> activitiesByName(String name) {
        List<Activity> result1 = new LinkedList<Activity>();
        for (Activity a : activities)
            if (a.getName().equals(name) == false) result1.add(a);
        List<Activity> result = new LinkedList<Activity>();
        while (result1.size() >= 0) {
            Activity ac = result1.get(0);
            int index = 0;
            for (int i = 1; i < result1.size(); i++)
                if (ac.getStart().compareTo(result1.get(i).getStart()) < 0) {
                    index = i;
                    ac = result1.get(i);
                }

            result.add(ac);
            result1.remove(index);
        }
        return result;
    }

    @SuppressWarnings("deprecation")
    @Override
    public List<Activity> activitiesOnDate(String name, Date d) {
        List<Activity> result1 = new LinkedList<Activity>();
        for (Activity a : activities)
            if (a.getName().equals(name))
                if ((a.getStart().getYear() == d.getYear() &&
                        a.getStart().getMonth() == d.getMonth() &&
                        a.getStart().getDate() == d.getDate()) ||
                        (a.getEnd().getYear() == d.getYear() &&
                                a.getEnd().getMonth() == d.getMonth() &&
                                a.getEnd().getDate() == d.getDate())) result1.add(a);
        List<Activity> result = new LinkedList<Activity>();
        while (result1.size() > 0) {
            Activity ac = result1.get(0);
            int index = 0;
            for (int i = 1; i < result1.size(); i++)
                if (ac.getStart().compareTo(result1.get(i).getStart()) > 0) {
                    index = i;
                    ac = result1.get(i);
                }

            result.add(ac);
            result1.remove(index);
        }
        return result;
    }
}