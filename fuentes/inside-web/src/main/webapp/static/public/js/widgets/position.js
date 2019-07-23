$mf.position = function($el,$a,v,h,over_v,over_h,no_change){
    var aux = {top: 0, left: 0},
        options_v = {
            top: function(){},
            center: function(){
                aux.top = $(window).outerHeight()/2  - $el.outerHeight()/2;
            },
            bottom: function(){
                aux.top = $(window).outerHeight() - $el.outerHeight();
            }
        },
        options_a_v = {
            top: function(){
                if(!over_v){
                    aux.top = aux.top - $el.outerHeight();
                }
            },
            center: function(){
                aux.top = aux.top + $a.outerHeight()/2 - $el.outerHeight()/2;
            },
            bottom: function(){
                if(!over_v){
                    aux.top = aux.top + $a.outerHeight();
                }
                else{
                    aux.top = aux.top + $a.outerHeight() - $el.outerHeight();
                }
            }
        },
        options_h = {
            left: function(){},
            center: function(){
                aux.left = $(window).outerWidth()/2 - $el.outerWidth()/2;
            },
            right: function(){
                aux.left =  $(window).outerWidth() - $el.outerWidth();
            }
        },
        options_a_h = {
            left: function(){
                if(!over_h){
                    aux.left = aux.left - $el.outerWidth();
                }
            },
            center: function(){
                aux.left = aux.left + $a.outerWidth()/2 - $el.outerWidth()/2;
            },
            right: function(){
                if(!over_h){
                    aux.left = aux.left + $a.outerWidth();
                }
                else{
                    aux.left = aux.left + $a.outerWidth() - $el.outerWidth();
                }
            }
        },
        validate = function(){
            aux.left = aux.left < 0 ? 0 :
                aux.left + $el.outerWidth() > $(window).outerWidth() ? $(window).outerWidth()-$el.outerWidth() :
                    aux.left;
            aux.top = aux.top < 0 ? 0 :
                aux.top + $el.outerHeight() > $(window).outerHeight() ? $(window).outerHeight()-$el.outerHeight() :
                    aux.top;
        }
    if($a != undefined && $a.length > 0){
        aux = {top: $a.position().top-$(window).scrollTop(), left: $a.position().left-$(window).scrollLeft()};
        if(v)options_a_v[v]();
        if(h)options_a_h[h]();
    }
    else{
        if(v)options_v[v]();
        if(h)options_h[h]();
    }
    validate();
    if(!no_change)$el.css(aux);
    return aux;
}

