/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

/**
 *
 * @author Apurva Sawant
 */
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JFrame;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class AppControl extends JPanel
{
private PSO ball = new PSO();
private JButton jbtSuspend = new JButton("Suspend");
private JButton jbtResume = new JButton("Resume");
private JScrollBar jsbDelay = new JScrollBar();

public AppControl()
{
//group buttons in a panel
JPanel panel = new JPanel();
panel.add(jbtSuspend);
panel.add(jbtResume);
panel.setSize(10000, 10000);
//add ball and buttons to the panel
ball.setBorder(new javax.swing.border.LineBorder(Color.red));
jsbDelay.setOrientation(JScrollBar.HORIZONTAL);
ball.setDelay(jsbDelay.getMaximum());
setLayout(new BorderLayout());
add(jsbDelay, BorderLayout.NORTH);
add(ball, BorderLayout.CENTER);
add(panel, BorderLayout.SOUTH);

//register listeners
jbtSuspend.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent e)
{
ball.suspend();
}
});

jbtResume.addActionListener(new ActionListener()
{
public void actionPerformed (ActionEvent e)
{
ball.resume();
}
});

jsbDelay.addAdjustmentListener(new AdjustmentListener()
{
public void adjustmentValueChanged(AdjustmentEvent e)
{
ball.setDelay(jsbDelay.getMaximum() - e.getValue());
}
});
}
}