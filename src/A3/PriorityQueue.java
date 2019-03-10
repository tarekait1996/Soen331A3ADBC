package A3;
import java.util.*;
import java.util.Comparator;
import be.ac.ua.ansymo.adbc.annotations.*;
//import A3.PQEntry;
//import A3.minComparator;

// invariant of the class

@invariant({
	"$this.size() >= 0",
	"$this.capacity > 0",
	"$this.size() <= $this.capacity"
	})
public class PriorityQueue<V,K> {
	
	private Comparator<K> comp;
	public int capacity = 5 ;// initially 5 elements capacity
	ArrayList <PQEntry<V,K>> priorityArray;
	private int size = 0; // setting intial size
	
	//constructor 
	@requires ({"capacity > 0"})
	@ensures	({
					"$this.priorityArray != null",
					"$this.capacity > 0"
    })
	public PriorityQueue(int capacity) {
		this.priorityArray = new ArrayList<PQEntry<V,K>>(capacity);
		this.capacity = capacity;
	}
	@requires({"$this.isEmpty() == false"})
	@ensures ({
				"$topEntry != null",
				"$topEntry == $old($this.priorityArray.get(0))",
				"$this.size() == $old($this.size()) - 1"
	})
	public PQEntry<V,K> remove(){
		/**
		 * removes and returns the entry (a key, value pair) with the smallest
		 *  key of the priority queue.
		 */
		if(isEmpty()) {
			return null;
		}
		else {
				// initializing a new entry which is set to be equal to top of the heap
			PQEntry<V,K> topEntry = min();
			
				// set the top of the array to be the last entry of the heap
			priorityArray.set(0, priorityArray.get(size-1));
			
				// setting the last entry to null in order to get rid of it
			priorityArray.set(size-1,null);
			
			size--;
			
				// reshaping (arrange order of the heap) of the heap is needed now --> using DownHeap method, going from the top to bottom
				//we call downheap with 0 in order to start from top and go until the bottom
			if (!isEmpty()) {
				downHeap(0);
			}
				// return the removed entry
			return topEntry;
		}
	}
	@requires	({
		"value != null",
		"key != null",
		"$this.capacity > size"
	})
	@ensures	({
		"$this.priorityArray.contains((value, key))",
		"$this.size() == $old($this.size()) + 1"
	})
	public PQEntry<V,K> insert(V value, K key) throws IllegalArgumentException{
		/**
		 * Insert the (k,v) entry which is a key(k), value(v) pair to the priority queue.
		 */
		// checking if the keys are comparable and compatible
		checkKey(key);
		// create an entry based on data provided
		PQEntry<V,K> newest = new PQEntry<>(value,key);

		// reorder the heap now based on upheap starting 
		upheap(size);
		
		size ++;
		
		return newest;
	}
	protected boolean checkKey(K key) throws IllegalArgumentException { 
		/**
		 * helping function to make sure the keys are valid 
		 */
		try { 
			return (comp.compare(key,key) == 0); // see if key can be compared to itself
		} catch (ClassCastException e) { 
			throw new IllegalArgumentException("Incompatible key");
		}
	}
	private void upheap(int i) {
		/**
		 * helping function to reorder the heap after an insert()
		 */
		        if (i != 0) {
		            int p = (i-1)/2;
		                if (comp.compare(priorityArray.get(p).getKey(),priorityArray.get(i).getKey()) > 0) {
		                    PQEntry<V,K> tmp = priorityArray.get(p);
		                    priorityArray.set(p, priorityArray.get(i));
		                    priorityArray.set(i, tmp);
		                    upheap(p);
		            }
		        }
		    }
	private void downHeap(int index) {
		// getting the index of the left child 
        int leftIndex = (2*index) + 1;
        // getting the index of the right child
        int rightIndex = leftIndex + 1;
        // index of the one we are going to compare to 
        int a; 
        
        if (rightIndex >= size()) {//no right child
            if (leftIndex >= size()) {//no left child
                return;
            } else {
            	//if only left child we treat a as left child
                a = leftIndex;
            }
        } 
        //otherwise check which one of left or right has priority and assign a
        else {
        		// for min it means if left < right 
            if (comp.compare(priorityArray.get(leftIndex).getKey(), priorityArray.get(rightIndex).getKey()) < 0) {
                a = leftIndex;
            } else {
                a = rightIndex;
            }
        }
        // compare the index to the child that has the best priority among the two
        if (comp.compare(priorityArray.get(index).getKey(),priorityArray.get(a).getKey()) > 0) {//swap the value
            PQEntry<V,K> tmp = priorityArray.get(a);
            priorityArray.set(a,priorityArray.get(index));
            priorityArray.set(index, tmp);
            downHeap(a);
        }
		
	}
	private boolean isEmpty() {
		return size == 0;
	}
	public int size() {
		/**
		 * returns the current number of entries in the priority queue
		 */
        return size;
     }
	public PQEntry<V, K> min() {
    	/**
    	 * returns the top entry (with the minimum or the maximum key depending on whether it is a Min- or 
    	 * Max-priority queue, without removing the entry.
    	 */
        if (isEmpty()) {
            return null;
        }
        return priorityArray.get(0);
    }
	public String toString() {
    	
    	/**
    	 * this is a method that returns a string containing the informations about the priorityQueue
    	 */
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < size; i++) {
        	// we assume top is entry 1 and last entry is last 
            sb.append("Entry " + (i+1) + " =>");
            sb.append("[key:" + priorityArray.get(i).k + ", value:\"" + priorityArray.get(i).v + "\"], ");
        }
        return sb.toString();
    }
}
