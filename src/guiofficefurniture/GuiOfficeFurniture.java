package guiofficefurniture;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.Writer;

public class GuiOfficeFurniture extends JFrame implements ActionListener, MouseListener, MouseMotionListener{
        
    private JLabel Chair1, Chair2, Desk1, Desk2, Desk3, Desk4, Table1, Table2;
    //private JLabel emptySmall1, emptySmall2, emptySmall3, emptySmall4, emptySmall5, emptySmall6;
    //private JLabel emptyLarge1, emptyLarge2, emptyLarge3;
    private ImageIcon c1, c2, d1, d2, d3, d4, t1, t2; 
    //private ImageIcon eS1, eS2, eS3, eS4, eS5, eS6, eL1, eL2, eL3;
    
    int smallGridpos = 0;
    int largeGridpos = 0;
    int pos;
    String chairIDNum;
    String tableIDNum; 
    String deskIDNum;
    String selectedWood;
    String selectedArmType;
    String selectedTableType;
    int amountOfDrawers;
    int itemPrice;
    int totalPrice;
    String fileName;
    String newFurniture;
    int q;
    String qty;
    String output ;
    
    //ARRAYS
    static FurnitureItem[] smallF = new FurnitureItem[6];
    static FurnitureItem[] largeV = new FurnitureItem[3];
    String FurnitureSummary[];
    ArrayList <String> FurnitureArray = new ArrayList<String>();
    
    
    //smallF = 
    
    //CONSTRUCTORS
    Chair newChair = new Chair(pos, chairIDNum, selectedWood, qty, selectedArmType, itemPrice);
    Table newTable = new Table(pos, tableIDNum, selectedWood, qty, selectedArmType, itemPrice);
    Desk newDesk = new Desk(pos, deskIDNum, selectedWood, qty, selectedArmType, itemPrice);
    
    JTextField chairID = new JTextField(8);
    JTextField tableID = new JTextField(8);
    JTextField deskID = new JTextField(8);
    JTextField fileID = new JTextField(8);
    JTextField typeOfWood = new JTextField(8);
    JTextField quantity = new JTextField(5);
    
  
    ImageIcon emptySmall = new ImageIcon(getClass().getResource("/img/empty_w100xh100.png"));
    JLabel[] emptySmallLabel = new JLabel[6];
    
    ImageIcon emptyLarge = new ImageIcon(getClass().getResource("/img/empty_w150xh100.png"));   
    JLabel[] emptyLargeLabel = new JLabel[3];
    
    private JButton addChairBTN = new JButton();
    private JButton addTableBTN = new JButton();
    private JButton addDeskBTN = new JButton();
    private JButton clearAllBTN = new JButton();
    private JButton totalPriceBTN = new JButton();
    private JButton saveBTN = new JButton();
    private JButton loadBTN = new JButton();
    private JButton summaryBTN = new JButton();
    
    JTextArea txtArea = new JTextArea(8,38);
    int x,y;
    
    JMenuBar menuBar = new JMenuBar();

    JMenu menu1 = new JMenu("File"); 

    JMenuItem item1 = new JMenuItem("Open");

    JMenuItem item2 = new JMenuItem("Cancel"); 
    
    final JPanel warning = new JPanel();
    
