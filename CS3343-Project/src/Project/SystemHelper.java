package Project;

import java.util.*;

public class SystemHelper {
    public static void moveProcessFrom(ArrayList<ProcessInCPU> first, ArrayList<ProcessInCPU> second){
    	ProcessInCPU tmp = first.get(0);
    	second.add(tmp);
    	first.remove(0);
    }
}
