import java.util.*;
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MovieRunnerWithFilters {
    public void printAverageRatings(){
        
        ThirdRatings tr = new ThirdRatings("ratings_short.csv");
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
    
    public void printAverageRatingsByYear(){
        ThirdRatings tr = new ThirdRatings("ratings_short.csv");
        Filter f = new YearAfterFilter(2000);
        ArrayList<Rating> list = tr.getAverageRatingsByFilter(1,f);
        System.out.println("found "+list.size()+" movies");
        for( Rating r : list){
             if( r.getValue() != 0.0 ){
                MovieRunnerAverage mra = new MovieRunnerAverage();
                System.out.println(r.getValue()+"\t"+MovieDatabase.getYear(r.getItem())+"\t"+mra.getTitle(r.getItem()));
           }
        }
    }
    
    public void printAverageRatingsByGenre(){
        ThirdRatings tr = new ThirdRatings("ratings_short.csv");
        Filter f = new GenreFilter("Crime");
        ArrayList<Rating> list = tr.getAverageRatingsByFilter(1,f);
        System.out.println("found "+list.size()+" movies");
        for ( Rating r : list){
            if(  r.getValue() != 0.0){
                MovieRunnerAverage mv = new MovieRunnerAverage();
                System.out.println(r.getValue()+"\t"+mv.getTitle(r.getItem()));
                System.out.println(MovieDatabase.getGenres(r.getItem()));
            }
        }
    }
    
    public void printAverageRatingsByMinutes(){
        ThirdRatings tr = new ThirdRatings("ratings_short.csv");
        Filter f = new MinutesFilter(110,170);
        ArrayList<Rating> list = tr.getAverageRatingsByFilter(1,f);
        System.out.println("found "+list.size()+" movies");
        Collections.sort(list);
        for ( Rating r : list){
            if( r.getValue() != 0.0){
                MovieRunnerAverage mv = new MovieRunnerAverage();
                System.out.println(r.getValue()+"\t"+MovieDatabase.getMinutes(r.getItem())+"\t"+mv.getTitle(r.getItem()));
            }
        }
    }
    
    public void printAverageRatingsByDirectors(){
        ThirdRatings tr = new ThirdRatings("ratings_short.csv");
        Filter f = new DirectorsFilter("Charles Chaplin,Michael Mann,Spike Jonze");
        ArrayList<Rating> list = tr.getAverageRatingsByFilter(1,f);
        System.out.println("found "+list.size()+" movies");
        for( Rating r : list ){
            if( r.getValue() != 0.0){
                MovieRunnerAverage mv = new MovieRunnerAverage();
                System.out.println(r.getValue()+"\t"+mv.getTitle(r.getItem()));
                System.out.println(MovieDatabase.getDirector(r.getItem()));
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
    
    public void printAverageRatingsByMinutesAndDirectors(){
        ThirdRatings tr = new ThirdRatings();
        AllFilters af = new AllFilters();
        MinutesFilter Y = new MinutesFilter(30,170);
        DirectorsFilter G = new DirectorsFilter("Spike Jonze,Michael Mann,Charles Chaplin,Francis Ford Coppola");
        af.addFilter(Y);
        af.addFilter(G);
        ArrayList<Rating> list = tr.getAverageRatingsByFilter(1,af);
        System.out.println(list.size()+" movie(s) found");
        for(Rating r : list){
            if( r.getValue()!= 0.0){
                MovieRunnerAverage mv = new MovieRunnerAverage();
                System.out.println(r.getValue()+"\t"+MovieDatabase.getMinutes(r.getItem())+"\t"+mv.getTitle(r.getItem()));
                System.out.println(MovieDatabase.getDirector(r.getItem()));
            }
        }
    }

}
