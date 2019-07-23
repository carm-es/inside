/*
 * Copyright (C) 2016 MINHAP, Gobierno de España This program is licensed and may be used, modified
 * and redistributed under the terms of the European Public License (EUPL), either version 1.1 or
 * (at your option) any later version as soon as they are approved by the European Commission.
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and more details. You
 * should have received a copy of the EUPL1.1 license along with this program; if not, you may find
 * it at http://joinup.ec.europa.eu/software/page/eupl/licence-eupl
 */

package es.mpt.dsic.integration.cmis.adapter;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.annotation.PostConstruct;
import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import es.mpt.dsic.integration.cmis.CmisTestBase;
import es.mpt.dsic.integration.cmis.adapter.CmisAdapterInterface;
import es.mpt.dsic.integration.cmis.exception.RepositoryCmisException;
import es.mpt.dsic.integration.cmis.model.ContenidoDocumento;
import es.mpt.dsic.integration.cmis.utils.CmisUtils;

public class CmisAdapterTest extends CmisTestBase {

  private static final Log logger = LogFactory.getLog(CmisAdapterTest.class);

  @Autowired
  private CmisAdapterInterface integration;

  private int documentosAlta;


  private static String[] rutaFolder = null;
  private static String[] rutaFolderDocs = new String[] {"d1", "d2"};

  private static String folderType = "F:inside:expediente";
  private static String folderAtr1 = "inside:exp_versionNTI";
  private static String folderAtr2 = "inside:exp_identificador";
  private static String folderAtr3 = "inside:exp_organo";
  private static String folderAtr4 = "inside:exp_fechaApertura";
  private static String folderAtr5 = "inside:exp_clasificacion";
  private static String folderAtr6 = "inside:exp_estado";
  private static String folderAtr7 = "inside:exp_interesado";
  private static String folderAtr8 = "inside:exp_tipoFirma";
  private static String folderAtr9 = "inside:exp_valorCSV";
  private static String folderAtr10 = "inside:exp_defGeneracionCSV";

  private static String documentType = "D:inside:documento";
  private static String documentAtr1 = "inside:doc_versionNTI";
  private static String documentAtr2 = "inside:doc_identificador";
  private static String documentAtr3 = "inside:doc_organo";
  private static String documentAtr4 = "inside:doc_fechaCaptura";
  private static String documentAtr5 = "inside:doc_origen";
  private static String documentAtr6 = "inside:doc_estadoElaboracion";
  private static String documentAtr7 = "inside:doc_nombreFormato";
  private static String documentAtr8 = "inside:doc_tipoDocumental";
  private static String documentAtr9 = "inside:doc_tipoFirma";
  private static String documentAtr10 = "inside:doc_valorCSV";
  private static String documentAtr11 = "inside:doc_defGeneracionCSV";
  private static String documentAtr12 = "inside:doc_identificadorDocOrigen";

  private static String valorAtributoString = "valorAtributoString";
  private static GregorianCalendar valorAtributoDate = new GregorianCalendar();
  private static List<String> valorAtributoListaString = new ArrayList<String>();
  private static boolean valorAtributoBoolean = true;

  private static String valorAtributoStringModif = "valorAtributoModifString";
  private static GregorianCalendar valorAtributoDateModif = new GregorianCalendar();
  private static List<String> valorAtributoListaStringModif = new ArrayList<String>();
  private static boolean valorAtributoBooleanModif = false;

  private static Map<String, Object> metadatosDocumento = new HashMap<String, Object>();
  private static Map<String, Object> metadatosCarpeta = new HashMap<String, Object>();

  private static Map<String, Object> metadatosDocumentoModif = new HashMap<String, Object>();
  private static Map<String, Object> metadatosCarpetaModif = new HashMap<String, Object>();

  private static String rutaDocDisco = null;

  private static String documentId = null;
  private static String documentName = null;
  private static List<String> docsBorrar = new ArrayList<String>();

  private static String folderId = null;

  private static String[] rutaFolderPedirDocs = null;

