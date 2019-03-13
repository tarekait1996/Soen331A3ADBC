package A3;
import java.util.*;
import java.util.Comparator;
import be.ac.ua.ansymo.adbc.annotations.*;


@invariant	({
	"$this.size >=0",
	"$this.capacity > 0",
	"$this.size <= $this.capacity"
 })
public class PriorityQueue<V,K> {
	
	public Comparator<K> comp;
	public int capacity;
	public PQEntry<V,K>[] pqArray;
	public int size = 0;
	
	@ensures	({
					"$this.pqArray != null", 
					"$this.capacity > 0",
					"$this.comp != null"
    })
	public PriorityQueue() {
		capacity = 5;
		pqArray= new PQEntry[capacity];
		comp = (Comparator<K>) new minComparator();
	}

	@requires ({"capacity > 0"})
	@ensures	({
					"$this.pqArray != null", 
					"$this.capacity > 0",
					"$this.comp != null"
    })
	public PriorityQueue(int capacity) {
		pqArray= new PQEntry[capacity];
		this.capacity = capacity;
		comp = (Comparator<K>) new minComparator();
	}
	
	@requires({"$this.isEmpty() == false"})
	@ensures ({
				"$result != null",
				"$result == $old($this.min())",
				"$this.getSize() == $old($this.getSize()) - 1",
				"$this.verifyMinOnTop() == true"
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
			pqArray[0] = pqArray[size-1];
			
			// setting the last entry to null in order to get rid of it
			pqArray[size - 1] = null;
			size--;
			
			//Reshaping (arrange order of the heap) of the heap is needed now --> using DownHeap method, going from the top to bottom
			//we call downheap with 0 in order to start from top and go until the bottom
			if (!isEmpty()) {
				downHeap(0);
			}
				// return the removed entry
			System.out.println("Just removed entry [key: \""+ topEntry.k +"\", value: \""+topEntry.v+"\"] out of the PriorityQueue");
			return topEntry;
		}
	}
	
	@ensures	({
		
	})
	public boolean verifyMinOnTop() {
		K keyOnTop = min().k;
		for (int i= 0; i < pqArray.length; i++) {
			if (comp.compare(pqArray[i].getKey(), keyOnTop) < 0)
			 return false;
		}
		return true;
	}
	@requires	({
		"key != null",
		"value != null",
		"$this.isFull() == false"
	})
	@ensures	({
			"$this.pqArray.contains(value, key)",
			"$this.size() == $old($this.size()) + 1"
	})
	
	//
	public PQEntry<V,K> insert(V value, K key) throws IllegalArgumentException{
		/**
		 * Insert the (k,v) entry which is a key(k), value(v) pair to the priority queue.
		 */
		// checking if the keys are comparable and compatible
		checkKey(key);
		// create an entry based on data provided
		PQEntry<V,K> newest = new PQEntry<>(value,key);
		System.out.println("Just inserted entry [key: \""+ newest.k +"\", value: \""+newest.v+"\"] into the PriorityQueue");
		priorityArray.add(size, newest);
		// reorder the heap now based on upheap starting 
		upheap(size);
		
		size ++;

		return newest;
	}
	
	public boolean contains(V value, K key) { 
		for (int i =0; i < pqArray.length; i++) {
			if (pqArray[i].k == key && pqArray[i].v == value) return true;
		}
		return false;
	}
	
	private boolean isFull() {
		return size == pqArray.length;
	}
	
	private boolean checkKey(K key) throws IllegalArgumentException { 
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
		                if (comp.compare(pqArray[p].getKey(),pqArray[i].getKey()) > 0) {
		                    PQEntry<V,K> tmp = pqArray[p];
		                    pqArray[p] = pqArray[i];
		                    pqArray[i] = tmp;
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
            if (comp.compare(pqArray[leftIndex].getKey(), pqArray[rightIndex].getKey()) < 0) {
                a = leftIndex;
            } else {
                a = rightIndex;
            }
        }
        // compare the index to the child that has the best priority among the two
        if (comp.compare(pqArray[index].getKey(),pqArray[a].getKey()) > 0) {//swap the value
            PQEntry<V,K> tmp = priorityArray.get(a);
            priorityArray.set(a,priorityArray.get(index));
            priorityArray.set(index, tmp);
            downHeap(a);
        }
		
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	public int getSize() {
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
            sb.append("[key:" + pqArray[i].k + ", value:\"" + pqArray[i].v + "\"], ");
        }
        return sb.toString();
    }
	public String toString2() {
    	
    	/**
    	 * this is a method that returns a string containing the informations about the priorityQueue
    	 */
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < size; i++) {
        	// we assume top is entry 1 and last entry is last 
            sb.append("Entry " + (i+1) + " =>");
            sb.append("[key:" + pqArray[i].k + ", value:\"" + pqArray[i].v + "\"], ");
        }
        return sb.toString();
    }
}
