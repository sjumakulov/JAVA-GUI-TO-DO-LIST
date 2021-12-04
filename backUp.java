import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
// by Sherzod Jumakulov 09.April.2021. Thank you ;)

public class backUp{
//====================Start of Member Variables ================================
    JFrame mainFrame = new JFrame("To Do List");
    JPanel nestedPanelBig = new JPanel();
    JPanel nestedPanelSmall = new JPanel();
    JButton addListButton = new JButton("Add List");
    JButton backButton = new JButton("Back");
    JButton addTaskButton = new JButton("Add Task");
    JButton removeTaskButton = new JButton("Remove task");
    JButton saveButton = new JButton("Save");
    
    int cardIndex=1;
    String stringCardIndexAfter2xClick;
    
    //*********scroll components*********
    JPanel panelForScrollPane;
    JPanel cardPanel;
    JPanel newListPanel;
    Color color;
    CardLayout card;
    //*********scroll components*********
    
    //********* listScrollPane Component *******
    JPanel listPanel;
    //******** listScrollPane Component ********
    
    //******** newTaskLabel Components *********
    JLabel newTaskLabel;
    JCheckBox checkBox;
    Color color2;
    Map<String, Color> colorMap = new HashMap<String, Color>();
    int integerKeys=1;
    String stringKeys;
    //******** newTaskLabel Components *********
    
    //******** JPanel with boxLayout for new Tasks after going there by 2x click throuh new list******
    JPanel listPanel3;
    //******** JPanel with boxLayout for new Tasks after going there by 2x click throuh new list******
//====================End of Member Variables ==================================    
    
    
//====================Start of Component Methods================================
    public final JScrollPane listScrollPane(String title){
        newTaskLabel = new JLabel(); //============================> new task title come to this label [TITLE Panel].
        newTaskLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        newTaskLabel.setText(title);
        newTaskLabel.setForeground(Color.white);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout());
        mainPanel.setMaximumSize(new Dimension(380,50));
        mainPanel.setBackground(Color.darkGray);
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        mainPanel.add(newTaskLabel);
        mainPanel.setName("Hey I am the Main Panel");
        
        listPanel = new JPanel(); // ============================================> newTaskPanel is added here.
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBackground(color);
        listPanel.add(mainPanel);
        listPanel.setName("I am a jpanel");
        
