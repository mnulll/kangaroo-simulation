/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kangaroo;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JFrame;
import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JComboBox;

public class KangarooSIM {

    JFrame frame;
    //GENERAL VARIABLES
    private int ID = 1;
    private int cells = 20;
    private int delay = 30;
    private double dense = .5;
    private double density = (cells * cells) * .5;
    private int kangaroo = 1;
    private int height = 5;
    private int maxKang = 5;
    private int food = 10;
    private int tool = 0;
    private int pouch = 5;
    private int length = 0;
    private int colony = 4;
    private int WIDTH = 1000;
    private final int HEIGHT = 660;
    private final int MSIZE = 600;
    private int CSIZE = MSIZE / cells;
    int xx = 0;
    //util array
    private String[] tools = {"Pencil", "Eraser"};
    private ArrayList<point> points = new ArrayList();
    private ArrayList<Kangaroo> kangaroos = new ArrayList();
    private Graph<point, Integer> graphs = new Graph();
    private ArrayList<point> temppoints;
    GraphNode<point, point> temp;
    GraphNode<point, point> target;
    private String[] gender = {"Male", "Female"};
//    boolean canDraw = false;

    //util
    point[][] map;
    point[] pointList;
    Random r = new Random();
    //slider
    JSlider heightSL = new JSlider(0, 10, 5);
    JSlider foodSL = new JSlider(0, 20, 10);
    JSlider maxkangSL = new JSlider(1, 10, 5);
    JSlider kangarooSL = new JSlider(1, 21, 1);
    JSlider pouchSL = new JSlider(1, 10, 5);
    JSlider colonySL=new JSlider(3,10,4);
    //label
    JLabel toolL = new JLabel("Toolbox");
    JLabel sizeL = new JLabel("Size");
    JLabel pointsL = new JLabel("Number of points : " + points.size());
    JLabel pointL = new JLabel("Point");
    JLabel foodL = new JLabel("Food");
    JLabel foodnoL = new JLabel("" + food);
    JLabel kanglimL = new JLabel("<html>Kangaroo Limit/ <brx/>Point</html>");
    JLabel maxkangL = new JLabel(maxKang + " Kangaroo(s)");
    JLabel pathL = new JLabel("Path");
    JLabel pathenL = new JLabel("<html>Enable<br>Path?</html>");
    JLabel heightL = new JLabel("Set Height");
    JLabel heightlvL = new JLabel(height + " garoo(s)");
    JLabel kangnoL = new JLabel("<html>Set Kangaroo<br>Number</html>");
    JLabel kangarooL = new JLabel(kangaroo + " Kangaroo(s)");
    JLabel reverseL = new JLabel("2-Way?");
    JLabel kangL = new JLabel("Kangaroo");
    JLabel genderL = new JLabel("Gender: ");
    JLabel colonyL = new JLabel("Colony Slider: ");
    JLabel colonynoL=new JLabel(colony+" kangaroo(s)");
    JLabel maxpouchL = new JLabel("<html>Set Max<br>Pouch</html>");
    JLabel pouchL = new JLabel(pouch + " Food(s)");
    JLabel posL = new JLabel("Initial Position");

    //buttons
    JButton startB = new JButton("Start");
    JButton resetB = new JButton("Reset");
    JButton ranmapB = new JButton("Random Map");
    JButton clearmapB = new JButton("Clear Map");
    JButton creditB = new JButton("Credits");
    //list
    JComboBox toolBx = new JComboBox(tools);
    JComboBox pointBx = new JComboBox(graphs.VtoArray());
    JComboBox pathBx = new JComboBox(graphs.VtoArray());
    JComboBox kangBx = new JComboBox(kangaroos.toArray());
    JComboBox posBx = new JComboBox(graphs.VtoArray());
    JComboBox genderBx = new JComboBox(gender);
    //panel
    JPanel toolP = new JPanel();
    JPanel pointP = new JPanel();
    JPanel kangarooP = new JPanel();
    //canvas
    Map canvas;
    upperPanel uP;
    //border
    Border lowered = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
    //check box
    JCheckBox pathCh = new JCheckBox();

