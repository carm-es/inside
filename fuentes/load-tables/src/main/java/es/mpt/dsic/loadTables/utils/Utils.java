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

package es.mpt.dsic.loadTables.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Utils {

	protected static final Log logger = LogFactory.getLog(Utils.class);

	public static void getUnZippedFile(FileInputStream fBase64, String file)
			throws IOException {
		logger.debug("Inicio getUnZippedFile");

		FileOutputStream retorno = new FileOutputStream(
				file);
		byte[] data = IOUtils.toByteArray(fBase64);
		byte[] base64 = Base64.decodeBase64(data);
		fBase64.close();

		retorno.write(base64);
		retorno.close();

		logger.debug("Fin getUnZippedFile");
	}

	public static void deleteBefore(String path, String txtFile, String zipFile, String pathTemp) {
		deleteFile(path + txtFile);
		deleteFile(path + zipFile);
		deleteFile(path + pathTemp);
	}

	public static void deleteFile(String path) {
		File file = new File(path);
		if (file.exists()) {
			file.delete();
		}
	}

	public static void unZipFile(String zipFile, String outputFolder) {

		byte[] buffer = new byte[1024];

		try {

			// create output directory is not exists
			File folder = new File(outputFolder);
			folder.mkdir();

			// get the zip file content
			ZipInputStream zis = new ZipInputStream(
					new FileInputStream(zipFile));
			// get the zipped file list entry
			ZipEntry ze = zis.getNextEntry();

			while (ze != null) {

				String fileName = ze.getName();
				File newFile = new File(outputFolder + File.separator
						+ fileName);

				logger.debug("file unzip : " + newFile.getAbsoluteFile());

				// create all non exists folders
				// else you will hit FileNotFoundException for compressed folder
				new File(newFile.getParent()).mkdirs();

				FileOutputStream fos = new FileOutputStream(newFile);

				int len;
				while ((len = zis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}

				fos.close();
				ze = zis.getNextEntry();
			}

			zis.closeEntry();
			zis.close();

			logger.debug("unZipFile Done");

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public static void writeDataBase64(String data, String path, String file) throws IOException {
		FileOutputStream fileOut = new FileOutputStream(
				path + file);
		fileOut.write(data.getBytes());
		fileOut.close();
	}

	public static String readFile(String filePath) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		String line = null;
		StringBuilder stringBuilder = new StringBuilder();
		String ls = Constantes.LINE_SEPARATOR;
		while ((line = reader.readLine()) != null) {
			stringBuilder.append(line);
			stringBuilder.append(ls);
		}
		reader.close();
		return stringBuilder.toString();
	}

}
