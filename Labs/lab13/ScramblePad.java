/**
 * A GUI representing a Scramble pad that does nothing.
 *
 * @author Andy Nguyen
 */

import javax.swing.*;
import java.awt.*;

public class  ScramblePad  extends JFrame { 
    

    /**
     * Contructor for ScamblePad Creates and displays the GUI. 
     */
    public ScramblePad() {
        // set frane properties
        this.setTitle( "DCS Scamble Pad" );
        this.setVisible( true );
        this.setSize( 200, 300 );
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );        
        
        // create and populate content container
        Container container = getContentPane();
        container.setLayout( new BorderLayout() );

        container.add( createStatusPanel(), BorderLayout.NORTH );
        container.add( createButtonPanel(), BorderLayout.SOUTH );
        container.add( createNumberPadPanel(), BorderLayout.CENTER );
    }

    /**
     * Create status panel.
     *
     * @return a JPanel containing the status label
     */
    private JPanel createStatusPanel() {
        JPanel statusPanel = new JPanel();
        JLabel statusLabel = new JLabel( "LOCKED" );

        statusLabel.setForeground( Color.RED ); 
        statusPanel.setBackground( Color.BLACK );
        statusPanel.add( statusLabel );

        return statusPanel;
    }

    /**
     * Create number pad panel
     *
     * @return a JPanel containing the number pad
     */
    private JPanel createNumberPadPanel() {
        JPanel numberPadPanel = new JPanel( new GridLayout( 4, 3 ) );
        JButton number = null;

        numberPadPanel.setBackground( Color.BLACK );
        // fill number pad with number buttons
        for( int i = 1; i < 13; i++ ) {
            if( i == 11 ) { // 0 button
                number = new JButton( "0" ); 
            }
            else if(  i == 10 || i == 12  ) {// empty spaces before and after 0
                number = new JButton( "" );
                number.setEnabled( false ); // make these buttons unclickable
            }
            else { // 1 - 9
                number = new JButton( i + "" );
            }

            number.setBackground( Color.BLACK );
            number.setForeground( Color.YELLOW );
            numberPadPanel.add( number );

        }
        return numberPadPanel;
    }

    /**
     * Create button panel.
     *
     * @return a JPanel containing buttons
     */
    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground( Color.BLACK );
        
        buttonPanel.add( new JButton( "Start" ) );
        buttonPanel.add( new JButton( "Okay" ) );

        return buttonPanel;
    }


    public static void main( String args[] ) {
        ScramblePad scramblePad = new ScramblePad();
    }

}


/*
 * ScramblePad.java
 * 
 * Version: 
 *     $Id: ScramblePad.java,v 1.3 2013/11/25 01:24:58 andy Exp andy $
 * 
 * Revisions: 
 *     $Log: ScramblePad.java,v $
 *     Revision 1.3  2013/11/25 01:24:58  andy
 *     Implemented methods.
 *
 *     Revision 1.2  2013/11/25 01:02:13  andy
 *     Skeleton
 *
 *     Revision 1.1  2013/11/25 00:54:18  andy
 *     Initial revision
 *
 *
 */