    public GuiOfficeFurniture(){
        super("Real Office Furniture");
              
        setJMenuBar(menuBar);
        
        //setLayout(new FlowLayout()); 
        setLayout(new BorderLayout());
        Container contentPane = getContentPane();
        
        JPanel pnlNorth = new JPanel();        
        pnlNorth.setBackground(Color.gray);
        pnlNorth.setOpaque(true);
        
        JPanel pnlSouth = new JPanel();        
        pnlSouth.setBackground(Color.gray);
        pnlSouth.setOpaque(true);
                
        JPanel btnPanel = new JPanel(new GridLayout(8,1));
        
        JPanel ctPanel = new JPanel(new GridLayout(3,2)); 
        
        JPanel deskPanel = new JPanel(new GridLayout(3,1)); 
             
        addChairBTN.setPreferredSize(new Dimension(120, 50));
        addChairBTN.setText("Add Chair");
        btnPanel.add(addChairBTN);
        
        addTableBTN.setPreferredSize(new Dimension(120, 50));
        addTableBTN.setText("Add Table");
        btnPanel.add(addTableBTN);
        
        addDeskBTN.setPreferredSize(new Dimension(120, 50));
        addDeskBTN.setText("Add Desk");
        btnPanel.add(addDeskBTN);
        
        clearAllBTN.setPreferredSize(new Dimension(120, 50));
        clearAllBTN.setText("Clear All");
        btnPanel.add(clearAllBTN);
        
        totalPriceBTN.setPreferredSize(new Dimension(120, 50));
        totalPriceBTN.setText("Total Price");
        btnPanel.add(totalPriceBTN);
        
        saveBTN.setPreferredSize(new Dimension(120, 50));
        saveBTN.setText("Save");
        btnPanel.add(saveBTN);
        
        loadBTN.setPreferredSize(new Dimension(120, 50));
        loadBTN.setText("Load");
        btnPanel.add(loadBTN);
        
        summaryBTN.setPreferredSize(new Dimension(120, 50));
        summaryBTN.setText("Total for day");
        btnPanel.add(summaryBTN);
        
        c1 = new ImageIcon(getClass().getResource("/img/Chair1.png"));
        Chair1 = new JLabel(c1);
        Chair1.setOpaque(true);
        
        c2 = new ImageIcon(getClass().getResource("/img/Chair2.png"));
        Chair2 = new JLabel(c2);
        Chair2.setOpaque(true);
        
        d1 = new ImageIcon(getClass().getResource("/img/Desk1.png"));
        Desk1 = new JLabel(d1);
        Desk1.setOpaque(true);
        
        d2 = new ImageIcon(getClass().getResource("/img/Desk2.png"));
        Desk2 = new JLabel(d2);
        Desk2.setOpaque(true);
        
        d3 = new ImageIcon(getClass().getResource("/img/Desk3.png"));
        Desk3 = new JLabel(d3);
        Desk3.setOpaque(true);
        
        d4 = new ImageIcon(getClass().getResource("/img/Desk4.png"));
        Desk4 = new JLabel(d4);
        Desk4.setOpaque(true);
        
        t1 = new ImageIcon(getClass().getResource("/img/Table1.png"));
        Table1 = new JLabel(t1);
        Table1.setOpaque(true);
        
        t2 = new ImageIcon(getClass().getResource("/img/Table2.png"));
        Table2 = new JLabel(t2);
        Table2.setOpaque(true);
       
        for (int i = 0; i < 6; i++){     
            emptySmallLabel[i] = new JLabel();
            emptySmallLabel[i].setIcon(emptySmall);          
        }
        
        for (int i = 0; i < 3; i++){     
            emptyLargeLabel[i] = new JLabel();
            emptyLargeLabel[i].setIcon(emptyLarge);          
        }
        /**
        eS1 = new ImageIcon(getClass().getResource("/img/empty_w100xh100.png"));
        emptySmall1 = new JLabel(eS1);
        emptySmall1.setOpaque(true);
        
        eS2 = new ImageIcon(getClass().getResource("/img/empty_w100xh100.png"));
        emptySmall2 = new JLabel(eS2);
        emptySmall2.setOpaque(true);
        
        eS3 = new ImageIcon(getClass().getResource("/img/empty_w100xh100.png"));
        emptySmall3 = new JLabel(eS3);
        emptySmall3.setOpaque(true);
        
        eS4 = new ImageIcon(getClass().getResource("/img/empty_w100xh100.png"));
        emptySmall4 = new JLabel(eS4);
        emptySmall4.setOpaque(true);
        
        eS5 = new ImageIcon(getClass().getResource("/img/empty_w100xh100.png"));
        emptySmall5 = new JLabel(eS5);
        emptySmall5.setOpaque(true);
        
        eS6 = new ImageIcon(getClass().getResource("/img/empty_w100xh100.png"));
        emptySmall6 = new JLabel(eS6);
        emptySmall6.setOpaque(true);
        
        eL1 = new ImageIcon(getClass().getResource("/img/empty_w150xh100.png"));
        emptyLarge1 = new JLabel(eL1);
        emptyLarge1.setOpaque(true);
        
        eL2 = new ImageIcon(getClass().getResource("/img/empty_w150xh100.png"));
        emptyLarge2 = new JLabel(eL2);
        emptyLarge2.setOpaque(true);
        
        eL3 = new ImageIcon(getClass().getResource("/img/empty_w150xh100.png"));
        emptyLarge3 = new JLabel(eL3);
        emptyLarge3.setOpaque(true);

        ctPanel.add(emptySmall1);
        ctPanel.add(emptySmall2);
        ctPanel.add(emptySmall3);
        ctPanel.add(emptySmall4);
        ctPanel.add(emptySmall5);
        ctPanel.add(emptySmall6);
        **/
        ctPanel.add(emptySmallLabel[0]);
        ctPanel.add(emptySmallLabel[1]);
        ctPanel.add(emptySmallLabel[2]);
        ctPanel.add(emptySmallLabel[3]);
        ctPanel.add(emptySmallLabel[4]);
        ctPanel.add(emptySmallLabel[5]);

              
        //deskPanel.add(emptyLarge1);
        //deskPanel.add(emptyLarge2);
        //deskPanel.add(emptyLarge3);
        deskPanel.add(emptyLargeLabel[0]);
        deskPanel.add(emptyLargeLabel[1]);
        deskPanel.add(emptyLargeLabel[2]);

               
        pnlNorth.add(new JLabel("Real Office Funiture"));
        pnlSouth.add(txtArea);
        
        contentPane.add("North", pnlNorth);
        contentPane.add("West", btnPanel); 
        contentPane.add("Center", ctPanel);
        contentPane.add("East", deskPanel);
        contentPane.add("South", pnlSouth);
             
        addChairBTN.addActionListener(this);

        addTableBTN.addActionListener(this);

        addDeskBTN.addActionListener(this); 
        
        clearAllBTN.addActionListener(this);
        
        totalPriceBTN.addActionListener(this);
        
        saveBTN.addActionListener(this);
        
        summaryBTN.addActionListener(this);
        

        //emptySmallLabel[0].addMouseListener(this);
        //emptySmallLabel[1].addMouseListener(this);
        //emptySmallLabel[2].addMouseListener(this);
        //emptySmallLabel[3].addMouseListener(this);
        //emptySmallLabel[4].addMouseListener(this);
        //emptySmallLabel[5].addMouseListener(this);
        
        for (int i = 0; i < 6; i++){
        
            emptySmallLabel[i].addMouseListener(this);
        }
        
        //emptyLargeLabel[0].addMouseListener(this);
        //emptyLargeLabel[1].addMouseListener(this);
        //emptyLargeLabel[2].addMouseListener(this);
        
        for (int i = 0; i < 3; i++){
        
            emptyLargeLabel[i].addMouseListener(this);
            
        }
        
        Chair1.addMouseListener(this);
        Chair2.addMouseListener(this);
        Desk1.addMouseListener(this);
        Desk2.addMouseListener(this);
        Desk3.addMouseListener(this);
        Desk4.addMouseListener(this);
        Table1.addMouseListener(this);
        Table2.addMouseListener(this);
        
   
        //emptySmall1.addMouseListener(this);
        //emptySmall2.addMouseListener(this);
        //emptySmall3.addMouseListener(this);
        //emptySmall4.addMouseListener(this);
        //emptySmall5.addMouseListener(this);
        //emptySmall6.addMouseListener(this);

        txtArea.addMouseListener(this);

        txtArea.addMouseMotionListener(this);
        
        menu1.add(item1);

        menu1.add(item2);

        menuBar.add(menu1);      
        
    } //end of constructor
    
