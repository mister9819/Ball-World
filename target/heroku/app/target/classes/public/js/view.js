'use strict';

//app to draw polymorphic shapes on canvas
var app;

/**
 * Create the ball world app for a canvas
 * @param canvas The canvas to draw balls on
 * @returns {{drawBall: drawBall, clear: clear}}
 */
function createApp(canvas) {
    let c = canvas.getContext("2d");
    let img = new Image();
    img.src = 'js/fish.png';

    /**
     * Draw a circle
     * @param x  The x location coordinate
     * @param y  The y location coordinate
     * @param radius  The circle radius
     * @param color The circl color
     */
    let drawCircle = function(x, y, radius, color) {
        c.fillStyle = color;
        c.beginPath();
        c.arc(x, y, radius, 0, 2 * Math.PI, false);
        c.closePath();
        c.fill();
    };

    let clear = function() {
        c.clearRect(0,0, canvas.width, canvas.height);
    };

    var drawImage = function(x, y, width, height, angle, scaleX, scaleY) {
        c.translate(x, y);
        c.scale(scaleX, scaleY);
        c.rotate(angle * Math.PI / 180);
        c.drawImage(img, -width / 2, -height / 2, width, height);
        c.rotate(-angle  * Math.PI / 180);
        c.scale(scaleX, scaleY);
        c.translate(-x, -y);
    }

    return {
        drawCircle,
        clear,
        drawImage,
        dims: {height: canvas.height, width: canvas.width}
    }
}


window.onload = function() {
    app = createApp(document.querySelector("canvas"));
    canvasDims();

    $("#btn-load-ball").click(loadBall);
    $("#btn-load-image").click(loadImage);

    $("#btn-load-sball").click(loadSBall);
    $("#btn-load-sfish").click(loadSFish);
    $("#btn-switch").click(switchStrategy)

    $("#btn-clear").click(clear);
    $("#btn-remove").click(remove);

    setInterval(updateBallWorld, 100);
    clear();
};

/**
 * load ball at a location on the canvas
 */
function loadBall() {
    let values = document.getElementById('movement').value.toLowerCase() + "," +
        document.getElementById('collision').value.toLowerCase()
    $.post("/load", {strategies: values, type: "ball"}, function (data) {
        updateBallWorld();
    }, "json");
}

/**
 * load image at a location on the canvas
 */
function loadImage() {
    let values = document.getElementById('movement').value.toLowerCase();
    $.post("/load", {strategies: values, type: "image"}, function (data) {
        updateBallWorld();
    }, "json");
}

/**
 * load switcher ball at a location on the canvas
 */
function loadSBall() {
    let values = document.getElementById('movement').value.toLowerCase()
    $.post("/load", {strategies: values, type: "sball"}, function (data) {

    }, "json");
}

/**
 * load switcher fish at a location on the canvas
 */
function loadSFish() {
    let values = document.getElementById('movement').value.toLowerCase()
    $.post("/load", {strategies: values, type: "sfish"}, function (data) {

    }, "json");
}


/**
 * Switch ball strategies
 */
function switchStrategy() {
    let values = document.getElementById('movementFrom').value.toLowerCase() + "," +
        document.getElementById('movementTo').value.toLowerCase();
    $.post("/switch", {strategies: values}, function (data) {
        updateBallWorld();
    }, "json");
}

function updateBallWorld() {
    $.get("/update", function(data) {
        //console.log(data)
        app.clear();
        for (let i in data) {
            let o = data[i];
            if (o.type === "image") {
                app.drawImage(o.loc.x, o.loc.y, o.width, o.height, o.angle, o.scale.x, o.scale.y);
            } else {
                app.drawCircle(o.loc.x, o.loc.y, o.width, o.color);
            }
        }
    }, "json");
}

/**
 * Pass along the canvas dimensions
 */
function canvasDims() {
    $.post("/canvas/dims", {height: app.dims.height, width: app.dims.width});
}

/**
 * Clear the canvas
 */
function clear() {
    $.get("/clear");
    app.clear();
}

/**
 * remove a few objects the canvas
 */
function remove() {
    let values = document.getElementById('remove').value.toLowerCase();
    $.get("/remove/" + values);
}