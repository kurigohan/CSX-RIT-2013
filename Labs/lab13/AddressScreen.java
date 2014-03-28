/**
 * Displays a GUI with address information.
 *
 * @author Andy Nguyen
 */

import javax.swing.*;
import java.awt.*;

public class AddressScreen extends JFrame{

    /**
     * Contructor for AddressScreen. Creates and displays the GUI. 
     */
    public AddressScreen() {
        // set frane properties
        this.setTitle( "Address Information" );
        this.setVisible( true );
        this.setSize( 370, 210 );
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        // create and populate content container
        Container container = this.getContentPane(); 
        container.setLayout( new BorderLayout() );
        container.add( createButtonPanel(), BorderLayout.SOUTH );
        container.add( createLabelPanel(), BorderLayout.WEST );
        container.add( createTextFieldPanel(), BorderLayout.EAST);

    }


    /**
     * Create label panel.
     *
     * @return a JPanel containing labels
     */
    private JPanel createLabelPanel() {
        JPanel labelPanel = new JPanel( new GridLayout( 5, 0 ) );

        // add labels
        labelPanel.add( new JLabel( "Name" ) );
        labelPanel.add( new JLabel( "Address" ) );
        labelPanel.add( new JLabel( "City" ) );
        labelPanel.add( new JLabel( "State" ) );
        labelPanel.add( new JLabel( "Zip" ) );

        return labelPanel;
    }


    /**
     * Create textfield panel.
     *
     * @return a JPanel containing textfields
     */
    private JPanel createTextFieldPanel() {
        int FIELD_COUNT = 5;
        JPanel textFieldPanel = new JPanel( new GridLayout( 5, 0 ) );

        for( int i = 0; i < FIELD_COUNT; ++i ) {
            textFieldPanel.add( new JTextField( 15 ) );
        }

        return textFieldPanel;
    }

    /**
     * Create button panel.
     *
     * @return a JPanel containing buttons
     */
    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        // create padding around panel
        buttonPanel.setBorder( BorderFactory.createEmptyBorder( 20, 0 , 5 , 0 ) );
        // add buttons
        buttonPanel.add( new JButton( "Add" ) );
        buttonPanel.add( new JButton( "Modify" ) );
        buttonPanel.add( new JButton( "Delete" ) );

        return buttonPanel;
    }


    public static void main ( String args[] ) {
        AddressScreen frame = new AddressScreen();

    }

}


/*
 * AddressScreen.java
 * 
 * Version: 
 *     $Id: AddressScreen.java,v 1.2 2013/11/25 00:50:28 andy Exp andy $
 * 
 * Revisions: 
 *     $Log: AddressScreen.java,v $
 *     Revision 1.2  2013/11/25 00:50:28  andy
 *     Implemented methods.
 *
 *     Revision 1.1  2013/11/24 23:49:56  andy
 *     Initial revision
 *
 *
 */