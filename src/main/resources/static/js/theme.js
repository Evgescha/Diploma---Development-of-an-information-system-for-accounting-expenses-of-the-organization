"use strict";
var fixed_menu = true;

// Custom select
function enableSelectBoxes(){
	"use strict";
	jQuery('div.selectBox').each(function(){
		jQuery(this).children('span.selected').html(jQuery(this).children('div.selectOptions').children('span.selectOption:first').html());
		jQuery(this).attr('value',jQuery(this).children('div.selectOptions').children('span.selectOption:first').attr('value'));
		
		jQuery(this).children('span.selected, span.selectArrow').on("click", function(){
			if(jQuery(this).parent().children('div.selectOptions').css('display') == 'none'){
				jQuery(this).parent().children('div.selectOptions').css('display','block');
				jQuery(this).parents('.selectBox').addClass('act');
			}
			else
			{
				jQuery(this).parent().children('div.selectOptions').css('display','none');
				jQuery(this).parents('.selectBox').removeClass('act');
			}
		});
		
		jQuery(this).find('span.selectOption').on("click", function(){
			jQuery(this).parent().css('display','none');
			jQuery(this).closest('div.selectBox').attr('value',jQuery(this).attr('value'));
			jQuery(this).parent().siblings('span.selected').html(jQuery(this).html());
			jQuery(this).parents('.selectBox').removeClass('act');
		});
		
		jQuery("div.selectOptions").mouseleave(function() {
			jQuery('div.selectOptions').css('display','none');
			jQuery('.selectBox').removeClass('act');
		});
		
	});				
}

function fw_block() {
	"use strict";
	if (jQuery('div').hasClass('right-sidebar') || jQuery('div').hasClass('left-sidebar')) {
	} else {
		var fw_block = jQuery('.fw_block');
		var fw_block_parent = fw_block.parent().width();
		var fw_site_width = fw_block.parents('.wrapper').width();
		var fw_contentarea_site_width_diff = fw_site_width - fw_block_parent;
		
		fw_block.css('margin-left', '-'+fw_contentarea_site_width_diff/2+'px').css('width', fw_site_width+'px').children().css('padding-left', fw_contentarea_site_width_diff/2+'px').css('padding-right', fw_contentarea_site_width_diff/2+'px');
	jQuery('.wall_wrap .fw_wrapinner').css('padding-left', '0px').css('padding-right', '0px');
	}
}

function fltr_tooltip() {
	jQuery('.filter_navigation .optionset li').each(function() {
		var cat_name = jQuery(this).find('a').data("catname");
		if(cat_name == 'all') {
			var cat_count = jQuery('div.element').length;	
		} else {
			var cat_count = jQuery('div.'+cat_name).length;
		}
		jQuery(this).find('a').attr('data-title', cat_count);
	});
}

function megamenu() {
	"use strict";
	if (jQuery('.megamenu').size() > 0) {
		jQuery('.megamenu .sub-nav').css({'width': jQuery(window).width() + 'px'});
		jQuery('.megamenu').each(function(){
			var left_marg = jQuery(this).offset().left;
			jQuery(this).find('.sub-nav').css({'margin-left': '-' + left_marg + 'px'});			
		});
	}
}

function portfhover() {
	jQuery('.featured_portfolio li').each(function() {
		var featured_info_h = jQuery(this).find(".featured_items_body").height() + 30;
		jQuery(this).find(".item_wrapper").mouseenter(function() {
			jQuery(this).find(".view_link").css({'margin-top' : - 30 - featured_info_h/2 + 'px'});
		}).mouseleave(function() {
			jQuery(this).find(".view_link").css({'margin-top' : - 30 + 'px'});
		});
	});
}

