package v2;

import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.Timer;

public class Bar {

	private MenuBar bar;
	public Bar(Panel panel, Text text,Timer timer, int speed){
		bar = new MenuBar();
		
		Menu menu = new Menu("options");

		MenuItem item = new MenuItem();
		item.setLabel("set text");
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String box = JOptionPane.showInputDialog("new text");
				text.setText(box);
			}
		});
		menu.add(item);
		
		MenuItem item2 = new MenuItem();
		item2.setLabel("set refreshrate");
		item2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String box = JOptionPane.showInputDialog("set refreshrate in milliseconds");
				timer.setDelay(Integer.parseInt(box));
			}
		});
		menu.add(item2);
		
		MenuItem item3 = new MenuItem();
		item3.setLabel("set gradient speed");
		item3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String box = JOptionPane.showInputDialog("set gradient speed in milliseconds");
				panel.setSpeed(Integer.parseInt(box));
			}
		});
		menu.add(item3);

		bar.add(menu);
	}
	
	public MenuBar getMenuBar(){
		return bar;
	}
}
