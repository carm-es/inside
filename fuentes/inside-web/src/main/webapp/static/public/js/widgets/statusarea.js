$mf.Status = function() {
    var self = this, action = null, options = null;
    this.$status = $('.mf-status');
    this.$content = this.$status.find('.mf-status--content');
    this.$actions = $('<a href="#!" class="mf-status--action"></a>').appendTo(this.$status).click(function(e) {
        e.preventDefault();
        var obj = self.$status.data('action') != undefined ? eval(self.$status.data('action')) : action;
        obj.apply(null, self.$status.data('options') != undefined ? eval(self.$status.data('options')) : options);
    });
    if (this.$status.data('action') == undefined) {
        this.$actions.hide();
    }
    this.setAction = function(a) {
        action = a;
    }
    this.setOptions = function(o) {
        options = o;
    }
}
