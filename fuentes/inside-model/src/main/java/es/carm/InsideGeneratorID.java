package es.carm;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.type.Type;

public class InsideGeneratorID implements IdentifierGenerator, Configurable {

  private static final String DEFAULT_SEQUENCE = "GEN_DefaultSeq";
  private static final String JNDI_ORACLE = "java:/comp/env/Oracle_INSIDE";
  private static final Log log = LogFactory.getLog(InsideGeneratorID.class);

  private String seq = null;

  private String traduceSequence(String sequence) {

    if (sequence.equalsIgnoreCase("GEN_AuditoriaRespuestaEnvioJusticia")) {
      return "GEN_AuditRespEnvioJusticia";
    } else if (sequence.equalsIgnoreCase("GEN_ComunicacionTokenExpediente")) {
      return "GEN_ComTokenExpediente";
    } else if (sequence.equalsIgnoreCase("GEN_ExpedienteInsideIndiceCarInd")) {
      return "GEN_ExpInsideIndiceCarInd";
    } else if (sequence.equalsIgnoreCase("GEN_ExpedienteInsideIndiceDocInd")) {
      return "GEN_ExpInsideIndiceDocInd";
    } else if (sequence.equalsIgnoreCase("GEN_ExpedienteInsideIndiceFirmas")) {
      return "GEN_ExpInsideIndiceFirmas";
    } else if (sequence.equalsIgnoreCase("GEN_ExpedienteInsideRespuestaEnvioJusticia")) {
      return "GEN_ExpInsideResEnvioJusticia";
    } else if (sequence.equalsIgnoreCase("GEN_ExpedienteInsideResultadoEnvioJusticia")) {
      return "GEN_ExpInsideResultEnvJusticia";
    } else if (sequence.equalsIgnoreCase("GEN_ExpedienteNoInsideResultadoEnvioJusticia")) {
      return "GEN_ExpNoInsideRslEnvJusticia";
    }
    return sequence;
  }

  private long readSequence(Connection con, String sequence) {
    long retVal = -1;
    StringBuilder sql = new StringBuilder();
    sql.append(" SELECT " + sequence + ".nextval FROM dual ");

    PreparedStatement pstQuery = null;
    ResultSet rs = null;
    try {
      pstQuery = con.prepareStatement(sql.toString());
      rs = pstQuery.executeQuery();
      if (rs.next()) {
        retVal = rs.getLong(1);
      }
      rs.close();
      rs = null;
      pstQuery.close();
      pstQuery=null;
      
      log.debug("IBM78M-PARCHE-TableGenerator: Resultado de ejecutar SQL '"+ sql +"'="+ retVal);
      System.err.println("IBM78M-PARCHE-TableGenerator: Resultado de ejecutar SQL '"+ sql +"'="+ retVal); 

    } catch (SQLException ex) {
      log.error("Al ejecutar el SQL '" + sql + "'", ex);

    } finally {
      if (null != rs) {
        try {
          rs.close();
        } catch (Exception e) {
          /* Ignorar */ }
      }
      if (null != pstQuery) {
        try {
          pstQuery.close();
        } catch (Exception e) {
          /* Ignorar */ }
      }
    }
    return retVal;
  }

  private long readSequenceFromDatabase(Connection con) {
    if (null == con) {
      return -1;
    }
    String oraSeq = this.traduceSequence(seq);
    if (null == seq) {
      oraSeq = DEFAULT_SEQUENCE;
    }
    long retVal = -1;
    try {
      retVal = readSequence(con, oraSeq);
      if (0 >= retVal) {
        log.error("Usando la secuencia por defecto '" + DEFAULT_SEQUENCE + "' al no existir '"
            + oraSeq + "'");
        retVal = readSequence(con, DEFAULT_SEQUENCE);
      }
      con.commit();

    } catch (Exception x) {
      // Ignorar
    } finally {
      try {
        con.close();
      } catch (Exception x) {
        /* ignorar */ }
    }
    return retVal;
  }

  @Override
  public Serializable generate(SessionImplementor session, Object object)
      throws HibernateException {
    // Ejemplo: GEN_ExpedienteInsideIndiceFirmas
    long returnValue = -1;

    try {
      // Obtener una conexion a la Base de datos, configurada desde el Tomcat...
      Context ctx = new InitialContext();
      DataSource ds = (DataSource) ctx.lookup(JNDI_ORACLE);
      returnValue = readSequenceFromDatabase(ds.getConnection());

    } catch (Exception e) {
      log.error("No se pudo conectar a Oracle '" + JNDI_ORACLE + "'", e);
    }
    System.err.println("IBM78M-PARCHE-TableGenerator ... saliendo con ID="+ returnValue +" / para la SEQ="+ seq );
    if (0 < returnValue) {
      return returnValue;
    }
    throw new HibernateException("No se pudo obtener un valor de la secuencia: '" + seq + "'");
  }

  @Override
  public void configure(Type type, Properties params, Dialect d) throws MappingException {
    seq = params.getProperty("sequence");
  }
}
