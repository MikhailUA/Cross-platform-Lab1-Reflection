package reflectionBasics;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.Arrays;

public class Task3 {
	
	public static void main(String[] args) throws ClassNotFoundException {
		Object arr = createSimpleArray("java.lang.Boolean" , 5);
		for (int i = 0; i < Array.getLength(arr); i++){
			System.out.println(Array.get(arr, i));
		}
		
		Object arr2D = create2DArray("java.lang.Boolean" , 5, 2);
		
		for (int r = 0; r < Array.getLength(arr2D); r++) {
	          Object row = Array.get(arr2D, r);
	  		for (int col = 0; col < Array.getLength(row); col++) {
	          System.out.print(Array.get(row,col));
	  		}
	  		System.out.println("");
		}
		
		Object arrNew = createSimpleArray("java.lang.Boolean" , 8);
		System.arraycopy(arr, 0, arrNew, 0, Array.getLength(arr));
		
		for (int i = 0; i < Array.getLength(arrNew); i++){
			System.out.println(Array.get(arrNew, i));
		}		
		
	}
		
	public static Object createSimpleArray (String typename , int length) throws ClassNotFoundException {
		
		Class<?> cls = Class.forName(typename);        
		Object defaultValue = null;
		
		if (Number.class.isAssignableFrom(cls)) {
			defaultValue = 0;
		}else if (Boolean.class.isAssignableFrom(cls)){
			defaultValue = false;
		}
		
		Object arr = Array.newInstance(cls, length);
		
		for (int i = 0; i < length; i++) {
          Array.set(arr, i, defaultValue);
	    }
		
		return arr;		
	}
	
	public static Object create2DArray (String typename , int rows, int cols) throws ClassNotFoundException{
		
		Class<?> cls = Class.forName(typename);        
		Object defaultValue = null;
		
		if (Number.class.isAssignableFrom(cls)) {
			defaultValue = 0;
		}else if (Boolean.class.isAssignableFrom(cls)){
			defaultValue = false;
		}
		
		Object arr = Array.newInstance(cls, rows, cols);
		
		for (int r = 0; r < rows; r++) {
          Object row = Array.get(arr, r);
          for (int col = 0; col < cols; col++){
        	  Array.set(row, col, defaultValue);
          }
	    }
		
		return arr;			
	}
	
}