    JCheckBox reverseCh = new JCheckBox();

    public static void main(String[] args) {
        new KangarooSIM();
    }

    public KangarooSIM() {
        clearMap();
        initialize();
    }

    public void initialize() {
        points.add(new point());
        graphs.addVertice(new point());
        kangaroos.add(new Kangaroo());
        updateBox();
        frame = new JFrame();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setSize(WIDTH, HEIGHT);
        frame.setTitle("Kangarooooo");
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        toolP.setBorder(BorderFactory.createTitledBorder(lowered, "Controls"));
        int space = 25, space2 = 20, space3 = 20;
        int buff = 35;

        toolP.setLayout(null);
        toolP.setBounds(620, 10, 359, 600);

        startB.setBounds(50, space, 120, 25);
        toolP.add(startB);
        resetB.setBounds(190, space, 120, 25);
        toolP.add(resetB);
        space += buff;

        ranmapB.setBounds(50, space, 120, 25);
        toolP.add(ranmapB);
        clearmapB.setBounds(190, space, 120, 25);
        toolP.add(clearmapB);
        space += buff;

        toolL.setBounds(30, space, 120, 25);
        toolP.add(toolL);
        space += 25;

        toolBx.setBounds(30, space, 120, 25);
        toolP.add(toolBx);
        space += buff;

        pointP.setLayout(null);
        pointP.setBorder(BorderFactory.createTitledBorder(lowered, "Point Controls"));
        pointP.setBounds(5, space, 350, 200);//    space+=buff
//    space2+=buff;

        pointL.setBounds(10, space2, 50, 25);
        pointP.add(pointL);
        pointBx.setBounds(45, space2, 120, 25);
        pointP.add(pointBx);
        space2 += buff;

        foodL.setBounds(10, space2, 50, 25);
        pointP.add(foodL);
        foodSL.setBounds(105, space2, 100, 25);
        pointP.add(foodSL);
        foodnoL.setBounds(210, space2, 50, 25);
        pointP.add(foodnoL);
        space2 += buff;

        kanglimL.setBounds(10, space2, 100, 25);
        pointP.add(kanglimL);
        maxkangSL.setBounds(105, space2, 100, 25);
        pointP.add(maxkangSL);
        maxkangL.setBounds(210, space2, 100, 25);
        pointP.add(maxkangL);
        space2 += buff;

        pathL.setBounds(10, space2, 50, 25);
        pointP.add(pathL);
        pathBx.setBounds(45, space2, 120, 25);
        pointP.add(pathBx);
//        pathCh.setBounds(170, space2 + 2, 20, 20);
//        pathCh.setSelected(true);
//        pointP.add(pathCh);
//        pathenL.setBounds(195, space2, 50, 25);
//        pointP.add(pathenL);
//        reverseCh.setBounds(240, space2 + 2, 20, 20);
//        pointP.add(reverseCh);
//        reverseL.setBounds(260, space2, 70, 25);
//        pointP.add(reverseL);
        space2 += buff;

        heightL.setBounds(10, space2, 100, 25);
        pointP.add(heightL);
        heightSL.setBounds(105, space2, 100, 25);
        pointP.add(heightSL);
        heightlvL.setBounds(210, space2, 100, 25);
        pointP.add(heightlvL);
        space2 += buff;

        toolP.add(pointP);
        space += 200;

        kangarooP.setLayout(null);
        kangarooP.setBorder(BorderFactory.createTitledBorder(lowered, "Kangaroo Controls"));
        kangarooP.setBounds(5, space, 350, 170);
        kangnoL.setBounds(10, space3, 100, 50);
        kangarooP.add(kangnoL);
        kangarooSL.setBounds(105, space3 + 15, 100, 25);
        kangarooP.add(kangarooSL);
        kangarooL.setBounds(210, space3 + 15, 90, 25);
        kangarooP.add(kangarooL);
        space3 += buff + 10;

        kangL.setBounds(10, space3, 70, 25);
        kangarooP.add(kangL);
        kangBx.setBounds(70, space3, 140, 25);
        kangarooP.add(kangBx);
        genderL.setBounds(215, space3, 60, 25);
        kangarooP.add(genderL);
        genderBx.setBounds(270, space3, 70, 25);
        kangarooP.add(genderBx);

        space3 += buff;

        maxpouchL.setBounds(10, space3, 70, 25);
        kangarooP.add(maxpouchL);
        pouchSL.setBounds(85, space3, 100, 25);
        kangarooP.add(pouchSL);
        pouchL.setBounds(190, space3, 70, 25);
        kangarooP.add(pouchL);
        space3 += buff;

        posL.setBounds(10, space3, 100, 25);
        kangarooP.add(posL);
        posBx.setBounds(110, space3, 120, 25);
        kangarooP.add(posBx);
        space3 += buff;

        toolP.add(kangarooP);
        space += 175;
        
        colonyL.setBounds(15, space, 100, 25);
        toolP.add(colonyL);
        colonySL.setBounds(110, space, 100, 25);
        toolP.add(colonySL);
        colonynoL.setBounds(215, space, 100, 25);
        toolP.add(colonynoL);
        space +=buff;

        pointsL.setBounds(15, space, 150, 25);
        toolP.add(pointsL);
        space += buff;

//    creditB.setBounds(40, space, 120, 25);
//    toolP.add(creditB);
        

        canvas = new Map();
        canvas.setLayout(null);
        canvas.setBounds(10, 10, MSIZE + 1, MSIZE + 1);
        uP = new upperPanel();
        uP.setLayout(null);
        uP.setBounds(1, 1,MSIZE+1,MSIZE+1);
        canvas.add(uP);
//        uP.add(toolP);
        frame.getContentPane().add(canvas);
//        frame.getContentPane().add(uP);
        frame.getContentPane().add(toolP);
        Update();
        // Action listener
        toolBx.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                tool = toolBx.getSelectedIndex();
            }
        });
        
        startB.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                start();
                result();
            }
        });
        resetB.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                clearMapB();
                resetKang();
            }
        });
        ranmapB.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                ranMapB();
            }
        });
        clearmapB.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                clearMapB();
            }
        });
        pointBx.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                temp = graphs.getVerticeIndex(pointBx.getSelectedIndex());
                food = ((point) temp.getVertice()).getFood();
                foodSL.setValue(food);
                maxKang = ((point) temp.getVertice()).getLimit();
                maxkangSL.setValue(maxKang);
                Update();
            }
        });
        foodSL.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                food = foodSL.getValue();
                ((point) temp.getVertice()).setFood(food);
                Update();
            }
        });
        maxkangSL.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                maxKang = maxkangSL.getValue();
                ((point) temp.getVertice()).setLimit(maxKang);
                Update();
            }
        });
        pathBx.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