    void smallFurnitureSummary(int r){    
        //System.out.println("Small Furniture Summary Here: " + r);     
        //txtArea.append("\nYou have clicked on a Small Furniture Item");
        String to_print = "ID: " + chairID.getText() + "\nType Of Wood: " + selectedWood + "\nQuantity: " + quantity.getText() + "\nArmRests: " + selectedArmType + "\nPrice :  £" +  itemPrice/100;
        JOptionPane.showMessageDialog(null, to_print, "Item Details", JOptionPane.INFORMATION_MESSAGE);
        int i = 0;
        FurnitureSummary[i] = to_print;
        i ++;
        
    }
    
    public Chair addChair(){
        
       itemPrice = 0;
        
        ButtonGroup tow = new ButtonGroup();        
        JRadioButton oak = new JRadioButton("Oak", true);
        JRadioButton walnut = new JRadioButton("Walnut");
        tow.add(oak);
        tow.add(walnut);
        
        ButtonGroup chrType = new ButtonGroup(); 
        JRadioButton chair1 = new JRadioButton("Without Armrests", true);
        JRadioButton chair2 = new JRadioButton("With Armrests");
        chrType.add(chair1);
        chrType.add(chair2);
       
        JPanel myChair = new JPanel(new GridLayout(6,2));
        myChair.add(new JLabel("Chair ID Number: "));
        myChair.add(chairID);
        myChair.add(new JLabel("Quantity: "));
        myChair.add(quantity);
        myChair.add(oak);
        myChair.add(walnut);
        myChair.add(chair1);
        myChair.add(chair2);
        
        if (smallGridpos == 6){           
            JOptionPane.showMessageDialog(warning, "Small Furniture Display Limit Reached", "Error", JOptionPane.ERROR_MESSAGE);
        } //end of small grid limit if statement
        
        else { int chairBtnResult = JOptionPane.showConfirmDialog(null, myChair, "Chair Selected", JOptionPane.OK_CANCEL_OPTION);
        
        if (chairBtnResult == JOptionPane.OK_OPTION) {
            
           System.out.println("chairID: " + chairID.getText());
        
           if (oak.isSelected()){ 
                selectedWood = "Oak";
               itemPrice = (4*1625);
                System.out.println("Type of Wood Selected: " + oak.getText());               
           } 
           else if (walnut.isSelected()){
                selectedWood = "Walnut";
                itemPrice = (3*1625);
                System.out.println("Type of Wood Selected: " + walnut.getText());
           }         
           
           System.out.println("Quantity: " + quantity.getText());

           if (chair1.isSelected()){               
                System.out.println("Chair1: " + chair1.getText());
                //emptySmall3.setIcon(c1);
                selectedArmType = "Without Arm Rest";
                emptySmallLabel[smallGridpos].setIcon(c1);
                smallGridpos++;
           } 
           else if (chair2.isSelected()){
                System.out.println("Chair2: " + chair2.getText());
                //emptySmall6.setIcon(c2);
                
                if(oak.isSelected()){
                itemPrice = itemPrice + (250*4);
                }
                else if(walnut.isSelected()){
                itemPrice = itemPrice + (250*3);
                }
                selectedArmType = "With Arm Rest";
                emptySmallLabel[smallGridpos].setIcon(c2);
                smallGridpos++;
           } 
        }    
        if (chairBtnResult == JOptionPane.OK_OPTION){
            System.out.println("OK Pressed");
            
        } else if (chairBtnResult == JOptionPane.CANCEL_OPTION) {          
            System.out.println("Cancel Pressed");      
        }
        
        }//end else for grid position check
        
        String qTotal = quantity.getText();
        int qtotal = Integer.parseInt(qTotal);
        itemPrice = (itemPrice * qtotal);
        
     //Chair newChair = new Chair(chairID.getText());
        Chair newChair = new Chair(smallGridpos, chairID.getText(),quantity.getText(), selectedWood, selectedArmType, itemPrice);
        
        String to_print = "ID: " + chairID.getText() + "\nType Of Wood: " + selectedWood + "\nQuantity: " + quantity.getText() + "\nArmRests: " + selectedArmType + "\nPrice :  £" +  itemPrice/100;
        //JOptionPane.showMessageDialog(null, to_print, "Item Details", JOptionPane.INFORMATION_MESSAGE);
        
        FurnitureArray.add(to_print);
        
        FurnitureSummary = new String[] {to_print};
        
        totalPrice = totalPrice + itemPrice;
     
       
     return newChair;
        
     
     
    }
    
