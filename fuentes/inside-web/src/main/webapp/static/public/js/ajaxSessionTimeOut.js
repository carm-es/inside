// Incluida en header.html
// Redireccion a logout cuando se produce un error 403 en una llamada ajax.
// La clase AuthenticationEntryPoint está definida como punto de entrada en
// inside-security-context.xml . Pasa por este cada vez que se direcciona a login
// la primera vez que se crea la sesión o cuando ha expirado. 
// Esta clase AuthenticationEntryPoint filtra llamadas ajax y  envía error 403
jQuery(document).ajaxError(function(event, request, settings) {
    if (request.status === 403) {
        window.location.href = "/inside/logout";
    }
});