//            if(xx%2==0){
                temp = graphs.getVerticeIndex(pointBx.getSelectedIndex());
                point start = ((point) temp.getVertice());
                target = graphs.getVerticeIndex(pathBx.getSelectedIndex());
                point tg = ((point) target.getVertice());
                System.out.println("hehe");
                if(tg.compareTo(new point())!=0){
                if (graphs.isEdge(start, tg)) {///ocak
                    height = graphs.getWeight(temp.getVertice(), target.getVertice());
                    heightSL.setValue(height);
                } else {
                    graphs.addEge(start, tg, height);
                    graphs.showGraph();
                    //draw line
                    updateMap();
                }}
            }
//        xx++;}
        }
        );
        heightSL.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                height = heightSL.getValue();
                graphs.setWeight(temp.getVertice(), target.getVertice(), height);
                graphs.showGraph();
                Update();
                updateMap();
            }
        });

        kangarooSL.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if (xx % 2 == 0) {
                    int tempo = kangarooSL.getValue();
//                    System.out.println(tempo);
//                    System.out.println(kangaroo);
                    if (tempo > kangaroo) {
                        for (int i = kangaroo; i < tempo; i++) {
                            kangaroos.add(new Kangaroo(i));
                            updateBox();
                        }
                    }
                    if (kangaroo > tempo) {
                        for (int i = kangaroo - 1; i >= tempo; i--) {
                            kangaroos.remove(i);
                            updateBox();
                        }
                    }
                    kangaroo = tempo;
                    Update();

                }
