package doggytalents.core.addon.forestry;

import java.util.Locale;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import doggytalents.api.DogBedManager;
import doggytalents.core.addon.AddonEvent;

/**
 * @author ProPercivalalb
 */
public class ForestryAddon {

	private static ForestryAPI API = new ForestryAPI(ForestryLib.MOD_NAME);
	
	@SubscribeEvent
	public void onPre(AddonEvent.Pre event) {
		if(!Loader.isModLoaded(ForestryLib.MOD_NAME))
			return;
	}
	
	@SubscribeEvent
	public void onInit(AddonEvent.Init event) {
		if(!Loader.isModLoaded(ForestryLib.MOD_NAME))
			return;
	}

	@SubscribeEvent
	public void onPost(AddonEvent.Post event) throws Exception {
		if(!Loader.isModLoaded(ForestryLib.MOD_NAME))
			return;
		
		for(int i = 0; i < ForestryLib.PLANKS_1_COUNT; ++i)
			DogBedManager.registerBedWood(ForestryLib.PLANKS_1_ID, i);
		for(int i = 0; i < ForestryLib.PLANKS_2_COUNT; ++i)
			DogBedManager.registerBedWood(ForestryLib.PLANKS_2_ID, i);
		
	}
}
