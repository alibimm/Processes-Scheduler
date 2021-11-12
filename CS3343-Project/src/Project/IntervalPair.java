package Project;

public class IntervalPair {
	private int start;
	private int end;
	
	IntervalPair(int _start, int _end){
		this.start = _start;
		this.end = _end;
	}
	
	public static IntervalPair create(int _start, int _end){
		return new IntervalPair(_start, _end);
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
