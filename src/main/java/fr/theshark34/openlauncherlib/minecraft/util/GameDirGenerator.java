/*
 * Copyright 2015-2016 Adrien "Litarvan" Navratil
 *
 * This file is part of the OpenLauncherLib.

 * The OpenLauncherLib is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * The OpenLauncherLib is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with the OpenLauncherLib.  If not, see <http://www.gnu.org/licenses/>.
 */
package fr.theshark34.openlauncherlib.minecraft.util;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * The Minecraft Game Dir Generator
 *
 * <p>
 * This class contains a method to generate the minecraft directory of
 * the current OS like the default of Minecraft.
 * </p>
 *
 * @author Litarvan
 * @version 3.0.2-BETA
 * @since 2.0.0-BETA
 */
public class GameDirGenerator
{
    /**
     * Generate the game directory of the current OS by the given
     * server name, like the default of Minecraft.
     *
     * @param serverName The server name that will be the directory
     *                   name.
     * @param inLinuxLocalShare if true, the game dir would be ~/.local/share/server ; ~/.server else
     * @return The generated game directory
     */
    public static Path createGameDir(String serverName, boolean inLinuxLocalShare)
    {
        final String os = Objects.requireNonNull(System.getProperty("os.name")).toLowerCase();
        if (os.contains("win")) return Paths.get(System.getenv("APPDATA"), '.' + serverName);
        else if (os.contains("mac")) return Paths.get(System.getProperty("user.home"), "Library", "Application Support", serverName);
        else
        {
            if(inLinuxLocalShare && os.contains("linux")) return Paths.get(System.getProperty("user.home"), ".local", "share", serverName);
            else return Paths.get(System.getProperty("user.home"), '.' + serverName);
        }
    }
}
