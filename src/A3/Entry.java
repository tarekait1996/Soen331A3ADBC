package A3;

public interface Entry<V,K> { 
	K getKey( ); // returns the key stored in this entry
	V getValue( ); // returns the value stored in this entry
}