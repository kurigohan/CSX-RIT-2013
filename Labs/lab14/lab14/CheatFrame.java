/**
 *  Class definition for the cheating window in a concentration card game. 
 *
 * @author Andy Nguyen
 */

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class  CheatFrame extends JFrame {

    /**
     * Contructor for CheatFrame object.
     *
     * @param cardButtons      An ArrayList of CardButtons that are all showing their 
     *                                          numbers.
     * @param sizze                 The size (of one side) of the board (measured in cards)
     */ 
    public CheatFrame( ArrayList<CardButton> cardButtons,  int size) {
        this.setLayout( new GridLayout( size, size ) );
        int windowSize = (int) (size*100 * 0.75);
        this.setSize( windowSize, windowSize );
        this.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );

        for( CardButton cb : cardButtons ) {
            this.add( cb );
        }

        this.setVisible( true );
    }
}


/*
 * CheatFrame.java
 * 
 * Version: 
 *     $Id: CheatFrame.java,v 1.2 2013/12/05 21:29:55 andy Exp andy $
 * 
 * Revisions: 
 *     $Log: CheatFrame.java,v $
 *     Revision 1.2  2013/12/05 21:29:55  andy
 *     Implemented constructor
 *
 *     Revision 1.1  2013/12/05 16:38:20  andy
 *     Initial revision
 *
 *
 */