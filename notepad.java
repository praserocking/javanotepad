import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Toolkit.*;
import java.awt.datatransfer.*;
class myframe extends Frame implements ActionListener
{
  TextArea b;
        Clipboard clpbrd=Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection strsel;
	MenuItem open,save,exit,cut,copy,past,al,info;
	TextArea status;
	myframe(String s)
	{
		super(s);
		b=new TextArea();
		add(b);
		winada a=new winada();
		status=new TextArea("Status Bar --* Program's © SHENBAGA PRASANNA,S *-- OPENSOURCED",1,50,TextArea.SCROLLBARS_NONE);
		status.setEditable(false);
		add(status,BorderLayout.SOUTH);
		addWindowListener(a);
		MenuBar m=new MenuBar();
		Menu file=new Menu("File");
		Menu edit=new Menu("Edit");
                Menu help=new Menu("Help");
		al=new MenuItem("New");
		open=new MenuItem("Open");
		save=new MenuItem("Save");
		exit=new MenuItem("Exit");
		cut=new MenuItem("Cut");
		copy=new MenuItem("Copy");
		past=new MenuItem("Paste");
                info=new MenuItem("Information");
		file.add(al);
		file.add(open);
		file.add(save);
		file.add(exit);
		m.add(file);
		edit.add(cut);
		edit.add(copy);
		edit.add(past);
		m.add(edit);
                help.add(info);
                m.add(help);
		setMenuBar(m);
		al.addActionListener(this);
		open.addActionListener(this);
		exit.addActionListener(this);
		save.addActionListener(this);
                copy.addActionListener(this);
                past.addActionListener(this);
                cut.addActionListener(this);
                info.addActionListener(this);
	}
	public void actionPerformed(ActionEvent ae)
	{
		Object a=ae.getSource();
		if(a==al)
		{
			b.setText("");
		}
		if(a==open)
		{
			FileDialog f=new FileDialog(this,"Select file to open...");
			f.setVisible(true);
			String	s=f.getDirectory()+f.getFile();;
			try
			{
			File fin=new File(s);
			FileInputStream fis1 = new FileInputStream(fin);
       		        int fileSize = fis1.available();
       		        byte buf1[] = new byte[fileSize];
       		        fis1.read(buf1);
       		        b.setText(new String(buf1));
			}
			catch(Exception e)
			{
			}
			status.setText("Opened "+s+" ..."+"--* Program's © SHENBAGA PRASANNA,S *--");
		}
		if(a==exit)
		{
			System.exit(1);
		}
		if(a==save)
		{
			FileDialog f=new FileDialog(this,"Select the folder and Enter file name to save..",FileDialog.SAVE);
			f.setVisible(true);
			String s=f.getDirectory()+f.getFile();
			try
			{
			File fin=new File(s);
			FileOutputStream fis1 = new FileOutputStream(fin);
			Writer out = new OutputStreamWriter(fis1, "UTF8");
      		        out.write(b.getText());
      		        out.close();
			}
			catch(Exception e)
			{
			}
			status.setText("Saved @ "+s+" ..."+"--* Program's © SHENBAGA PRASANNA,S *--");
		}
                if(a==copy)
                {
                   strsel=new StringSelection(b.getSelectedText()); 
                   clpbrd.setContents(strsel, strsel);
                }
                if(a==past)
                {
                    try{
                    b.setText(b.getText()+clpbrd.getData(DataFlavor.stringFlavor));
                    }catch(Exception e){}
                }
                if(a==cut)
                {
                   strsel=new StringSelection(b.getSelectedText()); 
                   clpbrd.setContents(strsel, strsel);
                   int strt,end;
                   strt=b.getSelectionStart();
                   end=b.getSelectionEnd();
                   b.setText(b.getText().substring(0,strt)+b.getText().substring(end,b.getText().length()));
                }
                if(a==info)
                {
                    infobox a2=new infobox("Information About Notepad");
                    a2.setBounds(425,200,500,300);
                    a2.setVisible(true);
                }
	}
}
class mains
{
	public static void main(String[] arg)
	{
		myframe a=new myframe("Notepad");
		a.setSize(1366,754);
		a.setVisible(true);
	}
}
class winada extends WindowAdapter
{
	public void windowClosing(WindowEvent e)
	{
		System.exit(1);
	}
}
class infobox extends Frame implements ActionListener,WindowListener
{
    Button b=new Button("Close");
    infobox(String s)
    {
                super(s);
                TextArea a3=new TextArea("\n\n\n\t\t    This Program is Developed and Maintained by \n\t\t      Shenbaga Prasanna.S,SASTRA University.\n\n\t\t      This is a basic notepad application.\n\t\t       using Java©ORACLE frames\n\t\t      Source Opensourced by the developer.\n\n\t\thttp://www.github.com/praserocking/notepad",20,20,TextArea.SCROLLBARS_NONE);
                a3.setEditable(false);
                a3.setBackground(new Color(180,150,170));
                a3.setForeground(new Color(100,50,25));
                add(a3,BorderLayout.CENTER);
                add(b,BorderLayout.SOUTH);
                b.addActionListener(this);
                addWindowListener(this);
    }
    public void actionPerformed(ActionEvent ae)
    {
        Object t=ae.getSource();
        if(t==b)
        {
            this.setVisible(false);
        }
    }
    @Override
    public void windowClosing(WindowEvent e)
    {
        this.setVisible(false);
    }
    @Override
    public void windowOpened(WindowEvent e){}
    @Override
    public void windowActivated(WindowEvent e){}
    public void windowClosed(WindowEvent e){}
    public void windowIconified(WindowEvent e){}
    public void windowDeiconified(WindowEvent e){}
    public void windowDeactivated(WindowEvent e){}
}
