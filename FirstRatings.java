import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

/**
 * Write a description of FirstRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FirstRatings {
    
    public ArrayList<Movie> loadMovies(String filename){
        FileResource fr = new FileResource("data/"+filename);
        CSVParser parser = fr.getCSVParser();
        ArrayList<Movie> list = new ArrayList<Movie>();
        for( CSVRecord rec : parser){
            Movie details = new Movie(rec.get("id"),rec.get("title"),rec.get("year"),rec.get("genre"),rec.get("director"),rec.get("country"),rec.get("poster"),Integer.parseInt(rec.get("minutes")));
            
            list.add(details);
        }
        
        return list;
    }
    
    public void testLoadMovies(){
        ArrayList<Movie> result = loadMovies("ratedmovies_short.csv");
        System.out.println(result.size());
        int genre = 0;
        for(Movie m : result){
            //System.out.println(m.getTitle());
            if( m.getGenres().contains("Comedy") ){
                //genre++;
                //System.out.println(m.getTitle());
            }
            if( m.getMinutes()>150 ){
                genre++;
            }
            
        }
        HashMap<String,ArrayList<String>> map = directorList(result);
        System.out.println("There are "+genre+" movies that are of Comedy");
        int max =0;
        String highestDir = null;
        for( String s : map.keySet() ){
            //System.out.println(s+"\t"+map.get(s).size());
            if( map.get(s).size() > max ){
                max = map.get(s).size();
                highestDir = s;
            }
        }
        System.out.println(highestDir+"\t"+map.get(highestDir).size());
    }
    
    public HashMap<String,ArrayList<String>> directorList(ArrayList<Movie> m){
        HashMap<String,ArrayList<String>> map = new HashMap<String,ArrayList<String>>();
        for( Movie mov : m){
            ArrayList<String> names= new ArrayList<String>();
            String director= mov.getDirector();
            String title = mov.getTitle();
            names.add(title);
            if (! map.keySet().contains(director) ){
                map.put(director,names);
            }
            else{
                map.get(director).add(title);
            }
       }
       return map;
    }
    
    public ArrayList<Rater> loadRaters(String filename){
        FileResource fr = new FileResource("data/"+filename);
        CSVParser parser = fr.getCSVParser();
        ArrayList<Rater> list = new ArrayList<Rater>();
        
        for( CSVRecord r : parser){
            EfficientRater id =new EfficientRater( r.get("rater_id"));
            list.add(id);
            
        }
        
        return list;
    }
    
    public void testLoadRaters(){
        String filename = "ratings.csv";
        ArrayList<Rater> result = loadRaters(filename);
        ArrayList<String> ids = new ArrayList<String>();
        for( Rater r : result ){
            if( ! ids.contains(r.getID()) ){
                ids.add(r.getID());
            }
        }
        System.out.println(ids.size());
        int numberR = numberOfRatingsByRater(result,"193");
        System.out.println(numberR);
        HashMap<String,ArrayList<Rating>> map = numberOfRatings(filename);
        ArrayList<String> max = new ArrayList<String>();
        int size = 0;
        for( String key : map.keySet()){
            if( map.get(key).size() > size){
                size = map.get(key).size();
            }
        }
        for( String key : map.keySet()){
            if( map.get(key).size() == size){
                max.add(key);
            }
        }
        for (String s : map.keySet() ){
            
            for( int x=0;x<max.size();x++ ){
                if( s == max.get(x)  ){
                      System.out.println("Rater "+s+" has rated "+map.get(s).size());
                }
            }
         }
         int number = NoOfRatingsForItem(filename,"1798709");
         System.out.println("1798709 has "+number+" ratings");
         int total = (totalMovies(map)).size();
         System.out.println("There are "+total+" different movies");
         System.out.println(System.nanoTime());
    }
    
    public int NoOfRatingsForItem(String filename,String id){
        int count = 0;
        HashMap<String,ArrayList<Rating>> map = numberOfRatings(filename);
        for( String x : map.keySet() ){
            ArrayList<Rating> r = map.get(x);
            for( Rating ra : r){
                if( ra.getItem().contains(id) ){
                    count = count+1;
                
                }
            }
        }
        return count;
    }
    
    public ArrayList<String> totalMovies(HashMap<String,ArrayList<Rating>> map ){
        ArrayList<String> total =new ArrayList<String>();
        for( String key : map.keySet() ){
            ArrayList<Rating> r = map.get(key);
            for( Rating ra : r){
                if(! total.contains(ra.getItem()) ){
                    total.add(ra.getItem());
                }
            }
        }
        return total;
    }
    
    public int numberOfRatingsByRater(ArrayList<Rater> result,String s){
        int num = 0;
        for(Rater r : result){
            if( r.getID().equals(s) ){
                num = num +1;
            }
        }
        return num;
    }
    
    public HashMap<String,ArrayList<Rating>> numberOfRatings(String filename){
        FileResource fr = new FileResource("data/"+filename);
        CSVParser parser = fr.getCSVParser();
        HashMap<String,ArrayList<Rating>> list = new HashMap<String,ArrayList<Rating>>();
        for( CSVRecord rec : parser){
            String id = rec.get("rater_id");
            String item = rec.get("movie_id");
            double rate = Double.parseDouble(rec.get("rating"));
            ArrayList<Rating> Alist = new ArrayList<Rating>();
            Rating ting = new Rating(item,rate);
            if( ! list.containsKey(id) ){
                Alist.add(ting);
                list.put(id,Alist);
            }
            else{
                list.get(id).add(ting);
            }
        }
        //System.out.println(list);
        return list;
    }
    
    

}
