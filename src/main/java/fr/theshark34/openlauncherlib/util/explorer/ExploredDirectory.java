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
package fr.theshark34.openlauncherlib.util.explorer;

import fr.flowarg.openlauncherlib.ModifiedByFlow;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * The Explored Directory
 *
 * <p>
 * A directory, with some useful methods.
 * Generated by Explorer.dir or an other Explored Directory.
 * </p>
 *
 * @author Litarvan
 * @version 3.0.2-BETA
 * @since 3.0.0-BETA
 */
@ModifiedByFlow
public class ExploredDirectory
{
    /**
     * The current exploring directory
     */
    protected Path directory;

    /**
     * The Explored Directory
     *
     * @param directory The directory to explore
     */
    ExploredDirectory(Path directory)
    {
        this.directory = directory;
    }

    /**
     * Generate a list of all files and folders, recursively (sub-folder and sub-files included)
     * in this folder
     *
     * @return The generated list
     * @see FileList
     */
    public FileList allRecursive()
    {
        return new FileList(FilesUtil.listRecursive(this.directory));
    }

    /**
     * Return a list of the files/folders in this folder
     *
     * @return The generated list
     * @see FileList
     */
    public FileList list()
    {
        return new FileList(FilesUtil.list(this.directory));
    }

    /**
     * Return a sub directory of the current one
     *
     * @param directory The name of the sub directory
     * @return The sub directory as ExploredDirectory
     */
    public ExploredDirectory sub(String directory)
    {
        return new ExploredDirectory(FilesUtil.dir(this.directory, directory));
    }

    /**
     * Return a file of this directory
     *
     * @param file The file to get
     * @return The got file
     */
    public Path get(String file)
    {
        return FilesUtil.get(this.directory, file);
    }

    /**
     * Return the directories inside the current one (not the files, not recursively)
     *
     * @return A FileList of the directories
     * @see FileList
     */
    public FileList subs()
    {
        final List<Path> files = FilesUtil.list(this.directory);
        final List<Path> dirs  = new ArrayList<>();

        for (Path f : files)
            if (Files.isDirectory(f))
                dirs.add(f);

        return new FileList(dirs);
    }

    /**
     * Return the files inside this directory (not the directories, not recusrively)
     *
     * @return A FileList of the files
     */
    public FileList files()
    {
        List<Path> files = FilesUtil.list(this.directory);
        List<Path> fs = new ArrayList<>();

        for (Path f : files)
            if (!Files.isDirectory(f))
                fs.add(f);

        return new FileList(fs);
    }

    /**
     * Return this directory as a {@link Path}
     *
     * @return This, as {@link Path}
     */
    public Path get()
    {
        return this.directory;
    }
}