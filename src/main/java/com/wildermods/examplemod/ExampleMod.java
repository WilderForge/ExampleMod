package com.wildermods.examplemod;

import com.wildermods.wilderforge.api.eventV1.bus.SubscribeEvent;
import com.wildermods.wilderforge.api.mixins.v1.Cast;
import com.wildermods.wilderforge.api.modLoadingV1.CoremodInfo;
import com.wildermods.wilderforge.api.modLoadingV1.MissingCoremod;
import com.wildermods.wilderforge.api.modLoadingV1.Mod;
import com.wildermods.wilderforge.api.modLoadingV1.config.ConfigSavedEvent;
import com.wildermods.wilderforge.api.modLoadingV1.event.PreInitializationEvent;
import com.wildermods.wilderforge.launch.coremods.Coremods;
import com.wildermods.wilderforge.launch.logging.Logger;
import com.wildermods.wilderforge.mixins.LegacyDesktopAccessor;
import com.worldwalkergames.legacy.ui.menu.RootMenuScreen;

import static com.wildermods.examplemod.ExampleMod.MOD_ID;
import static com.wildermods.examplemod.ExampleMod.MOD_VERSION;

import com.wildermods.examplemod.config.ExampleModConfig;
import com.wildermods.examplemod.config.ExampleModSettings;
import com.wildermods.examplemod.mixin.MainScreenAccessor;

/**
 * This example mod does three things:
 * 
 * 1.
 */
@Mod(modid = MOD_ID, version = MOD_VERSION)
public class ExampleMod extends Throwable {
	
	public static final String MOD_ID = "examplemod"; //If you change this value, be sure to also change it in src/main/resources/fabric.mod.json
	public static final String MOD_VERSION = "@modVersion@"; //the mod version is applied to the code at compile time. You can change the version of your mod by editing gradle.properties in the project's root directory
	public static final String BUILD_INFO = "@builtBy@".equals("@" + "builtBy" + "@") ? "DEVELOPMENT VERSION" : "built by @builtBy@ on @buildDate@";
	
	public static final Logger LOGGER = new Logger(ExampleMod.class);
	private static LegacyDesktopAccessor mainApp;
	private static CoremodInfo coremodInfo;
	
	private ExampleMod(){};
	
	@SubscribeEvent
	public static void onPreInit(PreInitializationEvent e) {
		LOGGER.info("Starting Preinit Sequence...");
		LOGGER.info(MOD_ID + " " + BUILD_INFO);
		coremodInfo = Coremods.getCoremod(MOD_ID);
		mainApp = e.getMainApplicationAccessor();
		if(coremodInfo instanceof MissingCoremod) {
			throw new AssertionError("Coremod " + MOD_ID + " was not found?!");
		}
		LOGGER.info("PreInit Sequence Complete!");
	}
	
	@SubscribeEvent
	public static void onConfigChanged(ConfigSavedEvent e) { 
		//We want the main screen to update if the config for our example mod changes
		
		if(e.getCoremod().equals(coremodInfo)) { //if the config that is being changed is for this mod
			MainScreenAccessor screen = Cast.from(mainApp.getMainScreen());
			if(screen.getContent() instanceof RootMenuScreen) { //if the current screen is the main menu, then we want to reload the main menu.
				Cast.as(screen, Reloadable.class).reload(); //See LegacyUIRootMixin for implementation. RootMenuScreen is a LegacyUIRoot
			}
		}
	}
	
	public static CoremodInfo getInfo() {
		return coremodInfo;
	}
	
	public static ExampleModSettings getSettings() {
		ExampleModConfig config = Cast.from(coremodInfo.getConfig());
		return config.deriveSettings();
	}
	
}
