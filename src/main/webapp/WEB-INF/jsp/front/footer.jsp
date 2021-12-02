<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<footer>
	<div class="fly-footer">
		&copy;2021 HBlog.
	</div>
</footer>
</div>
</body>
<script src="${ctxStatic}/js/mo.min.js"></script>
<script src="${ctxStatic}/js/mojs-player.min.js"></script>
<script>
    var _extends = Object.assign || function (target) { for (var i = 1; i < arguments.length; i++) {if (window.CP.shouldStopExecution(2)){break;} var source = arguments[i]; for (var key in source) {if (window.CP.shouldStopExecution(1)){break;} if (Object.prototype.hasOwnProperty.call(source, key)) { target[key] = source[key]; } }
        window.CP.exitedLoop(1);
    }
        window.CP.exitedLoop(2);
        return target;
    };

    var OPTS = {
        fill: 'none',
        radius: 25,
        strokeWidth: { 50: 0 },
        scale: { 0: 1 },
        angle: { 'rand(-35, -70)': 0 },
        duration: 500,
        left: 0,
        top: 0,
        easing: 'cubic.out'
    };
    var circle1 = new mojs.Shape(_extends({}, OPTS, {
        stroke: '#34d0ff'
    }));
    var circle2 = new mojs.Shape(_extends({}, OPTS, {
        radius: { 0: 15 },
        strokeWidth: { 30: 0 },
        stroke: '#0ef9e3',
        delay: 'rand(75, 150)'
    }));
    document.addEventListener('click', function (e) {
        circle1.tune({ x: e.pageX, y: e.pageY }).replay();
        circle2.tune({ x: e.pageX, y: e.pageY }).replay();
    });
</script>
</html>