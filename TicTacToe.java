import javax.swing.JButton;
import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
public class TicTacToe extends JFrame implements ActionListener{
    private JLabel label,tally,p1,p2;
    private JButton[] buttons;
    private JButton reset,close;
    private ArrayList<Integer> x=new ArrayList<Integer>();
    private ArrayList<Integer> y=new ArrayList<Integer>();
    private int p1score=0,p2score=0;
    public TicTacToe(){
       setSize(500,500);
       setTitle("Tic-Tac-Toe");
       setDefaultCloseOperation(EXIT_ON_CLOSE);
       label=new JLabel("<html>Player 1's turn:</html>", JLabel.CENTER);
       tally=new JLabel("Scoreboard:",JLabel.CENTER);
       p1=new JLabel("<html>P1:<br></html>",JLabel.CENTER);
       p2=new JLabel("<html>P2:<br></html>",JLabel.CENTER);
       reset=new JButton("reset");
       close=new JButton(" ");
       Container pane=this.getContentPane();
       pane.setLayout(new GridLayout(5,4));
       buttons=new JButton[10];
       for(int i=1; i<=9;i++){
            buttons[i]=new JButton(String.valueOf(i));
            pane.add(buttons[i]);
            buttons[i].addActionListener(this);
        }
       reset.addActionListener(this);
       close.addActionListener(this);
       pane.add(label);
       pane.add(reset);
       pane.add(close);
       pane.add(tally);
       pane.add(p1);
       pane.add(p2);
       setVisible(true);
    } 
    public void actionPerformed (ActionEvent e){
      String action=e.getActionCommand();
      if(action.equals("reset")||action.equals("yes")){
          for(int i=1; i<=9;i++){
            buttons[i].setText(String.valueOf(i));
        }
        x=new ArrayList<Integer>();
        y=new ArrayList<Integer>();
        reset.setText("reset");
        label.setText("<html>Player 1's turn:</html>");
        close.setText(" ");
        }
      else if(action.equals("no"))
        System.exit(0);
      for(int i=1;i<=9;i++)
        if(action.equals(String.valueOf(i)))
            change(i);
      if(label.getText().equals("<html>Player 1 wins!<Br> Play again?</html>")==false && label.getText().equals("<html>Player 2 wins!<Br> Play again?</html>")==false)
            
            checker(); 
    }
    public void change(int place){
        if(label.getText().equals("<html>Player 1's turn:</html>")){
            buttons[place].setText("x");
            label.setText("<html>Player 2's turn:</html>");
            x.add(place);
        }
            
        else if (label.getText().equals("<html>Player 2's turn:</html>")){
            buttons[place].setText("o");
            label.setText("<html>Player 1's turn:</html>");
            y.add(place);
        }
    }
    public void checker(){
      int[][] combos={{1,2,3},{4,5,6},{7,8,9},{1,4,7},{2,5,8},{3,6,9},{1,5,9},{3,5,7}};
      int counter=0;
      if(checkcombo(x)){
        label.setText("<html>Player 1 wins!<Br> Play again?</html>");
        yn();
       }
      if(checkcombo(y)){
        label.setText("<html>Player 2 wins!<Br> Play again?</html>");
        yn();
       }
      if(label.getText().equals("<html>Player 1 wins!<Br> Play again?</html>")==false && label.getText().equals("<html>Player 2 wins!<Br> Play again?</html>")==false){
        for(int i=1;i<=9;i++){
            if(buttons[i].getText().equals("x")||buttons[i].getText().equals("o"))
                counter++;
            }
        if (counter==9){
            label.setText("<html>No winner!<Br> Play again?</html>");
            yn();
         }
       }
      
    }
    public boolean checkcombo(ArrayList a){
        int[][] combos={{1,2,3},{4,5,6},{7,8,9},{1,4,7},{2,5,8},{3,6,9},{1,5,9},{3,5,7}};
        int counter=0;
        for(int[] combo:combos){
           for(int digit: combo){
                if(a.indexOf(digit)>=0)
                    counter++;
                if(counter==3){
                    return true;
                }
            }  
           counter=0;
        }
        return false;
    }
    public void yn(){
        reset.setText("yes");
        close.setText("no");
        if(label.getText().equals("<html>Player 1 wins!<Br> Play again?</html>")){
            p1score++;
            p1.setText("<html>P1:<br>"+p1score+" point</html>");
        }
        else if (label.getText().equals("<html>Player 2 wins!<Br> Play again?</html>")){
            p2score++;
            p2.setText("<html>P2:<br>"+p2score+" point</html>");
        }
    }
   public static void main(String[] args){
        TicTacToe m=new TicTacToe();
    }
}