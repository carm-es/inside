INSERT INTO InsideWsAplicacion (id, nombre, passwordHash, activo, altaExpediente, 
								modificarExpediente, leerExpediente, altaDocumento, 
								modificarDocumento, leerDocumento, fechaCreacion, 
								administrarPermisos, email, telefono, responsable, serialNumberCertificado) 
								VALUES 
								((SELECT genValue FROM GeneradorClaves WHERE genName = 'GEN_InsideWsAplicacion'), '{{apli_name}}', (SELECT SHA2('{{apli_pass}}', 256)), true, true, 
								1, 1, 1, 
								1, 1, '2016-08-22 13:18:03', 
								1, null, null, null, {{SERIAL_NUMBER_DEL_CERTIFICADO}});
			
UPDATE GeneradorClaves SET genValue = genValue + 1 WHERE genName = 'GEN_InsideWsAplicacion';
commit;								
                          