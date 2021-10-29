package Project;

import java.util.*;

public class SystemHelper {
    public static void moveProcessFrom(ArrayList<Process> first, ArrayList<Process> second){
    	Process tmp = first.get(0);
    	second.add(tmp);
    	first.remove(0);
    }
}
