STEPS TO RUN PLUGIN PROJECT PLATFORM:


I. INCLUDE PLUGINS

1. Create as many plugins as you wish that have a "getPanel" method exposed that
 can be called from the platform to retrieve that plugins JPanel object. The plugin
 can perform operations on that JPanel to be displayed in the overall app GUI.

2. All plugins must inclide a "MANIFEST.mf" file which has the "Manifest-Version"
 property set to the class name based of its packages (i.e. edu.rosehulman.plugin.className).
 The platform will use this info to extract the class from the jar during runtime.

3. Export your plugins as a jar and bundle them with the manifest file that you create.

4. Place plugins into the "pluginFolder" in the root of the platform project
so that they can be loaded later. 


II. RUN THE PLATFORM

1. Assuming you are have the project loaded into eclipse, run the platform application
as a regular Java Appliation. The main platform GUI should appear with a list of loaded plugins,
a status panel, and a blank excution GUI for running the plugins.

2. Click on any of the plugins in the list to run them. You can also toggle between plugins.

3. You may also add / remove / alter any of the plugins in the pluginFolder during runtime 
using the steps described in the first section. The platform will automatically detect any
changes and reload the plugin that changes.
