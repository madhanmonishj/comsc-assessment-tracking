// load content in content div based on the option selected
$(document).ready(function () {
    loadContent("file"); 

    $(".sidebar a").click(function (e) {
        e.preventDefault();
        var section = $(this).data("section");
        loadContent(section);
    });
});

function loadContent(section) {
    const userID = decodeURIComponent(new URLSearchParams(window.location.search).get("userId")) || 0

    if(section === "tracking"){
        window.location.href = `http://localhost:8080/tracking?userId=${userID}`;
    }
    var contentUrl = "/admin/sideNav/" + section;
    $("#mainContent").load(contentUrl);
}   