  static {
    valorAtributoListaString.add("valor1");
    valorAtributoListaString.add("valor2");

    valorAtributoListaStringModif.add("valor1Modif");
    valorAtributoListaStringModif.add("valor2Modif");


    metadatosCarpeta.put(folderAtr1, valorAtributoString);
    metadatosCarpeta.put(folderAtr2, valorAtributoString);
    metadatosCarpeta.put(folderAtr3, valorAtributoListaString);
    metadatosCarpeta.put(folderAtr4, valorAtributoDate);
    metadatosCarpeta.put(folderAtr5, valorAtributoString);
    metadatosCarpeta.put(folderAtr6, valorAtributoString);
    metadatosCarpeta.put(folderAtr7, valorAtributoListaString);
    metadatosCarpeta.put(folderAtr8, valorAtributoListaString);
    metadatosCarpeta.put(folderAtr9, valorAtributoListaString);
    metadatosCarpeta.put(folderAtr10, valorAtributoListaString);


    metadatosCarpetaModif.put(folderAtr1, valorAtributoStringModif);
    metadatosCarpetaModif.put(folderAtr2, valorAtributoStringModif);
    metadatosCarpetaModif.put(folderAtr3, valorAtributoListaStringModif);
    metadatosCarpetaModif.put(folderAtr4, valorAtributoDateModif);
    metadatosCarpetaModif.put(folderAtr5, valorAtributoStringModif);
    metadatosCarpetaModif.put(folderAtr6, valorAtributoStringModif);
    metadatosCarpetaModif.put(folderAtr7, valorAtributoListaStringModif);
    metadatosCarpetaModif.put(folderAtr8, valorAtributoListaStringModif);
    metadatosCarpetaModif.put(folderAtr9, valorAtributoListaStringModif);
    metadatosCarpetaModif.put(folderAtr10, valorAtributoListaStringModif);


    metadatosDocumento.put(documentAtr1, valorAtributoString);
    metadatosDocumento.put(documentAtr2, valorAtributoString);
    metadatosDocumento.put(documentAtr3, valorAtributoListaString);
    metadatosDocumento.put(documentAtr4, valorAtributoDate);
    metadatosDocumento.put(documentAtr5, valorAtributoBoolean);
    metadatosDocumento.put(documentAtr6, valorAtributoString);
    metadatosDocumento.put(documentAtr7, valorAtributoString);
    metadatosDocumento.put(documentAtr8, valorAtributoString);
    metadatosDocumento.put(documentAtr9, valorAtributoListaString);
    metadatosDocumento.put(documentAtr10, valorAtributoListaString);
    metadatosDocumento.put(documentAtr11, valorAtributoListaString);
    metadatosDocumento.put(documentAtr12, valorAtributoString);



    metadatosDocumentoModif.put(documentAtr1, valorAtributoStringModif);
    metadatosDocumentoModif.put(documentAtr2, valorAtributoStringModif);
    metadatosDocumentoModif.put(documentAtr3, valorAtributoListaStringModif);
    metadatosDocumentoModif.put(documentAtr4, valorAtributoDateModif);
    metadatosDocumentoModif.put(documentAtr5, valorAtributoBooleanModif);
    metadatosDocumentoModif.put(documentAtr6, valorAtributoStringModif);
    metadatosDocumentoModif.put(documentAtr7, valorAtributoStringModif);
    metadatosDocumentoModif.put(documentAtr8, valorAtributoStringModif);
    metadatosDocumentoModif.put(documentAtr9, valorAtributoListaStringModif);
    metadatosDocumentoModif.put(documentAtr10, valorAtributoListaStringModif);
    metadatosDocumentoModif.put(documentAtr11, valorAtributoListaStringModif);
    metadatosDocumentoModif.put(documentAtr12, valorAtributoStringModif);



  }

  @PostConstruct
  public void configure() throws RepositoryCmisException, URISyntaxException {

    if (rutaDocDisco == null) {

      File[] files = rutaDocsPruebasDir.listFiles();
      Assert.assertTrue(
          "La ruta de los ficheros de prueba no contiene ningún fichero" + rutaDocsPruebasDir,
          files.length > 0);

      rutaDocDisco = files[0].getPath();

      logger.info("El documento por defecto para las pruebas es " + rutaDocDisco);
    }

  }

