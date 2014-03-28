/* 
 * Negative.java 
 * 
 * Version: 
 *     $Id: Negative.java,v 1.6 2013/09/05 23:09:34 agn3691 Exp agn3691 $ 
 * 
 * Revisions: 
 *     $Log: Negative.java,v $
 *     Revision 1.6  2013/09/05 23:09:34  agn3691
 *     Changed method name negateImage to negateColor
 *
 *     Revision 1.4  2013/09/05 22:15:38  agn3691
 *     Changed error messages to fit lab req
 *
 *     Revision 1.3  2013/09/05 22:07:28  agn3691
 *     Gets filename from command line argument instead of using preset filename.
 *
 *     Revision 1.2  2013/09/05 20:41:51  agn3691
 *     Added code to main method.
 *
 *     Revision 1.1  2013/09/05 20:11:26  agn3691
 *     Initial revision
 * 
 */

/**
 * Negate the colors of a JPEG image file.
 *
 * @author      Andy Nguyen
 */
import java.awt.Color;
public class Negative {

  public static void main( String args [] ) {
    Picture pic = new Picture(); // instantiate a picture object
    int width = 0; // width of picture
    int height = 0; // height of picture
    // set fileName to the 1st command line arg
    String fileName = ""; 
    if( args.length > 0 ) { // check for one command line argument
      fileName = args[0];
      if( pic.load( fileName ) ) { // check if valid file
        // set width and height of picture
        width = pic.getWidth();
        height = pic.getHeight();
        pic = negateColor(pic);
        pic.show(); // display the picture

        // output picture information
        System.out.println( "width: {" + width + "}");
        System.out.println( "height: {" + height + "}");
        System.out.println( "number of loop cycles: {" + width*height + "}" );
        System.out.println( "picture: " + pic);

        // exit program after short delay 
        try {
          Thread.sleep( 9000 );
        }
        catch ( InterruptedException exc ) {}
          System.exit( 0 );
        } // end if(pic.load(fileName))
      else{
        System.out.println("usage: java Negative JPGfile");
      }
    } //end if(args.length == 1)
    
    else{
      System.out.println("usage: java Negative JPGfile");
    }
  }
  
  /**
   * Negates the colors of a picture.
   * 
   * @param      pic      picture to negate
   * @return     pic      negated picture
   */


  private static Picture negateColor(Picture pic){
    int red = 0, green = 0, blue = 0;

    for( Pixel p : pic.getPixels()){
      // get colors of pixel and negate
      red = 255 - p.getRed();
      green = 255 - p.getGreen();
      blue = 255 - p.getBlue();
      p.setColor(new Color(red, green, blue));
      // pic.setBasicPixel(p.getX(), p.getY(), color.hashCode());
    }
    return pic;
  }

} // Negative