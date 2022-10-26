
package graphics.cells;


import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;

public abstract class AbstractCellView extends JTextPane implements CellView {
    protected int id, parentSize, level;

    public AbstractCellView(int id, int parentSize, int level){
        super();
        this.setText("");
        this.parentSize = parentSize;
        this.level = level;
        this.setEditable(false);
        int size = parentSize / level;
        this.id = id;
        this.setSize(new Dimension(size, size));
        this.setFont(new Font("Verdana", Font.PLAIN, size/3));
        this.setBorder(BorderFactory.createDashedBorder(Color.gray));
        StyledDocument doc = this.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
    }

    public int getId(){
        return id;
    }

    @Override
    public String toString(){
        return getValue();
    }

}