  @Test
  public void testAltaCarpeta() throws RepositoryCmisException {
    logger.info("Iniciando el test del método altaCarpeta");
    String nombreFolder = String.valueOf(System.nanoTime());

    String t2 = "t2";
    Map<String, Object> metadatos1 = new HashMap<String, Object>();

    String[] ruta = new String[] {"f1", "f2"};
    metadatos1 = CmisUtils.getMetadatos(
        integration.altaCarpeta(nombreFolder + t2, ruta, folderType, metadatosCarpeta, true));

    pintarPropiedades(metadatos1);
    Assert.assertNotNull(metadatos1);
    @SuppressWarnings("unchecked")
    List<String> lista = (List<String>) metadatos1.get(folderAtr7);
    Assert.assertArrayEquals(valorAtributoListaString.toArray(), lista.toArray());

    rutaFolderPedirDocs = new String[] {"f1", "f2", "t2" + nombreFolder};

    folderId = metadatos1.get("cmis:objectId").toString();

    logger.info("Fin Test con ruta, con metadatos, con object Type");

    rutaFolder = new String[] {"f1", "f2", nombreFolder + t2};

    String t4 = "t4";
    ruta = new String[] {"no1", "no2"};

    boolean errorEsperado = false;

    try {
      metadatos1 = CmisUtils.getMetadatos(
          integration.altaCarpeta(nombreFolder + t4, ruta, folderType, metadatosCarpeta, false));
    } catch (RepositoryCmisException e) {
      errorEsperado = true;

    }

    Assert.assertTrue(errorEsperado);
    logger.info("Fin Test con ruta, con metadatos, con object Type, crear = false");

    logger.info("Finalizando el test del método altaCarpeta");
  }

  @SuppressWarnings("unchecked")
  @Test
  public void testModificaCarpeta() throws RepositoryCmisException {
    logger.info("Iniciando el test del método modificaCarpeta");

    Map<String, Object> metadatos1 =
        CmisUtils.getMetadatos(integration.modificaCarpeta(folderId, metadatosCarpetaModif));

    pintarPropiedades(metadatos1);

    Assert.assertNotNull(metadatos1);
    Assert.assertEquals(valorAtributoStringModif, metadatos1.get(folderAtr1));
    Assert.assertEquals(valorAtributoStringModif, metadatos1.get(folderAtr2));
    List<String> lista = (List<String>) metadatos1.get(folderAtr3);
    Assert.assertArrayEquals(valorAtributoListaStringModif.toArray(), lista.toArray());

    lista = (List<String>) metadatos1.get(folderAtr10);
    Assert.assertArrayEquals(valorAtributoListaStringModif.toArray(), lista.toArray());

    logger.info("Iniciando el test método modificaCarpeta");
  }



  @Test
  public void testAltaDocumento() throws RepositoryCmisException {
    logger.info("Iniciando el test del método altaDocumento");

    boolean errorEsperado = false;
    try {
      altaDocSinMetaRutaNoExisteSinTipoCrearFalse(rutaDocDisco);
    } catch (RepositoryCmisException e) {
      errorEsperado = true;
    }
    Assert.assertTrue(errorEsperado);
    logger.info("Fin Test sin metadatos, ruta no existe, sin tipo, crear false");

    for (File docprueba : rutaDocsPruebasDir.listFiles()) {
      logger.info("Realizando test de alta para el documento " + docprueba.getAbsolutePath());

      Map<String, Object> meta = CmisUtils
          .getMetadatos(altaDocSinMetaRutaNoExisteSinTipoCrearTrue(docprueba.getAbsolutePath()));
      pintarPropiedades(meta);
      logger.info("Fin Test sin metadatos para documento " + docprueba.getAbsolutePath()
          + ", ruta no existe, sin tipo, crear true");
      docsBorrar.add((String) meta.get("cmis:objectId"));
      // Assert.assertNotNull(meta.get("IdUnico"));

      meta = CmisUtils.getMetadatos(altaDocSinMetaRutaExisteSinTipo(docprueba.getAbsolutePath()));
      pintarPropiedades(meta);
      // Assert.assertNotNull(meta.get("IdUnico"));
      logger.info("Fin Test sin metadatos para documento " + docprueba.getAbsolutePath()
          + ", ruta existe, sin tipo, crear false");
      docsBorrar.add((String) meta.get("cmis:objectId"));

      meta = CmisUtils
          .getMetadatos(altaDocConMetaRutaExisteConTipo(docprueba.getAbsolutePath(), false));
      pintarPropiedades(meta);
      docsBorrar.add((String) meta.get("cmis:objectId"));

      documentId = (String) meta.get("cmis:objectId");


      // Versionado no funciona
      // meta = altaDocConMetaRutaExisteConTipo (true);

      // Assert.assertNotNull(meta.get("IdUnico"));
      logger.info("Fin Test con metadatos para documento " + docprueba.getAbsolutePath()
          + ", ruta existe, con tipo, crear false");
    }



  }



