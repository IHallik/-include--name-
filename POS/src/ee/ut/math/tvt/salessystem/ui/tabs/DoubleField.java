package ee.ut.math.tvt.salessystem.ui.tabs;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

/**
 * A JTextField that accepts only integers and '.'
 *
 * @author David Buzatto
 * moded by Innar Hallik
 */
public class DoubleField extends JTextField {

    public DoubleField() {
        super();
    }

    public DoubleField( int cols ) {
        super( cols );
    }

    @Override
    protected Document createDefaultModel() {
        return new UpperCaseDocument();
    }

    static class UpperCaseDocument extends PlainDocument {

        @Override
        public void insertString( int offs, String str, AttributeSet a )
                throws BadLocationException {

            if ( str == null ) {
                return;
            }

            char[] chars = str.toCharArray();
            boolean ok = true;

            for ( int i = 0; i < chars.length; i++ ) {

                try {
                	if (!( String.valueOf( chars[i] ).equals("."))){
                		Integer.parseInt( String.valueOf( chars[i] ) );
                	}
                } catch ( NumberFormatException exc ) {
                    ok = false;
                    break;
                }


            }

            if ( ok )
                super.insertString( offs, new String( chars ), a );

        }
    }

}