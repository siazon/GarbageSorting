
(function($) {
	var max = Math.max,
		min = Math.min;
	$.pt = $.pureToolTips = function(options) {
		var opts = $.extend({
			target 		: null,		
			position 	: 't',		
			align		: 'c',		
			arrow		: true,		
			content 	: '',		
			width 		: 200,		
			height 		: 'auto',	
			autoClose 	: true,		
			time 		: 2000,		
			leaveClose 	: false,	
			close 		: null		
		}, options || {}),

		$ao, $ai, w, h,
		$pt = $('.pt'),
		$target = $(opts.target),
		top = $target.offset().top,
		left = $target.offset().left,
		width = $target.outerWidth(),
		height = $target.outerHeight(),

		position = opts.position,
		align = opts.align,
		arrow = opts.arrow,

		constant = {b:'pt-up', t:'pt-down', r:'pt-left', l:'pt-right'}, 
		arrowClass = constant[position] || constant.t;

		
		function init() {
			if(!opts.target) {
				return;
			}
			if(!$pt.length) {
				$pt = $('<div class="pt pt-down"><div class="cont"></div><b class="out"></b><b class="in"></b></div>').appendTo(document.body);
			}
			$pt.removeClass().addClass('pt ' + (arrow ? arrowClass : '')).find('.cont').html(opts.content).css({width:opts.width, height:opts.height});
			$ao = $pt.find('.out').toggle(arrow);
			$ai =  $pt.find('.in').toggle(arrow);
			w = $pt.outerWidth();
			h = $pt.outerHeight();
			arrow && autoAdjust();			
			$pt.css(setPos()).show();		
			opts.leaveClose && leaveClose();
			opts.autoClose && !opts.leaveClose && autoClose(opts.timeout);	
			return $pt;
		}
		
		function setPos() {
			var btw = arrow ? parseInt($ao.css('border-top-width'), 10) : 3,
				brw = arrow ? parseInt($ao.css('border-right-width'), 10) : 3,
				result = {};
			switch(align) {
				case 'c': break;
				case 't': result.top = top; break;
				case 'b': result.top = top + height - h; break;
				case 'l': result.left = left; break;
				case 'r': result.left = left + width - w; break;
			}
			switch(position) {
				case 't': result.top = top - h - brw; break;
				case 'b': result.top = top + height + brw; break;
				case 'l': result.left = left - w - btw; break;
				case 'r': result.left = left + width + btw; break;
			}
			result.top || (result.top = top + height/2 - h/2);
			result.left || (result.left = left + width/2 - w/2);
			return result;
		}

		
		function autoAdjust() {
			var aop, aip, bw, auto='auto';
			switch(position) {
				case't':
					bw = parseInt($ao.css('border-top-width'), 10);
					aop = {bottom:-bw, left:w/2-bw, top:auto, right:auto};
					alignLR();
					aip = {top:auto, left:aop.left+1, bottom:aop.bottom+1, right:auto};
					break;
				case'b':
					bw = parseInt($ao.css('border-bottom-width'), 10);
					aop = {top:-bw, left:w/2 - bw, right:auto, bottom:auto};
					alignLR();
					aip = {top:aop.top+1, left:aop.left+1, bottom:auto, right:auto};
					break;
				case'l':
					bw = parseInt($ao.css('border-left-width'), 10);
					aop = {top:h/2 - bw, right:-bw, left:auto, bottom:auto};
					alignTB();
					aip = {top:aop.top+1, right:aop.right+1, left:auto, bottom:auto};
					break;
				case'r':
					bw = parseInt($ao.css('border-right-width'), 10);
					aop = {top:h/2 - bw, left:-bw, right:auto, bottom:auto};
					alignTB();
					aip = {top:aop.top+1, left:aop.left+1, right:auto, bottom:auto};
					break;
			}
			
			function alignLR() {
				if(align === 'l' && width/2 > bw && width/2 < w-bw) {
					aop.left = width/2-bw/2;
				} else if(align === 'r' && width/2 > bw && width/2 < w-bw) {
					aop.left = w-width/2-bw/2;
				}
			}
			
			function alignTB() {
				if(align === 't' && height/2 > bw && height/2 < h-bw) {
					aop.top = height/2 - bw;
				} else if(align === 'b' && height/2 > bw && height/2 < h-bw) {
					aop.top = h - height/2 - bw;
				}
			}
			$ao.css(aop);
			$ai.css(aip);
		}
		
		function autoClose() {
			window.ptt && clearTimeout(ptt);
			window.pta && clearTimeout(pta);
			window.pta = setTimeout(function() {
				$pt.hide();
				$.isFunction(opts.close) && opts.close();
			}, opts.time);
		}
		
		function leaveClose() {
			
			$pt.unbind('mouseleave').mouseleave(function(e) {
				$pt.hide();
				$.isFunction(opts.close) && opts.close();
			}).unbind('mouseenter').mouseenter(function() {
				window.ptt && clearTimeout(ptt);
			});
		}
		return init();
	};

	
	$.fn.pt = $.fn.pureToolTips = function(options) {
		var opts = $.extend({
			leaveClose:true
		}, options || {});
		return this.each(function() {
			$(this).mouseenter(function() {
				window.ptt && clearTimeout(ptt);
				window.pta && clearTimeout(pta);
				opts.target = this;
				$.pt(opts);
			}).mouseleave(function() {
				window.ptt = setTimeout(function() {
					$('.pt').hide();
					$.isFunction(opts.close) && opts.close();
				}, 500);
			});
		});
	};
})(jQuery);