    public Table addTable(){
        
        JTextField width = new JTextField(3);
        JTextField length = new JTextField(3);
        itemPrice = 0;
        
        
        ButtonGroup tow = new ButtonGroup();        
        JRadioButton oak = new JRadioButton("Oak", true);
        JRadioButton walnut = new JRadioButton("Walnut");
        tow.add(oak);
        tow.add(walnut);
        
        ButtonGroup tblType = new ButtonGroup(); 
        JRadioButton table1 = new JRadioButton("Wooden", true);
        JRadioButton table2 = new JRadioButton("Chrome");
        tblType.add(table1);
        tblType.add(table2);
       
        JPanel myTable = new JPanel(new GridLayout(6,2));
        myTable.add(new JLabel("Table ID Number: "));
        myTable.add(tableID);
        myTable.add(new JLabel("Quantity: "));
        myTable.add(quantity);
        myTable.add(new JLabel("Width: "));
        myTable.add(width);
        myTable.add(new JLabel("Length: "));
        myTable.add(length);
        myTable.add(oak);
        myTable.add(walnut);
        myTable.add(table1);
        myTable.add(table2);
        
        if (smallGridpos == 6){           
            JOptionPane.showMessageDialog(warning, "Small Furniture Display Limit Reached", "Error", JOptionPane.ERROR_MESSAGE);
        } //end of small grid limit if statement
        
        else { int tableBtnResult = JOptionPane.showConfirmDialog(null, myTable, "Table Selected", JOptionPane.OK_CANCEL_OPTION);
        
        
        String sWidth = width.getText();
        int iWidth = Integer.parseInt(sWidth);
        System.out.println(iWidth);
        
        String sLength = length.getText();
        int iLength = Integer.parseInt(sLength);
        System.out.println(iLength);
        
        int diameter = iLength * iWidth;
        
        System.out.println(diameter);
        
        if (tableBtnResult == JOptionPane.OK_OPTION) {
            
           System.out.println("tableID: " + tableID.getText());
        
           if (oak.isSelected()){ 
                selectedWood = "Oak";
                itemPrice = (4 * diameter);
                System.out.println("Type of Wood Selected: " + oak.getText());               
           } 
           else if (walnut.isSelected()){
                selectedWood = "Walnut";
                itemPrice = (3 * diameter);
                System.out.println("Type of Wood Selected: " + walnut.getText());
           }         
           
           System.out.println("Quantity: " + quantity.getText());
           System.out.println(itemPrice);
           
           if (table1.isSelected()){               
                System.out.println("Table1: " + table1.getText());
                //emptySmall3.setIcon(c1);
                selectedTableType = "Wooden";
                itemPrice = itemPrice + 4500;
                emptySmallLabel[smallGridpos].setIcon(t1);
                smallGridpos++;
           } 
           else if (table2.isSelected()){
                System.out.println("Table2: " + table2.getText());
                //emptySmall6.setIcon(c2);
                selectedTableType = "Chrome";
                itemPrice = itemPrice + 3500;
                emptySmallLabel[smallGridpos].setIcon(t2);
                smallGridpos++;
           } 
        }    
        if (tableBtnResult == JOptionPane.OK_OPTION){
            System.out.println("OK Pressed");
            
        } else if (tableBtnResult == JOptionPane.CANCEL_OPTION) {          
            System.out.println("Cancel Pressed");      
        }
        
        }//end else for grid position check
        
     //Table newTable = new Table(tableID.getText());
        
        String qTotal = quantity.getText();
        int qtotal = Integer.parseInt(qTotal);
        itemPrice = (itemPrice * qtotal);
        
        Table newTable = new Table(smallGridpos, tableID.getText(),quantity.getText(), selectedWood, selectedTableType, itemPrice);
        
         String to_print = "ID: " + deskID.getText() + "\nType Of Wood: " + selectedWood + "\nQuantity: " + quantity.getText() + "\nBase Type: " + selectedTableType + "\nPrice :  £" +  itemPrice/100;
        //JOptionPane.showMessageDialog(null, to_print, "Item Details", JOptionPane.INFORMATION_MESSAGE);
        
        FurnitureArray.add(to_print);
        
        FurnitureSummary = new String[] {to_print};
        
        totalPrice = totalPrice + itemPrice;
     
     return newTable;
        
    }
    
