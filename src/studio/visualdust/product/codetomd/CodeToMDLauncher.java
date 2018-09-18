package studio.visualdust.product.codetomd;

import studio.visualdust.product.codetomd.gui.MainFrame;
import studio.visualdust.product.codetomd.method.EventRW;
import studio.visualdust.product.codetomd.resource.Resource;

import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
import java.awt.*;

public class CodeToMDLauncher {
    public static void main(String[] args) {
        new Resource();
        try {
            UIManager.setLookAndFeel(new MetalLookAndFeel());
        } catch (Exception e) {
            EventRW.Write(e);
        }
        ResetFonts();
        new MainFrame();
        EventRW.Write("CodeToMD launched");
    }

    public static Font defaultFont = new Font("微软雅黑", 0, 17);

    public static void ResetFonts() {

        String names[] = {"Label", "CheckBox", "PopupMenu", "MenuItem", "CheckBoxMenuItem",
                "JRadioButtonMenuItem", "ComboBox", "Button", "Tree", "ScrollPane",
                "TabbedPane", "EditorPane", "TitledBorder", "Menu", "TextArea",
                "OptionPane", "MenuBar", "ToolBar", "ToggleButton", "ToolTip",
                "ProgressBar", "TableHeader", "Panel", "List", "ColorChooser",
                "PasswordField", "TextField", "Table", "Label", "Viewport",
                "RadioButtonMenuItem", "RadioButton", "DesktopPane", "InternalFrame"
        };
        for (String item : names) {
            UIManager.put(item + ".font", defaultFont);
        }
    }
}
