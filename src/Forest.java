import java.util.ArrayList;

public class Forest {
   private static ArrayList<Tree> ForestArray = new ArrayList<>();
   private String name;
   public Forest(String forestName) {
        name = forestName;
        ForestArray = new ArrayList<>();
    } //end of default constructor

    public void addNewTree(Tree newTree) {
       ForestArray.add(newTree);
    } // end of add new tree

    public int getForestSize() {
       return ForestArray.size();
    } // end of forest size

    public ArrayList<Tree> getForestArray() {
       return ForestArray;
    } // end of get forest array
    public void printForest() {
        double average = 0;
        System.out.println("Forest name: " + name);
        for (int i = 0; i < ForestArray.size(); i++) {
            System.out.printf("    %d ", i);
            System.out.println(ForestArray.get(i).toString());
            average += ForestArray.get(i).getHeight();

        } // end of for loop
        System.out.printf("There are %d trees, with an average height of %.2f\n", ForestArray.size(), average / ForestArray.size());

    } // end of print forest

    public void addTree() {
        ForestArray.add(Tree.makeRandomTree());

    } // end of add tree method

    public void cutTree(int treeNumber) {
        ForestArray.remove(treeNumber);
    } // end of cut tree

    public void growTree() {

        for (int i = 0; i < ForestArray.size(); i++) {
            ForestArray.get(i).growing();

        } // end of for loop

    }/// end of grow tree method

    public void reapTree(String input) {

        int reapingHeight = Integer.parseInt(input);
        for (int i = 0; i < ForestArray.size(); i++) {
            if (ForestArray.get(i).getHeight() > reapingHeight) {
                Tree newTree = Tree.makeRandomTree();

                System.out.print("Reaping the tall tree  ");
                System.out.println(ForestArray.get(i).toString());

                System.out.print("Replaced with new tree ");
                System.out.println(newTree.toString());

                ForestArray.set(i, newTree);

            } // end of reap if statement

        } /// end of reaping for loop



    }/// end of reap tree method


} // end of forest class