    public Desk addDesk(){
        
        JTextField width = new JTextField(3);
        JTextField depth = new JTextField(3);
        itemPrice = 0;
        int woodType = 0;
        
        
        ButtonGroup tow = new ButtonGroup();        
        JRadioButton oak = new JRadioButton("Oak", true);
        JRadioButton walnut = new JRadioButton("Walnut");
        tow.add(oak);
        tow.add(walnut);
        
        ButtonGroup dskType = new ButtonGroup(); 
        JRadioButton desk1 = new JRadioButton("1 Drawer", true);
        JRadioButton desk2 = new JRadioButton("2 Drawers");
        JRadioButton desk3 = new JRadioButton("3 Drawers");
        JRadioButton desk4 = new JRadioButton("4 Drawers");
        dskType.add(desk1);
        dskType.add(desk2);
        dskType.add(desk3);
        dskType.add(desk4);
       
        JPanel myDesk = new JPanel(new GridLayout(8,2));
        myDesk.add(new JLabel("Desk ID Number: "));
        myDesk.add(deskID);
        myDesk.add(new JLabel("Quantity: "));
        myDesk.add(quantity);
        myDesk.add(new JLabel("Width: "));
        myDesk.add(width);
        myDesk.add(new JLabel("Depth: "));
        myDesk.add(depth);
        myDesk.add(oak);
        myDesk.add(walnut);
        myDesk.add(desk1);
        myDesk.add(desk2);
        myDesk.add(desk3);
        myDesk.add(desk4);
        
        if (smallGridpos == 6){           
            JOptionPane.showMessageDialog(warning, "Small Furniture Display Limit Reached", "Error", JOptionPane.ERROR_MESSAGE);
        } //end of small grid limit if statement
        
        else { int deskBtnResult = JOptionPane.showConfirmDialog(null, myDesk, "Desk Selected", JOptionPane.OK_CANCEL_OPTION);
        
        String sWidth = width.getText();
        int iWidth = Integer.parseInt(sWidth);
        System.out.println(iWidth);
        
        String sDepth = depth.getText();
        int iDepth = Integer.parseInt(sDepth);
        System.out.println(iDepth);
        
        if (deskBtnResult == JOptionPane.OK_OPTION) {
            
           System.out.println("deskID: " + deskID.getText());
        
           if (oak.isSelected()){ 
                selectedWood = "Oak";
                woodType = 4;
                System.out.println("Type of Wood Selected: " + oak.getText());               
           } 
           else if (walnut.isSelected()){
                selectedWood = "Walnut";
                woodType = 3;
                System.out.println("Type of Wood Selected: " + walnut.getText());
           }         
           
           System.out.println("Quantity: " + quantity.getText());

           if (desk1.isSelected()){               
                System.out.println("Desk1: " + desk1.getText());
                //emptySmall3.setIcon(c1);
                amountOfDrawers = 1;
                emptyLargeLabel[largeGridpos].setIcon(d1);
                largeGridpos++;
           } 
           else if (desk2.isSelected()){
                System.out.println("Desk2: " + desk2.getText());
                //emptySmall6.setIcon(c2);
                amountOfDrawers = 2;
                emptyLargeLabel[largeGridpos].setIcon(d2);
                largeGridpos++;
           }
           else if (desk3.isSelected()){
                System.out.println("Desk3: " + desk3.getText());
                //emptySmall6.setIcon(c2);
                amountOfDrawers = 3;
                emptyLargeLabel[largeGridpos].setIcon(d3);
                largeGridpos++;
           } 
           else if (desk4.isSelected()){
                System.out.println("Desk4: " + desk4.getText());
                //emptySmall6.setIcon(c2);
                amountOfDrawers = 4;
                emptyLargeLabel[largeGridpos].setIcon(d4);
                largeGridpos++;
           } 
        }    
        if (deskBtnResult == JOptionPane.OK_OPTION){
            System.out.println("OK Pressed");
            
        } else if (deskBtnResult == JOptionPane.CANCEL_OPTION) {          
            System.out.println("Cancel Pressed");      
        }
        itemPrice = (((80 + iWidth + iDepth) * 12) + (iDepth * iWidth) * woodType) + (amountOfDrawers * 850);
        
        }//end else for grid position check
        
        
        
        String qTotal = quantity.getText();
        int qtotal = Integer.parseInt(qTotal);
        itemPrice = (itemPrice * qtotal);
        
     //Desk newDesk = new Desk(deskID.getText());
        Desk newDesk = new Desk(smallGridpos, deskID.getText(),quantity.getText(), selectedWood,  selectedArmType, itemPrice);
        
         String to_print = "ID: " + deskID.getText() + "\nType Of Wood: " + selectedWood + "\nQuantity: " + quantity.getText() + "\nAmount of Drawers: " + amountOfDrawers + "\nPrice :  £" +  itemPrice/100;
        //JOptionPane.showMessageDialog(null, to_print, "Item Details", JOptionPane.INFORMATION_MESSAGE);
        
        FurnitureArray.add(to_print);
        
        FurnitureSummary = new String[] {to_print};
        
        totalPrice = totalPrice + itemPrice;
        
     return newDesk;
        
    }
    
    
    
    
 

