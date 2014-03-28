/**
 * Class definition for a button that represents a card in the concentration game.
 *
 * @author Andy Nguyen
 */

import javax.swing.JButton;

public class  CardButton extends JButton {

    private int position; // position of the CardButton
    
    /**
     * Construct a CardButton object. 
     *
     * @param pos      The position or index of the represented card in the model
     */
    public CardButton( int pos ) {
        position = pos;
    }


    /**
     * Get the position of the card
     *
     * @return An integer that is the position or index of the represented card in the model.
     */
    public int getPos() {
        return position;
    }

}


/*
 * CardButton.java
 * 
 * Version: 
 *     $Id: CardButton.java,v 1.1 2013/12/05 16:30:39 andy Exp andy $
 * 
 * Revisions: 
 *     $Log: CardButton.java,v $
 *     Revision 1.1  2013/12/05 16:30:39  andy
 *     Initial revision
 *
 *
 */