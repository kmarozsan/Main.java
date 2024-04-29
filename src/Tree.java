public class Tree {

    private static final int MIN_YEAR = 2000;
    private static final int MAX_YEAR = 2024;
    private static final double MAX_HEIGHT = 20;
    ;
    private static final double MIN_HEIGHT = 10;
    private static final double MAX_GROWTH_RATE = 20;
    private static final double MIN_GROWTH_RATE = 10;


    public enum TypeSpecies {UNKNOWN, MAPLE, FIR, BIRCH}

    /// end of species list
    private TypeSpecies Species;
    private int year;
    private double height;
    private double growthRate;

    // Default constructor
    public Tree() {
        Species = TypeSpecies.UNKNOWN;
        year = 0;
        height = 0.0;
        growthRate = 0.0;
    } //end of default constructor

    // CONSTRUCTOR
    public Tree(TypeSpecies newSpecies, int newYear, double newHeight, double newGrowthRate) {

        this();
        year = newYear;
        height = newHeight;
        growthRate = newGrowthRate;
        Species = newSpecies;

    } // end of constructor

    public TypeSpecies getSpecies() {return Species;}
    public int getYear() {
        return year;
    }

    public double getHeight() {
        return height;
    }

    public double getGrowthRate() {

        return growthRate;
    }

    public void growing() {
        height *= (100.0 + growthRate) / 100;
    }

    public String toString() {
        return (String.format("%-5s %-5d %5.2f' %5.1f%%", Species.toString(), year, height, growthRate));
    } // end of to string method


    public static Tree makeRandomTree() {
        Tree randomTree;

        randomTree = new Tree( (TypeSpecies.values()[(int) (1 + Math.random() * (TypeSpecies.values().length - 1))]),
                (int) (MIN_YEAR + Math.random() * (MAX_YEAR - MIN_YEAR)) ,
                MIN_HEIGHT + Math.random() * MAX_HEIGHT - MIN_HEIGHT,
                MIN_GROWTH_RATE + Math.random() * MAX_GROWTH_RATE - MIN_GROWTH_RATE
                );
        return (randomTree);
    } // end of tree class

} // end of tree public class
