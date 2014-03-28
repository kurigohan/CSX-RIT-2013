/**
 *  Class definition for the graphical view and controller. 
 *
 * @author Andy Nguyen
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;


public class  GViewControl extends JFrame implements Observer {
    private ConcentrationModel model;
    private ArrayList<CardButton> buttonList;
    private JLabel statusLabel;
    private Color colors[] = { Color.RED, Color.BLUE, Color.GREEN, Color.ORANGE,
                                                Color.CYAN, Color.GRAY, Color.MAGENTA, Color.PINK };

    /**
     * Constructs a GViewControl objects
     *
     * @param model      The model for the view and controller
     */
    public GViewControl( ConcentrationModel model ) {
        model.addObserver( this );
        this.model = model;
        buttonList = new ArrayList<CardButton>();

        // set frame properties
        this.setTitle( "Concentration Game" );
        this.setVisible( true );
        this.setSize( model.BOARD_SIZE * 100 , model.BOARD_SIZE * 100 + 50 );
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );        

        // create and populate content container
        Container container = getContentPane();
        container.setLayout( new BorderLayout() );

        statusLabel = new JLabel( "Moves: 0 Select first card." );
        JPanel statusPanel = new JPanel();
        statusPanel.add( statusLabel );



        container.add( statusPanel, BorderLayout.NORTH );
        container.add( createButtonPanel(), BorderLayout.SOUTH );
        container.add( createCardPanel(), BorderLayout.CENTER );
    }

    /**
     * Update the window when the model indicates an update is required. 
     * Update changes the color and string content of a CardButton based 
     * on the CardFaces in the model, and it changes the text in the label based
     * on the model state. 
     *
     * @param t      An Observable - not used
     * @param o      An Object - not used
     */
    public void update( Observable t, Object o ) {
        ArrayList<CardFace> cards = model.getCards();

        int moveCount = model.getMoveCount();
        if(model.howManyCardsUp() == 1) {
            statusLabel.setText("Moves: "+ moveCount +" Select second card.");
        } else {
            statusLabel.setText("Moves: "+ moveCount +" Select first card.");
        }                 
                

        CardFace card = null;
        CardButton cardButton = null;
        // update card view
        for ( int i = 0, len = cards.size(); i < len; ++i ) {
            card = cards.get( i );
            cardButton = buttonList.get( i );
          
            if( card.isFaceUp() ) {
                cardButton.setBackground( colors[ card.getNumber() ] );
                cardButton.setText( card.getNumber() + "" );
            } else {
                cardButton.setBackground( Color.WHITE );
                cardButton.setText( "" );
            }
        }

        this.validate();
    }

    /**
     * JPanel containing buttons.
     */
    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();

        JButton resetButton = new JButton( "Reset" );
        resetButton.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent ae ) {
                   model.reset();
            }                
        }); 


        JButton undoButton = new JButton( "Undo" );
        undoButton.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent ae ) {
                model.undo();
            }
        });

        JButton cheatButton = new JButton( "Cheat" );
        cheatButton.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent ae ) {
                CheatFrame cheatFrame = new CheatFrame( getCheatView(), 
                                                                                                model.BOARD_SIZE );
                cheatFrame.setLocation( model.BOARD_SIZE * 100 + 50 , 100 );
            }
        });

        // add buttons to panel
        buttonPanel.add( resetButton );
        buttonPanel.add( undoButton );
        buttonPanel.add( cheatButton );

        return buttonPanel;
    }

    /**
     * @return JPanel containing the card buttons.
     */
    private JPanel createCardPanel() {
        JPanel cardPanel = new JPanel( new GridLayout( model.BOARD_SIZE, 
                                                                                            model.BOARD_SIZE) );

        CardButton cardButton = null;
        CardFace card = null;
        ArrayList<CardFace> cards = model.getCards();

        // fill panel with card buttons
        for( int i = 0; i < cards.size(); ++i  ) {
            card = cards.get( i );
            cardButton = new CardButton( i );
            cardButton.setBackground( Color.WHITE );

            cardButton.addActionListener( new ActionListener() {
                
                public void actionPerformed( ActionEvent ae )
                {
                    // execute when button is pressed
                    model.selectCard( ( (CardButton) ae.getSource() ).getPos() );
                }
             });     

            if( card.isFaceUp() ) { // show the card value if face up
                cardButton.setText( card.getNumber() + "" );
            } else { // show blank
                cardButton.setText( "" );
            }

            cardPanel.add( cardButton ); 
            buttonList.add( cardButton );
        }

        return cardPanel;
    }

    /**
     * @return arraylist containing the CardButtons showing the card value.
     */
    private ArrayList<CardButton> getCheatView() {
        ArrayList<CardFace> cheatCards = model.cheat();
        ArrayList<CardButton> cheatButtons= new ArrayList<CardButton>();
        CardButton cb  = null;

        int i = 0;
        for( CardFace card : cheatCards ) {
            cb = new CardButton( i );
            cb.setText( "" + card.getNumber() );
            cb.setBackground( colors[ card.getNumber() ] );
            cheatButtons.add( cb );
            ++i;
        }

        return cheatButtons;

    }

    /**
     * The main method used to play a game.
     */
    public static void main ( String[] args ) {
        GViewControl gui = new GViewControl( new ConcentrationModel());

    }
}


/*
 * GViewControl.java
 * 
 * Version: 
 *     $Id: GViewControl.java,v 1.3 2013/12/05 20:50:55 andy Exp andy $
 * 
 * Revisions: 
 *     $Log: GViewControl.java,v $
 *     Revision 1.3  2013/12/05 20:50:55  andy
 *     Attached actionlisteners to buttons
 *
 *     Revision 1.2  2013/12/05 17:19:59  andy
 *     Implemented gui methods.
 *
 *     Revision 1.1  2013/12/05 16:42:29  andy
 *     Initial revision
 *
 *
 */