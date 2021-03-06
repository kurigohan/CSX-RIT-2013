/* 
 * PictureBorder.java 
 * 
 * Version: 
 *     $Id: PictureBorder.java,v 1.4 2013/09/06 01:26:38 agn3691 Exp agn3691 $ 
 * 
 * Revisions: 
 *     $Log: PictureBorder.java,v $
 *     Revision 1.4  2013/09/06 01:26:38  agn3691
 *     Fixed error in addBorder method (forgot return statement)
 *
 *     Revision 1.3  2013/09/06 01:23:09  agn3691
 *     Changed error message.
 *
 *     Revision 1.2  2013/09/06 01:20:03  agn3691
 *     Implemented addBorder method.
 *
 *     Revision 1.1  2013/09/06 01:15:53  agn3691
 *     Initial revision
 *
 * 
 */

/**
 * Add a border to a picture.
 *
 * @author      Andy Nguyen
 */
import java.awt.Color;
public class PictureBorder {

  public static void main( String args [] ) {
    Picture pic = new Picture(); // instantiate a picture object
    int color = 0;
    int borderWidth = 10;
    // set fileName to the 1st command line arg
    String fileName = ""; 

    if( args.length > 0 ) { // check for one command line argument
      fileName = args[0];

      if( pic.load( fileName ) ) { // check if valid file
        pic = addBorder(pic, borderWidth, color); // add a border to the picture
        pic.show(); // display the picture

        // output picture information
        System.out.println( "file: {" + fileName + "}");
        System.out.println( "border width: {" + borderWidth + "}");
        System.out.println( "Color: {" + color + "}");
       //System.out.println( "number of loop cycles: {" + width*height + "}" );
        System.out.println( "picture: " + pic);

        // exit program after short delay 
        try {
          Thread.sleep( 9000 );
        }
        catch ( InterruptedException exc ) {}
          System.exit( 0 );
        } // end if(pic.load(fileName))
      else{
        System.out.println("usage: java PictureBorder JPGfile");
      }
    } //end if(args.length == 1)
    
    else{
      System.out.println("usage: java PictureBorder JPGfile");
    }
  }
  
  /**
   * Adds a border to a picture by looping through the edges of the picture 
   * and replacing the pixels.
   * 
   * @param      pic      picture to add border to
   * @return     pic      picture with border
   */
  private static Picture addBorder(Picture pic, int borderWidth, int color){
    int width = pic.getWidth();
    int height = pic.getHeight();

    // add top border
    for( int x = 0; x < width; ++x){
      for( int y = 0; y < borderWidth; ++y){
        pic.setBasicPixel(x, y, color);
      }
    }
    // add bottom border
    for( int x = 0; x < width; ++x){
      for( int y = height-1; y > height-borderWidth; --y){
        pic.setBasicPixel(x, y, color);
      }
    }
    // add left border
    for( int y = 0; y < height; ++y){
      for( int x = 0; x < borderWidth; ++x){
        pic.setBasicPixel(x, y, color);
      }
    }
    // add right border
    for( int y = 0; y < height; ++y){
      for( int x = width-1; x > width-borderWidth; --x){
        pic.setBasicPixel(x, y, color);
      }
    }

    return pic;
  }

} // PictureBorder