        JScrollPane listScrollPane = new JScrollPane(listPanel);
        listScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        
        return listScrollPane;
    }
    
    //&&&&&&&&&& Start of Main Scroll Pane For nestedPanelBig &&&&&&&&&&&&
    public final JScrollPane scrollPane(){
        panelForScrollPane = new JPanel();
        panelForScrollPane.setLayout(new BoxLayout(panelForScrollPane, BoxLayout.Y_AXIS));
        
        JPanel titlePanel = newTaskPanel("Lists");
        
        JPanel p = (JPanel)titlePanel.getComponent(1);
        p.setLayout(new FlowLayout());
        p.getComponent(0).setForeground(Color.white);
        saveButton.addActionListener(AL);
        saveButton.setFocusable(false);
        p.add(saveButton);
        
        titlePanel.remove(0);
        titlePanel.setBackground(Color.darkGray);
        titlePanel.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setFont(new Font("Tahoma", Font.PLAIN, 25));
        
        panelForScrollPane.add(titlePanel); // ===================================> to this panel are added public final JPanel newListPanel(String listName)
        
        JScrollPane scrollPane = new JScrollPane(panelForScrollPane);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        
        return scrollPane;
    }
    //&&&&&&&&&& End of Main Scroll Pane For nestedPanelBig &&&&&&&&&&&&
    
    public final JPanel nestedPanelBig(){
        cardPanel = new JPanel();
        card = new CardLayout();
        cardPanel.setLayout(card);
        
        JScrollPane scrollP = scrollPane();
        scrollP.setName("I am the main card");
        cardPanel.add("mainCard", scrollP);
        
        nestedPanelBig.setBounds(0,0,385,500);
        nestedPanelBig.setLayout(new BorderLayout());
        
        nestedPanelBig.add(cardPanel);
        return nestedPanelBig;
    }
    
    public final JPanel nestedPanelSmall(){
        nestedPanelSmall.setBounds(0,500,385,60);
        
        addListButton.setFocusable(false);
        addListButton.addActionListener(AL);
        addListButton.setVisible(true);
        
        addTaskButton.setFocusable(false);
        addTaskButton.addActionListener(AL);
        addTaskButton.setVisible(false);
        
        backButton.setFocusable(false);
        backButton.addActionListener(AL);
        backButton.setVisible(false);
        
        removeTaskButton.setFocusable(false);
        removeTaskButton.addActionListener(ALcheckBox);
        removeTaskButton.setVisible(false);
        
        nestedPanelSmall.add(addListButton);
        nestedPanelSmall.add(addTaskButton);
        nestedPanelSmall.add(backButton);
        nestedPanelSmall.add(removeTaskButton);
        
        nestedPanelSmall.setBackground(Color.darkGray);
        nestedPanelSmall.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        return nestedPanelSmall;
    }
    
    public final JPanel newListPanel(String listName){  // Creates new List and is added to the panelForScrollPane in scrollPane.
        JLabel newListLabel = new JLabel();
        newListLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        newListLabel.setText(listName);
        newListLabel.setMaximumSize(new Dimension(220, 200));
        newListLabel.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        JButton removeButton = new JButton("Remove");
        removeButton.setFocusable(false);
        removeButton.addActionListener(ALforRemoveButton);
        
        JButton editButton = new JButton("Edit");
        editButton.setFocusable(false);
        editButton.addActionListener(ALforEditButton);
        
        //************* Start of Nested Panels for newListPanel ***********
        JPanel nestedPanel1 = new JPanel();
        nestedPanel1.setPreferredSize(new Dimension(200,40));
        nestedPanel1.setLayout(new BorderLayout());
        nestedPanel1.add(newListLabel);
        nestedPanel1.setOpaque(false);
        
        JPanel nestedPanel2 = new JPanel();
        nestedPanel2.setPreferredSize(new Dimension(150, 40));
        nestedPanel2.setLayout(new FlowLayout());
        nestedPanel2.add(editButton);
        nestedPanel2.add(removeButton);
        nestedPanel2.setOpaque(false);
        //************* End of Nested Panels for newListPanel ***********
        
        color = new Color(randomNumber(), randomNumber(), randomNumber());
        
        newListPanel = new JPanel();
        newListPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        newListPanel.setMaximumSize(new Dimension(380, 50));
        newListPanel.add(nestedPanel1);
        newListPanel.add(nestedPanel2);
        newListPanel.setBackground(color);
        newListPanel.addMouseListener(ML);
    
        return newListPanel;
    }
    
    public final JPanel newTaskPanel(String newListLabelText){ // creates new task panels and are added to listPanel in listScrollPane
        checkBox = new JCheckBox();
        checkBox.setOpaque(false);
        checkBox.addActionListener(ALcheckBox);
        
        color2 = new Color(randomNumber(), randomNumber(), randomNumber());
        
        newTaskLabel = new JLabel();
        newTaskLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        newTaskLabel.setText(newListLabelText);
        
        JPanel mainPanel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(mainPanel, BoxLayout.X_AXIS);
        mainPanel.setLayout(boxLayout);
        mainPanel.setMaximumSize(new Dimension(380,50));
        mainPanel.setBackground(color2);
        
        JPanel nestedPanelCheckBox = new JPanel();
        nestedPanelCheckBox.setLayout(new BorderLayout());
        nestedPanelCheckBox.add(checkBox);
        nestedPanelCheckBox.setOpaque(false);
         
        JPanel nestedPanelLabel = new JPanel();
        nestedPanelLabel.setLayout(new BorderLayout());
        nestedPanelLabel.add(newTaskLabel);
        nestedPanelLabel.setPreferredSize(new Dimension(330,50));
        nestedPanelLabel.setOpaque(false);
        
        mainPanel.add(nestedPanelCheckBox);
        mainPanel.add(nestedPanelLabel);
        
        return mainPanel;
    }
