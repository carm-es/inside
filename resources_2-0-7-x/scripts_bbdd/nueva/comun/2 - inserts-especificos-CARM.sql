-- {{id_unidad_numérico_corresponde_con_DIR3_A14002961-Región-de-Murcia}}:
-- 		select id 
--		from inside.UnidadOrganica
--		where codigo_unidad_organica='A14002961'

-- Alta como opción seleccionable en la interfaz Web de la firma en Servidor, para la unidad orgánica que corresponda
INSERT into UnidadAplicacionEeutil (id,id_unidad,id_aplicacion,password) 
VALUES ((SELECT genValue FROM GeneradorClaves WHERE genName = 'GEN_UnidadAplicacionEeutil'),
		'{{id_unidad_numérico_corresponde_con_DIR3_A14002961-Región-de-Murcia}}',
		'{{login_alta_eeutils_minhap}}','{{pass_alta_eeutils_minhap}}');
UPDATE GeneradorClaves SET genValue = genValue + 1 WHERE genName = 'GEN_UnidadAplicacionEeutil';		
COMMIT;

-- Permisos unidad orgánica para usuario Web Service		
INSERT INTO UnidadWsAplicacion	(id, id_unidad, id_aplicacion, activo, id_procedimiento) 
VALUES 
	((SELECT genValue FROM GeneradorClaves WHERE genName = 'GEN_UnidadWsAplicacion'), 
		{{id_unidad_numérico_corresponde_con_DIR3_A14002961-Región-de-Murcia}}, 1, 1, null);
UPDATE GeneradorClaves SET genValue = genValue + 1 WHERE genName = 'GEN_UnidadWsAplicacion';		
COMMIT;
