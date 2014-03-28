/**
 * The TestCells class tests the Cell classes, it's subclasses 
 * (WaterCell and ShipCell), and also the Ship class used for the battleship game.
 * 
 * @author Andy Nguyen
 */

public class TestCells {
    public enum Result{
        MISS, HIT, SUNK, SAME
    }

    public static void main( String args[] ) {
        testWaterCell();
        testShipCell();
    }

    public static void testWaterCell() {
        System.out.println("WaterCell");
        WaterCell wc = new WaterCell();
        System.out.println( wc );
        System.out.println( Result.values()[wc.hit()] );
        System.out.println( Result.values()[wc.hit()] );
        System.out.println( wc );
        System.out.println();

    }

    public static void testShipCell() {

        Ship s = new Ship("A", 3 );   
        System.out.println("Ship At Creation");
        System.out.println( s );
        System.out.println();

        ShipCell sc1 = new ShipCell( s );
        System.out.println("ShipCell");
        System.out.println( sc1 );
        System.out.println( Result.values()[sc1.hit()] );
        System.out.println( Result.values()[sc1.hit()] );
        System.out.println( sc1 );
        System.out.println();

        ShipCell sc2 = new ShipCell( s );
        System.out.println("ShipCell");
        System.out.println( sc2 );
        System.out.println( Result.values()[sc2.hit()] );
        System.out.println( sc2 );
        System.out.println();

        System.out.println("Hit ship without creating ShipCell");
        s.hit();
        System.out.println("Ship After");
        System.out.println( s );

    }
}