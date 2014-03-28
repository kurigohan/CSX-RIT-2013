/**
 * A GUI that displays dialog boxes.
 *
 * @author Andy Nguyen
 */

import javax.swing.*;
import java.awt.*;

public class  DialogViewer {

    /**
     * Displays a dialog box corresponding to the command given.
     *
     * @param command      indicates the type of dialog to display
     */
    public void displayDialog( String command ) {
        command = command.toLowerCase();
        if( command.equals( "f" ) ) {
            displayFileChooserDialog();
        }
        else if( command.equals( "c" ) ) {
            displayConfirmDialog();
        }
        else if( command.equals( "m" ) ) {
            displayMessageDialog();
        }
        else{
            System.err.println( "Usage: java DialogViewer f/c/m" );
        }

        System.exit( 0 );
    }

    /**
     * Display a file chooser dialog.
     */
    private void displayFileChooserDialog() {
        JFileChooser chooser = new JFileChooser();
        int selection = chooser.showOpenDialog( null );

        // print file name if a file is selected
        if( selection == JFileChooser.APPROVE_OPTION ) {
            System.out.println( chooser.getSelectedFile() );
        } 
        else {
            System.out.println( "No file selected." );
        }
    }

    /**
     * Display a confimation dialog.
     */
    private void displayConfirmDialog() {
        int selection = JOptionPane.showConfirmDialog( null, "Are you sure?", "Confirmation Dialog", 
                                                                                            JOptionPane.YES_NO_CANCEL_OPTION );
        
        switch( selection ) {
            case 0: 
                System.out.println( "Selected 'Yes'" );
                break;
            case 1: 
                System.out.println( "Selected 'No'" );
                break;
            case 2:
                System.out.println( "Selected 'Cancel'" );
                break;
            case -1:
                System.out.println( "Confirmation dialog closed." );
                break;
        }        
    }

    /**
     * Display a message dialog.
     */
    private void displayMessageDialog() {
        JOptionPane.showMessageDialog( null, "Andy", "Message Dialog", 
                                                                     JOptionPane.INFORMATION_MESSAGE );
    }



    public static void main( String[] args ) {
        if( args.length > 0 ) {
            DialogViewer viewer = new DialogViewer();
            viewer.displayDialog( args[0] );
        } 
        else {
            System.err.println( "Usage: java DialogViewer f/c/m" );
        }
    }

}


/*
 * DialogViewer.java
 * 
 * Version: 
 *     $Id: DialogViewer.java,v 1.6 2013/11/25 02:35:47 andy Exp andy $
 * 
 * Revisions: 
 *     $Log: DialogViewer.java,v $
 *     Revision 1.6  2013/11/25 02:35:47  andy
 *     Implemented rest of methods.
 *
 *     Revision 1.5  2013/11/25 01:55:52  andy
 *     renamed methods.
 *
 *     Revision 1.4  2013/11/25 01:54:24  andy
 *     implemented displayFileChooserDialog
 *
 *     Revision 1.3  2013/11/25 01:40:00  andy
 *     implemented displayDialog method.
 *
 *     Revision 1.2  2013/11/25 01:29:53  andy
 *     skeleton.
 *
 *     Revision 1.1  2013/11/25 01:28:41  andy
 *     Initial revision
 *
 *
 */