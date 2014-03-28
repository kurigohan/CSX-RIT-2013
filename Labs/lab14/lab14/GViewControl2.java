import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class GViewControl2 extends JFrame implements Observer{
    JLabel title = new JLabel("Moves: 0 Select first card.");
        public Color colors[]={Color.RED,Color.BLUE,Color.GREEN,Color.ORANGE,Color.CYAN,Color.GRAY,Color.MAGENTA,Color.PINK};
        public ConcentrationModel model;
        public ArrayList<CardButton> buttonlist=new ArrayList<CardButton>();
        int count=0;
        
        //main just makes an object with a model
        public static void main(String args[]){
                new GViewControl2(new ConcentrationModel());
        }
        //constructor, makes all the frames described above
        public GViewControl2(ConcentrationModel model) {
                
                this.model=model;
                model.addObserver(this);
        JFrame gridFrame = new JFrame("Concentration Game");
        gridFrame.setSize(400,300);
        gridFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        

        
        JPanel mainpanel = new JPanel();
        mainpanel.setLayout(new GridLayout(4,4));
   
        gridFrame.add(mainpanel,BorderLayout.CENTER);
        

        gridFrame.add(title,BorderLayout.NORTH);
        JPanel bottombuttons = new JPanel();
        
        FlowLayout bottumlayout = new FlowLayout();
        bottumlayout.setAlignment(FlowLayout.RIGHT);
        
        bottombuttons.setLayout(bottumlayout);
        
        JButton reset=new JButton("reset");
        JButton cheat=new JButton("Cheat");
        JButton undo=new JButton("Undo");

        reset.addActionListener(new reset());
        
        cheat.addActionListener(new cheat());
        
        undo.addActionListener(new undo());
        
        bottombuttons.add(reset);
        bottombuttons.add(cheat);
        bottombuttons.add(undo);
        
        
        gridFrame.add(bottombuttons, BorderLayout.SOUTH);
    
        for(int i=0; i < 16; i++) {
                CardButton btn=new CardButton(i);
                buttonlist.add(btn);
            btn.addActionListener(new cardbuttonlistener());
                         
            mainpanel.add(btn);
        }
        //gridFrame.pack();
        gridFrame.setVisible(true);
        }
//heres the listener for the button, it calls selectcard        
        class cardbuttonlistener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent ae) {
                        count++;
               model.selectCard(((CardButton)ae.getSource()).getPos());
             
        }                
        }
//listener for reset, it resets count and calls reset on model        
        class reset implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent ae) {
                        count = 0;
               model.reset();
        }                
        }        
//listener for undo, calls undo on model
        class undo implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent ae) {
               model.undo();
        }                
        }        
        //for cheat a new cheatframe is created and it's got all the specs that the gridframe of the game has
        class cheat implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent ae) {
               
                        CheatFrame cheat = new CheatFrame(new ArrayList<CardButton>(), 1);
               cheat.setLayout(new GridLayout(4,4));
               cheat.setSize(400,300);
               cheat.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
               ArrayList<CardFace> cards = model.cheat();
                       for (CardFace card : cards){
                                CardButton bla = new CardButton(0);
                                bla.setBackground(colors[card.getNumber()]);
                                bla.setText(""+(card.getNumber()));
                                cheat.add(bla);
                    }//endfor
                       cheat.setVisible(true);
               
        }                
        }        
        //the update function changes the top and flips the cards and does everything else that visually changes
        public void update(Observable t, Object o){
                ArrayList<CardFace> cards = model.getCards();
                int i=0;
        if(model.howManyCardsUp() == 1) {
                        title.setText("Moves: "+count+" Select second card.");
                } else {
                        title.setText("Moves: "+count+" Select first card.");
                }                 
                
                for (CardFace card : cards){
                        if (card.isFaceUp()){
                                CardButton bla = buttonlist.get(i);
                                bla.setBackground(colors[card.getNumber()]);
                                bla.setText(""+(card.getNumber()));
                        }
                        else{
                                CardButton bla = buttonlist.get(i);
                                bla.setBackground(Color.WHITE);
                                bla.setText("");
                        }
                        i++;
                }//endfor
                
                
        }
        
}