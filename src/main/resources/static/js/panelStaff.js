$(document).ready(function() {
    $("#saveFeedback").click(function(e){
        var considered = $('#considered').prop('checked');
        var considerationDate = $('#considerationDate').val();
        var feedback = $('#feedback').val();
        var notifiedModeratorSetter = $('#notifiedModeratorSetter').prop('checked');
        var notificationDate = $('#notificationDate').val();
        var notifiedModuleLeader = $('#notifiedModuleLeader').prop('checked');
        var moduleLeaderNotificationDate = $('#moduleLeaderNotificationDate').val();
        var assessmentStatus = $("#assessmentStatus").val();
        var jsonData = {
            considered: considered,
            considerationDate: considerationDate,
            feedback: feedback,
            notifiedModeratorSetter: notifiedModeratorSetter,
            notificationDate: notificationDate,
            notifiedModuleLeader: notifiedModuleLeader,
            moduleLeaderNotificationDate: moduleLeaderNotificationDate,
            assessmentStatus: assessmentStatus,
            assessmentId: decodeURIComponent(new URLSearchParams(window.location.search).get("id")) || 0
        };
        $.ajax({
            type: 'POST',
            url: '/public/saveFeedback',
            contentType: 'application/json',
            data: JSON.stringify(jsonData),
            success: function () {
                alert("Save successful");
                window.history.back();
            },
            error: function (xhr, status, error) {
                console.error("Save failed. Status: " + status + ", Error: " + error);
                alert("Save failed");
            }
        });

    });
})
