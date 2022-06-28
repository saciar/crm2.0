package crm.client.html.editor;
import java.awt.BorderLayout;
import java.awt.Event;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.InputMap;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.Element;
import javax.swing.text.JTextComponent;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.HTMLWriter;

import org.apache.commons.lang.StringUtils;

public class BasicHTMLEditor extends JPanel {    
	private final static String OPEN_TAG = "<";
	private final static String CLOSE_TAG = "</";
	private final static String DEFAULT_CONTENT = "<ul><li></li></ul>";
	
	
	private JTextPane textPane;
	private HTMLDocument document;
	private HashMap actions;
	private HTMLCaretListener caretListener;
	
    public BasicHTMLEditor(List descriptionLines) {
    	this.setLayout(new BorderLayout());

        this.textPane = new JTextPane();
        this.textPane.setContentType("text/html");
        this.textPane.setCaretPosition(0);
        this.textPane.setMargin(new Insets(5,5,5,5));
        this.document = (HTMLDocument)this.textPane.getDocument();
        this.caretListener = new HTMLCaretListener();

        JScrollPane scrollPane = new JScrollPane(textPane);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        this.add(scrollPane, BorderLayout.CENTER);
               
        createActionTable(textPane);
        addBindings();
        //initDocument();
        
        
        String html = null;
        if(descriptionLines != null && !descriptionLines.isEmpty()){        	
        	html += "<ul>";        	
        	Iterator it = descriptionLines.iterator();
        	while(it.hasNext()){
        		String line = (String)it.next();
        		html += "<li>" + line + "</li>";
        	}        	
        	html += "</ul>";
        	
        }else{
        	html = DEFAULT_CONTENT;
        }
        setHTMLContent(html);
        
        this.textPane.addCaretListener(this.caretListener);
    }

    public BasicHTMLEditor() {
    	this.setLayout(new BorderLayout());

        this.textPane = new JTextPane();
        this.textPane.setContentType("text/html");
        this.textPane.setCaretPosition(0);
        this.textPane.setMargin(new Insets(5,5,5,5));
        this.document = (HTMLDocument)this.textPane.getDocument();
        this.caretListener = new HTMLCaretListener();

        JScrollPane scrollPane = new JScrollPane(textPane);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        this.add(scrollPane, BorderLayout.CENTER);
               
        createActionTable(textPane);
        addBindings();
        //initDocument();
        
        
        
        
        
    }

    public void setDescriptionLines(List descriptionLines){
    	String html = null;
        if(descriptionLines != null && !descriptionLines.isEmpty()){        	
        	html += "<ul>";        	
        	Iterator it = descriptionLines.iterator();
        	while(it.hasNext()){
        		String line = (String)it.next();
        		html += "<li>" + line + "</li>";
        	}        	
        	html += "</ul>";
        	
        }else{
        	html = DEFAULT_CONTENT;
        }
        setHTMLContent(html);
        
        //this.textPane.addCaretListener(this.caretListener);
    }
    
    protected void addBindings() {
        InputMap inputMap = textPane.getInputMap();
        
        KeyStroke key = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0);
        inputMap.put(key, new AddLIAction(textPane,document));
        
