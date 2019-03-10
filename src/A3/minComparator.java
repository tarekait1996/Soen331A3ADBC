package A3;
import java.util.Comparator;

public class minComparator<K> implements Comparator<K> {
	public int compare(K a, K b) { 
		return ((Comparable<K>) a).compareTo(b);
	}
}
