package Shield;

public class Square extends FlatFigure {
    public int calculateArea(){
        return getHight()*getWeight();
    }

    public static void main(String[] args) {
        Square squre=new Square();
        squre.setHight(10);
        squre.setWeight(10);
        squre.showDim();
        System.out.println(squre.calculateArea());
    }

}