//                xx++;
//                System.out.println("xx " + xx);
            }
        });
        kangBx.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {

                pouch = kangaroos.get(kangBx.getSelectedIndex()).getMaxfoodPouch();
                pouchSL.setValue(pouch);
                point temp = kangaroos.get(kangBx.getSelectedIndex()).getPoint();
                posBx.setSelectedItem(temp);
                int gender = kangaroos.get(kangBx.getSelectedIndex()).getGender();
                genderBx.setSelectedIndex(gender);
            }
        });
        genderBx.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                int gender = genderBx.getSelectedIndex();
                kangaroos.get(kangBx.getSelectedIndex()).setGender(gender);
            }
        });
        pouchSL.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                pouch = pouchSL.getValue();
                kangaroos.get(kangBx.getSelectedIndex()).setMaxfoodPouch(pouch);
//                System.out.println(kangaroos.get(kangBx.getSelectedIndex()));
                Update();
            }
        });
        posBx.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if(xx%2==0){
                Kangaroo a=kangaroos.get(kangBx.getSelectedIndex());
                int gender=kangaroos.get(kangBx.getSelectedIndex()).getGender();
                ((point)kangaroos.get(kangBx.getSelectedIndex()).getPoint()).KangOut(a);
                point temp = (point) posBx.getSelectedItem();
                kangaroos.get(kangBx.getSelectedIndex()).setPoint(temp);
//                if(gender==0){
//                kangaroos.get(kangBx.getSelectedIndex()).getPoint().MaleIn(a);}
//                else{kangaroos.get(kangBx.getSelectedIndex()).getPoint().FemaleIn(a);}
//                xx++;
//                    System.out.println(xx);
                }xx++;
