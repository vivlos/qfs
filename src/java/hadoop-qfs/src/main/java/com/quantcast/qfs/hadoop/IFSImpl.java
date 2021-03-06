/**
 *
 * Licensed under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * permissions and limitations under the License.
 *
 * We need to provide the ability to the code in fs/qfs without really
 * having a QFS deployment.  In particular, the glue code that wraps
 * around calls to KfsAccess object.  This is accomplished by defining a
 * filesystem implementation interface:
 *   -- for testing purposes, a dummy implementation of this interface
 *      will suffice; as long as the dummy implementation is close enough
 *      to doing what QFS does, we are good.
 *   -- for deployment purposes with QFS, this interface is implemented by
 *      the QfsImpl object.
 */

package com.quantcast.qfs.hadoop;

import java.io.*;

import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import com.quantcast.qfs.access.KfsFileAttr;

interface IFSImpl {
  public boolean exists(String path) throws IOException;
  public boolean isDirectory(String path) throws IOException;
  public boolean isFile(String path) throws IOException;
  public String[] readdir(String path) throws IOException;
  public FileStatus[] readdirplus(Path path) throws IOException;
  public FileStatus stat(Path path) throws IOException;
  public FileStatus lstat(Path path) throws IOException;
  public KfsFileAttr fullStat(Path path) throws IOException;

  public int mkdirs(String path, int mode) throws IOException;
  public int mkdir(String path, int mode) throws IOException;
  public int rename(String source, String dest) throws IOException;
  public int rename2(String source, String dest, boolean overwrite) throws IOException;

  public int rmdir(String path) throws IOException;
  public int rmdirs(String path) throws IOException;
  public int remove(String path) throws IOException;
  public long filesize(String path) throws IOException;
  public short getReplication(String path) throws IOException;
  public short setReplication(String path, short replication)
           throws IOException;
  public String[][] getDataLocation(String path, long start, long len)
           throws IOException;
  public String[][] getBlocksLocation(String path, long start, long len)
           throws IOException;

  public long getModificationTime(String path) throws IOException;
  public FSDataOutputStream create(String path, short replication,
           int bufferSize, boolean overwrite, int mode) throws IOException;
  public FSDataOutputStream create(String path, short replication,
            int bufferSize, boolean overwrite, int mode,
            boolean append) throws IOException;
  public FSDataOutputStream create(String path, boolean overwrite,
          String createParams, int mode, boolean forceType) throws IOException;
  public FSDataOutputStream append(String path, short replication,
           int bufferSize) throws IOException;
  public FSDataInputStream open(String path, int bufferSize)
           throws IOException;
  public void setPermission(String path, int mode) throws IOException;
  public void setOwner(String path, String username, String groupname)
           throws IOException;
  public void retToIoException(int ret)
           throws IOException;
  public int getUMask() throws IOException;
  public void setUMask(int mask) throws IOException;
  public CloseableIterator<FileStatus> getFileStatusIterator(FileSystem fs, Path path)
           throws IOException;
  public void symlink(String target, String link, int mode, boolean overwrite)
           throws IOException;
  public Path getLinkTarget(Path path) throws IOException;
};
