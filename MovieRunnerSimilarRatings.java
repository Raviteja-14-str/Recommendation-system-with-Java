import java.util.*;
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MovieRunnerSimilarRatings {
    public void printAverageRatings(){
        
        FourthRatings tr = new FourthRatings(); 
        System.out.println("There are a total of "+tr.getRaterSize()+" Movies");
        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("There are a total of "+MovieDatabase.size()+" Raters");
        //double d = getAverageByID("1798709",5);
        //System.out.println(d);
        ArrayList<Rating> list = tr.getAverageRatings(1);
        Collections.sort(list);
        for( Rating r : list){
            if( r.getValue() != 0.0 ){
                MovieRunnerAverage mra = new MovieRunnerAverage();
                System.out.println(r.getValue()+"\t"+mra.getTitle(r.getItem()));
           }
        }
    }
    
    public void printAverageRatingsByYearAfterAndGenre(){
        ThirdRatings tr = new ThirdRatings();
        AllFilters af = new AllFilters();
        YearAfterFilter Y = new YearAfterFilter(1980);
        GenreFilter G = new GenreFilter("Romance");
        af.addFilter(Y);
        af.addFilter(G);
        ArrayList<Rating> list = tr.getAverageRatingsByFilter(1,af);
        System.out.println(list.size()+" movie(s) found");
        for(Rating r : list){
            if( r.getValue()!= 0.0){
                MovieRunnerAverage mv = new MovieRunnerAverage();
                System.out.println(r.getValue()+"\t"+MovieDatabase.getYear(r.getItem())+"\t"+mv.getTitle(r.getItem()));
                System.out.println(MovieDatabase.getGenres(r.getItem()));
            }
        }
    }
    
    public void printSimilarRatings(){
        FourthRatings fr = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        ArrayList<Rating> list = fr.getSimilarRatings("65",20,5);
        for( Rating r : list){
            System.out.println(MovieDatabase.getTitle(r.getItem()));
            
        }
    }
    
    public void printSimilarRatingsByGenre(){
        FourthRatings fr = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        Filter f = new GenreFilter("Action");
        ArrayList<Rating> list = fr.getSimilarRatingsByFilter("65",20,5,f);
        for( Rating r : list){
            System.out.println(MovieDatabase.getTitle(r.getItem()));
            
        }
    }
    
     public void printSimilarRatingsByDirector(){
        FourthRatings fr = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        Filter f = new DirectorsFilter("Clint Eastwood,Sydney Pollack,David Cronenberg,Oliver Stone");
        ArrayList<Rating> list = fr.getSimilarRatingsByFilter("1034",10,3,f);
        for( Rating r : list){
            System.out.println(MovieDatabase.getTitle(r.getItem()));
            
        }
    }
    
    public void printSimilarRatingsByGenreAndMinutes(){
        FourthRatings fr = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        AllFilters f = new AllFilters();
        Filter f1 = new GenreFilter("Adventure");
        Filter f2 = new MinutesFilter(100,200);
        f.addFilter(f1);
        f.addFilter(f2);
        ArrayList<Rating> list = fr.getSimilarRatingsByFilter("65",10,5,f);
        for( Rating r : list){
            System.out.println(MovieDatabase.getTitle(r.getItem()));
            
        }
    }
    
    public void printSimilarRatingsByYearAfterAndMinutes(){
        FourthRatings fr = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        AllFilters f = new AllFilters();
        Filter f1 = new YearAfterFilter(2000);
        Filter f2 = new MinutesFilter(80,100);
        f.addFilter(f1);
        f.addFilter(f2);
        ArrayList<Rating> list = fr.getSimilarRatingsByFilter("65",10,5,f);
        for( Rating r : list){
            System.out.println(MovieDatabase.getTitle(r.getItem()));
            
        }
    }
   
    public void test(){
        Rater x = RaterDatabase.getRater("65");
        System.out.println(x.numRatings());
        for( String s : x.getItemsRated()){
           System.out.println(s);
       }
    }

}
