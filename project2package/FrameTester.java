package project2package;

import java.util.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class FrameTester 
{
	
	public static void main(String[] args)
	{
		JFrame j = new JFrame("Project 2");
		FrameContent cont = new FrameContent(j);
		j.getContentPane().add(cont);		
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		j.setVisible(true);
	}
	
	
	
	
}