//====================End of Component Methods==================================
    
    
//====================Start of Constructor =====================================
    backUp() {
        
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                mainFrame.setLayout(null);
                mainFrame.setSize(400,600);
                mainFrame.setResizable(false);
                
                mainFrame.add(nestedPanelBig());
                mainFrame.add(nestedPanelSmall());
                
//======================= Start of Loading from Memory ==========================================
                try{
                    FileInputStream inFile = new FileInputStream("newListMemoryTitle.txt");
                    ObjectInputStream in = new ObjectInputStream(inFile);
                    newListMemoryTitle = (Map<String, String>)in.readObject();
                    in.close();
                    inFile.close();
                    
                    FileInputStream inFile2 = new FileInputStream("newListMemoryColor.txt");
                    ObjectInputStream in2 = new ObjectInputStream(inFile2);
                    newListMemoryColor = (Map<String,Color>)in2.readObject();
                    in2.close();
                    inFile2.close();
                    
                    FileInputStream inFile3 = new FileInputStream("newListMemoryIndexNames.txt");
                    ObjectInputStream in3 = new ObjectInputStream(inFile3);
                    newListMemoryIndexNames = (ArrayList<String>)in3.readObject();
                    in3.close();
                    inFile3.close();
                    
//====== added today 07 april 20 === Loading new Tasks =====
                    FileInputStream inFile4 = new FileInputStream("newTaskMemoryTitle.txt");
                    ObjectInputStream in4 = new ObjectInputStream(inFile4);
                    newTaskMemoryTitle = (Map<String, Map>)in4.readObject();
                    in4.close();
                    inFile4.close();
                    
                    FileInputStream inFile5 = new FileInputStream("newTaskMemoryColor.txt");
                    ObjectInputStream in5 = new ObjectInputStream(inFile5);
                    newTaskMemoryColor = (Map<String,Map>)in5.readObject();
                    in5.close();
                    inFile5.close();
                    
                    FileInputStream inFile6 = new FileInputStream("newTaskMemoryStringKeys.txt");
                    ObjectInputStream in6 = new ObjectInputStream(inFile6);
                    newTaskMemoryStringKeys = (Map<String, ArrayList>)in6.readObject();
                    in6.close();
                    inFile6.close();
                    
 //====== added today 07 april 20 === Loading new Tasks =====   
                    
                }
                catch(IOException e){
                  // fileNotFound Exception might occur.   
                  //  e.printStackTrace(System.out);
                }
                catch(ClassNotFoundException e){
                    System.err.println("ClassNotFoundException occured");
                }
                if(!newListMemoryTitle.isEmpty() && !newListMemoryColor.isEmpty() && !newListMemoryIndexNames.isEmpty()){
                    cardIndex=Integer.parseInt(newListMemoryIndexNames.get(newListMemoryIndexNames.size()-1))+1;
                    
                    for(int i=0; i<newListMemoryIndexNames.size(); i++){
                        
                        JPanel newListPanelSaved = newListPanel(newListMemoryTitle.get(newListMemoryIndexNames.get(i)));
                        
                        newListPanelSaved.setName(newListMemoryIndexNames.get(i));
                        newListPanelSaved.setBackground(newListMemoryColor.get(newListMemoryIndexNames.get(i)));
                        panelForScrollPane.add(newListPanelSaved);
                        
                        JScrollPane listScrollPaneSaved = listScrollPane(newListMemoryTitle.get(newListMemoryIndexNames.get(i)));
                        listScrollPaneSaved.setName(newListMemoryIndexNames.get(i));
                        listScrollPaneSaved.getViewport().getComponent(0).setBackground(newListMemoryColor.get(newListMemoryIndexNames.get(i)));
                        cardPanel.add(newListMemoryIndexNames.get(i), listScrollPaneSaved);
                    }
                    
                    
                                            
// ====================== Start of Loading newTaskPanel ===============
if(!newTaskMemoryTitle.isEmpty() && !newTaskMemoryColor.isEmpty() && !newTaskMemoryStringKeys.isEmpty()){
    
    // ========= start of Fining the largest integerKeys in newTaskMemoryStringKeys ==============
        ArrayList<Integer> arrayListToCompareStringKeys = new ArrayList<Integer>();
           for(int i=0; i<newListMemoryIndexNames.size(); i++){
               ArrayList<String> oneOfTheArrayLists = newTaskMemoryStringKeys.get(newListMemoryIndexNames.get(i)); // one of the arrayLists
               
               if(!oneOfTheArrayLists.isEmpty()){
                   int largestStringKeyInThisArrayList = Integer.parseInt(oneOfTheArrayLists.get(oneOfTheArrayLists.size()-1)); // largest stringKeys in this arrayList
                   arrayListToCompareStringKeys.add(largestStringKeyInThisArrayList);
               }
               
           } 
           System.out.println(" before finding the largest integer: " + arrayListToCompareStringKeys);
                int counter = 1;
                int num1;
                int num2=0;
        if(!arrayListToCompareStringKeys.isEmpty()){
            for(int i=0; i<arrayListToCompareStringKeys.size(); i++){

                 num1 = arrayListToCompareStringKeys.get(i);

                 if(counter<arrayListToCompareStringKeys.size()){
                     num2 = arrayListToCompareStringKeys.get(counter);

                     if(num1>num2){
                         arrayListToCompareStringKeys.remove(counter);
                         System.out.println("arrayListToCompareStringKeys: "+ arrayListToCompareStringKeys);
                     }
                     if(num1<num2){
                       // do nothing
                     }
                 }

                 counter++;
              }
            integerKeys = arrayListToCompareStringKeys.get(arrayListToCompareStringKeys.size()-1)+1; // largest integerKeys
            System.out.println("integerKeys : " + integerKeys);
        }
          
          
// ========= End of Fining the largest integerKeys in newTaskMemoryStringKeys ==============
    
        for(int i=0; i<newListMemoryIndexNames.size(); i++){
            String stringCardIndex4 = newListMemoryIndexNames.get(i);
            
            JScrollPane listScrollPane4; // listScrollPaneSaved
            JPanel listPanel4 = null;  // listPanel to which we add newTaskPanels
            System.out.println("CardPanel has :" +cardPanel.getComponentCount() +"components");
            
            for(int y=1; y<=newListMemoryIndexNames.size(); y++){
                if(cardPanel.getComponent(y).getName().equals(stringCardIndex4)){
                    listScrollPane4 = (JScrollPane)cardPanel.getComponent(y); // saved listScrollPane
                    listPanel4 = (JPanel)listScrollPane4.getViewport().getComponent(0); // saved listPanel with boxLayout to which we add newTaskPanels
                    break;
                }
            }
            
            Map<String, String> newTaskMemoryTitleNestedMap = newTaskMemoryTitle.get(stringCardIndex4); // Title Map for this particular listPanel with stringCardIndex.
            Map<String, Color> newTaskMemoryColorNestedMap = newTaskMemoryColor.get(stringCardIndex4); // Color Map for this particular listPanel with stringCardIndex.
            ArrayList<String> newTaskMemoryStringKeysNestedArrayList2 = newTaskMemoryStringKeys.get(stringCardIndex4);

            for(int x=0; x<newTaskMemoryStringKeysNestedArrayList2.size(); x++){
                String stringKeys2 = newTaskMemoryStringKeysNestedArrayList2.get(x);
                
                JPanel newTaskPanel4 = newTaskPanel(newTaskMemoryTitleNestedMap.get(stringKeys2));
                newTaskPanel4.setBackground(newTaskMemoryColorNestedMap.get(stringKeys2));
                newTaskPanel4.setName(stringKeys2);
                colorMap.put(stringKeys2, newTaskMemoryColorNestedMap.get(stringKeys2));
                
                listPanel4.add(newTaskPanel4);
            }



        }
    
    
}
    
// ====================== End of Loading newTaskPanel ===============
                    
                    
                    System.out.println(newListMemoryTitle.toString());
                    System.out.println("cardIndex: " + cardIndex);
                }
                
                //============ added today 07 april 20 ===========
              
                
                //============ added today 07 april 20 ===========  

//======================= end of Loding From Memory ============================================

                mainFrame.setVisible(true);
            }
        });
    }
