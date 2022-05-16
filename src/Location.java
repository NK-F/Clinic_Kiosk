/**
 * This enum Location class is made to create, initialize, and return the
 * object location to the necessary methods in other classes. There are five locations
 * initialized, or counties, taken into consideration, with its respective city name and zip code.
 * @author Nicolas Karris-Flores
 * @author Kyle Mlynarski
 */
public enum Location {
    SOMERSET("Bridgewater", "08807"),
    MIDDLESEX("Piscataway", "08854"),
    MERCER("Princeton", "08542"),
    MORRIS("Morristown", "07960"),
    UNION("Union", "07083");

    //initializing the properties of the different counties
    public final String city;
    public final String zipCode;

    //giving the instance variable their values
    /**
     * This constructor initializes the location object by initializing
     * its properties the city name and zip code.
     * @param city, zipCode, the city name and zip code
     */
    Location(String city, String zipCode){
        this.city = city;
        this.zipCode = zipCode;
    }

    /**
     * This method only returns the String statement containing the city name
     * and zip code respective to the county.
     * @return String sentence of location properties
     */
    public String getLocationString() {
        return this.city + " " + this.zipCode + ", " + this;
    }


}

