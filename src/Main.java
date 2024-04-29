import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String data;
        String[] infoArray;
        Scanner keyboard = new Scanner(System.in);
        char option = 0;
        String filePath;
        File myFile;
        int index = 0;
        boolean readFile;

        Forest forestClass = new Forest("Montane");

        System.out.println("Welcome to the Forestry Simulation");
        System.out.println("----------------------------------");

        while (true) {
            readFile = true;
            try {
                if (option == 'L') {
                    System.out.print("Enter forest name : ");
                    filePath = keyboard.next();
                    myFile = new File(filePath + ".db");
                    if (myFile.exists())
                        forestClass = new Forest(filePath);
                    else {
                        System.out.println("Error opening/reading " + filePath + ".db");
                        System.out.println("Old forest retained");
                        readFile = false;
                    }
                } /// end of L option
                else {
                    filePath = args[index];
                    myFile = new File(filePath + ".csv");
                    if (myFile.exists()) {
                        forestClass = new Forest(args[index]);
                    } else {
                        System.out.println("Initializing from " + filePath);
                        System.out.println("Error opening/reading " + filePath + ".csv");
                        index++;
                        filePath = args[index];
                        myFile = new File(filePath + ".csv");
                        forestClass = new Forest(filePath);
                    }
                } // end of else statement

                if (readFile) {
                    Scanner myReader = new Scanner(myFile);
                    if (option != 'L') {
                        System.out.println("Initializing from " + filePath);
                    }
                    while (myReader.hasNextLine()) {
                        data = myReader.nextLine();
                        infoArray = data.split(",");

                        Tree newTree = new Tree(stringToEnum(infoArray[0]), Integer.parseInt(infoArray[1]), Double.parseDouble(infoArray[2]), Double.parseDouble(infoArray[3]));
                        forestClass.addNewTree(newTree);

                    } /// end of while loop
                } // end of if read file

            } /// end of try block
            catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } /// end of catch block

            while (true) {
                /// enter switch
                System.out.print("(P)rint, (A)dd, (C)ut, (G)row, (R)eap, (S)ave, (L)oad, (N)ext, e(X)it : ");
                option = Character.toUpperCase(keyboard.next().charAt(0));
                switch (option) {
                    case 'P':
                        forestClass.printForest();
                        break;
                    case 'A':
                        forestClass.addTree();
                        break;
                    case 'C':
                        cutTree(forestClass);
                        break;
                    case 'G':
                        forestClass.growTree();
                        break;
                    case 'R':
                        reapTree(forestClass);
                        break;
                    case 'S':
                        saveTree(forestClass.getForestArray(), "Montane");
                        break;
                    case 'L':
                        break;
                    case 'N':
                        break;
                    case 'X':
                        break;
                        default:
                        System.out.println("Invalid menu option, try again");
                } /// end of switch statement

                if (option == 'N') {
                    System.out.println("Moving to next forest");
                    index++;
                    break;
                } // end of if statement

                if (option == 'L')
                    break;

                if (option == 'X')
                    break;

            }/// end of while loop

            if (option == 'X')
                break;

        } // end of while loop
        System.out.println("Exiting the Forestry Simulation");

    } // end of main

    public static Tree.TypeSpecies stringToEnum(String toEnum) {
        switch(toEnum) {
            case "Maple":
                return Tree.TypeSpecies.MAPLE;
            case "Birch":
                return Tree.TypeSpecies.BIRCH;
            case "Fir":
                return Tree.TypeSpecies.FIR;
        } // end of switch
        return null;
    } // end of string to enum


    ///// START OF CUT METHOD ------------------------------------------------------------------------------------------

    /**
    user launches forestry simulation above
    program asks what tree to cut
    tree is terminated if valid
    if invlaid, error displays
     *
     */

    public static void cutTree(Forest forestClass) {
        Scanner keyboard = new Scanner(System.in);
        String input;
        int index;

        do {
            System.out.print("Tree number to cut down: ");
            input = keyboard.next();
            if (!isNumber(input)) {
                System.out.println("That is not an integer");
            }
        } while (!isNumber(input)); /// means if it is not a number

        index = Integer.parseInt(input);

        if (index >= forestClass.getForestSize()) {
            System.out.println("Tree number " + index + " does not exist");
            return;
        } // end of if statement
            forestClass.cutTree(index);

    } // end of cut tree method

// END OF CUT TREE METHOD ----------------------------------------------------------------------------------------------

    // START OF REAP TREE METHOD ---------------------------------------------------------------------------------------

    /** user inputs 'R'
     * program asks height to reap from
     * trees taller than heights are removed and replaced with random tree
     * back to menu
     */

    public static void reapTree(Forest forestClass) {
        Scanner keyboard = new Scanner(System.in);
        String input;
        do {
            System.out.print("Height to reap from: ");
            input = keyboard.next();
            if (!isNumber(input))
                System.out.println("That is not an integer");
        } while (!isNumber(input));

        forestClass.reapTree(input);
    } // END OF REAP TREE METHOD ---------------------------------------------------------------------------------------

// START OF IS NUMBER METHOD -------------------------------------------------------------------------------------------
    public static boolean isNumber(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    } // end of is number method

//// START OF SAVE TREE METHOD -----------------------------------------------------------------------------------------

    /** user inputs 'S'
     * program asks for file name
     * user saves forest to file
     * back to menu
     */
    public static void saveTree(ArrayList<Tree> ForestArray, String forestName) {
        FileWriter writeFile = null;
        try {
            writeFile = new FileWriter(forestName + ".db");
            for (int i = 0; i < ForestArray.size(); i++) {
                String treeType = ForestArray.get(i).getSpecies().toString();
                treeType = treeType.charAt(0) + treeType.substring(1).toLowerCase();
                writeFile.write(treeType + ",");
                writeFile.write(ForestArray.get(i).getYear() + ",");
                writeFile.write(ForestArray.get(i).getHeight() + ",");
                writeFile.write(ForestArray.get(i).getGrowthRate() + "\n");


                //---------- treeType.charAt(0) + treeType.SubString(1).toLowerCase; ----------
                /// "M" + "aple"

            } // end of save tree for loop
            writeFile.close();
        } catch (IOException e) {

        } // end of save tree for loop


    } //// end of save tree

} // end of public main class