//====================End of Constructor========================================    
    
    
       //*************** Start of Memory **********
    Map<String, String> newListMemoryTitle = new HashMap<String,String>(); // <stringCardIndex, new list titles>
    Map<String, Color> newListMemoryColor = new HashMap<String, Color>(); // <stringCardIndex, new list colors>
    ArrayList<String> newListMemoryIndexNames = new ArrayList<String>(); // <stringCardIndex>
    
    
    // newListMemoryIndexNames can be used to recall nested Maps:
    Map<String, Map> newTaskMemoryTitle = new HashMap<String, Map>(); // Keeps the titles for newTaskPanels 
    // Map <StringCardIndex, Map<stringKeys, newTaskTitle>>
    Map<String, Map> newTaskMemoryColor = new HashMap<String, Map>();// keeps colors for newTaskPanels
    // Map <StringCardIndex, Map<stringKeys, newTaskColor>>
    Map<String, ArrayList> newTaskMemoryStringKeys = new HashMap<String, ArrayList>(); // keeps stringKeys for every newList
    // Map <stringCardIndex, ArrayList<stringKeys>>
    
    
    //*************** End of Memory ************
    
//==================== Start of Action Listener for addListButton, backButton, addTaskButton ==================================    
    ActionListener AL = new ActionListener(){
            @Override 
            public void actionPerformed(ActionEvent e){
                //++++++++++++++++ addListButton ++++++++++++++++++++++++++++++++
                if(e.getSource()==addListButton){
                        String listName = JOptionPane.showInputDialog(mainFrame, "Name your list", "+ new list", JOptionPane.PLAIN_MESSAGE);
                       
                        if(listName!=null && !listName.isBlank()){
                               JPanel newListPanel1 = newListPanel(listName);
                               String stringCardIndex = Integer.toString(cardIndex);
                               //System.out.println("List added with an index: " + stringCardIndex);
                               newListPanel1.setName(stringCardIndex);
                               panelForScrollPane.add(newListPanel1);
                                
                   /*listScrollPanes have stringCardIndex*/ //********************************************
                               JScrollPane listScrollPane1 = listScrollPane(listName);
                               listScrollPane1.setName(stringCardIndex);
                               cardPanel.add(stringCardIndex, listScrollPane1);
                               
                    //========= Start of saving to memory ==========   
                            
            //****************Added on 07 april ***************
    Map<String, String> newTaskMemoryTitleNested = new HashMap<String,String>(); // Map<stringKeys, newTaskTitle>
    newTaskMemoryTitle.put(stringCardIndex, newTaskMemoryTitleNested);
    
    Map<String, Color> newTaskMemoryColorNested = new HashMap<String,Color>(); // Map<stringKeys, newTaskColor>
    newTaskMemoryColor.put(stringCardIndex, newTaskMemoryColorNested);
    
    ArrayList<String> newTaskMemoryStringKeysNestedArrayList = new ArrayList<String>();
    newTaskMemoryStringKeys.put(stringCardIndex, newTaskMemoryStringKeysNestedArrayList);
            //***************Added on 07 april ****************
                               
                               newListMemoryTitle.put(stringCardIndex, listName);
                               newListMemoryColor.put(stringCardIndex, color);
                               newListMemoryIndexNames.add(stringCardIndex);
                    //========= End of saving to memory ============   
                               
                               cardIndex++;
                        }
                }
                //++++++++++++++++++ End of addListButton+++++++++++++++++++++++++++++++++++
                
                
                //++++++++++++++++++ backButton ++++++++++++++++++++++++++++++++++++++++++++
                if(e.getSource()==backButton){
                        card.show(cardPanel, "mainCard");
                        backButton.setVisible(false);
                        addListButton.setVisible(true);
                        addTaskButton.setVisible(false);
                        removeTaskButton.setVisible(false);
                } 
                //++++++++++++++++++ End of backButton +++++++++++++++++++++++++++++++++++
                
                //++++++++++++++++++ addTaskButton +++++++++++++++++++++++++++++++++++++++
                if(e.getSource() == addTaskButton ){
                    String taskName = JOptionPane.showInputDialog(mainFrame, "Name your task: ", "+ Task", JOptionPane.PLAIN_MESSAGE);
                        
                    if(taskName!=null &&!taskName.isBlank()){
                            for(int i=0; i<cardPanel.getComponentCount(); i++){
                                    Component scrollPaneInCardPanel = cardPanel.getComponent(i);

                                    if(scrollPaneInCardPanel.getName().equals(stringCardIndexAfter2xClick)){
                                        stringKeys = String.valueOf(integerKeys);
                                        
 /*pane = listScrollPane has cardStringIndex*/                                        
                                        JScrollPane pane = (JScrollPane)scrollPaneInCardPanel;
/*panel = listPanel in listScrollPane*/ JPanel panel = (JPanel)pane.getViewport().getComponent(0);

         /**/                           JPanel newTaskPanel1 = newTaskPanel(taskName);
/*newTaskPanels have stringKeys*/       newTaskPanel1.setName(stringKeys);
                                        panel.add(newTaskPanel1);
                                        
                                        colorMap.put(stringKeys, color2);
     /*integerKeys = stringKyes*/       integerKeys++;

                                      //  System.out.println("Name of Scroll Pane in Card Panel: " +scrollPaneInCardPanel.getName());
                                      //  System.out.println("Name of List: "+ newListName);
                                      //  System.out.println("Name of Panel a new Task was added to: " + panel.getName());
                                      //  System.out.println("Size of taskPanel: " + taskPanel.getSize());
                                      //  System.out.println("ComponentCount in JPanel: "+ panel.getComponentCount());
                                      
                    //************* Start of Saving newTaskPanel informarion Added on 07 april 20***************
                                      newTaskMemoryTitle.get(stringCardIndexAfter2xClick).put(stringKeys, taskName);
                                      newTaskMemoryColor.get(stringCardIndexAfter2xClick).put(stringKeys, color2);
                                      newTaskMemoryStringKeys.get(stringCardIndexAfter2xClick).add(stringKeys);
                    //************* End of Saving newTaskPanel informarion  ***************

                                        mainFrame.revalidate();
                                        mainFrame.repaint();
                                        break;
                                    }
                                }
                            removeTaskButton.setVisible(true);
                    }
                }
                //++++++++++++++++++ End of addTaskButton ++++++++++++++++++++++++++++++++
                
                //++++++++++++++++++ Start of saveButton++++++++++++++++++++++++++++++++++
                if(e.getSource() == saveButton){
                    try{
                            FileOutputStream outFile = new FileOutputStream("newListMemoryTitle.txt");
                            ObjectOutputStream out = new ObjectOutputStream(outFile);
                            out.writeObject(newListMemoryTitle);
                            out.close();
                            outFile.close();
                            
                            FileOutputStream outFile2 = new FileOutputStream("newListMemoryColor.txt");
                            ObjectOutputStream out2 = new ObjectOutputStream(outFile2);
                            out2.writeObject(newListMemoryColor);
                            out2.close();
                            outFile2.close();
                            
                            FileOutputStream outFile3 = new FileOutputStream("newListMemoryIndexNames.txt");
                            ObjectOutputStream out3 = new ObjectOutputStream(outFile3);
                            out3.writeObject(newListMemoryIndexNames);
                            out3.close();
                            outFile3.close();
                            
                            
                //========= satrt of saving newTask added on 07 april ================
                            //*******************Experiment******************* 07 april 20
                            FileOutputStream outFile4 = new FileOutputStream("newTaskMemoryTitle.txt");
                            ObjectOutputStream out4 = new ObjectOutputStream(outFile4);
                            out4.writeObject(newTaskMemoryTitle);
                            out4.close();
                            outFile4.close();
                            //*******************Experiment*******************
                            
                            FileOutputStream outFile5 = new FileOutputStream("newTaskMemoryColor.txt");
                            ObjectOutputStream out5 = new ObjectOutputStream(outFile5);
                            out5.writeObject(newTaskMemoryColor);
                            out5.close();
                            outFile5.close();
                            
                            FileOutputStream outFile6 = new FileOutputStream("newTaskMemoryStringKeys.txt");
                            ObjectOutputStream out6 = new ObjectOutputStream(outFile6);
                            out6.writeObject(newTaskMemoryStringKeys);
                            out6.close();
                            outFile6.close();
                            
                //========= End of saving newTask ==================
                       }
                    catch(IOException exception){
                            exception.printStackTrace(System.out);
                       }
                }
                //++++++++++++++++++ End of saveButton++++++++++++++++++++++++++++++++++
                
                mainFrame.revalidate();
                mainFrame.repaint();
            }
    };
