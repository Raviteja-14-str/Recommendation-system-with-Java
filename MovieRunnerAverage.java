import java.util.*;
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MovieRunnerAverage {
    private String filename1;
    private String filename2;
    public MovieRunnerAverage(){
        filename1= "ratedmoviesfull.csv";
        filename2 = "ratings.csv";
    }
    
    public void printAverageRatings(){
        
        SecondRatings sr = new SecondRatings(filename1, filename2);
        System.out.println("There are a total of "+sr.getMovieSize()+" Movies");
        
        System.out.println("There are a total of "+sr.getRaterSize()+" Raters");
        double d = getAverageByID("1798709",5);
        System.out.println(d);
        ArrayList<Rating> list = getAverageRatings(3);
        Collections.sort(list);
        for( Rating r : list){
            if( r.getValue() != 0.0 ){
                System.out.println(r.getValue()+"\t"+getTitle(r.getItem()));
           }
        }
    }
    
    public double getAverageByID(String id,int minimalRaters){
      
        FirstRatings fr = new FirstRatings();
        if( fr.NoOfRatingsForItem(filename2,id) >= minimalRaters ){
        int num = fr.NoOfRatingsForItem(filename2,id);
        double rate = 0.0;
        HashMap<String,ArrayList<Rating>> map = fr.numberOfRatings(filename2);
        for(String key : map.keySet()){
            ArrayList<Rating> r = map.get(key);
            for( Rating ra : r){
                if( ra.getItem().equals(id) ){
                     rate = rate + ra.getValue();
                     
                }
            }
        }
        double average = rate/num;
        return average;
       }
       return 0.0;
     }
 
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        FirstRatings fr= new FirstRatings();
         HashMap<String,ArrayList<Rating>> map = fr.numberOfRatings(filename2);
        ArrayList<String> movies = fr.totalMovies(map);
        ArrayList<Rating> list = new ArrayList<Rating>();
        for ( String s : movies ){
            double avg = getAverageByID(s,minimalRaters);
            Rating r = new Rating(s,avg);
            list.add(r);
        }
        return list;
    }
    
    public String getTitle(String id){
        FirstRatings fr = new FirstRatings();
        ArrayList<Movie> movs = fr.loadMovies(filename1);
        for( Movie m: movs){
            if ( m.getID().contains(id) ){
                return m.getTitle();
            }
        }
        return "ID notfound";
    }
    
    public String getID(String title){
        FirstRatings fr = new FirstRatings();
        ArrayList<Movie> movs = fr.loadMovies(filename1);
        for( Movie m : movs){
            if( m.getTitle().contains(title) ){
                return m.getID();
            }
        }
        return "NO SUCH TITLE";
    }
    
    public void getAverageRatingOneMovie(){
        SecondRatings sr = new SecondRatings(filename1,filename2);
        String id = getID("The Godfather");
        double avg = getAverageByID(id,0);
        System.out.println(avg);
    }
}
