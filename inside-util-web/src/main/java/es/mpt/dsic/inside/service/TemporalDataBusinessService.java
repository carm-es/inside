/* Copyright (C) 2016 MINHAP, Gobierno de España
   This program is licensed and may be used, modified and redistributed under the terms
   of the European Public License (EUPL), either version 1.1 or (at your
   option) any later version as soon as they are approved by the European Commission.
   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
   or implied. See the License for the specific language governing permissions and
   more details.
   You should have received a copy of the EUPL1.1 license
   along with this program; if not, you may find it at
   http://joinup.ec.europa.eu/software/page/eupl/licence-eupl */

package es.mpt.dsic.inside.service;

import java.io.FileNotFoundException;
import java.io.IOException;

import es.mpt.dsic.inside.service.exception.ServiceException;


public interface TemporalDataBusinessService {

	String escribir(byte[] data, String folder, String filename, boolean create) throws ServiceException, FileNotFoundException, IOException;
	
	byte[] getFile(String folder, String file) throws FileNotFoundException, IOException;
	
	byte[] getFilePath(String Path) throws FileNotFoundException, IOException;
	
	String getFileContent(String folder, String file) throws FileNotFoundException, IOException;
	
	String getPath(String folder, String file);
	
	void cleanDirectory();
	
	long getFreeSpace();
	
	long getTotalSpace();
	
	void createDirectory(String folder, String filename);
	
	void cleanDirectory(String folder);
}
