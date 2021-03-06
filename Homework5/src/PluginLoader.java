/*
 * Copyright (c) 2008, 2010, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle nor the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
 
import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;

import java.awt.Dimension;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

import javax.swing.JPanel;
 
/**
 * Example to watch a directory (or tree) for changes to files.
 */
 
public class PluginLoader {
 
	public final static String PLUGIN_KEY = "Manifest-Version";
    private final WatchService watcher;
    private final Map<WatchKey,Path> keys;
    private boolean trace = false;
    private GUIController gui;
    private File pluginDir;
    private JPanel pluginPanel;
    private ByteArrayOutputStream out;
    
    /**
     * Creates a WatchService and registers the given directory
     * @throws IOException 
     */
    PluginLoader(GUIController gui, File pluginDir) throws IOException {
    	this.pluginPanel = new JPanel();
        this.watcher = FileSystems.getDefault().newWatchService();
        this.keys = new HashMap<WatchKey,Path>();
        this.gui = gui;
        this.pluginDir = pluginDir;
    }
    
    public void watchPlugins() throws IOException {
        register(this.pluginDir.toPath());
        this.trace = true;
        processEvents();
    }
    
	public void loadAllPlugins() {
		File[] filesList = this.pluginDir.listFiles();
		for (File file : filesList) {
			if(file.isFile()) {
				loadPlugin(file.getName());
			}
		}
	}
	
	private void loadPlugin(String plugin) {
		this.gui.addPlugin(plugin);
	}
	
	private void removePlugin(String plugin) {
		this.gui.removePlugin(plugin);
	}
    
	@SuppressWarnings({ "resource", "deprecation" })
	public void launchPlugin(String pluginName) {
		File[] filesList = this.pluginDir.listFiles();
		for (File file : filesList) {
			if(file.isFile() && file.getName().equalsIgnoreCase(pluginName)) {
				try {
					URL[] classLoaderURLs = {file.toURL()};
					URLClassLoader classLoader = new URLClassLoader(classLoaderURLs);
					Manifest m = new JarFile(file.toString()).getManifest();
					Attributes attr = m.getMainAttributes();
					String val = attr.getValue(PLUGIN_KEY);
					Class<?> pluginClass = classLoader.loadClass(val);
					
					// Create a new instance from the loaded class
					Constructor<?> constructor = pluginClass.getConstructor();
					
					Object pluginObj = constructor.newInstance();
					
					// Getting a method from the loaded class and invoke it
					Method method1 = pluginClass.getMethod("getStream");
					Object out = method1.invoke(pluginObj);
					this.out = (ByteArrayOutputStream) out;
					
					Method method2 = pluginClass.getMethod("getPanel");
					Object pan = method2.invoke(pluginObj);
					pluginPanel = (JPanel) pan;

				} catch (ClassNotFoundException | IOException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					// Do Nothing! :)
				}
				pluginPanel.setPreferredSize(new Dimension(300, 350));
				pluginPanel.setVisible(true);
				Plugin p = new Plugin(pluginName, pluginPanel);
				gui.startPlugin(p, this.out);
			}
		}
	}
	
    @SuppressWarnings("unchecked")
    static <T> WatchEvent<T> cast(WatchEvent<?> event) {
        return (WatchEvent<T>)event;
    }
 
    /**
     * Register the given directory with the WatchService
     */
    private void register(Path dir) throws IOException {
        WatchKey key = dir.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
        if (trace) {
            Path prev = keys.get(key);
            if (prev == null) {
                System.out.format("register: %s\n", dir);
            } else {
                if (!dir.equals(prev)) {
                    System.out.format("update: %s -> %s\n", prev, dir);
                }
            }
        }
        keys.put(key, dir);
    }
 
    /**
     * Process all events for keys queued to the watcher
     */
    void processEvents() {
        for (;;) {
            // wait for key to be signalled
            WatchKey key;
            try {
                key = watcher.take();
            } catch (InterruptedException x) {
                return;
            }
 
            Path dir = keys.get(key);
            if (dir == null) {
                System.err.println("WatchKey not recognized!!");
                continue;
            }
 
            for (WatchEvent<?> event: key.pollEvents()) {
                @SuppressWarnings("rawtypes")
				WatchEvent.Kind kind = event.kind();
 
                // TBD - provide example of how OVERFLOW event is handled
                if (kind == OVERFLOW) {
                    continue;
                }
 
                // Context for directory entry event is the file name of entry
                WatchEvent<Path> ev = cast(event);
                Path name = ev.context();
                Path child = dir.resolve(name);
 
                // print out event
                System.out.format("%s: %s\n", event.kind().name(), child);
                
                if (kind == ENTRY_CREATE) {
                	loadPlugin(name.toString());
                } else if (kind == ENTRY_DELETE) {
                	removePlugin(name.toString());
                } else {
                	removePlugin(name.toString());
                	loadPlugin(name.toString());
                }
 
            }
 
            // reset key and remove from set if directory no longer accessible
            boolean valid = key.reset();
            if (!valid) {
                keys.remove(key);
 
                // all directories are inaccessible
                if (keys.isEmpty()) {
                    break;
                }
            }
        }
    }
 
    static void usage() {
        System.err.println("usage: java WatchDir [-r] dir");
        System.exit(-1);
    }
}