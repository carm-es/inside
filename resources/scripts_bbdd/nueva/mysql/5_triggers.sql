DROP TRIGGER update_expediente_vinculado;
--/
CREATE DEFINER=`insidews`@`%` TRIGGER update_expediente_vinculado
AFTER INSERT ON 
insidews.ExpedienteInside
FOR EACH ROW BEGIN
    
	IF(NEW.version > 1) THEN
	
	SET @id_vinculado_a_cambiar = (select id from ExpedienteInside where identificador = New.identificador and version = (NEW.version - 1));
	UPDATE ExpedienteInsideIndice SET id_expediente_vinculado=New.id WHERE id_expediente_vinculado = @id_vinculado_a_cambiar;
	
	END IF;
    END
/
