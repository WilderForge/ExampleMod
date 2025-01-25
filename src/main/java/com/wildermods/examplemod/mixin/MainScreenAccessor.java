package com.wildermods.examplemod.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import com.worldwalkergames.legacy.ui.MainScreen;
import com.worldwalkergames.legacy.ui.titlescreen.ITopLevelScreen;

@Mixin(MainScreen.class)
public interface MainScreenAccessor {

	public @Accessor ITopLevelScreen getContent();
	
}
