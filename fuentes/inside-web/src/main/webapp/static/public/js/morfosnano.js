$(document).ready(function() {
    // var elementH = $('#header h1').outerHeight();
    var elementH = 0;
    var wtoolbarH = $('#windowToolbar').outerHeight();

    $('body').addClass('mob-nav');

    $.ajax({
        url : "js/libs/waypoints.min.js",
        dataType : "script",
        cache : true,
        success : function(script, textStatus) {
            $('html').addClass("sticky");
            mobileNav(elementH - 1);
        },
        error : function(jqxhr, settings, exception) {
            console.log("Triggered ajaxError handler.");
        }
    });

    $('#showNav-btn').on('click', function() {
        $('body').toggleClass('showNav');

        if ($('body').hasClass('showNav')) {
            $('#appMenu').css('top', elementH);
            $('#header').css('top', elementH);
        } else {
            $('#appMenu').css('top', elementH);
            $('#header').css('top', 0);
        }

        return false;
    });

    $('#showUserMenu-btn').on('click', function() {
        $('body').toggleClass('showUserMenu');

        if ($('body').hasClass('showUserMenu')) {
            $('#topBar').css('top', elementH);
            $('#header').css('top', elementH);
        } else {
            $('#topBar').css('top', elementH);
            $('#header').css('top', 0);
        }

        return false;
    });

    /*$('#userLn').on('click', function(){
    	if($('body').hasClass('showNav')) {
    		$(this).parent().toggleClass('opened');
    		$(this).siblings().css('top', elementH);
    	}
    	return false;
    });*/
});

// Sticky Navigation bars 

function mobileNav($offset) {
    $('#appMenu').children('.menu').children('.dropdown').waypoint(
            function(direction) {
                var categoryH = $(this).children('span').outerHeight();

                if (direction == 'down' && $('body').hasClass('showNav')) {
                    $(this).children('span').addClass('sticked').wrap('<div class="sticky-wrapper"/>').css('top',
                            $offset).parent().outerHeight(categoryH);
                }

                if (direction == 'up' && $('body').hasClass('showNav')) {
                    $(this).find('span.sticked').unwrap();
                    $(this).children('span').removeClass('sticked');
                }
            }, {
                context : '#appMenu',
                offset : 0
            }).waypoint(
            function(direction) {
                var categoryH = $(this).children('span').outerHeight();

                if (direction == 'down' && $('body').hasClass('showNav')) {
                    $(this).find('span.sticked').unwrap();
                    $(this).children('span').removeClass('sticked');
                }

                if (direction == 'up' && $('body').hasClass('showNav')) {
                    $(this).children('span').addClass('sticked').wrap('<div class="sticky-wrapper"/>').css('top',
                            $offset + 1).parent().outerHeight(categoryH);
                }
            }, {
                context : '#appMenu',
                offset : function() {
                    var downOffset = $(this).outerHeight() - ($(this).children('span').outerHeight() + 2);
                    return -(downOffset);
                }
            });

}
