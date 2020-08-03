import java.util.*;
/**
 * Write a description of FourthRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FourthRatings {

    
    public FourthRatings(String filename){
        RaterDatabase.addRatings("data/"+filename);
        
    }
    
    public FourthRatings(){
        this("ratings.csv");
    }
        
     
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        MovieRunnerAverage avg = new MovieRunnerAverage();
        ArrayList<Rating> list  =avg.getAverageRatings(minimalRaters);
        return list;
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
    
    public int getRaterSize(){
         return RaterDatabase.size();
    }
    
    private  double dotProduct(Rater me,Rater r){
        double dotprod = 0;
        
        for( int i=0;i<me.numRatings();i++){
            ArrayList<Rating> list = new ArrayList<Rating>();
            for(String s : r.getItemsRated()){
                if( me.getItemsRated().contains(s) ){
                    double myRating = me.getRating(s);
                    double hisRating = r.getRating(s);
                    double finalval = 0;
                    finalval = (myRating-5)*(hisRating-5);
                    dotprod = dotprod +finalval;
                }
            } 
        }
        return dotprod;
    }
    
    private ArrayList<Rating> getSimilarities(String id){
        ArrayList<Rating> list = new ArrayList<Rating>();
        Rater x = RaterDatabase.getRater(id);
        
        for( Rater r : RaterDatabase.getRaters()){
            if( ! r.getID().equals(id)){
                double sim = dotProduct(x,r);
                if( sim > 0 ){
                    list.add(new Rating(r.getID(),sim));
                 }
            }
        }
        Collections.sort(list,Collections.reverseOrder());
        return list;
    }
    
    public ArrayList<Rating> getSimilarRatings(String id,int numSimilarRaters,int minimalRaters){
       ArrayList<Rating> similar = getSimilarities(id);
       ArrayList<Rating> getRatings = new ArrayList<Rating>();
       ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
       HashMap<String,ArrayList<Double>> favRaters = new HashMap<String,ArrayList<Double>>();
       for(String movieId : movies){
           for(int i=0;i<numSimilarRaters;i++){
               Rating r = similar.get(i);
               double sim = r.getValue();
               String rater_id = r.getItem();
               ArrayList<Rater> Raters = RaterDatabase.getRaters();
               for(Rater ra : Raters){
                   if( rater_id.equals(ra.getID()) ){
                       for(String s : ra.getItemsRated() ){
                           if( s.equals(movieId) ){
                               ArrayList<Double> simef = new ArrayList<Double>();
                               if( ! favRaters.containsKey(s) ){
                                   simef.add(sim*ra.getRating(s));
                                   favRaters.put(s,simef);
                                }
                                else{
                                    ArrayList<Double> mine = favRaters.get(s);
                                    mine.add(sim*ra.getRating(s));
                                    favRaters.put(s,mine);
                                }
                            }
                        }
                    }
                }
            }
       }
       for(String s: favRaters.keySet() ){
           if(favRaters.get(s).size() >= minimalRaters){
               double total = 0;
               for (double num: favRaters.get(s) ){
                   total = total+num;
                }
                double finalval = total/favRaters.get(s).size();
                getRatings.add(new Rating(s,finalval));
            }
       }
       Collections.sort(getRatings,Collections.reverseOrder());
       return getRatings;
    }
    
    public ArrayList<Rating> getSimilarRatingsByFilter(String id,int numSimilarRaters,int minimalRaters,Filter f){
       ArrayList<Rating> similar = getSimilarities(id);
       ArrayList<Rating> getRatings = new ArrayList<Rating>();
       ArrayList<String> movies = MovieDatabase.filterBy(f);
       HashMap<String,ArrayList<Double>> favRaters = new HashMap<String,ArrayList<Double>>();
       for(String movieId : movies){
           for(int i=0;i<numSimilarRaters;i++){
               Rating r = similar.get(i);
               double sim = r.getValue();
               String rater_id = r.getItem();
               ArrayList<Rater> Raters = RaterDatabase.getRaters();
               for(Rater ra : Raters){
                   if( rater_id.equals(ra.getID()) ){
                       for(String s : ra.getItemsRated() ){
                           if( s.equals(movieId) ){
                               ArrayList<Double> simef = new ArrayList<Double>();
                               if( ! favRaters.containsKey(s) ){
                                   simef.add(sim*ra.getRating(s));
                                   favRaters.put(s,simef);
                                }
                                else{
                                    ArrayList<Double> mine = favRaters.get(s);
                                    mine.add(sim*ra.getRating(s));
                                    favRaters.put(s,mine);
                                }
                            }
                        }
                    }
                }
            }
       }
       for(String s: favRaters.keySet() ){
           if(favRaters.get(s).size() >= minimalRaters){
               double total = 0;
               for (double num: favRaters.get(s) ){
                   total = total+num;
                }
                double finalval = total/favRaters.get(s).size();
                getRatings.add(new Rating(s,finalval));
            }
       }
       Collections.sort(getRatings,Collections.reverseOrder());
       return getRatings;
    }
    
    
    

}
