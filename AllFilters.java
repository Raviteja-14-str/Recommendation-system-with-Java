import java.util.*;

/**
 * Write a description of AllFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AllFilters implements Filter {
    ArrayList<Filter> filters;
    
    public AllFilters(){ 
       filters = new ArrayList<Filter>();
   }
   
   public void addFilter(Filter f){
       filters.add(f);
    }
   
    public boolean satisfies(String id){
        for (Filter f: filters){
        
          if( ! f.satisfies(id) ){
              return  false;
        
          }
       }
       return true;
    }
    // Testing 
}
