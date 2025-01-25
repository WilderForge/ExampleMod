package com.wildermods.examplemod.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.wildermods.examplemod.ExampleMod;
import static com.wildermods.wilderforge.api.mixins.v1.Descriptor.*;
import com.worldwalkergames.legacy.game.model.comics.ComicPanel;

@Mixin(targets = "com.worldwalkergames.legacy.ui.menu.RootMenu$BackgroundInfo")
public class BackgroundInfoMixin {
	
	//This may look a bit complicated, but it's actually pretty simple
	
	@Inject(                                                                             //We are injecting code
		method = "addActorSlot("                                                         //into the method called addActorSlot
				+ "Lcom/worldwalkergames/legacy/game/model/comics/ComicPanel$ActorSlot;" //that takes one parameter, which is an ActorSlot
				+ ")" + VOID,                                                            //that returns VOID
				
		at = @At("HEAD")                                                                 //We want to inject the code at the top of the method
	)
	public void manipulateActorSlot(ComicPanel.ActorSlot actorSlot, CallbackInfo c) {
		if(ExampleMod.getInfo() != null) {
			actorSlot.size = actorSlot.size * ExampleMod.getSettings().characterScaleFactor();
		}
	}
	
	@Inject(                                                                             //We are injecting code                              
		method = "addActorSlot("                                                         //into the method called addActorSlot                
				+ "Lcom/worldwalkergames/legacy/game/model/comics/ComicPanel$ActorSlot;" //in which first parameter is an ActorSlot  
				+ INT                                                                    //in which the second parameter is an int
				+ ")" + VOID,                                                            //that returns VOID                                  
				
		at = @At("HEAD")                                                                 //We want to inject the code at the top of the method
	)
	public void manipulateActorSlot(ComicPanel.ActorSlot actorSlot, int priority, CallbackInfo c) {
		if(ExampleMod.getInfo() != null) {
			actorSlot.size = actorSlot.size * ExampleMod.getSettings().characterScaleFactor();
		}
	}
	
}