  @Test
  public void getInfoDocumento() throws RepositoryCmisException {
    try {
      Map<String, Object> metadatos = integration.getInfoDocumento(documentId);
      pintarPropiedades(metadatos);
    } catch (Exception e) {
      Assert.assertTrue(false);
      e.printStackTrace();
    }
  }


  @Test
  public void testGetContenidoDoc() throws RepositoryCmisException {
    logger.info("Iniciando el test del método getContenidoDoc");

    final ContenidoDocumento contenido = integration.getContenidoDoc(documentId);
    Assert.assertNotNull(contenido);
    Assert.assertNotNull(contenido.getContenido());

    logger.info("Finalizando el test del método getContenidoDoc");
  }


  @Test
  public void testModificaDocumento() throws RepositoryCmisException {
    logger.info("Iniciando el test del método modificaDocumento");

    final Map<String, Object> document =
        integration.modificarDocumento(documentId, metadatosDocumentoModif, null);
    Assert.assertNotNull(document);
    Assert.assertEquals(valorAtributoStringModif, document.get(documentAtr1));
    Assert.assertNotNull(document.get(documentAtr2));
    Assert.assertEquals(valorAtributoStringModif, document.get(documentAtr6));
    Assert.assertEquals(valorAtributoStringModif, document.get(documentAtr12));

    @SuppressWarnings("unchecked")
    List<String> valoresLista = (List<String>) document.get(documentAtr3);
    Assert.assertArrayEquals(valorAtributoListaStringModif.toArray(), valoresLista.toArray());
    logger.info("Finalizando el test del método modificaDocumento");
  }

  @Test
  public void testGetDocsCarpeta() throws RepositoryCmisException {

    Map<String, Object> metadatos1 = new HashMap<String, Object>();

    metadatos1 = CmisUtils.getMetadatos(
        integration.altaCarpeta("s2", new String[] {"s1"}, folderType, metadatosCarpeta, true));
    String folderId = (String) metadatos1.get("cmis:objectId");

    byte[] bytes = fileToByteArray(rutaDocDisco);

    Map<String, Object> metadatos2 = CmisUtils
        .getMetadatos(integration.altaDocumento("parla1", new String[] {"s1", "s2"}, documentType,
            bytes, CmisUtils.getMimeType(rutaDocDisco, bytes), metadatosDocumento, false));
    docsBorrar.add((String) metadatos2.get("cmis:objectId"));

    metadatos2 = CmisUtils
        .getMetadatos(integration.altaDocumento("parla2", new String[] {"s1", "s2"}, documentType,
            bytes, CmisUtils.getMimeType(rutaDocDisco, bytes), metadatosDocumento, false));
    docsBorrar.add((String) metadatos2.get("cmis:objectId"));

    logger.info("Iniciando el test del método getDocsCarpeta");

    final List<Map<String, Object>> documentos = integration.getDocsCarpeta(folderId);
    Assert.assertNotNull(documentos);
    // TODO ahora hay otro número de documentos!!
    Assert.assertTrue(documentos.size() == documentosAlta);

    logger.info("Finalizando el test del método getDocsCarpeta");
  }

  @Test
  public void testBorrarDocumentos() throws RepositoryCmisException {
    logger.info("Iniciando el test del método borrar documentos");

    for (String documentId : docsBorrar) {
      integration.borrarDocumento(documentId, true);
    }

    logger.info("Finalizando el test del método modificaDocumento");
  }



  private Document altaDocSinMetaRutaNoExisteSinTipoCrearFalse(String rutaDocDisco)
      throws RepositoryCmisException {
    String[] ruta =
        new String[] {"f1" + System.currentTimeMillis(), "f2" + System.currentTimeMillis()};
    return this.altaDocumento(rutaDocDisco, String.valueOf(System.nanoTime()), ruta, null, null,
        false);
  }


  private Document altaDocSinMetaRutaNoExisteSinTipoCrearTrue(String rutaDocDisco)
      throws RepositoryCmisException {
    return this.altaDocumento(rutaDocDisco, String.valueOf(System.nanoTime()), rutaFolderDocs, null,
        null, true);
  }

  private Document altaDocConMetaRutaExisteConTipo(String rutaDocDisco, boolean version)
      throws RepositoryCmisException {
    String nombreDocumento = version ? documentName : String.valueOf(System.nanoTime() + "contipo");

    return this.altaDocumento(rutaDocDisco, nombreDocumento, rutaFolderDocs, documentType,
        metadatosDocumento, false);
  }

