package doggytalents.handlers;

import doggytalents.DoggyTalentsMod;
import doggytalents.entity.EntityDTDoggy;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.EntityInteractEvent;

/**
 * @author ProPercivalalb
 * You may look at this file to gain knowledge of javascript
 * but must not copy any features or code directly.
 **/
public class EventRightClickEntity {
	
	@ForgeSubscribe
	public void rightClickEntity(EntityInteractEvent var1) {
		 if(var1.target instanceof EntityWolf && var1.entityPlayer.getCurrentEquippedItem() != null && var1.entityPlayer.getCurrentEquippedItem().itemID == DoggyTalentsMod.trainingTreat.itemID)
		 {
			 EntityWolf wolf = (EntityWolf)var1.target;
			 if(wolf.isTamed() && wolf.getOwnerName() == var1.entityPlayer.username)
			 {
				var1.target.setDead();
			 	World worldObj = var1.entityPlayer.worldObj;
			 	EntityDTDoggy dog = new EntityDTDoggy(worldObj);
			 	dog.setTamed(true);
			 	dog.setOwner(var1.entityPlayer.username);
			 	dog.setSitting(false);
			 	dog.setEntityHealth(dog.getMaxHealth());
			 	dog.setPositionAndRotation(var1.target.posX, var1.target.posY, var1.target.posZ, var1.target.rotationYaw, var1.target.rotationPitch);
			 	if(!var1.target.worldObj.isRemote)
			 	{
			 		worldObj.spawnEntityInWorld(dog);
			 	}
			 }
		 }
	}
}