//                System.out.println(xx);
            }
        });
        colonySL.addChangeListener(new ChangeListener(){
         public void stateChanged(ChangeEvent e){
             colony=colonySL.getValue();
             for(int i=1;i<graphs.getSize();i++){
                 ((point)graphs.getVerticeIndex(i).getVertice()).setThreshold(colony);
             }
             Update();
             
         }
       
        });
    
    }
    public void clearMap() {
        map = new point[cells][cells];
        for (int x = 0; x < cells; x++) {
            for (int y = 0; y < cells; y++) {
                map[x][y] = new point(0, x, y);
            }
        }
    }

    public void Update() {
        foodnoL.setText("" + food);
        maxkangL.setText(maxKang + " Kangaroo(s)");
        kangarooL.setText(kangaroo - 1 + " Kangaroo(s)");
        heightlvL.setText(height + " garoo(s)");
        pouchL.setText(pouch + " food(s)");
        colonynoL.setText(colony+" kangaroo(s)");
    }

    public void updateMap() {
        canvas.repaint();
        uP.repaint();
    }

    public void updateBox() {
        pointBx.setModel(new DefaultComboBoxModel(graphs.VtoArray()));
        posBx.setModel(new DefaultComboBoxModel(graphs.VtoArray()));
        pathBx.setModel(new DefaultComboBoxModel(graphs.VtoArray()));
        kangBx.setModel(new DefaultComboBoxModel(kangaroos.toArray()));
        pointsL.setText("Number of points : " + (graphs.getSize() - 1));
    }

    public void resetMap() {	//RESET MAP
        for (int x = 0; x < cells; x++) {
            for (int y = 0; y < cells; y++) {
                point current = map[x][y];
                if (current.getType() == 3 || current.getType() != 1) //CHECK TO SEE IF CURRENT NODE IS EITHER CHECKED OR FINAL PATH
                {
                    map[x][y] = new point(0, x, y);	//RESET IT TO AN EMPTY NODE
                }
            }
        }
    }
    public void updateAll(){
        Update();
        updateMap();
        updateBox();
    }

    class Map extends JPanel implements MouseListener, MouseMotionListener {	//MAP CLASS

        private point current;

        public Map() {
            addMouseListener(this);
            addMouseMotionListener(this);
        }

        public void paintComponent(Graphics g) {	//REPAINT
            super.paintComponent(g);
            for (int x = 0; x < cells; x++) {	//PAINT EACH NODE IN THE GRID
                for (int y = 0; y < cells; y++) {
                    switch (map[x][y].getType()) {
                        case 0:
                            g.setColor(Color.getHSBColor(65, 47, 75).darker());
                            break;
                        case 1:
                            g.setColor(Color.getHSBColor(65, 47, 80));
                            break;
                        case 2:
                            g.setColor(Color.BLACK);
                            break;
                        case 3:
                            g.setColor(Color.getHSBColor(65, 47, 75).brighter());
                            break;
                        case 4:
                            g.setColor(Color.CYAN);
                            break;
                        case 5:
                            g.setColor(Color.YELLOW);
                            break;
                    }
//                    g.setColor(Color.gray); 
                    g.fillRect(x * CSIZE, y * CSIZE, CSIZE, CSIZE);
                               
//					g.setColor(Color.BLACK);
                    g.drawRect(x * CSIZE, y * CSIZE, CSIZE, CSIZE);
                    
                    //DEBUG STUFF
                    /*
					if(curAlg == 1)
						g.drawString(map[x][y].getHops()+"/"+map[x][y].getEuclidDist(), (x*CSIZE)+(CSIZE/2)-10, (y*CSIZE)+(CSIZE/2));
					else 
						g.drawString(""+map[x][y].getHops(), (x*CSIZE)+(CSIZE/2), (y*CSIZE)+(CSIZE/2));
                     */
                }
            }
        }

        public void paintline(Graphics g) {
            super.paintComponent(g);

        }

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            resetMap();
            try {
                int x = e.getX() / CSIZE;
                int y = e.getY() / CSIZE;
                current = map[x][y];
                switch (tool) {
                    case 0:
                        if (current.getType() != 1) {
                            current.setType(1);
                            current.setID(ID);
                            graphs.addVertice(current);
                            ID++;
                            updateBox();
                        }
                        break;
                    case 1:
                        if (current.getType() == 1) {
                            current.setType(0);
                            graphs.removeVertice(current);
                            graphs.showGraph();
                            updateBox();
                        }
                        break;
                }

            } catch (Exception f) {
            }
        }

        @Override
        public void mouseReleased(MouseEvent arg0) {
        }

        @Override
        public void mouseEntered(MouseEvent arg0) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
            resetMap();
            try {
                if (current.getType() == 3 && current.getType() != 1) {
                    current.setType(0);
                }
                updateMap();
            } catch (Exception f) {
            }
        }

        @Override
        public void mouseDragged(MouseEvent arg0) {
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            resetMap();
            try {
                int x = e.getX() / CSIZE;
                int y = e.getY() / CSIZE;
                current = map[x][y];
                if (current.getType() != 1) {
                    current.setType(3);

                }
                updateMap();
            } catch (Exception f) {
            }
        }
    }
