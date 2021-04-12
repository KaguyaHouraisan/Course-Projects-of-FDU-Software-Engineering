package Test;
import java.util.Scanner ;
import java.lang.Math ;

public class HomeWork {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the length of the cuboid...");
        int length = input.nextInt();
        System.out.println("Enter the width of the cuboid...");
        int width = input.nextInt();
        System.out.println("Enter the height of the cuboid...");
        int height = input.nextInt();
        int cuboidArea = ( length * width + length * height + width * height ) * 2;
        System.out.println("The surface area of the Cuboid is " + cuboidArea);

        System.out.println("Enter the radius of the sphere...");
        float radius = input.nextFloat();
        double sphereArea = radius * radius * Math.PI * 4;
        System.out.println("The surface area of the sphere is " + sphereArea);

        System.out.println("Enter the letter...");
        char letter = input.next().charAt(0);

        char result = (letter >= 'A' && letter <= 'Z') ? (letter += 32) : (letter -= 32);
        System.out.println("The other form is " +  result);
    }
}
