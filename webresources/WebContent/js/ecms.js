$(document).ready(function () {
    $(".btn-search").click(function () {
        $(".phonebook-results").toggleClass('hidden');
    });
    $(".btn-back").click(function () {
        history.go(-1);
    });

//
// Phonebook Search Page
// --------------------------------------------------
    if ($('#sidebar-wrapper').is(':hidden')) {
        $('#search-results').removeClass('col-md-10 col-md-offset-2').addClass('col-md-12 col-md-offset-0');
    }
    if ($('#sidebar-wrapper').is(':visible')) {
        $('#search-results').addClass('col-md-10 col-md-offset-2').removeClass('col-md-12 col-md-offset-0');
    }
    $('#menu-toggle').click(function (ev) {
        ev.preventDefault();
        $("#menu-toggle").toggleClass("open").find('span').toggleClass("fa-search fa-arrows-h");
        $('#sidebar-wrapper').animate({
            width: 'toggle'
        }, 500);
        $('#search-results').toggleClass('col-md-10 col-md-offset-2 col-md-12 col-md-offset-0');
    });

    $('#search-results-table').DataTable( {
        responsive: true
    });


//
// Utility Functions
// --------------------------------------------------
    $('#account-status').multiselect();
    $('*[data-toggle="collapse"]').click(function (e) {
        $(e.target).find(".fa").toggleClass('fa-chevron-down fa-chevron-right');
    });

});