//==================== End of Action Listener for addListButton, backButton, addTaskButton ==================================== 
 
//====================Start of Action Listener for removeButton ==================================    
    ActionListener ALforRemoveButton = new ActionListener(){
            @Override 
            public void actionPerformed(ActionEvent e){
               
                Component newListPanel2 = panelForScrollPane.getComponentAt(panelForScrollPane.getMousePosition());
                panelForScrollPane.remove(newListPanel2);
                String stringCardIndex2 = newListPanel2.getName(); // newListPanel's name = stringCardIndex
                
                for(int i=0; i<(cardPanel.getComponentCount()); i++){
                    Component listScrollPane1 = cardPanel.getComponent(i); // we are getting listScrollPane = compInCardPanel
                    
                        if(listScrollPane1.getName().equals(stringCardIndex2)){
                                cardPanel.remove(listScrollPane1);
                               // System.out.println("name of List removed: "+ nameNewListToBeRemoved);
                               // System.out.println("name of Card removed: "+ compInCardPanel.getName());
                               // System.out.println("Number of Components after removing: " +cardPanel.getComponentCount());
                                break;
                        }
                }
                
                //========= start of Remove from memory ==========
                System.out.println("nameNewListToBeRemoved: "+ newListPanel2.getName());
                System.out.println("title removed from newListMemoryTitle: "+ newListMemoryTitle.get(stringCardIndex2));
                System.out.println("Count of PanelForScrollPane: "+ panelForScrollPane.getComponentCount());
                
                newListMemoryTitle.remove(stringCardIndex2);
                newListMemoryColor.remove(stringCardIndex2);
                newListMemoryIndexNames.remove(stringCardIndex2);
                
                newTaskMemoryTitle.remove(stringCardIndex2);
                newTaskMemoryTitle.remove(stringCardIndex2);
                newTaskMemoryStringKeys.remove(stringCardIndex2);
                
                if(panelForScrollPane.getComponentCount()<2){
                    newListMemoryTitle.clear();
                    newListMemoryColor.clear();
                    newListMemoryIndexNames.clear();
                    
                    newTaskMemoryTitle.clear();
                    newTaskMemoryColor.clear();
                    newTaskMemoryStringKeys.clear();
                }
                
                //========= End of Remove from memory ============
                
                panelForScrollPane.revalidate();
                panelForScrollPane.repaint();
            }
    };
