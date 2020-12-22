package university;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Tree myTree=new Tree();
        Scanner scanner=new Scanner(System.in);

        //Елементи в порядку додавання: 10, 11, 7, 6, 8, 12
        int[] arr = {10,11,7,6,8,12};

        for (int i = 0; i < arr.length; i++) {
            myTree.add(arr[i], i);
        }

        System.out.print("Симетричний: 6, 7, 8, 10, 11, 12\nДействительный: ");
        myTree.detourSymmetrical(myTree.getRoot());
        System.out.println("\n");

        System.out.print("Прямий: 10, 7, 6, 8, 11, 12\nДействительный: ");
        myTree.detourDirect(myTree.getRoot());
        System.out.println("\n");

        System.out.print("Зворотній: 6, 8, 7, 12, 11, 10\nДействительный: ");
        myTree.detoutReverse(myTree.getRoot());
        System.out.println("\n");
    }
}
