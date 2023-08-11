import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RenderWindowAdjustmentGUI {
    JFrame frame;
    public RenderWindowAdjustmentGUI(FieldRendererAnimPanel renderer) {

        frame = new JFrame("Render Window Adjustment GUI");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.X_AXIS));

        JLabel   gapLabel = new JLabel("Gap Spacing:");
        JTextField gapBox = new JTextField(renderer.GAP+"",4);
        JSlider gapSlider = new JSlider(JSlider.HORIZONTAL, -50,250, renderer.GAP);

        JLabel   scaleLabel = new JLabel("Scaling Factor(%):");
        JTextField scaleBox = new JTextField(renderer.sf+"",4);
        JButton scaleButton = new JButton("Enabled: "+renderer.scalingEnabled);
        JSlider scaleSlider = new JSlider(JSlider.HORIZONTAL, 0,250, Math.round(renderer.sf*100));


        gapBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev){
                try{
                    int gap = Integer.parseInt(gapBox.getText().strip());
                    renderer.GAP = gap;
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }
                updateGUI();
            }
        });

        gapSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e){
                renderer.GAP = gapSlider.getValue();
                updateGUI();
            }
        });

        scaleBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev){
                try{
                    float scale = Float.parseFloat(scaleBox.getText().strip());
                    renderer.sf = scale;
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }
                updateGUI();
            }
        });
        scaleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev){
                renderer.scalingEnabled = !renderer.scalingEnabled;
                updateGUI();
            }
        });

        scaleSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e){
                renderer.sf = scaleSlider.getValue()/100.0f;
                updateGUI();
            }
        });


        JPanel gapPanel = new JPanel();
        gapPanel.setLayout(new FlowLayout());
        gapPanel.add(gapLabel);
        gapPanel.add(gapBox);
        gapPanel.add(gapSlider);

        JPanel scalePanel = new JPanel();
        scalePanel.setLayout(new FlowLayout());
        scalePanel.add(scaleLabel);
        scalePanel.add(scaleBox);
        scalePanel.add(scaleButton);
        scalePanel.add(scaleSlider);

        panel.add(gapPanel);
        panel.add(scalePanel);

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
        frame.setAlwaysOnTop(true);
    }

    protected void updateGUI() {
    }


    public void close() {
        frame.dispose();
    }
}