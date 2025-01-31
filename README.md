# ExampleMod

This repository is an example for a basic coremod for the game Wildermyth. It adds a UI element to the main menu and changes the size of the characters in the main menu. It also adds a configuration to WilderForge's coremod menu which can toggle the UI element and change the scale of the characters in the main menu.

Image of the example mod in use:
![image](https://github.com/user-attachments/assets/8f65d998-3284-40d3-84a4-a789886e0cc6)


## Making your Mod

### Prerequisites

In order to build your mod, you must have the following software installed:

* Git
* Java 17 or later
* Wildermyth

__Note:__ This project uses WilderWorkspace to configure itself for IDEs. Currently, WilderWorkspace only supports the Eclipse IDE. Pull Requests to add support for additional IDEs are welcome, and can be submitted at the [WilderWorkspace repository](https://wildermods.com/wilderworkspace)

### Getting Started

1. Clone the git repository by executing the following command:

    ```shell
    git clone git@github.com:WilderForge/ExampleMod.git
    ```
    ***
2. Navigate into the project's main directory:

    ```shell
    cd ./examplemod
    ```
    ***
3. Setup the project workspace:

    ```shell
    ./gradlew setupDecompWorkspace
    ```

    __Important:__ This process will take a while. You will see errors appear in the console. Usually these are related to issues with decompiling koltin files. If you see `BUILD SUCCESSFUL` after the command has executed, you may proceed to the next step.

    __Important:__ by default, WilderWorkspace assumes the base game files are located in the default directory for a steam installation on your operating system. 

    Please see [WIKI PAGE TO BE CREATED] if you have the game installed in a different location, or you receive a `BUILD FAILED` with one of the following errors when setting up the workspace:
    ```
      java.io.FileNotFoundException: [STEAM_DIRECTORY]/steamapps/common/Wildermyth
    ```
    ```
      org.apache.commons.lang3.NotImplementedException: I don't know where the default install directory for Wildermyth is for the [PLATFORM] platform. Submit a pull request or input a raw path to the installation location.
    ```

    ***

4. Change your mod's metadata:

    You probably don't want your mod to be called 'examplemod'. To properly convey the name of your mod within the game, change the following values:

    ### `build.gradle`
      * `group` - Change this to your reversed domain name. For example, if your domain was `example.com`, then your group would be `com.example`. If you don't have a domain, pick something unique so it doesn't clash with another person's code. You should generally use the same group across all of your mods.
      * `archivesBaseName` - Change this to the modid of your mod

    ### `gradle.properties`
      * `modID` - Change this to the modid of your mod
      * `modVersion` - Change this to the version of your mod. Update this value when you release a new version. The buildscript `replaceTokenScript.gradle` will replace all instances of `@modVersion@` in your source code at compile time with the version number supplied here.

    ### `settings.gradle`
      * `rootproject.name` - Change this to the modid of your mod.

    ### `src/main/resources/fabric.mod.json`
      The full specification for fabric.mod.json can be found here: https://wiki.fabricmc.net/documentation:fabric_mod_json
      * `id` - (REQUIRED) Change this to the modid of your mod
      * `version` - (REQUIRED) The version of your mod. You can keep this as `@modVersion@` unless you don't want to let `replaceTokenScript.gradle` do its thing.
      * `name` - (REQUIRED) Change this to a human readable name for your mod. For example, if your modid was `mycoolmod`, then the name could be `My Cool Mod`.
      * `description` - (Optional, but highly recommended) Describe your mod.

      In addtion to the fabric.mod.json specification linked above, WilderForge also allows you to supply custom credits which appear at the end of a campaign. 
      * `custom` - (OPTIONAL)
        * `credits` - (OPTIONAL) An array of strings that will be used in the end credits.

    ### Resources
      All game assets for your mod are expected to be in `src/main/resources/assets/[YOUR_MOD_ID]`. Rename `assets/examplemod` to `assets/[YOUR_MOD_ID]`
      * `src/main/resources/[YOUR_MOD_ID]/icon.png` - Optional. Wilderforge uses the file with this exact name for the mod menu. If this file doesn't exist, a placeholder is used.
      * `README.md` - Before you publish your mod, you should remove this README.md file, or replace it with your own content.

    ### Code
      * If you're building this examplemod, be sure to update the `MOD_ID` field in `com.wildermods.examplemod.ExampleMod`
      * Before publishing your mod, be sure to move the code out of the `com.wildermods` namespace. By convention, your packages should start with the `group` defined in `build.gradle`, followed by the value in `archivesBaseName`.
    ***

5. Configure the project for your IDE:

    Configure the project for your IDE.
    __Note:__ This project uses WilderWorkspace to configure itself for IDEs. Currently, WilderWorkspace only supports the Eclipse IDE. Pull Requests to add support for additional IDEs are welcome, and can be submitted at the [WilderWorkspace repository](https://wildermods.com/wilderworkspace)

    If you're using Eclipse, Execute the following commands to configure the project for your IDE. If you're not using eclipse, you're going to have to figure out how to configure the project for your particular IDE yourself.

    * Configure the project
    ```shell
    ./gradlew eclipse --refresh-dependencies
    ```

    * Generate run configurations to allow your IDE to start and debug the game:
    ```shell
    ./gradlew genEclipseRuns
    ```
    * You should now be able to import the project into your IDE and begin developing your mod.

    ***
        
## Updating WilderWorkspace

The workspace plugin may occasionally receive updates. To update WilderWorkspace, you must do the following:

1. Open the following file: `[project root]/gradle/libs.versions.toml`
   ***
2. Change the value of `workspace_version` to the desired version.
    ***
3. Notify your IDE of the changes:
    ```
    ./gradlew eclipse --refresh-dependencies
    ```
    ***
## Updating Wildermyth

__Extremely Important:__ Updating the base game requires deletion of the copy of the game in the workspace, including save data and legacy files. The original game (from Steam, GOG, etc.) will remain unaffected.

If the base game receives an update and you wish to build against it, follow these steps:

1. Ensure the base game is up to date on your system.
    ***
2. Execute the following command to delete the old version from the workspace:

    ```shell
    ./gradlew clearLocalRuntime
    ```
    ***
3. Setup the project workspace again
    ```
    ./gradlew setupDecompWorkspace
    ```
    ***
4. Notify your IDE of the changes:
    ```
    ./gradlew eclipse --refresh-dependencies
    ```
    ***