//==================== End of Action Listener for removeButton ====================================     


//==================== Start of Action Listener for editButton ==================================    
    ActionListener ALforEditButton = new ActionListener(){
            @Override 
            public void actionPerformed(ActionEvent e){
              Component newListPanel1 = panelForScrollPane.getComponentAt(panelForScrollPane.getMousePosition());
              String editedTitleFromUser = JOptionPane.showInputDialog(mainFrame, "Edit list name", "Edit", JOptionPane.PLAIN_MESSAGE);
              if(editedTitleFromUser!=null && !editedTitleFromUser.isBlank()){
                  String stringCardIndex1 = newListPanel1.getName(); // stringCardIndex = stringCardIndex1
                    if(newListPanel1 instanceof JPanel){
                          JPanel PanelForScrollPane1 = (JPanel)newListPanel1;
                          JPanel nestedPanelForLabel = (JPanel)(PanelForScrollPane1.getComponent(0));
                          JLabel newListLabel1 = (JLabel)nestedPanelForLabel.getComponent(0);
                          newListLabel1.setText(editedTitleFromUser);
                          
                //=========== Start of Memory editing =========
                          newListMemoryTitle.remove(PanelForScrollPane1.getName());
                          newListMemoryTitle.put(PanelForScrollPane1.getName(), editedTitleFromUser);
                //=========== End of Memory editing ===========
                          
                          System.out.println("Edited list Name: " + PanelForScrollPane1.getName());
                    }
                    
                    for(int i=0; i<cardPanel.getComponentCount(); i++){
                        JScrollPane listScrollPane2 = (JScrollPane)cardPanel.getComponent(i);
                        
                        if( listScrollPane2.getName().equals(stringCardIndex1)){ //
                            
                            JPanel listPanel1 = (JPanel)listScrollPane2.getViewport().getComponent(0); // listPanel1 with boxLayout
                            JPanel titlePanelForListPanel = (JPanel)listPanel1.getComponent(0);
                            JLabel titleLabel = (JLabel)titlePanelForListPanel.getComponent(0);
                            titleLabel.setText(editedTitleFromUser);
                            break;
                        }
                    }
              }
              panelForScrollPane.revalidate();
              panelForScrollPane.repaint();
            }
    };
