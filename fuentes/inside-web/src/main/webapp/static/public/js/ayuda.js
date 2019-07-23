function showAyudaDialog() {

    $('#init-modal-ayuda').removeClass('hidden');

    var divData = $('#init-modal-ayuda');
    var dataModal = ({
        title : "Ayuda",
        data_modal : "1",
        width : "500",
        data_draggable : "0",
        data_clazz : "tr-dialog tr-help-dialog mf-document-dialog js-dialog-documento",
        position_h : "center",
        position_v : 'center',
    });

    dataModal.data = divData.html();

    $mf.my_dialog.appendDialog(dataModal, true, true);

}