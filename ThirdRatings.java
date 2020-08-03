import java.util.*;
/**
 * Write a description of ThirdRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ThirdRatings {
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings(){
        this("ratings_short.csv");
    }
    
    public ThirdRatings(String Ratingsfile){
        FirstRatings fr = new FirstRatings();
        myRaters = fr.loadRaters(Ratingsfile);
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        MovieRunnerAverage avg = new MovieRunnerAverage();
        ArrayList<Rating> list  =avg.getAverageRatings(minimalRaters);
        return list;
    }
    
    public int getRaterSize(){
        ArrayList<String> list = new ArrayList<String>();
        for( Rater r : myRaters){
            if( ! list.contains(r.getID()) ){
                list.add(r.getID());
            }
        }
        return list.size();
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters,Filter filterCriteria){
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        ArrayList<Rating> list = new ArrayList<Rating>();
        MovieRunnerAverage mv = new MovieRunnerAverage();
        for( String s : movies){
            double avg = mv.getAverageByID(s,minimalRaters);
            Rating r = new Rating(s,avg);
            list.add(r);
        }
        return list;
    }

}
