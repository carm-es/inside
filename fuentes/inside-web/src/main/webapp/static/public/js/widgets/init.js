$mf.init_widgets = function($el) {
    $mf.my_dialog = $mf.my_dialog || new $mf.Dialog($("div.mf-dialog").empty());
    //$mf.TopBar();
    //$mf.Marquesina(0,$el);
    //$mf.Formulario($el);
    //$mf.autocomplete($el);
    //$mf.DataGrid($el);
    //$mf.dropdown($el);
    $mf.Datepicker($el);
    //$mf.Paginator($el);
    //$mf.Tabs($el);
    //$mf.ConfNav($el);
    $mf.AjaxModal($el);
    //$mf.my_status = $mf.my_status || new $mf.Status();
    $('.mf-veil').length || $('<div class="mf-veil"></div>').appendTo($('body')).hide();
}

$(document).ready(function() {
    $mf.init_widgets();
});
