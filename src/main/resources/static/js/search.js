$("#search").on('keypress',function(e) {
    if(e.which == 13) {
        window.location.replace("/search?text=" + $("#search").val().replace("#", ""));
        e.preventDefault();
    }
});