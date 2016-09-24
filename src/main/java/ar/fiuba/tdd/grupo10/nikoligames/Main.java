package ar.fiuba.tdd.grupo10.nikoligames;

import java.util.List;

//2016
public class Main {
    public static void main(String[] args) {
        System.out.println("This is just a template project");

        List<List<Integer>> exampleGrid = SudokuFactory.constructorAlgorithm();
        for ( List<Integer> row : exampleGrid ) {
            for ( int number : row ) {
                System.out.print( number + " " );
            }
            System.out.println();
        }
    }


}
