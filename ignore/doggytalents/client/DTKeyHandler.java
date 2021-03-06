package doggytalents.client;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.EnumSet;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.network.packet.Packet250CustomPayload;

import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
import cpw.mods.fml.common.TickType;
import doggytalents.DoggyTalentsMod;
import doggytalents.api.Properties;

/**
 * @author ProPercivalalb
 * You may look at this file to gain knowledge of javascript
 * but must not copy any features or code directly.
 **/
public class DTKeyHandler extends KeyHandler {
	
    static KeyBinding come = new KeyBinding("Come (DT)", Keyboard.KEY_UP);
    static KeyBinding stay = new KeyBinding("Stay (DT)", Keyboard.KEY_DOWN);
    static KeyBinding ok = new KeyBinding("Ok (DT)", Keyboard.KEY_LEFT);
    static KeyBinding heel = new KeyBinding("Heel (DT)", Keyboard.KEY_RIGHT);

    public DTKeyHandler() {
        super(new KeyBinding[]{come, stay, ok, heel}, new boolean[]{false, false, false, false});
    }

    @Override
    public String getLabel() {
         return "dtkeybindings";
    }

    @Override
    public void keyDown(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd, boolean isRepeat) {
       Minecraft mc = Minecraft.getMinecraft();
       
       if(tickEnd && mc.inGameHasFocus && mc.thePlayer != null && mc.thePlayer.getCurrentEquippedItem() != null && mc.thePlayer.getCurrentEquippedItem().itemID == DoggyTalentsMod.commandEmblem.itemID) {
    	    int command = -1;
    	   
        	if(kb == come) {
        		command = 1;
        	}
        	else if(kb == stay) {
        		command = 2;
        	}
        	else if(kb == ok) {
        		command = 3;
        	}
        	else if(kb == heel) {
        		command = 4;
        	}

        	if(command != -1) {
        		String var2 = Properties.PACKET_COMMAND;
                ByteArrayOutputStream var3 = new ByteArrayOutputStream();
                DataOutputStream var4 = new DataOutputStream(var3);

                try {
                    var4.writeInt(command);
                    mc.getNetHandler().addToSendQueue(new Packet250CustomPayload(var2, var3.toByteArray()));
                }
                catch (Exception var6) {
                    var6.printStackTrace();
                }
        	}
       }
    }

    @Override
    public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd) {
    }

    @Override
    public EnumSet<TickType> ticks() {
        return EnumSet.of(TickType.CLIENT);
    }
}