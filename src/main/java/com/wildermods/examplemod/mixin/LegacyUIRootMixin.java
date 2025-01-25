package com.wildermods.examplemod.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import com.badlogic.gdx.scenes.scene2d.ui.RuntimeSkin;
import com.wildermods.examplemod.Reloadable;
import com.worldwalkergames.legacy.ui.LegacyUIRoot;

@Mixin(LegacyUIRoot.class)
public class LegacyUIRootMixin implements Reloadable {

	protected @Shadow RuntimeSkin uiSkin; //@Shadow allows us to access the uiSkin field from LegacyUIRoot
	
	@Override
	public @Unique void reload() {
		uiSkin.skinChanged.dispatch(); //simply tell the skin to send an update to reload the screen
	}
	
}