        key = KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE,0);
        inputMap.put(key, new DeletePrevCharAction(textPane));    
        
        key = KeyStroke.getKeyStroke(KeyEvent.VK_DELETE,0);
        inputMap.put(key, new DeleteNextCharAction(textPane));
        
        key = KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT,0);
        inputMap.put(key, new ForwardAction(textPane));
        
        key = KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT,Event.CTRL_MASK);
        inputMap.put(key, new NextWordAction(textPane));       
        
        key = KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT,Event.SHIFT_MASK);
        inputMap.put(key, new SelectionForwardAction(textPane)); 
    }


    private void createActionTable(JTextComponent textComponent) {
        actions = new HashMap();
        Action[] actionsArray = textComponent.getActions();
        for (int i = 0; i < actionsArray.length; i++) {
            Action a = actionsArray[i];
            actions.put(a.getValue(Action.NAME), a);
        }
    }

    private Action getActionByName(String name) {
        return (Action)(actions.get(name));
    }
    
    public List getDescriptionLines(){    	
    	return getDescriptionLines(document.getDefaultRootElement(),new ArrayList());
    }
    
    public List getDescriptionLines(Element element,List lines){
    	try {
    		if(element != null){	
    			if(element.getName().equals(HTML.Tag.LI.toString())){				
    				lines.add(new String(document.getText(element.getStartOffset(),element.getEndOffset() - element.getStartOffset())));				
    			}else{
    				int count = element.getElementCount();
    				for(int i = 0;i < count;i ++){
    					Element child = element.getElement(i);
    					getDescriptionLines(child,lines);
    				}
    			}
    		}
    	} catch (BadLocationException e1) {
			e1.printStackTrace();
		}		
    	return lines;
    }    
    /*
    public StringBuffer getHTMLContent(){    	
    	try{
    		StringWriter stringWriter = new StringWriter();
    		HTMLWriter htmlWriter = new HTMLWriter(stringWriter,this.document);
    		htmlWriter.write();
    		
    		return stringWriter.getBuffer();    		
    	}catch(IOException e){
    		System.out.println("IOException: " + e.getMessage());
    	} catch (BadLocationException e) {
			e.printStackTrace();
		}
    	
    	
    	
    	return null;
    }
    */
    public void setHTMLContent(String content){
    	this.caretListener.setIgnoreChanges(true);
		Element e = document.getParagraphElement(0);
		if(e != null){
			int startIndex = content.indexOf(OPEN_TAG + HTML.Tag.UL.toString());
			int endIndex = content.indexOf(CLOSE_TAG + HTML.Tag.UL.toString());
			if(startIndex >= 0 && endIndex >= 0){
				String ULContent = content.substring(startIndex,endIndex);
				try {		
    				document.insertBeforeEnd(e,ULContent);		
    				textPane.setCaretPosition(e.getStartOffset());    				
    			} catch (BadLocationException e1) {
    				e1.printStackTrace();
    			} catch (IOException e1) {
    				e1.printStackTrace();
    			}
    		}    		
    	}
    	/*
    	StringReader stringReader = new StringReader(content);
    	HTMLEditorKit editorkit = new HTMLEditorKit();    	
    	try {
    		this.document.remove(0,this.document.getLength());
			editorkit.read(stringReader,this.document, 0);
		} catch (IOException e) {			
			e.printStackTrace();
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		*/
		this.caretListener.setIgnoreChanges(false);
    }
    
    public Element getElement(HTML.Tag tag){
    	return getElement(tag,this.textPane.getCaretPosition());
    }
    
    public Element getElement(HTML.Tag tag,int position){
    	Element unorderedList = null;
		Element current = this.document.getCharacterElement(position);
		if(current != null){
			unorderedList = current;
			while(unorderedList != null && !unorderedList.getName().equals(tag.toString()) ){
				unorderedList = unorderedList.getParentElement();
			}
		}
		return unorderedList;
    }
    
    public void initDocument(){
    	this.caretListener.setIgnoreChanges(true);
		Element e = document.getParagraphElement(0);
		if(e != null){
			try {								
				document.insertBeforeEnd(e,"<ul><li></li></ul>");				
			} catch (BadLocationException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		this.caretListener.setIgnoreChanges(false);
    }
 


    //--------LISTENER---------------------------------------
    private class HTMLCaretListener implements CaretListener{
    	private boolean ignoreChanges;
    	
    	public HTMLCaretListener(){
    		this.ignoreChanges = false;
    	}
    	
		public void caretUpdate(CaretEvent arg) {
			if(!this.ignoreChanges && getElement(HTML.Tag.UL) == null){
				textPane.setCaretPosition(0);
			}
		}

		public void setIgnoreChanges(boolean ignoreChanges) {
			this.ignoreChanges = ignoreChanges;
		}
		
		
    }
    
    //--------------------------------------------------
    //--------------------------------------------------
    //---------------ACTIONS----------------------------
    //--------------------------------------------------
    //--------------------------------------------------
    private class AddLIAction extends AbstractAction{
    	
    	private JTextPane textPane;
    	private HTMLDocument document;
    	
    	public AddLIAction(JTextPane textPane,HTMLDocument document){
    		this.textPane = textPane;
    		this.document = document;    		
    	}
    	
		public void actionPerformed(ActionEvent arg0) {
			Element e = getElement(HTML.Tag.LI);
			if(e != null){				
				try {
					this.document.insertAfterEnd(e,"<li></li>");
					
					this.textPane.setCaretPosition(e.getEndOffset());
				} catch (BadLocationException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}			
		}
    	
    }
    
    private class DeletePrevCharAction extends AbstractAction{
    	private JTextPane textPane;

    	public DeletePrevCharAction(JTextPane textPane){
    		this.textPane = textPane;	
    	}
    	
		public void actionPerformed(ActionEvent event) {
			Element e = getElement(HTML.Tag.UL);
			if(e != null){				
				boolean canDelete = false;
				if(this.textPane.getCaretPosition() > e.getStartOffset()){
					canDelete = true;
				}else{
					if(textPane.getSelectionStart() >= e.getStartOffset() && textPane.getSelectionEnd() > textPane.getSelectionStart()){
						canDelete = true;	
					}
				}
				if(canDelete){
					getActionByName(DefaultEditorKit.deletePrevCharAction).actionPerformed(event);
				}
			}
			
		}
    	
    }
    
    private class DeleteNextCharAction extends AbstractAction{
    	private JTextPane textPane;

    	public DeleteNextCharAction(JTextPane textPane){
    		this.textPane = textPane;	
    	}
    	
		public void actionPerformed(ActionEvent event) {
			Element e = getElement(HTML.Tag.UL);
			if(e != null){				
				boolean canDelete = false;
				if((this.textPane.getCaretPosition() + 1) < e.getEndOffset()){
					canDelete = true;
				}else{
					if((textPane.getSelectionEnd() + 1) <= e.getEndOffset() && textPane.getSelectionStart() < textPane.getSelectionEnd()){
						canDelete = true;	
					}
				}
				if(canDelete){
					getActionByName(DefaultEditorKit.deleteNextCharAction).actionPerformed(event);
				}
			}
			
		}
    }
    
    private class ForwardAction extends AbstractAction{
    	private JTextPane textPane;

    	public ForwardAction(JTextPane textPane){
    		this.textPane = textPane;	
    	}
    	
		public void actionPerformed(ActionEvent event) {
			Element e = getElement(HTML.Tag.UL);
			if(e != null){				
				if((this.textPane.getCaretPosition() + 1) < e.getEndOffset()){
					getActionByName(DefaultEditorKit.forwardAction).actionPerformed(event);
				}
			}			
		}
    }
    
    private class NextWordAction extends AbstractAction{
    	private JTextPane textPane;

    	public NextWordAction(JTextPane textPane){
    		this.textPane = textPane;	
    	}
    	
		public void actionPerformed(ActionEvent event) {
			Element e = getElement(HTML.Tag.UL);
			if(e != null){				
				if((this.textPane.getCaretPosition() + 1) < e.getEndOffset()){
					getActionByName(DefaultEditorKit.nextWordAction).actionPerformed(event);
				}
			}
			
		}
    }
    
    private class SelectionForwardAction extends AbstractAction{
    	private JTextPane textPane;

    	public SelectionForwardAction(JTextPane textPane){
    		this.textPane = textPane;	
    	}
    	
		public void actionPerformed(ActionEvent event) {
			Element e = getElement(HTML.Tag.UL);
			if(e != null){				
				if((this.textPane.getCaretPosition() + 1) < e.getEndOffset()){
					getActionByName(DefaultEditorKit.selectionForwardAction).actionPerformed(event);
				}
			}
			
		}
    }

	public HTMLDocument getHTMLDocument() {
		return (HTMLDocument)this.textPane.getDocument();
	}

}
