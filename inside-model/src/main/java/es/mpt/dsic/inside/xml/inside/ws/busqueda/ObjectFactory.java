
package es.mpt.dsic.inside.xml.inside.ws.busqueda;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the es.mpt.dsic.inside.xml.inside.ws.busqueda package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ExpedienteResultadoBusqueda_QNAME = new QName("https://ssweb.seap.minhap.es/Inside/XSD/v1.0/busqueda/resultados", "ExpedienteResultadoBusqueda");
    private final static QName _DocumentoResultadoBusqueda_QNAME = new QName("https://ssweb.seap.minhap.es/Inside/XSD/v1.0/busqueda/resultados", "DocumentoResultadoBusqueda");
    private final static QName _ConsultaWsInside_QNAME = new QName("https://ssweb.seap.minhap.es/Inside/XSD/v1.0/busqueda", "consultaWsInside");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: es.mpt.dsic.inside.xml.inside.ws.busqueda
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link MetadatoBusquedaWsInside.Valor.Between }
     * 
     */
    public MetadatoBusquedaWsInside.Valor.Between createMetadatoBusquedaWsInsideValorBetween() {
        return new MetadatoBusquedaWsInside.Valor.Between();
    }

    /**
     * Create an instance of {@link InfoResultadosBusquedaInside }
     * 
     */
    public InfoResultadosBusquedaInside createInfoResultadosBusquedaInside() {
        return new InfoResultadosBusquedaInside();
    }

    /**
     * Create an instance of {@link ExpedienteResultadoBusqueda.Resultados }
     * 
     */
    public ExpedienteResultadoBusqueda.Resultados createExpedienteResultadoBusquedaResultados() {
        return new ExpedienteResultadoBusqueda.Resultados();
    }

    /**
     * Create an instance of {@link SubConsultaWsInside }
     * 
     */
    public SubConsultaWsInside createSubConsultaWsInside() {
        return new SubConsultaWsInside();
    }

    /**
     * Create an instance of {@link MetadatoBusquedaWsInside.Valor }
     * 
     */
    public MetadatoBusquedaWsInside.Valor createMetadatoBusquedaWsInsideValor() {
        return new MetadatoBusquedaWsInside.Valor();
    }

    /**
     * Create an instance of {@link DocumentoResultadoBusqueda.Resultados }
     * 
     */
    public DocumentoResultadoBusqueda.Resultados createDocumentoResultadoBusquedaResultados() {
        return new DocumentoResultadoBusqueda.Resultados();
    }

    /**
     * Create an instance of {@link MetadatoBusquedaWsInside.Valor.DateRange }
     * 
     */
    public MetadatoBusquedaWsInside.Valor.DateRange createMetadatoBusquedaWsInsideValorDateRange() {
        return new MetadatoBusquedaWsInside.Valor.DateRange();
    }

    /**
     * Create an instance of {@link DocumentoResultadoBusqueda }
     * 
     */
    public DocumentoResultadoBusqueda createDocumentoResultadoBusqueda() {
        return new DocumentoResultadoBusqueda();
    }

    /**
     * Create an instance of {@link ExpedienteResultadoBusqueda }
     * 
     */
    public ExpedienteResultadoBusqueda createExpedienteResultadoBusqueda() {
        return new ExpedienteResultadoBusqueda();
    }

    /**
     * Create an instance of {@link MetadatoBusquedaWsInside }
     * 
     */
    public MetadatoBusquedaWsInside createMetadatoBusquedaWsInside() {
        return new MetadatoBusquedaWsInside();
    }

    /**
     * Create an instance of {@link ConsultaWsInside }
     * 
     */
    public ConsultaWsInside createConsultaWsInside() {
        return new ConsultaWsInside();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExpedienteResultadoBusqueda }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/busqueda/resultados", name = "ExpedienteResultadoBusqueda")
    public JAXBElement<ExpedienteResultadoBusqueda> createExpedienteResultadoBusqueda(ExpedienteResultadoBusqueda value) {
        return new JAXBElement<ExpedienteResultadoBusqueda>(_ExpedienteResultadoBusqueda_QNAME, ExpedienteResultadoBusqueda.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DocumentoResultadoBusqueda }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/busqueda/resultados", name = "DocumentoResultadoBusqueda")
    public JAXBElement<DocumentoResultadoBusqueda> createDocumentoResultadoBusqueda(DocumentoResultadoBusqueda value) {
        return new JAXBElement<DocumentoResultadoBusqueda>(_DocumentoResultadoBusqueda_QNAME, DocumentoResultadoBusqueda.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultaWsInside }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/busqueda", name = "consultaWsInside")
    public JAXBElement<ConsultaWsInside> createConsultaWsInside(ConsultaWsInside value) {
        return new JAXBElement<ConsultaWsInside>(_ConsultaWsInside_QNAME, ConsultaWsInside.class, null, value);
    }

}
