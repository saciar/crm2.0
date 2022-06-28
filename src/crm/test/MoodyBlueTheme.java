package crm.test;

import java.awt.Color;

import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.metal.DefaultMetalTheme;
import javax.swing.plaf.metal.MetalTheme;

public class MoodyBlueTheme extends MetalTheme
{
    public String getName() { return "Moody Blues"; }
    
          // blue shades
    private final ColorUIResource primary1     = 
           new ColorUIResource(153,153,153);
    private final ColorUIResource primary2     = 
           new ColorUIResource(175,175,175);
    private final ColorUIResource primary3     = 
           new ColorUIResource(102,102,102); 
    
    private final ColorUIResource secondary1   = 
           new ColorUIResource(0, 0, 0);
    private final ColorUIResource secondary2   = 
            new ColorUIResource(0x9F, 0x9F, 0x9F);
    private final ColorUIResource secondary3   = 
           new ColorUIResource(0x1f, 0x7f, 0xDC);
    
    private final FontUIResource font1  = 
    	   new FontUIResource(new java.awt.Font("Tahoma", 1, 11));
          // the functions overridden from the base 
          // class => DefaultMetalTheme
    
    protected ColorUIResource getPrimary1() { return primary1; }  
    protected ColorUIResource getPrimary2() { return primary2; } 
    protected ColorUIResource getPrimary3() { return primary3; }
	@Override
	protected ColorUIResource getSecondary1() {
		// TODO Auto-generated method stub
		return super.getPrimaryControl();
	}
	@Override
	protected ColorUIResource getSecondary2() {
		// TODO Auto-generated method stub
		return super.getPrimaryControl();
	}
	@Override
	protected ColorUIResource getSecondary3() {
		// TODO Auto-generated method stub
		return super.getPrimaryControl();
	}
	@Override
	public FontUIResource getControlTextFont() {
		// TODO Auto-generated method stub
		return font1;
	}
	@Override
	public FontUIResource getSystemTextFont() {
		// TODO Auto-generated method stub
		return font1;
	}
	@Override
	public FontUIResource getUserTextFont() {
		// TODO Auto-generated method stub
		return font1;
	}
	@Override
	public FontUIResource getMenuTextFont() {
		// TODO Auto-generated method stub
		return font1;
	}
	@Override
	public FontUIResource getWindowTitleFont() {
		// TODO Auto-generated method stub
		return font1;
	}
	@Override
	public FontUIResource getSubTextFont() {
		// TODO Auto-generated method stub
		return font1;
	} 

    //protected ColorUIResource getSecondary1() { return secondary1; }
   //protected ColorUIResource getSecondary2() { return secondary2; }
   // protected ColorUIResource getSecondary3() { return secondary3; }
}
