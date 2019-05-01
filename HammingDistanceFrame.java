import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * high level overiew of the class and it p[urpose/behavior
 * 
 * @author bachngoxuan2000
 * @version DATE
 */

public class HammingDistanceFrame extends JFrame {

	JPanel creativePanel = new JPanel(new GridBagLayout());  
	JButton remove = new JButton("Remove");
	
	
	JPanel panel = new JPanel(new GridBagLayout());  
	int[] Distance = new int[5];
	
	HashMap<Integer, ArrayList<String>> stationsbhd = new HashMap<Integer, ArrayList<String>>();
	
	
	private ArrayList<String> WeatherStations = new ArrayList <String>();

	//all JTextFields
	JTextField hammingDist = new JTextField(20);
	JTextArea  stationsList = new JTextArea(20, 20);
	JTextField dist0 = new JTextField(10);
	JTextField dist1 = new JTextField(10);
	JTextField dist2 = new JTextField(10);
	JTextField dist3 = new JTextField(10);
	JTextField dist4 = new JTextField(10);
	JTextField addStationtf = new JTextField(10);
	
	
	JComboBox stations;
	
	ArrayList<JTextField> distancetf = new ArrayList<JTextField>();
	
	//all the labels
	JLabel enterHammingDist = new JLabel("Enter Hamming Dist:");
	JLabel compareWith = new JLabel("Compare With:");
	JLabel d0 = new JLabel("Distance 0");
	JLabel d1 = new JLabel("Distance 1");
	JLabel d2 = new JLabel("Distance 2");
	JLabel d3 = new JLabel("Distance 3");
	JLabel d4 = new JLabel("Distance 4");
	
	ArrayList<JLabel> distLabel = new ArrayList<>(5);
	
	
	
	
	//all the 3 buttons
	JButton showStation = new JButton("Show Station");
	JButton CalculateHD = new JButton("Calculate HD");
	JButton AddStation = new JButton("Add Station");
	
	//slider
	JSlider slider = new JSlider(1, 4);
	
	public void read (String Mesonet) throws IOException   {
		
		BufferedReader br = new BufferedReader(new FileReader(Mesonet));
		String stationIDs = br.readLine(); //read the 1st line
    	while (stationIDs != null)    {
    		WeatherStations.add(stationIDs.substring(0,4)); //adding to WeatherStations starting from index 1 to index 4 (index 5 is excluded)
    		stationIDs = br.readLine(); //keep reading the String
    		
    	}	
    	br.close(); // close the BufferedReader
    	
	}
	
	
	public HammingDistanceFrame() throws IOException   {
		slider.addChangeListener(new ChangeListener() {

		@Override
		public void stateChanged(ChangeEvent event) {
			int sliderVal;

			sliderVal = slider.getValue();
			hammingDist.setText(sliderVal + ""); 
							 				
			}});
		
		//setting up the slider
		slider.setMajorTickSpacing(1);  
		slider.setPaintTicks(true);  
		slider.setPaintLabels(true);  
		//position the slider
		GridBagConstraints layoutConst = new GridBagConstraints();
	    layoutConst.gridx = 0;
	    layoutConst.gridy = 1;
	    layoutConst.insets = new Insets(10, 10, 10, 10);
		panel.add(slider, layoutConst);  
		add(panel);
		
		//position the first label
		GridBagConstraints layoutConst1 = new GridBagConstraints();
		layoutConst1.gridx = 0;
		layoutConst1.gridy = 0;
		layoutConst1.anchor = GridBagConstraints.LINE_START;
		layoutConst1.insets = new Insets(10, 10, 10, 10);
		panel.add(enterHammingDist, layoutConst1);  
		add(panel);
		
		//position the Show Station button
		//showStation.addActionListener(this);
		 GridBagConstraints layoutConst2 = new GridBagConstraints();
		 layoutConst2.gridx = 0;
		 layoutConst2.gridy = 3;
		 layoutConst2.insets = new Insets(10, 10, 10, 20);
		 layoutConst2.anchor = GridBagConstraints.LINE_START;
		 panel.add(showStation, layoutConst2);
		 add(panel);
		 
		 //
		 //hammingDist.setBounds(100,100,100,100);
		 GridBagConstraints layoutConst3 = new GridBagConstraints();
		 layoutConst3.insets = new Insets(10, -100, 1, 10);
		 layoutConst3.gridx = 1;
		 layoutConst3.gridy = 0;
		 layoutConst3.gridwidth = 1;
		 layoutConst3.fill = GridBagConstraints.HORIZONTAL;
		 panel.add(hammingDist, layoutConst3);
		 add(panel);
		
		 //
		 GridBagConstraints layoutConst4 = new GridBagConstraints();
		 layoutConst4.gridx = 0;
		 layoutConst4.gridy = 5;
		 layoutConst4.insets = new Insets(10, 10, 10, 10);
		 panel.add(stationsList, layoutConst4);
		 add(panel);
		 
		 GridBagConstraints layoutConst5 = new GridBagConstraints();
		 layoutConst5.gridx = 0;
		 layoutConst5.gridy = 7;
		 layoutConst5.insets = new Insets(10, 10, 10, 20);
		 layoutConst5.anchor = GridBagConstraints.LINE_START;
		 panel.add(compareWith, layoutConst5);
		 add(panel);
		 
		 GridBagConstraints layoutConst6 = new GridBagConstraints();
		 layoutConst6.gridx = 0;
		 layoutConst6.gridy = 10;
		 layoutConst6.insets = new Insets(10, 10, 10, 20);
		 layoutConst6.anchor = GridBagConstraints.LINE_START;
		 panel.add(CalculateHD, layoutConst6);
		 add(panel);
		 
		 GridBagConstraints layoutConst7 = new GridBagConstraints();
		 layoutConst7.gridx = 0;
		 layoutConst7.gridy = 11;
		 layoutConst7.insets = new Insets(10, 10, 10, 20);
		 panel.add(d0,layoutConst7);
		 add(panel);
		 
		 distLabel.add(d0);
		 distLabel.add(d1);
		 distLabel.add(d2);
		 distLabel.add(d3);
		 distLabel.add(d4);
		 
		 for (int x = 0; x < distLabel.size(); ++x)   {
			 GridBagConstraints jLabellayoutConstant = new GridBagConstraints();
			 jLabellayoutConstant.gridx = 0;
			 jLabellayoutConstant.gridy = 11 + 2*x;
			 jLabellayoutConstant.insets = new Insets(10, 10, 10, 10);
			 jLabellayoutConstant.anchor = GridBagConstraints.LINE_START;
			 panel.add(distLabel.get(x),jLabellayoutConstant);
			 add(panel);
		 }
		
		 distancetf.add(dist0);
		 distancetf.add(dist1);
		 distancetf.add(dist2);
		 distancetf.add(dist3);
		 distancetf.add(dist4);
		 
		 for (int x = 0; x < distancetf.size(); ++x)   {
			 GridBagConstraints jTFlayoutConstant = new GridBagConstraints();
			 distancetf.get(x).setBounds(100,100,100,100);
			 jTFlayoutConstant.gridx = 1;
			 jTFlayoutConstant.gridy = 11 + 2*x;
			 jTFlayoutConstant.insets = new Insets(10, -100, 10, 10);
			 jTFlayoutConstant.anchor = GridBagConstraints.LINE_START;
			 panel.add(distancetf.get(x), jTFlayoutConstant);
			 add(panel); 
		 }
		 
		 GridBagConstraints layoutConst8 = new GridBagConstraints();
		 layoutConst8.gridx = 0;
		 layoutConst8.gridy = 21;
		 layoutConst8.insets = new Insets(10, 10, 10, 20);
		 layoutConst8.anchor = GridBagConstraints.LINE_START;
		 panel.add(AddStation,layoutConst8);
		 add(panel);
		 
		 GridBagConstraints layoutConst9 = new GridBagConstraints();
		 layoutConst9.gridx = 1;
		 layoutConst9.gridy = 21;
		 layoutConst9.anchor = GridBagConstraints.LINE_START;
		 layoutConst9.insets = new Insets(10, -100, 10, 10);
		 panel.add(addStationtf,layoutConst9);
		 add(panel);
		 
		 //
		 read("Mesonet.txt");
		 stations = new JComboBox(WeatherStations.toArray());
		 //stations.addActionListener(this);
		 GridBagConstraints layoutConst10 = new GridBagConstraints();
		 layoutConst10.gridx = 1;
		 layoutConst10.gridy = 7;
		 layoutConst10.insets = new Insets(10,-200, 10, 20);
		 panel.add(stations,layoutConst10);
		 add(panel);
		
		 
		 showStation.addActionListener((e) -> {
				String input = stations.getSelectedItem().toString();
				int value = slider.getValue();
				calculateNumberOfNodes(input);
				String stations = "";
				ArrayList<String> stationNames = stationsbhd.get(value);
				int index = stationsbhd.get(value).size();
				for (int x = 0; x < index; ++x )   {
					stations = stations + stationNames.get(x) + "\n";
				}
				stationsList.setText(stations);
			});
		 
		 CalculateHD.addActionListener((e) -> {
				String input = stations.getSelectedItem().toString();
				Distance = calculateNumberOfNodes (input);
				dist0.setText(String.valueOf(Distance[0]));
				dist1.setText(String.valueOf(Distance[1]));
				dist2.setText(String.valueOf(Distance[2]));
				dist3.setText(String.valueOf(Distance[3]));
				dist4.setText(String.valueOf(Distance[4]));
		 });
		 
		 AddStation.addActionListener((e) -> {
			 String input = addStationtf.getText();
			 if(((DefaultComboBoxModel)stations.getModel()).getIndexOf(input) == -1) {
				 stations.addItem(input);
				}
			 
		 });
		 
		/*GridBagConstraints newlayout = new GridBagConstraints();
		// newlayout.insets = new Insets(10, -100, 1, 10);
		 newlayout.gridx = 20;
		 newlayout.gridy = 10;
		// layoutConst3.gridwidth = 1;
		 //layoutConst3.fill = GridBagConstraints.HORIZONTAL;
		 panel.add(remove, newlayout);
		 add(panel);*/
		 
		 
	}
	

	public static int HammingDistance (String station1, String station2)   {
		int count = 0; // count is used to keep track of differences between two stations
	 	for(int index = 0; index < station1.length(); ++index)   {
	 		if (station1.charAt(index) != station2.charAt(index))   {
	 			++ count;
	 		}
	 	}
	return count;   
	}
	 public int[] calculateNumberOfNodes (String station)  {
		 stationsbhd = new HashMap<Integer, ArrayList<String>>();
		 for (int x = 1; x < 5; ++x)   {
			 stationsbhd.put(x, new ArrayList<String>());
		 }
		 int distance0 = 0;
		 int distance1 = 0; //keep track of 1 letter difference
		 int distance2 = 0; //keep track of 2 letter difference
		 int distance3 = 0; //keep track of 3 letter difference
		 int distance4 = 0; //keep track of 4 letter difference
		 int hold; // hold to keep track of each difference above
		 for (int x = 0; x < WeatherStations.size(); ++x)   {
			hold = HammingDistance(WeatherStations.get(x), station);
			if (hold ==0)   {
				++distance0;
			}
			if (hold == 1)    {
				++distance1;
				stationsbhd.get(hold).add(WeatherStations.get(x));
			}
			if (hold == 2)   {
				++distance2;
				stationsbhd.get(hold).add(WeatherStations.get(x));
			}
			 if (hold == 3)   {
				 ++distance3;
				 stationsbhd.get(hold).add(WeatherStations.get(x));
			 }
			 
			if (hold == 4)   {
				++distance4;
				stationsbhd.get(hold).add(WeatherStations.get(x));
			}
		 }	
		 //int array to store differences
		 int[] Distance = {distance0, distance1, distance2, distance3, distance4};
		 
		 return Distance;
	 }
	
	
	//public void actionPerformed(ActionEvent e) {
      //  JComboBox cb = (JComboBox)e.getSource();
        //String stationName = (String)cb.getSelectedItem();
       
    //}
	/* public static class drawFlg extends JPanel {
			
			public void paintComponent(Graphics g)   {
				
				//super.paintComponents(g);
				g.setColor(Color.blue);
				g.fillRect(0,0,300,900);
				g.setColor(Color.white);
				g.fillRect(300,0, 300, 900);
				g.setColor(Color.red);
				g.fillRect(600,0,300, 900);
				
				Graphics2D g2 = (Graphics2D) g;
				 
				    double height = super.getSize().getHeight();;
				    double width = super.getSize().getWidth();
				 
				    double rw = .6 * width;
				    double xc = .4 * width;
				 
				    g2.setPaint(Color.BLUE);
				    g2.fill(new Rectangle2D.Double(0, 0, .4 * width, .53846 * height));
				 
				    for (int i = 0; i < 13; i++) {
				      if (i == 7) {
				        xc = 0;
				        rw = width;
				      }
				 
				      if (i % 2 == 0) {
				        g2.setPaint(Color.RED);
				      } else {
				        g2.setPaint(Color.WHITE);
				      }
				 
				      g2.fill(new Rectangle2D.Double(xc, i * .0769 * height, rw, .0769 * height));
				    }
				 
				    double x[] = {
				      .0002105 * width, .0126 * width, .0163 * width, .02 * width, .0324 * width,
				      .0217 * width, .02574 * width, .0163 * width, .0069 * width, .0109 * width
				    };
				    double y[] = {
				      .02 * height, .02 * height, 0, .02 * height, .02 * height, 
				      .03 * height, .05 * height, .04 * height, .05 * height, .03 * height
				    };
				 
				    GeneralPath star = new GeneralPath();
				 
				    star.moveTo((float) x[0], (float) y[0]);
				 
				    for (int i = 1; i < x.length; i++) {
				      star.lineTo((float) x[i], (float) y[i]);
				    }
				 
				    star.closePath();
				 
				    g2.translate(.017 * width, .0232 * height);
				    g2.setPaint(Color.WHITE);
				    g2.fill(star);
				 
				    for (int i = 0; i < 5; i++) {
				      g2.translate(.066316 * width, 0);
				      g2.fill(star);
				    }
				 
				    for (int i = 0; i < 4; i++) {
				      g2.translate(-.03316 * width, .054 * height);
				      g2.fill(star);
				 
				      for (int j = 0; j < 4; j++) {
				        g2.translate(-.066316 * width, 0);
				        g2.fill(star);
				      }
				 
				      g2.translate(-.03316 * width, .054 * height);
				      g2.fill(star);
				 
				      for (int k = 0; k < 5; k++) {
				        g2.translate(.066316 * width, 0);
				        g2.fill(star);
				      }
				    }
				    g.setColor(Color.blue);
					g.fillRect(0,0,300,900);
					g.setColor(Color.white);
					g.fillRect(300,0, 300, 900);
					g.setColor(Color.red);
					g.fillRect(600,0,300, 900);
				
				 
			}*/
		
	
	public static void main(String []args) throws IOException {  
		 
		HammingDistanceFrame frame = new HammingDistanceFrame();  
		frame.setTitle("Hamming Distance");
		frame.setSize(1000, 1000);
		frame.pack();  
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);  
		
		}   
	 }