jQuery(document).ready(function ($) {
	"use strict";
	
	var bodytimer = setTimeout(function(){
		jQuery('body').animate({'opacity' : '1'}, 500);
		clearTimeout(bodytimer);
	}, 500);
	
	// Fixed Menu
	if (jQuery('.fixed-menu').size() && fixed_menu == true) {		
		jQuery('.fixed-menu').append(jQuery('.header_parent_wrap').html());
				
		jQuery(window).on('scroll', function() {
			if (jQuery('.first-module').hasClass("module_slider") || jQuery('div').hasClass("events_countdown") || jQuery('div').hasClass("portfolio_gallery")) {
				var header_offset = jQuery(window).height() - 80;
			}		
			else if (jQuery('.main_header').hasClass('type2') || jQuery('.main_header').hasClass('type3')) {
				var header_offset = jQuery('.header_parent_wrap').offset().top + jQuery('.header_parent_wrap').height();		
			}
			else {
				var header_offset = jQuery('.header_parent_wrap').offset().top;
			}		
			// if (jQuery(window).scrollTop() > header_offset) {
			// 	jQuery('.fixed-menu').addClass('fixed_show');
			// } else {
			// 	jQuery('.fixed-menu').removeClass('fixed_show');
			// }
			megamenu();	
			if (jQuery('.featured_portfolio').size() > 0) {
				portfhover();
			}								
		});			
	}
	
	// MobileMenu
	jQuery('header').append('<a href="javascript:void(0)" class="menu_toggler"></a><a href="javascript:void(0)" class="tagline_toggler"></a><div class="mobile_menu_wrapper"><ul class="mobile_menu container"/></div>');	
	jQuery('.mobile_menu').html(jQuery('header').find('.menu').html());
	jQuery('.mobile_menu_wrapper').hide();
	jQuery('.menu_toggler').on("click", function(){
		jQuery('.mobile_menu_wrapper').slideToggle(300);
		jQuery(this).toggleClass("close_toggler");
	});
	jQuery('.mobile_menu a').each(function(){
		jQuery(this).addClass("mob_link");			
	});
	if (jQuery('.megamenu_wrap').size() > 0) {
		jQuery('.megamenu_wrap a').each(function(){
			jQuery(this).removeClass("mob_link");			
		});
	}
	jQuery('.mobile_menu li').find('a').on("click", function() {
		jQuery(this).parent().toggleClass("showsub");
	});
	
	jQuery('.tagline_toggler').on("click", function(){
		jQuery('.tagline').slideToggle(300);
		jQuery(this).toggleClass("close_tagline");
	});
    
	if (jQuery('.language_select').size() > 0 || jQuery('.shop_ordering').size() > 0) {
		// Custom select
		enableSelectBoxes();
	}
	
	// Top Search focus
	if (jQuery('.top_search').size() > 0) {
		var $ctsearch = $('#top_search'),
			$ctsearchinput = $ctsearch.find('input.ct-search-input'),
			$tagline_items = $('.tagline_items'),
			$body = $('html,body'),
			openSearch = function() {
				$ctsearch.data('open',true).addClass('ct-search-open');
				$tagline_items.hide();
				$ctsearchinput.focus();
				return false;
			},
			closeSearch = function() {
				$ctsearch.data('open',false).removeClass('ct-search-open');
				$tagline_items.css({'display': 'inline-block'});
			};
		$ctsearchinput.on('click',function(e) { e.stopPropagation(); $ctsearch.data('open',true); });
		$ctsearch.on('click',function(e) {
			e.stopPropagation();
			if( !$ctsearch.data('open') ) {
				openSearch();
				$body.off( 'click' ).on( 'click', function(e) {
					closeSearch();
				} );
			}
			else {
				if( $ctsearchinput.val() === '' ) {
					closeSearch();
					return false;
				}
			}
		});
	}
	
	/* NivoSlider */
	if (jQuery('.nivoSlider').size() > 0) {
		jQuery('.nivoSlider').each(function(){
			jQuery(this).nivoSlider({
				directionNav: false,
				controlNav: true,
				effect:'fade',
				pauseTime:4000,
				slices: 1
			});
		});
	}
	
	// Twitter Setup (To activate your Twitter you need uncomment the code)
	if (jQuery('.tweet_module').size() > 0) {
		/*jQuery('.tweet_module').tweet({
			modpath: 'twitter/',
			count: 2,
			username : 'yourusername'
		});*/
	}
	
	// Counter
	if (jQuery('.shortcode_counter').size() > 0) {
		if (jQuery(window).width() > 760) {						
			jQuery('.shortcode_counter').each(function(){							
				if (jQuery(this).offset().top < jQuery(window).height()) {
					if (!jQuery(this).hasClass('done')) {
						var set_count = jQuery(this).find('.stat_count').attr('data-count');
						jQuery(this).find('.stat_temp').stop().animate({width: set_count}, {duration: 3000, step: function(now) {
								var data = Math.floor(now);
								jQuery(this).parents('.counter_wrapper').find('.stat_count').html(data);							
							}
						});	
						jQuery(this).addClass('done');
						jQuery(this).find('.stat_count');
					}							
				} else {
					jQuery(this).waypoint(function(){
						if (!jQuery(this).hasClass('done')) {
							var set_count = jQuery(this).find('.stat_count').attr('data-count');
							jQuery(this).find('.stat_temp').stop().animate({width: set_count}, {duration: 3000, step: function(now) {
									var data = Math.floor(now);
									jQuery(this).parents('.counter_wrapper').find('.stat_count').html(data);								
								}
							});	
							jQuery(this).addClass('done');
							jQuery(this).find('.stat_count');
						}
					},{offset: 'bottom-in-view'});								
				}														
			});
		} else {
			jQuery('.shortcode_counter').each(function(){							
				var set_count = jQuery(this).find('.stat_count').attr('data-count');
				jQuery(this).find('.stat_temp').animate({width: set_count}, {duration: 3000, step: function(now) {
						var data = Math.floor(now);
						jQuery(this).parents('.counter_wrapper').find('.stat_count').html(data);					
					}
				});
				jQuery(this).find('.stat_count');
			},{offset: 'bottom-in-view'});	
		}
	}
		
	// Skills
	if (jQuery('.shortcode_skills').size() > 0) {
		if (jQuery(window).width() > 760) {
			jQuery('.skills_start').waypoint(function(){
				jQuery('.skill_div').each(function(){
					var set_width = jQuery(this).attr('data-percent');
					jQuery(this).stop().animate({'width' : set_width + '%'},1500);
				});	
			},{offset: 'bottom-in-view'});
		} else {
			jQuery('.skill_div').each(function(){
				var set_width = jQuery(this).attr('data-percent');
				jQuery(this).stop().animate({'width' : set_width + '%'},1000);
			});		
		};
	}
	
	// Diagram
	if (jQuery('.shortcode_diagram').size() > 0) {
		jQuery('.chart').each(function(){
			jQuery(this).css({'font-size' : jQuery(this).parents('.diagram_list').attr('data-fontsize'), 'line-height' : jQuery(this).parents('.diagram_list').attr('data-size')});
			jQuery(this).find('span').css('font-size' , jQuery(this).parents('.diagram_list').attr('data-fontsize'));
		});
	
		if (jQuery(window).width() > 760) {
			jQuery('.diagram_li').waypoint(function(){
				jQuery('.chart').each(function(){
					jQuery(this).easyPieChart({
						barColor: jQuery(this).parents('ul.diagram_list').attr('data-color'),
						trackColor: jQuery(this).parents('ul.diagram_list').attr('data-bg'),
						scaleColor: false,
						lineCap: 'square',
						lineWidth: parseInt(jQuery(this).parents('ul.diagram_list').attr('data-width'), 10),
						size: parseInt(jQuery(this).parents('ul.diagram_list').attr('data-size'), 10),
						animate: 1500
					});
				});
			},{offset: 'bottom-in-view'});
		} else {
			jQuery('.chart').each(function(){
				jQuery(this).easyPieChart({
					barColor: jQuery(this).parents('ul.diagram_list').attr('data-color'),
					trackColor: jQuery(this).parents('ul.diagram_list').attr('data-bg'),
					scaleColor: false,
					lineCap: 'square',
					lineWidth: parseInt(jQuery(this).parents('ul.diagram_list').attr('data-width'), 10),
					size: parseInt(jQuery(this).parents('ul.diagram_list').attr('data-size'), 10),
					animate: 1500
				});
			});
		}
	}
	
	// Accordion & Toggle
	if (jQuery('.module_accordion').size() > 0 || jQuery('.module_toggle').size() > 0) {
		jQuery('.shortcode_accordion_item_title').on("click", function(){
			if (!jQuery(this).hasClass('state-active')) {
				jQuery(this).parents('.shortcode_accordion_shortcode').find('.shortcode_accordion_item_body').slideUp('fast');
				jQuery(this).next().slideToggle('fast');
				jQuery(this).parents('.shortcode_accordion_shortcode').find('.state-active').removeClass('state-active');
				jQuery(this).addClass('state-active');
			}
		});
		jQuery('.shortcode_toggles_item_title').on("click", function(){
			jQuery(this).next().slideToggle('fast');
			jQuery(this).toggleClass('state-active');
		});
	
		jQuery('.shortcode_accordion_item_title.expanded_yes, .shortcode_toggles_item_title.expanded_yes').each(function( index ) {
			jQuery(this).next().slideDown('fast');
			jQuery(this).addClass('state-active');
		});	
	}
	
	// Table Info Toggle
	if (jQuery('.module_table_info').size() > 0) {
		jQuery('.table_info_details').on("click", function(){
			jQuery(this).parents('div.table_info_title').next().slideToggle('fast');
			jQuery(this).parents('div.table_info_title').toggleClass('current-section');
		});
		jQuery('.table_info_title.expanded_yes').each(function( index ) {
			jQuery(this).next().slideDown('fast');
			jQuery(this).addClass('current-section');
		});
	}
	
	// Messagebox
	if (jQuery('.shortcode_messagebox').size() > 0) {
		jQuery('.shortcode_messagebox').find('.box_close').on("click", function(){
			jQuery(this).parents('.module_messageboxes').fadeOut(400);
		});
	}
		
	// Tabs
	if (jQuery('.shortcode_tabs').size() > 0) {
		jQuery('.shortcode_tabs').each(function(index) {
			// GET ALL HEADERS
			var i = 1;
			jQuery(this).find('.shortcode_tab_item_title').each(function(index) {
				jQuery(this).addClass('it'+i); jQuery(this).attr('whatopen', 'body'+i);
				jQuery(this).addClass('head'+i);
				jQuery(this).parents('.shortcode_tabs').find('.all_heads_cont').append(this);
				i++;
			});
		
			// GET ALL BODY
			var i = 1;
			jQuery(this).find('.shortcode_tab_item_body').each(function(index) {
				jQuery(this).addClass('body'+i);
				jQuery(this).addClass('it'+i);
				jQuery(this).parents('.shortcode_tabs').find('.all_body_cont').append(this);
				i++;
			});
		
			// OPEN ON START
			jQuery(this).find('.expand_yes').addClass('active');
			var whatopenOnStart = jQuery(this).find('.expand_yes').attr('whatopen');
			jQuery(this).find('.'+whatopenOnStart).fadeIn();
			jQuery(this).find('.'+whatopenOnStart).addClass('active');
		});
		
		jQuery(document).on('click', '.shortcode_tab_item_title', function(){
			jQuery(this).parents('.shortcode_tabs').find('.shortcode_tab_item_body').removeClass('active');
			jQuery(this).parents('.shortcode_tabs').find('.shortcode_tab_item_title').removeClass('active');
			var whatopen = jQuery(this).attr('whatopen');
			jQuery(this).parents('div.shortcode_tabs').find('.shortcode_tab_item_body').hide();
			jQuery(this).parents('.shortcode_tabs').find('.'+whatopen).fadeIn();
			jQuery(this).parents('.shortcode_tabs').find('.'+whatopen).addClass('active');
			jQuery(this).addClass('active');
		});
		
		// For Tabs Type3
		if (jQuery('.shortcode_tabs').hasClass("type3")) {
			jQuery('.shortcode_tabs.type3').each(function() {
				var tab_item_count = jQuery(this).find(".shortcode_tab_item_title").length; 
				var tab_item_w = Math.floor(jQuery(this).width()/tab_item_count); 
				jQuery(this).find(".shortcode_tab_item_title").css({'width': tab_item_w + 'px'});
			});					
		}	
	}


	// jQuery('.fw_block').wrapInner('<div class="fw_wrapinner"></div>');
    
	fw_block();
	
	// Header Cart Info
	if (jQuery('.cart_wrap').size() > 0) {
		jQuery('.view_cart_btn').addClass('has_items');		
		jQuery('.remove_products').on("click", function(){
			jQuery('.view_cart_btn').removeClass('has_items');
			jQuery('.cart_wrap').hide();
			jQuery('.cart_submenu, .widget_cart').append('<p class="empty">No products in the cart.</p>');		
		});		
	}
	

	if (jQuery('.first-module').hasClass("module_slider")) {
		jQuery('.first-module.module_slider').parents('.fw_block').addClass('mt_90');
		jQuery('.first-module.module_slider').parents('.row').addClass('full_width_row');
	}
	
	// Recent Projects
	if (jQuery('.recent_projects').size() > 0) {
		$('.proj_list a').on("click", function(){
			var proj_preview = $(this).find('.large_img').attr("src");
			var proj_link = $(this).data('link');
			var proj_title = $(this).find('b').html();
			var proj_catlink = $(this).data('catlink');
			var proj_catname = $(this).find('span').html();
			
			$('.og-details').animate({'opacity' : '0'}, 600, function(){				
				$(this).empty();
				$(this).append('<img src="'+proj_preview+'" alt="" /><div class="proj_info"><div class="proj_title"><h5><a href="'+proj_link+'">'+proj_title+'</a></h5></div><div class="proj_meta"><a href="'+proj_catlink+'">'+proj_catname+'</a></div></div>');
				$(this).animate({'opacity' : '1'}, 600);
			});
			
			$('.proj_list a').removeClass('current');
			$(this).addClass('current');
		});
	}
	
	// Mega Menu	
	megamenu();
	
	if (jQuery('.fullscreen_slider').size() > 0) {
		jQuery('.fullscreen_slider').parent().append('<div class="mouse_icon"></div>');
	}
	
	// Magnific Popup
	if (jQuery('.photozoom').size() > 0) {
		if (jQuery('.photozoom').parents('.photo_gallery').hasClass('photo_gallery')) {
			jQuery('.photo_gallery').magnificPopup({
			  delegate: 'a',
			  type: 'image',
			  gallery: {
				enabled: true
			  }				    
			});	
		} else {
			jQuery('.photozoom').magnificPopup({type:'image'});
		}	
	}
	
	// Login Popup
	if (jQuery('.login_popup').size() > 0) {
		jQuery('.login_popup').on('show.bs.modal', function (e) {
			jQuery('html').addClass('no_scroll');
		});
		jQuery('.login_popup').on('hide.bs.modal', function (e) {
			jQuery('html').removeClass('no_scroll');
		});	
	}
					
});

