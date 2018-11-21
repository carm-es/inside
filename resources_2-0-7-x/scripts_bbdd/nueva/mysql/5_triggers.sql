DROP TRIGGER IF EXISTS update_expediente_vinculado;
-- ---------
DELIMITER $$

CREATE DEFINER = CURRENT_USER TRIGGER update_expediente_vinculado
AFTER INSERT ON 
inside.ExpedienteInside
FOR EACH ROW BEGIN
    
	IF (NEW.version > 1) THEN
	
	SET @id_vinculado_a_cambiar = (select id from ExpedienteInside where identificador = New.identificador and version = (NEW.version - 1));
	UPDATE ExpedienteInsideIndice SET id_expediente_vinculado=New.id WHERE id_expediente_vinculado = @id_vinculado_a_cambiar;
	
	END IF;
    END
$$

DELIMITER ;
-- --------