    public void mouseEntered(MouseEvent event){

        //txtAea.setText("Mouse Entered");

    }

    public void mousePressed(MouseEvent event){

        //txtArea.append("\nMouse Pressed at X: " + x + " Y" + y);
        
        //txtArea.append("\nSource Address: " + event.getSource());
        
        
        
        /**
        if (SwingUtilities.isLeftMouseButton(event)){
            txtArea.append("\nLeft Mouse Button Clicked");
        }
        if (SwingUtilities.isMiddleMouseButton(event)){
            txtArea.append("\nMiddle Mouse Button Clicked");
        }
        if (SwingUtilities.isRightMouseButton(event)){
            txtArea.append("\nRight Mouse Button Clicked");
        }
        **/

    }

    public void mouseReleased(MouseEvent event){

        //txtArea.append("\nMouse released");

    }

    public void mouseClicked(MouseEvent event){
        
    JLabel clickedLabel = (JLabel) event.getSource();
         
        int smallLabelPos = -1; //no match
        
        for (int i = 0; i < 6; i++) {
            if (clickedLabel == emptySmallLabel[i])    // match
            {
                smallLabelPos = i;
                txtArea.append("\nYou have clicked on a small empty space");
                break;
            }
        }
        System.out.println("Position: " + smallLabelPos);
       
        int largeLabelPos = -1; //no match
        
        for (int i = 0; i < 3; i++) {
            if (clickedLabel == emptyLargeLabel[i])    // match
            {
                largeLabelPos = i;
                txtArea.append("\nYou have clicked on a large empty space");
                break;
            }
        }
        System.out.println("Position: " + largeLabelPos);

        if(event.getButton() == MouseEvent.BUTTON1) {             
            if (clickedLabel.getIcon() == c1 || clickedLabel.getIcon() == c2){           
            System.out.println("Small Furniture Found");
            smallFurnitureSummary(smallLabelPos);       
            }
            /**
            if (clickedLabel.getIcon() == c1 || clickedLabel.getIcon() == c2 ){
            txtArea.append("\nYou have clicked on a Chair 1");
            String to_print = "ID: " + chairID.getText() + "\nType Of Wood: " + selectedWood + "\nQuantity: " + quantity.getText() + "\nArmRests: " + selectedArmType;
            JOptionPane.showMessageDialog(null, to_print, "Item Details", JOptionPane.INFORMATION_MESSAGE);
            txtArea.append("\nLeft Click!");
          }
          **/
        }// end button 1 
          if(event.getButton() == MouseEvent.BUTTON2) {
            if (clickedLabel.getIcon() == c1){
            JOptionPane.showMessageDialog(null, "","Change/Update Item", JOptionPane.INFORMATION_MESSAGE);
            txtArea.append("\nMiddle Click!");
          } 
            
        } // end button 2
          if(event.getButton() == MouseEvent.BUTTON3) {
            if (clickedLabel.getIcon() == c1){
            JOptionPane.showMessageDialog(null, "", "Remove the Item", JOptionPane.INFORMATION_MESSAGE);
            txtArea.append("\nRight Click!");
            }
          } //end button 3

    }

