package ru.fals3r.functions.impl.render;

import net.minecraft.client.gui.GuiScreen;
import ru.fals3r.clickgui.ClickGUI;
import ru.fals3r.functions.Category;
import ru.fals3r.functions.Function;

public class ClickGui extends Function {
   public static GuiScreen ClickGuiScreen;

   public ClickGui() {
      this.registerName("ClickGui");
      this.registerBind(54);
      this.registerCategory(Category.Render);
   }

   public void onEnable() {
      if (ClickGuiScreen == null) {
         ClickGuiScreen = new ClickGUI();
      }

      this.mc.displayGuiScreen(ClickGuiScreen);
      this.toggle(false);
   }
}