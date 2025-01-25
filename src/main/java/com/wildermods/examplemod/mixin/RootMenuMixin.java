package com.wildermods.examplemod.mixin;

import org.spongepowered.asm.mixin.Mixin;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;

import com.wildermods.examplemod.ExampleMod;
import com.wildermods.wilderforge.api.uiV1.TextureFilterDrawable;

@Mixin(targets = "com.worldwalkergames.legacy.ui.menu.RootMenu")
public class RootMenuMixin extends WidgetGroup {
	
	@Inject(               //We are injecting code
		method = "build",  //in the method called 'build'
		at = @At("TAIL")   //at the very end of the method
	)
	private void onBuild(CallbackInfo c) {
		if(ExampleMod.getSettings().showIcon()) {
			FileHandle imageFile = Gdx.files.internal("assets/" + ExampleMod.MOD_ID + "/icon.png");
			Image exampleImage = new Image(new TextureFilterDrawable(imageFile.path(), ExampleMod.MOD_ID, TextureFilter.Nearest));
			this.addActor(exampleImage);
		}
	}
	
}
