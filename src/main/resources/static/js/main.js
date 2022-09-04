window.onload = function () {
    TagCanvas.textFont = 'Impact,"Arial Black",sans-serif';
    TagCanvas.textColour = '#f8f9fa';
    TagCanvas.textHeight = 25;
    TagCanvas.outlineColour = '#85abd3';
    TagCanvas.outlineThickness = 3;
    TagCanvas.outlineOffset = 5;
    TagCanvas.outlineMethod = 'outline';
    TagCanvas.maxSpeed = 0.06;
    TagCanvas.minBrightness = 0.2;
    TagCanvas.depth = 0.95;
    TagCanvas.pulsateTo = 0.2;
    TagCanvas.pulsateTime = 0.75;
    TagCanvas.decel = 0.9;
    TagCanvas.reverse = true;
    TagCanvas.shadow = '#336';
    TagCanvas.shadowBlur = 3;
    TagCanvas.shadowOffset = [1, 1];
    TagCanvas.wheelZoom = false;
    TagCanvas.fadeIn = 800;
    try {
        TagCanvas.Start('tagcanvas1', 'ptTags');
        TagCanvas.Start('tagcanvas2', 'ptTags', {shape: 'vcylinder'});
        TagCanvas.Start('tagcanvas3', 'ptTags', {shape: 'hcylinder', lock: 'x'});
        TagCanvas.Start('tagcanvas4', 'ptTags1', {shape: 'hring', lock: 'x'});
        TagCanvas.Start('tagcanvas5', 'ptTags', {
            shape: 'vring(0.5)',
            offsetY: -60,
            lock: 'y'
        });
        TagCanvas.Start('tagcanvas6', 'ptTags', {
            shape: 'DblHelix',
            lock: 'y'
        });
    } catch (e) {
    }
};

function DblHelix(n, rx, ry, rz) {
    var a = Math.PI / n, i, j, p = [], z = rz * 2 / n;
    for (i = 0; i < n; ++i) {
        j = a * i;
        if (i % 2)
            j += Math.PI;
        p.push([rx * Math.cos(j), rz - z * i, ry * Math.sin(j)]);
    }
    return p;
}


$("#search").on('keypress', function (e) {
    if (e.which == 13) {
        window.location.replace("/search?text=" + $("#search").val().replace("#", ""));
        e.preventDefault();
    }
});

function redirect(url) {
    window.location.replace(url);
}

$('#lang').on('change', function (e) {

    let valueSelected = this.value;

    let actionUrl = window.document.location.origin

    window.location.replace(actionUrl + "?lang=" + valueSelected)
});

$('#change-style').on('change', changeStyle);


function changeStyle(e) {

    let valueSelected = this.value;

    let actionUrl = window.document.location.origin

    $.ajax({
        type: "GET",
        data:{"style" : valueSelected},
        url: actionUrl,
    });

    $('body').toggleClass('gradient-dark')

    $('button').toggleClass('btn-info')

    $('div.bg-light').toggleClass('bg-dark')

    $('div.bg-light').toggleClass('text-white')

}

if ($('#change-style').val() == "dark") {
    changeStyle()
}