  private Document altaDocSinMetaRutaExisteSinTipo(String rutaDocDisco)
      throws RepositoryCmisException {
    byte[] bytes = fileToByteArray(rutaDocDisco);
    return integration.altaDocumento(String.valueOf(System.nanoTime()), rutaFolderDocs, null, bytes,
        CmisUtils.getMimeType(rutaDocDisco, bytes), null, false);
  }

  /**
   * Método que llama al altaDocumento del CmisAdapter Extrae el contenido del fichero en base a una
   * ruta absoluta Obtiene el tipo Mime del fichero Lleva la cuenta de los documentos subidos
   * 
   * @param rutaDocDisco
   * @param documentName
   * @param ruta
   * @param objectTypeId
   * @param metadatos
   * @param crearRuta
   * @return
   * @throws RepositoryCmisException
   */
  private Document altaDocumento(String rutaDocDisco, final String documentName,
      final String[] ruta, final String objectTypeId, final Map<String, Object> metadatos,
      boolean crearRuta) throws RepositoryCmisException {
    logger.info(String.format("Voy a dar el alta del documento %s desde el fichero %s",
        documentName, rutaDocDisco));
    byte[] bytes = fileToByteArray(rutaDocDisco);
    Document documento = integration.altaDocumento(documentName, ruta, objectTypeId, bytes,
        CmisUtils.getMimeType(rutaDocDisco, bytes), metadatos, crearRuta);
    documentosAlta++;
    return documento;
  }

  private void pintarPropiedades(Map<String, Object> propiedades) {

    for (Entry<String, Object> entry : propiedades.entrySet()) {
      // logger.debug("Propiedad - Nombre ["+ entry.getKey() + " / Valor [" + entry.getValue() +
      // "]");
    }
  }



