/*?

 Messages
 ============

 * @desc         Crea un objeto con métodos que lanzan mensajes animados de error, éxito u otros tipos a definir.
 * @name         Messages
 * @author
 * @tested
 * @requires     main.js
 * @bugs

 */

(function(global, $) {
    "use strict";
    var Messages = function() {
        var $elLog, _messages = {
            delay : 5000,
            close : function($elem, wait) {
                var timer = (wait && !isNaN(wait)) ? +wait : this.delay, self = this, hideElement = function($el) {
                    var remove = function($el) {
                        $el.remove();
                        if ($elLog.children().length == 0)
                            $elLog.addClass("mf-alerts-hidden");
                    }
                    if ($el !== undefined && $el.parent($elLog).length > 0) {
                        if ($('html').hasClass('csstransforms')) {
                            $el.addClass("mf-alert-log-hide");
                            $el.one('transitionend', function(e) {
                                e.stopPropagation();
                                remove($el);
                            });
                        } else {
                            remove($el);
                        }
                    }
                };
                $elem.on("click", function() {
                    hideElement($elem);
                });
                if (wait === 0)
                    return;
                setTimeout(function() {
                    hideElement($elem);
                }, timer);
            },

            init : function() {
                if ($elLog == undefined) {
                    // $elLog = $("<section id='mf-alerts' class='mf-alert mf-alert mf-alert-hidden'></section>")
                    $elLog = $(
                            "<section id='mf-alerts' class='mf-alerts mf-alert-container mf-alerts-hidden'></section>")
                            .appendTo('body');
                }
            },

            log : function(message, type, wait) {
                this.init();
                $elLog.removeClass('mf-alerts-hidden');
                return this.notify(message, type, wait);
            },

            notify : function(message, type, wait) {
                var $log = $("<article class='mf-alert-log'></article>").addClass(
                        (typeof type === "string" && type !== "") ? " mf-alert-" + type : "").html(message).appendTo(
                        $elLog);
                setTimeout(function() {
                    $log.addClass("mf-alert-show");
                }, 50);
                this.close($log, wait);
                return $log;
            }
        };
        return {
            init : _messages.init,
            log : function(message, type, wait) {
                return _messages.log(message, type, wait);
            },
            success : function(message, wait) {
                return _messages.log(message, "success", wait);
            },
            error : function(message, wait) {
                return _messages.log(message, "error", wait);
            }
        };
    };

    // AMD and window support
    if (typeof define === "function") {
        define([], function() {
            return new Messages();
        });
    } else if (global.messages === undefined) {
        global.messages = new Messages();
    }

}(this, $));
