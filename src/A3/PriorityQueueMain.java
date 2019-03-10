package A3;

public class PriorityQueueMain {
	public static void main(String[] args) {
	    	
	    	
		System.out.println("Creating a new Priority Queue ...");
//			
//		// instantiating a PQ with default constructor
		PriorityQueue<String, Integer> pq = new PriorityQueue<String, Integer>(5);
//		
		System.out.println("\nPriority Queue created");			
		System.out.println("\nAbout to insert 1 entry with random keys into the Priority Queue!");
//			// inserting random keys with element
		pq.insert("person 1", randomNum());
		pq.insert("person 2", randomNum());
		pq.insert("person 3", randomNum());
		pq.insert("person 4", randomNum());
		pq.insert("person 5", randomNum());
		pq.insert("person 6", randomNum());

	    System.out.println("\nThe PQ is now : " + pq.text());
	    pq.remove();
	    System.out.println("\nThe PQ is now : " + pq.text());
	
	}
	private static int randomNum() {
	return (int) (Math.random()*100);
	}
}
