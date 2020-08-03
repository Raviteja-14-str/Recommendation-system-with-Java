
/**
 * Write a description of GenreFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GenreFilter implements Filter{
    private String genre;
    
    public GenreFilter(String s){
        genre = s;
    }
    
    public boolean satisfies(String id){
        return MovieDatabase.getGenres(id).contains(genre);
    }

}
