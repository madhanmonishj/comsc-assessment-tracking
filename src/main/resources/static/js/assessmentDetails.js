// Get the trackerId from the URL query parameter
const trackerId = decodeURIComponent(new URLSearchParams(window.location.search).get("id")) || 0;

// Function to fetch users and bind dropdown
const bindModerators = (data) => {
    $.ajax({
        url: '/Users/lists',
        type: 'GET',
        dataType: 'json',
        success: function (users) {

            const {
                internalModeratorId,
                externalModeratorId
            } = data
            // Filter users based on assigned roles
            const internalModerators = users.filter(user => user.assignedRoles === "intModerator");
            const externalModerators = users.filter(user => user.assignedRoles === "extModerator");

            // Populate internal moderator dropdown
            internalModerators.forEach(user => {
                $('#internalModerator').append(`<option value="${user.id}">${user.firstName} ${user.lastName}</option>`);
            });

            // Populate external examiner dropdown
            externalModerators.forEach(user => {
                $('#externalExaminer').append(`<option value="${user.id}">${user.firstName} ${user.lastName}</option>`);
            });

            $('#internalModerator').val(internalModeratorId);
            $('#externalExaminer').val(externalModeratorId);
        }
    });
}

// Function to send an email
window.sendEmail = () => {
    const emailData = {
        mailto: 'team13project987@gmail.com',
        subject: 'Pending Assessment',
        body: 'You have a pending assessment to validate. Please do it or you will have thousand years of bad luck',
    };

    $.ajax({
        type: 'POST',
        url: '/admin/sendEmail',
        contentType: 'application/json',
        data: JSON.stringify(emailData),
        success: function () {
            alert("Email sent successfully");
        },
        error: function (xhr, status, error) {
            console.error("Email sending failed. Status: " + status + ", Error: " + error);
            alert("Email sending failed");
        }
    });
};

// Function to submit form data
window.submitForm = () => {
    const formData = {
        internalModeratorId: $('#internalModerator').val(),
        externalModeratorId: $('#externalExaminer').val(),
        trackerId
    };

    // Submit form data to update tracker
    $.ajax({
        url: '/Users/updateTracker',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(formData),
        success: function (response) {
            alert("Assessment Updated successfully");
            backForm();
        },
        error: function (xhr, textStatus, errorThrown) {
            console.error('Error submitting form data:', textStatus, errorThrown);
        }
    });
};

window.backForm = () => {
    window.history.back();
}

$(document).ready(function () {
    // Fetch assessment data based on trackerId
    $.ajax({
        url: '/Users/getassessment',
        type: 'GET',
        dataType: 'json',
        data: { trackerId },
        success: function (data) {
            const {
                moduleCode,
                moduleName,
                assessmentName,
                assessmentStatus
            } = data[0];

            // Populate Module Details
            $('#moduleCode').text(moduleCode);
            $('#moduleTitle').text(moduleName);

            // Populate Assessment Details
            $('#assessmentName').text(assessmentName);
            $('#assessmentStatus').text(assessmentStatus);

            // Bind internal and external moderators to dropdowns
            bindModerators(data[0]);
        },
        error: function (xhr, textStatus, errorThrown) {
            console.error('Error fetching assessment data:', textStatus, errorThrown);
        }
    });
});
