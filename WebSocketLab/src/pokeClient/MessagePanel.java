package pokeClient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;
import javax.websocket.EncodeException;
import javax.websocket.Session;

import wsMessages.PokeMessage;
import wsMessages.ProdMessage;
import wsMessages.PickleMessage;


public class MessagePanel extends JPanel implements ActionListener {
	
	private JTextField id;
	private JButton poke;
	private JButton prod;
	private JButton pickle;
	Session session;
	
	
	JTextArea messageArea;
	private String myId;
	
	public MessagePanel(Session session, JTextField id,
			JButton poke, JButton prod, JButton pickle) {
		this.session = session;
		this.id = id;
		this.poke = poke;
		this.prod = prod;
		this.pickle = pickle;
		myId = id.getText();	
		
		id.addActionListener(this);
		poke.addActionListener(this);
		prod.addActionListener(this);
		pickle.addActionListener(this);
		

		messageArea = new JTextArea(10, 40);
		JScrollPane scroller = new JScrollPane(messageArea);
		this.add(scroller);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == id)
			myId = id.getText();
		else if (e.getSource() == poke) {
			doPoke();
		}
		else if (e.getSource() == prod) {
			doProd();
		}
		else if (e.getSource() == pickle) {
			doPickle();
		}

	}

	private void doPickle() {
		PickleMessage pokeMsg = new PickleMessage(myId);
		try {
			session.getBasicRemote().sendObject(pokeMsg);
		} catch (IOException | EncodeException e) {
			System.err.println("Problem with sending a pickle-message.");
		}
		
	}

	private void doPoke() {
		PokeMessage pokeMsg = new PokeMessage(myId);
		try {
			session.getBasicRemote().sendObject(pokeMsg);
		} catch (IOException | EncodeException e) {
			System.err.println("Problem with sending a poke-message.");
		}
		
	}

	private void doProd() {
		ProdMessage prodMsg = new ProdMessage(myId);
		try {
			session.getBasicRemote().sendObject(prodMsg);
		} catch (IOException | EncodeException e) {
			System.err.println("Problem with sending a poke-message.");
		}
		
		
	}

	public void receivePoke(PokeMessage pokeMsg) {
		messageArea.append(pokeMsg.getID() + " poked.\n");
		
	}
	
	public void receiveProd(ProdMessage prodMsg) {
		messageArea.append(prodMsg.getID() + " prodded.\n");
		
	}
	
	public void receivePickle(PickleMessage pickleMsg) {
		messageArea.append(pickleMsg.getID() + "...pickled.\n");
		
	}

}