//==================== End of Action Listener for editButton ==================================== 
    
    
    //************** Start of Variables for ActionListener for checkBox ***************
        JCheckBox checkB;
        Component newTaskPanel2;
        Map<String, JPanel> newTaskpPanelsToBeRemovedMap = new HashMap<String, JPanel>();
        boolean checkBoxSelected=false;
        int checkBoxCounter=0;
        ArrayList<String> stringKeysArrayList = new ArrayList<String>(); // i need it
        
    //************** End of Variables for ActionListener for checkBox ***************         
                
//=================== Start of Action Listener for checkBox=====================
    ActionListener ALcheckBox = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){

            if(e.getSource()!=removeTaskButton){
                    newTaskPanel2 = listPanel3.getComponentAt(listPanel3.getMousePosition());
                    JPanel newTaskPanel = (JPanel)newTaskPanel2;
                    JPanel smallP = (JPanel)newTaskPanel.getComponent(0);
                    checkB = (JCheckBox)smallP.getComponent(0);
                    String stringKeys1 = newTaskPanel2.getName(); // name of new task panel
                    
                    if(checkB.isSelected()){
                        newTaskPanel2.setBackground(Color.gray);
                        newTaskpPanelsToBeRemovedMap.put(stringKeys1,newTaskPanel);
                        stringKeysArrayList.add(stringKeys1);
                        checkBoxCounter++;
                        if(checkBoxCounter>0) checkBoxSelected = true;
                    }
                    
                    if(!checkB.isSelected()){
                        newTaskPanel2.setBackground(colorMap.get(stringKeys1));
                        newTaskpPanelsToBeRemovedMap.remove(stringKeys1);
                        stringKeysArrayList.remove(stringKeys1);
                        checkBoxCounter--;
                        if(checkBoxCounter<=0) checkBoxSelected = false;
                    }
            }

            if(e.getSource()==removeTaskButton){
                 if(checkBoxSelected == true){
                     ArrayList<String> tempNames = new ArrayList<String>();
                     for(int i=0; i<stringKeysArrayList.size(); i++){
                        for(int x=0; x<listPanel3.getComponentCount(); x++){
                                if(listPanel3.getComponent(x).equals(newTaskpPanelsToBeRemovedMap.get(stringKeysArrayList.get(i)))){
                                    
                                    
                                   newTaskMemoryTitle.get(stringCardIndexAfter2xClick).remove(stringKeysArrayList.get(i)); 
                                   newTaskMemoryColor.get(stringCardIndexAfter2xClick).remove(stringKeysArrayList.get(i));
                                   newTaskMemoryStringKeys.get(stringCardIndexAfter2xClick).remove(stringKeysArrayList.get(i));
                                   
                                   listPanel3.remove(newTaskpPanelsToBeRemovedMap.get(stringKeysArrayList.get(i)));
                                   tempNames.add(stringKeysArrayList.get(i));
                                   
                                   if(listPanel3.getComponentCount()<2) removeTaskButton.setVisible(false);
                                }
                        }
                     }
                     
                     
                     for(int i=0; i<tempNames.size(); i++){
                         newTaskpPanelsToBeRemovedMap.remove(tempNames.get(i));
                         stringKeysArrayList.remove(tempNames.get(i));// i need it for memory
                         colorMap.remove(tempNames.get(i)); // i need it for memory
                        // newTaskMemoryTitle.remove(tempNames.get(i)); // i need it for memory
                     }
                     //======= added today 07 april 20 =======
                     if(listPanel3.getComponentCount()<2){
                        stringKeysArrayList.clear();
                      //  colorMap.clear();
                         
                        newTaskMemoryColor.get(stringCardIndexAfter2xClick).clear();
                        newTaskMemoryStringKeys.get(stringCardIndexAfter2xClick).clear();
                        newTaskMemoryTitle.get(stringCardIndexAfter2xClick).clear();
                     }
                     //======= added today 07 april 20 =======
                     tempNames.clear();
                     
                     mainFrame.revalidate();
                     mainFrame.repaint();
                 }
             }
        }
    };