//public path getPath(point st,point dest){
//    for(int i=0;i<paths.size();i++){
//        if(paths.get(i).start.compareTo(st)==0&&paths.get(i).desti.compareTo(dest)==0) return paths.get(i);
//    }return null;
//}

    class upperPanel extends JPanel {

        public upperPanel() {
            
        }

        public void paintComponent(Graphics g) {
            ArrayList<Graphics> lines = new ArrayList();
                super.paintComponents(g);
                for (int i = 1; i < graphs.getSize(); i++) {
                    point a=(point) graphs.getVerticeIndex(i).getVertice();
                    g.setColor(Color.black);
                    g.drawString(a.toString(), Math.abs(a.x*CSIZE+15), Math.abs(a.y*CSIZE+15));
                    for (int j = 1; j < graphs.getSize(); j++) {
                        point b=(point) graphs.getVerticeIndex(j).getVertice();
                        if (j == i) {
                            continue;
                        } else if (graphs.isEdge((point) graphs.getVerticeIndex(i).getVertice(), (point) graphs.getVerticeIndex(j).getVertice())) {
                            int x=(((a.x*7+b.x*3)*CSIZE)/10)+10;
                            int y=(((a.y*7+b.y*3)*CSIZE)/10)+10;
                            g.setColor(Color.black);
                            g.drawString(graphs.getWeight(a, b)+"",x,y) ;
                            g.setColor(Color.RED);
                            g.drawLine( a.x * CSIZE+15, a.y * CSIZE+15, b.x * CSIZE+15, b.y * CSIZE+15);
                        }
                    }                   
                }
                //lukis kangaroo
//                System.out.println(kangaroos.size());
                for(int i=1;i<kangaroos.size();i++){
                    Kangaroo k=kangaroos.get(i);
                    point temp=k.getPoint();
                    point trap=new point();
//                    System.out.println("testo");
//                    System.out.println(temp.toString());
//                    System.out.println(trap.toString());
//                    System.out.println(temp.toString().compareTo(trap.toString())!=0);
                    if(temp.toString().compareTo(trap.toString())!=0){
                    if(k.getGender()==0){
                        g.setColor(Color.BLUE);
                        g.fillOval(temp.x*CSIZE-10, temp.y*CSIZE-10, 10, 10);                        
                    }
                    if(k.getGender()==1){
                       g.setColor(Color.PINK.darker());
                        g.fillOval(temp.x*CSIZE-10, temp.y*CSIZE-10, 10, 10); 
                    }
                    g.drawString(k.toString(), temp.x*CSIZE-10, temp.y*CSIZE-10);
                    }
                }
  
            }
    }
    public void clearMapB(){
        clearMap();
                graphs=new Graph();
                graphs.addVertice(new point());
                for(int i=1;i<kangaroos.size();i++){
                    kangaroos.get(i).setPoint(new point());
                }
                ID=1;
                updateAll();
    }
    public void ranMapB(){
        clearMapB();
        ranPoint();
        ranEdge();
        graphs.showGraph();
        updateAll();
        
    }
    public void ranPoint(){
        for(int i=0;i<r.nextInt(20)+1;i++){
            int x=r.nextInt(20);
            int y=r.nextInt(20);
            point a=map[x][y];
            a.setType(1);
            a.setFood(r.nextInt(20)+1);
            a.setID(ID);
            a.setLimit(r.nextInt(5)+1);
            graphs.addVertice(a);
            ID++;
        }
    }
    public void ranEdge(){
        for(int i=1;i<graphs.getSize();i++){
            point a=(point) graphs.getVerticeIndex(i).getVertice();
            int c=r.nextInt(3)+2;
//            System.out.println(c);
            for(int j=1;j<c;j++){
//                System.out.println(j);
            point b=(point) graphs.getVerticeIndex(r.nextInt(graphs.getSize()-1)+1).getVertice();
            graphs.addEge(a,b,r.nextInt(11));
//                System.out.println("j lepas :"+j);
            }
        }
    }
    public void start(){
        System.out.println("starting");
        int check=0;
        while(check<=kangaroos.size()){
            for(int i=1;i<kangaroos.size();i++){
                System.out.println(i);
                point initial=kangaroos.get(i).getPoint();
                kangaroos.get(i).thinkmove(graphs);
                point after=kangaroos.get(i).getPoint();
                delay();
                updateMap();
                if(initial.compareTo(after)==0){
                    check++;
                    System.out.println("check= "+check);
                }else{check=0;}
            }
        }
    }
    public void result(){
        int count=0;
        for(int i=1;i<graphs.getSize();i++){
            point temp=(point)graphs.getVerticeIndex(i).getVertice();
            if(temp.isColony()){
                System.out.println("colony point "+temp.toString()+" : " +temp.kang.size());
                count++;
            }
        }
        System.out.println("number of colony :"+count);
    }
    public void delay() {	//DELAY METHOD
		try {
			Thread.sleep(500);
		} catch(Exception e) {}
	}
    public void resetKang(){
        kangaroos=new ArrayList();
        kangaroo=0;
        kangarooSL.setValue(0);
        updateBox();
    }
}
