
package askisi2home9;

//Tsakalidou Elina
//A.M. 321/2008144
//Askisi2 Home9
//oi klaseis poy xreiazomai
import java.awt.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;

public class SimpleNotepad extends JFrame implements ActionListener{
//implements shmainei oti prei na kanei kai overdide tis synarthseis
	//oi metablhtes poy orizw edw leitoyrgoyn ws apo8hkes
	private String txtf = " ";
	private String txtc = " ";
        //gia ta onomata twn 2 "etiketwn"
        private JLabel FilePath;
        private JLabel msgbox;
        //gia ta periexomeno twn pediwn
        private JTextField tf1;
        private JTextField tf2;
        //gia thn kenh perioxh opou tha emfanizetai h anagrafomenh shmeiwsh
        private JTextArea ta;
        //to scroll to bazw gia na mporw grapsw kai se magalh seira kai se megalh sthlh
        private JScrollPane txte;
        //apothkeyw ta koumpia
        private JButton crb ;//create
	private JButton opb ;//open
	private JButton delb ;//delete
	private JButton clb;//clean
//constructor
        public SimpleNotepad(){
            //titlos
            super("Notepad");
            setLayout(null);
           //orizw to FilePath poy tha
         FilePath = new JLabel("Filename/Path:");
	 msgbox = new JLabel("Message Box:");

	 tf1 = new JTextField();//den pernei parametrous , orizw egw to path
	 tf2 = new JTextField("No messages!");//bazw ws default to No messages! opws zhthtai

	 ta = new JTextArea();
	//pernei ws parametro to ta
	txte = new JScrollPane(ta);
	//dhmioyrgv ta koumpia poy tha ekteloyn tis antistoixes energies
	crb = new JButton("Create");
	opb = new JButton("Open");
	delb = new JButton("Delete");
	clb = new JButton("Clear");


//h 1h parametros mas leei apo poio shmeioy toy parathyroy tha ksekinaei to create
// dhl orizei ton aksona x(aristera-deksia)
// h 2h parametros einai san na orizei ton aksona y(panw-katw)
//h 3h einai to mhkos to koumpioy
//h 4h einai to platos toy koumpioy
//dokimazontas arithmoys briskoyme toys katallhloys
         crb.setBounds(110, 3, 90, 20);
         opb.setBounds(210, 3, 90, 20);
         delb.setBounds(310, 3, 90, 20);
         FilePath.setBounds(5,38 , 200, 25);
         tf1.setBounds(110, 38, 291, 20);
         ta.setLayout(new BorderLayout());
//to epomeno bhma  einai gia tis grammes pou yparxoyn anamesa sto 
//pedio toy keimenoy.
		Border border1 = BorderFactory.createLineBorder(getBackground(), 15);
		Border border2 = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		Border border3 = BorderFactory.createCompoundBorder(border1, border2);
		ta.setBorder(border3);
                txte.setBounds(3, 64, 400, 200);
		msgbox.setBounds(5, 280,120, 20);
		tf2.setBounds(120, 280, 180, 20);
		tf2.setEditable(false);//
		clb.setBounds(321, 279, 80, 20);
		add(crb);
		add(opb);
		add(delb);
		add(FilePath);
		add(tf1);
		add(txte);
		add(msgbox);
		add(tf2);
		add(clb);
		

		////twra doulevv me to ActionListener
		crb.addActionListener(this);
		opb.addActionListener(this);
		delb.addActionListener(this);
		clb.addActionListener(this);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setSize(400,500);
	}//end of class
//orizoume th synarthsh actionPerformed apo thn klasshActionListener
	public void actionPerformed(ActionEvent e) {
		//elements for create open or write files.
		BufferedReader in = null;
		BufferedWriter out = null;
		//Array List used to save file(open for read) line by line in this example.
		ArrayList<String> txtfile = new ArrayList<String>();
		//file path for deleting file.
		

                //edw eksetazw tis energeies toy kathe koumpiou kai tadedomaena pou tah //emfanzontai
		if(e.getSource() == crb){
			txtf = tf1.getText();
		try{
		out = new BufferedWriter( new FileWriter(txtf + ".txt") );
	        in = new BufferedReader(new StringReader(ta.getText()));
		if( out != null  ){
                    tf2.setText("We created a file");
		while( (txtc = in.readLine()) !=null){
		out.write(txtc, 0, txtc.length());
		
		out.newLine();
		out.flush();
					}
				}
			}catch(Exception ex){
			tf2.setText(ex.getMessage());
			}finally{
				try {
					in.close();
					out.close();
				} catch (Exception e1) {
					
					tf2.setText(e1.getMessage());
				}
			}
		}
		if(e.getSource() == opb){
			txtf = tf1.getText();
			try{
				in = new BufferedReader( new FileReader( txtf + ".txt" ) );
				while((txtc = in.readLine()) != null){
					txtfile.add(txtc);
				}
				txtc = "";
				for(int i=0; i<txtfile.size(); i++){
					txtc += txtfile.get(i);
					txtc += "\n";
				}
				ta.setText(txtc);
				tf2.setText("We opened the file.");
			}catch(Exception ex){
				
				tf2.setText(ex.getMessage());
			}finally{
				try {
					in.close();
				} catch (Exception e1) {
					
					tf2.setText(e1.getMessage());
				}
			}

		}
		File name;
		if(e.getSource() == delb){
			txtf = tf1.getText();

			try{
				name = new File(txtf + ".txt");
				boolean txtDeleted = name.delete();
				if(!txtDeleted){
					tf2.setText("Error.");
				}else{
					tf2.setText("We deleted the file");
				}
			}catch(Exception ex){
				
				tf2.setText(ex.getMessage());
			}
		}
		
		if(e.getSource() == clb){
			in = null;
			out = null;
			txtfile.clear();
			txtf = " ";
			txtc = " ";
			ta.setText(" ");
			tf1.setText(" ");
			tf2.setText("No messages!");
		}
	}
}