    public void mouseExited(MouseEvent event){}

    public void mouseMoved(MouseEvent event){

        //x = event.getX(); y = event.getY();

    }

    public void mouseDragged(MouseEvent event){}
    
    

    

    public void actionPerformed(ActionEvent event){  

        if(event.getSource() == addChairBTN){

            addChair();
            System.out.println("Chair Button Pressed");
        }

        if(event.getSource() == addTableBTN){

            addTable();
            System.out.println("Table Button Pressed");

        }

        if(event.getSource() == addDeskBTN){

            addDesk();
            System.out.println("Desk Button Pressed");

        }
        
        if(event.getSource() == clearAllBTN){

            System.out.println("Clear All Button Pressed");
            smallGridpos = 0; //reset position
            
            for (int i = 0; i < 6; i++){     
                //emptySmallLabel[i] = new JLabel();
                emptySmallLabel[i].setIcon(emptySmall);          
            }
            
            for (int i = 0; i < 6; i++){     
                //emptySmallLabel[i] = new JLabel();
                emptyLargeLabel[i].setIcon(emptyLarge);          
            }

        }
        
        if(event.getSource() == totalPriceBTN){

            JOptionPane.showMessageDialog(warning, totalPrice,"warning", JOptionPane.ERROR_MESSAGE);

        }
        
        if(event.getSource() == saveBTN){
            
            JPanel myFile = new JPanel(new GridLayout(8,2));
            myFile.add(new JLabel("Enter a FileName: "));
            myFile.add(fileID);
            
            JOptionPane.showConfirmDialog(null, myFile, "Save Selected", JOptionPane.OK_CANCEL_OPTION);
            
           fileName= fileID.getText();
            
            writeToFile();

        }  
        
        
        
        if(event.getSource() == summaryBTN){
            
            
            if (smallGridpos == 0 && pos == 0){           
            JOptionPane.showMessageDialog(null, "No Furniture Selected", "Summary Selected", JOptionPane.ERROR_MESSAGE);
        } //end of small grid limit if statement
        
        else {  
        

for(int i = 0; i<FurnitureArray.size(); i++){
    String everything = FurnitureArray.get(i).toString();
    

    output += everything + "\n";       
}
JOptionPane.showMessageDialog(null, output + "/n" + totalPrice);

   
            }
              
    }

    }
    
    public void writeToFile(){
 
        Writer writer = null;
        
        for(int i = 0; i<FurnitureArray.size(); i++){
    String everything = FurnitureArray.get(i).toString();
    

    output += everything + "\n";       
}
        
        
        try {
    writer = new BufferedWriter(new OutputStreamWriter(
          new FileOutputStream(fileName + ".dat"), "utf-8"));
    writer.write(output);
} catch (IOException ex) {
    // Report
} finally {
   try {writer.close();} catch (Exception ex) {/*ignore*/}
}
        
    }//end of write to file module

    public static void main(String[] args) {
        GuiOfficeFurniture RealOfficeFuniture = new GuiOfficeFurniture(); 
        //RealOfficeFuniture.setSize(500,200);
        RealOfficeFuniture.pack();    
        RealOfficeFuniture.setDefaultCloseOperation(EXIT_ON_CLOSE);          
        RealOfficeFuniture.setVisible(true);              
        
    }

}
