import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.*;

public  class SimpleTextEditor implements ActionListener {
    JFrame frame;
    JTextArea textarea;
    JMenuBar menubar;
    JMenu file;
    JMenu edit;
    JMenu close;
    JMenuItem newfile;
    JMenuItem openfile;
    JMenuItem savefile;
    JMenuItem copy;
    JMenuItem paste;
    JMenuItem cut;
    JMenuItem printfile;
    JMenuItem closefile;
    SimpleTextEditor()
    {
        //creating the frame
        frame=new JFrame("SimpleTextEditor");
        //set the boundry of frame
        frame.setBounds(0,0,600,600);
        //Initialising the text area
        textarea=new JTextArea("welcome to editor");

        //creating Menubar
        menubar=new JMenuBar();
        //creating menubar Items
        file=new JMenu("File");
        edit=new JMenu("Edit");
        close=new JMenu("Close");
        //adding all items in to menubar
        menubar.add(file);
        menubar.add(edit);
        menubar.add(close);

        //creating MenuItems in FILE
        newfile=new JMenuItem("New");
        newfile.addActionListener(this);
        openfile=new JMenuItem("Open");
        openfile.addActionListener(this);
        savefile=new JMenuItem("Save");
        savefile.addActionListener(this);
        file.add(newfile);
        file.add(openfile);
        file.add(savefile);

        //creating MenuItems in EDIT
        copy=new JMenuItem("Copy");
        copy.addActionListener(this);
        paste=new JMenuItem("Paste");
        paste.addActionListener(this);
        cut=new JMenuItem("Cut");
        cut.addActionListener(this);
        printfile=new JMenuItem("Print");
        printfile.addActionListener(this);
        edit.add(copy);
        edit.add(paste);
        edit.add(cut);
        edit.add(printfile);

        //creating MenuItems in CLOSE
        closefile=new JMenuItem("Close All");
        closefile.addActionListener(this);
        close.add(closefile);


        frame.setJMenuBar(menubar);//set the menubar
        frame.add(textarea);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//it will close the file
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        SimpleTextEditor editor = new SimpleTextEditor();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if(s.equals("copy")){
            textarea.copy();
        }
        else if(s.equals("Paste")){
            textarea.paste();
        }
        else if(s.equals("Cut")){
            textarea.cut();
        }
        else if(s.equals("Print")){
            try {
                textarea.print();
            } catch (PrinterException ex) {
                throw new RuntimeException(ex);
            }
        }
        else if(s.equals("New")){
            textarea.setText("");
        }
        else if(s.equals("Open")){
            JFileChooser filechooser=new JFileChooser("C:");
            int ans=filechooser.showOpenDialog(null);
            if(ans==JFileChooser.APPROVE_OPTION){
                File file=new File(filechooser.getSelectedFile().getAbsolutePath());
                String s1="",s2="";
                try{
                    BufferedReader bufferedReader=new BufferedReader((new FileReader(file)));
                    s2=bufferedReader.readLine();
                    while((s1=bufferedReader.readLine())!=null)
                    {
                        s2+=s1+"\n";
                    }
                    textarea.setText(s2);
                }catch(FileNotFoundException ex){
                    throw new RuntimeException(ex);
                }catch(IOException ex){
                    throw new RuntimeException(ex);
                }
            }
        }
        else if(s.equals("Save"))
        {
            JFileChooser fileChooser=new JFileChooser("c:");
            int ans=fileChooser.showOpenDialog(null);
            if(ans==JFileChooser.APPROVE_OPTION)
            {
                File file=new File(fileChooser.getSelectedFile().getAbsolutePath());
                BufferedWriter writer=null;
                try {
                    writer=new BufferedWriter(new FileWriter(file,false));
                    writer.write((textarea.getText()));
                    writer.flush();
                    writer.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }
}