//=================== End of Action Listener for checkBox=======================    
    
    
//===================Start of Mouse Listener====================================
    MouseListener ML = new MouseListener(){
        @Override
        public void mouseEntered(MouseEvent e){
        }
        
        @Override
        public void mouseClicked (MouseEvent e){
            if(e.getClickCount()==2){
                
                stringCardIndexAfter2xClick = panelForScrollPane.getComponentAt(panelForScrollPane.getMousePosition()).getName();
                card.show(cardPanel, stringCardIndexAfter2xClick);
                
                 for(int i=0; i<cardPanel.getComponentCount(); i++){
                    JScrollPane listScrollPane3 = (JScrollPane)cardPanel.getComponent(i);
                    
                    if(listScrollPane3.getName().equals(stringCardIndexAfter2xClick)){
                        listPanel3 = (JPanel)listScrollPane3.getViewport().getComponent(0);
                        break;    
                    }
                }
                addListButton.setVisible(false);
                addTaskButton.setVisible(true);
                backButton.setVisible(true);
                if(listPanel3.getComponentCount()>1) removeTaskButton.setVisible(true);
            }
        }
        
        @Override
        public void mousePressed (MouseEvent e){
        
        }
        
        @Override
        public void mouseReleased (MouseEvent e){
        
        }
        
        @Override 
        public void mouseExited (MouseEvent e){
            
        }
    };
//===================End of Mouse Listener======================================    
    
    //=============== Start of random number generator for Color ===============
    public static int randomNumber(){
        int ran = new Random().nextInt(255);
        int random=101;
        
        if(ran>100) random = ran;
        
        return random;
    }
    //=============== End of random number generator for Color =================
    
}


