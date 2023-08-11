import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RenderWindowAdjustmentGUI {
    JFrame frame;

    JLabel   gapLabel;
    JTextField gapBox;
    JSlider gapSlider;

    JLabel   scaleLabel;
    JTextField scaleBox;
    JButton scaleButton;
    JSlider scaleSlider;



    public RenderWindowAdjustmentGUI(FieldRendererAnimPanel renderer) {

        frame = new JFrame("Render Window Adjustment GUI");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.X_AXIS));

        gapLabel = new JLabel("Gap Spacing:");
        gapBox = new JTextField(renderer.GAP+"",4);
        gapSlider = new JSlider(JSlider.HORIZONTAL, -50,250, renderer.GAP);
        scaleLabel = new JLabel("Scaling Factor(%):");
        scaleBox = new JTextField(renderer.sf+"",4);
        scaleButton = new JButton("Enabled: "+renderer.scalingEnabled);
        scaleSlider = new JSlider(JSlider.HORIZONTAL, 0,250, Math.round(renderer.sf*100));


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
                updateGUI(renderer);
            }
        });

        gapSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e){
                renderer.GAP = gapSlider.getValue();
                updateGUI(renderer);
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
                updateGUI(renderer);
            }
        });
        scaleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev){
                renderer.scalingEnabled = !renderer.scalingEnabled;
                updateGUI(renderer);
            }
        });

        scaleSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e){
                renderer.sf = scaleSlider.getValue()/100.0f;
                updateGUI(renderer);
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

    protected void updateGUI(FieldRendererAnimPanel renderer) {
        gapBox.setText(renderer.GAP+"");
        gapSlider.setValue(renderer.GAP);
        scaleBox.setText(renderer.sf+"");
        scaleButton.setText("Enabled: "+renderer.scalingEnabled);
        scaleSlider.setValue(Math.round(renderer.sf*100));
        renderer.makeScaleOp();
    }


    public void close() {
        frame.dispose();
    }
}