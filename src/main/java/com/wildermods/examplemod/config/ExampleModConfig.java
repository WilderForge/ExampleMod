package com.wildermods.examplemod.config;

import com.wildermods.examplemod.ExampleMod;
import com.wildermods.wilderforge.api.modLoadingV1.config.Config;
import com.wildermods.wilderforge.api.modLoadingV1.config.ConfigEntry;
import com.wildermods.wilderforge.api.modLoadingV1.config.ConfigEntry.Range;

import static com.wildermods.wilderforge.api.modLoadingV1.config.ConfigEntry.GUI.*;

/**
 * Wilderforge has a very basic mod configuration mechanism.
 * 
 * See {@link Config} and {@link ConfigEntry} for more information
 * 
 * The configuration can be changed in-game by clicking on [Coremods] in the main menu
 * Clicking on this mod, and changing the values.
 */

@Config(modid = ExampleMod.MOD_ID)
public class ExampleModConfig {
	
	@Localized(
		nameLocalizer = "examplemod.config.scale.character",
		tooltipLocalizer = "examplemod.config.scale.character.tooltip"
	)
	@Range(minDecimal = 0.1f, maxDecimal = 10f)
	private float characterScaleFactor = 2f;
	
	@Localized(
		nameLocalizer = "examplemod.config.menu.main.showIcon",
		tooltipLocalizer = "examplemod.config.menu.main.showIcon.tooltip"
	)
	private boolean showIcon = true;
	
	public ExampleModSettings deriveSettings() {
		return new ExampleModSettings(characterScaleFactor, showIcon);
	}
	
}
