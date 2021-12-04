package Project;

public class Interval {
	private int start;
	private int end;
	
	private Interval(int start, int end){
		this.start = start;
		this.end = end;
	}
	
	public static Interval create(int start, int end) {
		return new Interval(start, end);
	}
	
	public void print() {
	    System.out.print(start + " " + end + " ");
	  }
	public int getStart() {
		return this.start;
	}
	public int getEnd() {
		return this.end;
	}
}
