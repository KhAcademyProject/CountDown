package countDown.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.TextField;

import javax.swing.JDialog;
import javax.swing.JLabel;

public class LoginFrame extends JDialog {

	TextField smtpServer, mailFrom;
	GridBagLayout gbl;
	GridBagConstraints gbc;

	public LoginFrame() {
		this.setPreferredSize(new Dimension(500,800));
		
		smtpServer = new TextField(40);
		smtpServer.setPreferredSize(new Dimension(400, 20));
		mailFrom = new TextField(40);

		gbl = new GridBagLayout();
		gbc = new GridBagConstraints();

		setLayout(gbl);
		gbc.fill = GridBagConstraints.BOTH;

		gbc.weightx = 2.0;
		gbc.weighty = 2.0;

		add(new JLabel("메일서버: ", JLabel.RIGHT), 0, 0, 1, 1);

		add(smtpServer, 1, 0, 1, 1);

		add(new JLabel("From: ", JLabel.RIGHT), 0, 1, 1, 1);

		add(mailFrom, 1, 1, 3, 1);

		pack();
	}

	private void add(Component c, int x, int y, int w, int h) {

		gbc.gridx = x;

		gbc.gridy = y;

		gbc.gridwidth = w;

		gbc.gridheight = h;

		gbl.setConstraints(c, gbc);

		add(c);

	}
}