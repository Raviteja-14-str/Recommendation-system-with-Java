import java.util.*;
/**
 * Write a description of DirectorsFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DirectorsFilter implements Filter{
    private String director;
    
    public DirectorsFilter(String s){
        director = s;
    }
    
    public boolean satisfies(String id){
        ArrayList<String> list = split(director);
        for( String s: list){
            if(MovieDatabase.getDirector(id).contains(s)){
            return MovieDatabase.getDirector(id).contains(s);
           }
        }
        return false;
    }
    
    public ArrayList<String> split(String dir){
        ArrayList<String> list = new ArrayList<String>();
        int start =0;
        int index =0;
        while(start < dir.length() ){
           if( dir.contains(",")  ){
              index = dir.indexOf(",",start);
              if( index == -1){
                  index = dir.length();
                }
              String x = dir.substring(start,index);
              list.add(x);
              start = index+1;
           }
           else{
               list.add(dir);
            }
        }
        return list;
    }

}
