package Project;

public class Interval {
	private int start;
	private int end;
	
	Interval(int _start, int _end){
		this.start = _start;
		this.end = _end;
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
