import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ipguistarter {
    String portString = "5433";
    String ipaddress = "localhost";
    String ipaddressF = "localhost";
    int portF = 0;
    // static String ipaddress = "localhost";
    // static int port = 0;
    volatile public boolean connectionReady = false;

    // public static void main(String[] args) {
    // new Ipguistarter();
    // }
    JFrame frame;
    public Ipguistarter(String title) {

        frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JLabel ipAddressLabel = new JLabel("Server IP Address:");
        JTextField ipAddressTextField = new JTextField(15);
        JLabel portLabel = new JLabel("Port Number:");
        JTextField portTextField = new JTextField(5);
        JButton confirmButton = new JButton("Confirm");

        ipAddressTextField.setText(ipaddress);
        portTextField.setText(portString);

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ipaddressF = ipAddressTextField.getText();
                portF = Integer.parseInt(portTextField.getText());
                connectionReady = true;
                // JOptionPane.showMessageDialog(frame,
                // "Entered IP Address: " + ipAddress + "\nEntered Port Number: " + port);
            }
        });

        panel.add(ipAddressLabel);
        panel.add(ipAddressTextField);
        panel.add(portLabel);
        panel.add(portTextField);
        panel.add(confirmButton);

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
        frame.setAlwaysOnTop(true);
    }

    public String getipaddress() {
        return ipaddressF;
    }

    public int getport() {
        return portF;
    }



    public void close() {
        frame.dispose();
    }
}