  /*
   * @Test public void testAltaCarpeta() throws RepoIntegrationException {
   * logger.info("Iniciando el test del método altaCarpeta");
   * 
   * nombreFolder = String.valueOf(System.nanoTime()); final Map<String, Metadato> metadatos = new
   * HashMap<String, Metadato>();
   * 
   * final String tipo = context.getCmisExpedienteTipo(); metadatos.put(tipo,
   * createMetadatoSimple(tipo, "tipo tramite")); final String interesado =
   * context.getCmisExpedienteInteresado(); metadatos.put(interesado,
   * createMetadatoMulti(interesado, "id1", "id2")); final String asociado =
   * context.getCmisExpedienteAsociado(); metadatos.put(asociado, createMetadatoMulti(asociado,
   * "asociado1", "asociado2"));
   * 
   * final Map<String, Metadato> carpeta = integration.altaCarpeta(nombreFolder, metadatos);
   * Assert.assertNotNull(carpeta); Assert.assertNotNull(carpeta.get(tipo));
   * Assert.assertNotNull(carpeta.get(interesado)); Assert.assertNotNull(carpeta.get(asociado));
   * folderId = carpeta.get("cmis:objectId").getValor().toString();
   * 
   * logger.info("Finalizando el test del método altaCarpeta"); }
   * 
   * @Test public void testAltaDocumento() throws RepoIntegrationException {
   * logger.info("Iniciando el test del método altaDocumento");
   * 
   * final String nombreFichero = String.valueOf(System.nanoTime()); final Map<String, Metadato>
   * metadatos = new HashMap<String, Metadato>();
   * 
   * final String nombre = context.getCmisDocumentoNombre(); metadatos.put(nombre,
   * createMetadatoSimple(nombre, nombreFichero)); final String tipo =
   * context.getCmisDocumentoTipo(); metadatos.put(tipo, createMetadatoSimple(tipo,
   * "tipo documento")); final String id = context.getCmisDocumentoIdUnico(); metadatos.put(id,
   * createMetadatoSimple(id, "id único")); final String visibilidad =
   * context.getCmisDocumentoVisibilidad(); metadatos.put(visibilidad,
   * createMetadatoSimple(visibilidad, "visibilidad")); final String ids =
   * context.getCmisDocumentoIdImplicados(); metadatos.put(ids, createMetadatoMulti(ids, "id1",
   * "id2", "id3")); final String tipoFirma = context.getCmisDocumentoTipoFirma();
   * metadatos.put(tipoFirma, createMetadatoSimple(tipoFirma, "tipo firma")); final String
   * expediente = context.getCmisDocumentoExpediente(); metadatos.put(expediente,
   * createMetadatoMulti(expediente, nombreFolder));
   * 
   * final Map<String, Metadato> document = integration.altaDocumento(metadatos, nombreFichero,
   * null); Assert.assertNotNull(document); Assert.assertNotNull(document.get(tipo));
   * Assert.assertNotNull(document.get(id)); Assert.assertNotNull(document.get(expediente));
   * documentId = document.get("cmis:objectId").getValor().toString();
   * 
   * logger.info("Finalizando el test del método altaDocumento"); }
   * 
   * @Test public void testGetContenidoDoc() throws RepoIntegrationException {
   * logger.info("Iniciando el test del método getContenidoDoc");
   * 
   * final ContenidoDocumento contenido = integration.getContenidoDoc(documentId);
   * Assert.assertNotNull(contenido); Assert.assertNull(contenido.getContenido());
   * 
   * logger.info("Finalizando el test del método getContenidoDoc"); }
   * 
   * @Test public void testGetContenidoDocVersion() throws RepoIntegrationException {
   * logger.info("Iniciando el test del método getContenidoDocVersion");
   * 
   * final String version = null; final ContenidoDocumento contenido =
   * integration.getContenidoDoc(documentId, version); Assert.assertNotNull(contenido);
   * Assert.assertNull(contenido.getContenido());
   * 
   * logger.info("Finalizando el test del método getContenidoDocVersion"); }
   * 
   * @Test public void testGetDocsCarpeta() throws RepoIntegrationException {
   * logger.info("Iniciando el test del método getDocsCarpeta");
   * 
   * final List<Map<String, Metadato>> documentos = integration.getDocsCarpeta(folderId);
   * Assert.assertNotNull(documentos); Assert.assertTrue(documentos.size() == 1);
   * 
   * logger.info("Finalizando el test del método getDocsCarpeta"); }
   * 
   * @Test public void testGetVersionesDocumento() throws RepoIntegrationException {
   * logger.info("Iniciando el test del método getVersionesDocumento");
   * 
   * final Collection<String> versiones = integration.getVersionesDocumento(documentId);
   * Assert.assertNotNull(versiones); Assert.assertTrue(versiones.size() == 1);
   * 
   * logger.info("Finalizando el test del método getVersionesDocumento"); }
   * 
   * @Test public void testModificaCarpeta() throws RepoIntegrationException {
   * logger.info("Iniciando el test del método modificaCarpeta");
   * 
   * final Map<String, Metadato> metadatos = new HashMap<String, Metadato>();
   * 
   * final String tipo = context.getCmisExpedienteTipo(); metadatos.put(tipo,
   * createMetadatoSimple(tipo, "mi tipo...."));
   * 
   * final Map<String, Metadato> folder = integration.modificaCarpeta(folderId, metadatos);
   * Assert.assertNotNull(folder); Assert.assertNotNull(folder.get(tipo));
   * 
   * logger.info("Finalizando el test del método modificaCarpeta"); }
   * 
   * @Test public void testModificaDocumento() throws RepoIntegrationException {
   * logger.info("Iniciando el test del método modificaDocumento");
   * 
   * final Map<String, Metadato> metadatos = new HashMap<String, Metadato>(); final String estado =
   * context.getCmisDocumentoEstado(); metadatos.put(estado, createMetadatoSimple(estado,
   * "ABIERTO")); final String csv = context.getCmisDocumentoCSV(); metadatos.put(csv,
   * createMetadatoSimple(csv, "mi csv...."));
   * 
   * final Map<String, Metadato> document = integration.modificaDocumento(documentId, metadatos,
   * null); Assert.assertNotNull(document); Assert.assertNotNull(document.get(estado));
   * Assert.assertNotNull(document.get(csv));
   * 
   * logger.info("Finalizando el test del método modificaDocumento"); }
   * 
   * @Test public void testBorrarDocumento() throws RepoIntegrationException {
   * logger.info("Iniciando el test del método borrarDocumento");
   * 
   * integration.borrarDocumento(documentId, true);
   * 
   * logger.info("Finalizando el test del método borrarDocumento"); }
   * 
   * private Metadato createMetadatoSimple(final String clave, final Object valor) { final Metadato
   * metadato = new MetadatoSimple(); metadato.setClave(clave); metadato.setMultivalor(false);
   * metadato.setValor(valor); return metadato;
   * 
   * }
   * 
   * private Metadato createMetadatoMulti(final String clave, final Object... valor) { final
   * Metadato metadato = new MetadatoSimple(); metadato.setClave(clave);
   * metadato.setMultivalor(true); metadato.setValor(Arrays.asList(valor)); return metadato; }
   */
}
