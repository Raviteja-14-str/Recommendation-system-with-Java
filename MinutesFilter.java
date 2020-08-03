
/**
 * Write a description of MinutesFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MinutesFilter implements Filter{
    private int minutesmax;
    private int minutesmin;
    
    public MinutesFilter(int s,int x){
        minutesmin = s;
        minutesmax = x;
    }
    
    public boolean satisfies(String id){
        return MovieDatabase.getMinutes(id) >= minutesmin && MovieDatabase.getMinutes(id) <= minutesmax;
    }

}
