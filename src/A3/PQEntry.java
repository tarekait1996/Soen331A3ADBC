package A3;

public class PQEntry<V,K> implements Entry<V,K> {
	  K k; // key
	  V v; // value
	  public PQEntry( V value, K key) { 
		  this.k = key;
		  this.v = value;
	  }  
	  // methods of the Entry interface
	  public K getKey( ) { return k; } 
	  public V getValue( ) { return v; } 
	  protected void setKey(K key) { k = key; } 
	   protected void setValue(V value) { v = value; } 
	   public String toString() {
		   return "[key: \"+ k +\", value: \\\"\"+v+\"\\\"]";
	   }
} 