jQuery(window).resize(function () {
	"use strict";
	
	fw_block();
	
	// For Tabs Type3
	if (jQuery('.shortcode_tabs').hasClass("type3")) {
		jQuery('.shortcode_tabs.type3').each(function() {
			var tab_item_count = jQuery(this).find(".shortcode_tab_item_title").length; 
			var tab_item_w = Math.floor(jQuery(this).width()/tab_item_count); 
			jQuery(this).find(".shortcode_tab_item_title").css({'width': tab_item_w + 'px'});
		});					
	}
	
	//Feature Portfolio
	if (jQuery('.featured_portfolio').size() > 0) {
		portfhover();
	}	
	
	megamenu();
				    
});

jQuery(window).load(function () {
	"use strict";
		
	// Parallax
	if (jQuery(window).width() > 1025 && jQuery('.paralax').size() > 0) {	
		var $window = jQuery(window);
		jQuery('.paralax').each(function(){
			var $bgobj = jQuery(this); // assigning the object
			jQuery(window).on('scroll', function() {                   
				var yPos = ($bgobj.offset().top-$window.scrollTop())/2;
				var coords = '50% '+ yPos + 'px';
				$bgobj.css({ 'background-position': coords });		
			});
		});
	}
	
	if (jQuery('.fullscreen_slider').size() > 0) {
		var loadedtimer = setTimeout(function(){
			jQuery('.fullscreen_slider').parent().addClass('loaded');
			clearTimeout(loadedtimer);
		}, 3000);
	}
	    
});